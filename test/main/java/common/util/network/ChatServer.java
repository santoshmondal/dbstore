package common.util.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {
	private static Logger logger = Logger.getLogger("ChatServer");
	private static Observable observable = new Observable() {
		@Override
		public void notifyObservers(Object arg) {
			super.setChanged();
			super.notifyObservers(arg);
		}
	};

	/** Handler for an individual client */
	static class ClientHandler implements Runnable, Observer {
		private final Socket socket;
		private OutputStream outputStream;

		ClientHandler(Socket socket) {
			this.socket = socket;
		}

		/** Perform echoing to the client socket */
		@Override
		public void run() {
			logger.info("Accepted client " + socket.getInetAddress() + ":" + socket.getPort());
			observable.addObserver(this);
			try {
				outputStream = socket.getOutputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					observable.notifyObservers(line);
				}
				socket.close();
			} catch (IOException ex) {
				logger.log(Level.SEVERE, null, ex);
			}
			logger.info("Disconnected client " + socket.getInetAddress() + ":" + socket.getPort());
			observable.deleteObserver(this);
		}

		private static final String CRLF = "\r\n"; // newline

		@Override
		public void update(Observable o, Object arg) {
			try {
				PrintStream out = new PrintStream(outputStream);
				out.println(arg + CRLF);
				out.flush();
			} finally {
			}
		}
	}

	public static void main(String[] args) {

		int port = args.length > 0 ? Integer.parseInt(args[0]) : 1055;
		try {
			ServerSocket server = new ServerSocket(port);
			logger.info("Server started, listening at port " + port + " ...");
			ExecutorService executor = Executors.newCachedThreadPool();
			while (true) {
				Socket socket = server.accept();
				ClientHandler handler = new ClientHandler(socket);
				executor.execute(handler);
			}
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}
}
