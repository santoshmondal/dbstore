package common.util.ftp;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import common.util.CommonUtil;
import common.util.Constants;
import common.util.config.Config;

public class FtpUtil {

	private static final Logger LOG = Logger.getLogger(FtpUtil.class);
	private FTPClient ftpClient;

	public static FtpUtil getInstance() throws Exception {
		String ftpServerIp = Config.getProperty("FTP_SERVER_IP");
		int ftpPort = Integer.parseInt(Config.getProperty("FTP_SERVER_PORT"));
		String ftpUserName = Config.getProperty("FTP_SERVER_USER");
		String ftpPassword = Config.getProperty("FTP_SERVER_PWD");

		return new FtpUtil(ftpServerIp, ftpPort, ftpUserName, ftpPassword);
	}

	private FtpUtil(String serverIp, int serverPort, String userName, String password) throws Exception {
		this(serverIp, serverPort, userName, password, false);
	}

	private FtpUtil(String serverIp, int serverPort, String userName, String password, boolean isFtps) throws Exception {

		ftpClient = new FTPClient();

		if (isFtps) {
			ftpClient.setSecurity(FTPClient.SECURITY_FTPS);
		}

		LOG.info("Connecting to FTP server...");

		if (serverPort == 0) {
			ftpClient.connect(serverIp);
		} else {
			ftpClient.connect(serverIp, serverPort);
		}

		if (ftpClient.isCompressionSupported()) {
			LOG.info("Compression supported on '" + serverIp + "', enabling it for client.");
			ftpClient.setCompressionEnabled(true);
		}

		LOG.info("Logging in to FTP server...");
		ftpClient.login(userName, password);
	}

	public synchronized void uploadFile(InputStream sourceInputStream, String destination) throws Exception {

		long starttime = System.nanoTime();
		LOG.info("Uploading file at destination: " + destination);

		createDirectoriesIfRequired(destination);

		String[] destinationSplit = getSplitPath(destination);
		ftpClient.upload(destinationSplit[destinationSplit.length - 1], sourceInputStream, 0, 0, null);

		long endTime = System.nanoTime();
		LOG.info("File uploaded at destination: " + destination + ". Time Taken = " + CommonUtil.getTimeDifference(starttime, endTime));
	}

	public synchronized void uploadFile(File sourceFile, String destination) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(sourceFile);
		uploadFile(fileInputStream, destination);
	}

	public synchronized void uploadFile(String source, String destination) throws Exception {
		uploadFile(new File(source), destination);
	}

	private String[] getSplitPath(String destination) {

		destination = destination.replace("\\", Constants.FILE_SEPERATOR);

		String[] destinationSplit = destination.split(Constants.FILE_SEPERATOR);

		return destinationSplit;
	}

	public boolean isFileExists(String pathToFileOrDir) throws Exception {

		FTPFile[] ftpFiles = getFileList(pathToFileOrDir);

		if (ftpFiles != null && ftpFiles.length > 0) {
			return true;
		}

		return false;
	}

	public FTPFile[] getFileList() throws Exception {
		return getFileList(null);
	}

	public FTPFile[] getFileList(String path) throws Exception {

		if (CommonUtil.isEmpty(path)) {
			return ftpClient.list();
		} else {
			return ftpClient.list(path);
		}

	}

	public FTPFile[] getFileList(String path, Date minimumDate) throws Exception {

		List<FTPFile> ftpFiles = new ArrayList<FTPFile>();
		for (FTPFile ftpFile : getFileList(path)) {
			if (ftpFile.getModifiedDate().after(minimumDate)) {
				ftpFiles.add(ftpFile);
			}
		}

		return ftpFiles.toArray(new FTPFile[] {});
	}

	private void changeToRootDirectory() throws Exception {

		String currentDir = ftpClient.currentDirectory();
		String[] destinationSplit = currentDir.split(Constants.FILE_SEPERATOR);

		// a workaround to get to root directory
		for (@SuppressWarnings("unused")
		String element : destinationSplit) {
			ftpClient.changeDirectoryUp();
		}
	}

	private void changeToDirectory(String directory) throws Exception {

		// the api does not provide a direct method for this task.
		try {
			// try to change to directory if it exists
			ftpClient.changeDirectory(directory);
		} catch (Exception e) {
			// exception would mean that directory does not exist.
			// create it.
			ftpClient.createDirectory(directory);
			ftpClient.changeDirectory(directory);
		}
	}

	public void closeConnection() {

		try {
			ftpClient.disconnect(true);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			e.printStackTrace();
		} catch (FTPException e) {
			e.printStackTrace();
		}
		ftpClient = null;
	}

	private void createDirectoriesIfRequired(String destination) throws Exception {

		changeToRootDirectory();

		String[] destinationSplit = getSplitPath(destination);
		for (int i = 0; i < destinationSplit.length - 1; i++) {

			String directory = destinationSplit[i];

			if ("".equals(directory)) {
				continue;
			}

			changeToDirectory(directory);
		}
	}

	public static void main(String args[]) throws Exception {
		FtpUtil instance = FtpUtil.getInstance();
		if (instance != null) {
			LOG.info("FTP Connection Successful");
			ByteArrayInputStream byteInputStream = new ByteArrayInputStream("String content to byte content".getBytes());
			String destination = "/upload/abc.txt";
			instance.uploadFile(byteInputStream, destination);
		} else {
			LOG.info("FTP Connection failure.");
		}
	}
}
