package common.util.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class TCPUtil {
	private static final Logger LOG = Logger.getLogger(TCPUtil.class);
	private static final String SERVER = "localhost";
	private static final int PORT = 1198;

	public static void main(String[] args) throws Exception {

		/*Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				recievePacket();
			}
		});*/

		Executors.newCachedThreadPool().execute(new Runnable() {

			@Override
			public void run() {
				SampleObject sendObject = new SampleObject();
				sendObject.setUid(11);
				sendObject.setUname("tcpipname");
				sendPacket(SERVER, PORT, sendObject);
			}
		});

	}

	/**
	 * TCP Server. Listening at particulart port. 
	 * Fetching the incoming client request and generating 
	 * the response back to the client. 
	 */
	public static void recievePacket() {

		try {
			final ServerSocket serverSocket = new ServerSocket(PORT);

			Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {

				@Override
				public void run() {

					Socket clientRequest = null;
					ObjectInputStream clientInputStream = null;
					ObjectOutputStream clientOutoutStream = null;
					try {
						LOG.info("AT SERVER...");
						LOG.info("Waiting for client on port " + serverSocket.getLocalPort() + "...");
						clientRequest = serverSocket.accept();
						LOG.info("Just connected to " + clientRequest.getRemoteSocketAddress());

						clientInputStream = new ObjectInputStream(clientRequest.getInputStream());
						SampleObject incomingObject = (SampleObject) clientInputStream.readObject();

						if (incomingObject instanceof SampleObject) {
							SampleObject reqObject = incomingObject;
							System.out.println(reqObject.getUid());
							System.out.println(reqObject.getUname());
						}

						SampleObject response = new SampleObject();
						response.setCode(200);
						response.setMessage("SUCCESS");

						clientOutoutStream = new ObjectOutputStream(clientRequest.getOutputStream());
						clientOutoutStream.writeObject(response);
						clientOutoutStream.flush();

					} catch (SocketTimeoutException s) {
						LOG.error("Socket timed out!");
					} catch (IOException e) {
						LOG.error(e);
					} catch (ClassNotFoundException e) {
						LOG.error(e);
					} finally {
						try {
							clientInputStream.close();
							clientOutoutStream.close();
							clientRequest.close();
						} catch (IOException e) {
							LOG.error(e);
						}
					}
				}
			}, 0, 10, TimeUnit.SECONDS);

		} catch (Throwable e) {
			LOG.error(e);
		}
	}

	/**
	 * Client sending information and fetching the server
	 * reponse.
	 * 
	 * @param serverName
	 * @param port
	 * @param sendObject
	 * @return
	 */
	public static SampleObject sendPacket(String serverName, int port, SampleObject sendObject) {
		SampleObject readObject = null;
		Socket client = null;
		ObjectOutputStream serverOutputSteam = null;
		ObjectInputStream serverInputStream = null;
		try {
			LOG.info("Connecting to " + serverName + " on port " + port);
			client = new Socket(serverName, port);
			LOG.info("Just connected to " + client.getRemoteSocketAddress());

			serverOutputSteam = new ObjectOutputStream(client.getOutputStream());
			serverOutputSteam.writeObject(sendObject);
			serverOutputSteam.flush();

			serverInputStream = new ObjectInputStream(client.getInputStream());
			readObject = (SampleObject) serverInputStream.readObject();

			if (readObject instanceof SampleObject) {
				LOG.info(readObject.getCode());
				LOG.info(readObject.getMessage());
			}
		} catch (IOException e) {
			LOG.error(e);
		} catch (ClassNotFoundException e) {
			LOG.error(e);
		} finally {
			try {
				serverInputStream.close();
				serverInputStream.close();
				client.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		return readObject;
	}
}
