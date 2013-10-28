package common.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

	public static boolean writeFile(String fileName, String filePath, String fileContent) throws Exception {
		return writeFile(fileName, filePath, fileContent, false);
	}

	public static boolean writeFile(String fileName, String filePath, String fileContent, boolean append) throws Exception {
		File wFile = new File(fileName);
		if (filePath != null) {
			wFile = new File(filePath + File.separator + fileName);
		}

		if (!wFile.exists()) {
			wFile.createNewFile();
		}

		FileWriter fw = new FileWriter(wFile, append);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(fileContent);
		bw.close();

		return true;
	}

	public static String readFile(String fileName, String filePath, boolean classpath) throws Exception {
		String fileContent = "";

		String rFile = fileName;
		if (filePath != null) {
			rFile = filePath + File.separator + fileName;
		}

		BufferedReader br = null;
		if (classpath) {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(rFile);
			br = new BufferedReader(new InputStreamReader(is));
		} else {
			FileReader fr = new FileReader(rFile);
			br = new BufferedReader(fr);
		}

		String currentLine = br.readLine();
		while (null != currentLine) {
			fileContent += currentLine;
			currentLine = br.readLine();
		}

		br.close();
		return fileContent;
	}

	public static void main(String args[]) throws Exception {
		//System.out.println(writeFile("external.properties", null, "hello", true));
		System.out.println(readFile("packconfig.properties", "common/util/config", true));
	}
}
