package common.util.network;
/*
 * package test.net;
 * 
 * import java.io.IOException; import java.io.ObjectInputStream; import
 * java.io.ObjectOutputStream; import java.net.ServerSocket; import
 * java.net.Socket; import java.net.SocketTimeoutException; import
 * java.util.concurrent.Executors; import java.util.concurrent.TimeUnit;
 * 
 * import com.rediff.common.CommonUtility; import com.rediff.tcp.Mailer; import
 * com.rediff.tcp.Mailer.MAIL_TYPE; import com.rediff.tcp.Response; import
 * com.rediff.tcp.Response.MESSAGE_CODES; import com.rediff.tcp.Transfaereable;
 * 
 * public class TestTcpUtility {
 * 
 * public static void main(String[] args) throws Exception {
 * 
 * Executors.newSingleThreadExecutor().execute(new Runnable() {
 * 
 * @Override public void run() { recievePacket();
 * 
 * } });
 * 
 * Executors.newSingleThreadExecutor().execute(new Runnable() {
 * 
 * @Override public void run() {
 * 
 * Mailer mailer = new Mailer(MAIL_TYPE._TEST_NEWS_LETTER);
 * mailer.setMail_body("test t-mail"); sendPacket("202.137.233.143", 80,
 * mailer); } });
 * 
 * Executors.newSingleThreadExecutor().execute(new Runnable() {
 * 
 * @Override public void run() {
 * 
 * Mailer mailer = new Mailer(MAIL_TYPE._LIVE_NEWS_LETTER);
 * mailer.setMail_body("test n-mail"); sendPacket("localhost", 1198, mailer); }
 * });
 * 
 * }
 * 
 * public static void recievePacket() {
 * 
 * try {
 * 
 * final ServerSocket serverSocket = new ServerSocket(1198);
 * 
 * Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new
 * Runnable() {
 * 
 * @Override public void run() {
 * 
 * Socket server = null; ObjectInputStream clientInputStream = null;
 * ObjectOutputStream clientOutoutStream = null; try {
 * System.out.println("Waiting for client on port " +
 * serverSocket.getLocalPort() + "..."); server = serverSocket.accept();
 * System.out.println("Just connected to " + server.getRemoteSocketAddress());
 * 
 * clientInputStream = new ObjectInputStream(server.getInputStream());
 * Transfaereable incomingObject = (Transfaereable)
 * clientInputStream.readObject();
 * 
 * if (incomingObject instanceof Mailer) { Mailer mailer = (Mailer)
 * incomingObject; System.out.println(mailer.getMail_type());
 * System.out.println(mailer.getMail_body()); }
 * 
 * Response response = new Response(MESSAGE_CODES._SUCESS);
 * 
 * clientOutoutStream = new ObjectOutputStream(server.getOutputStream());
 * clientOutoutStream.writeObject(response); clientOutoutStream.flush();
 * 
 * } catch (SocketTimeoutException s) { System.out.println("Socket timed out!");
 * } catch (IOException e) { e.printStackTrace(); } catch
 * (ClassNotFoundException e) { e.printStackTrace(); } finally {
 * CommonUtility.close(clientOutoutStream);
 * CommonUtility.close(clientInputStream); if (null != server) { try {
 * server.close(); } catch (IOException e) { e.printStackTrace(); } } } } }, 0,
 * 10, TimeUnit.SECONDS);
 * 
 * } catch (Throwable e) { e.printStackTrace(); } }
 * 
 * public static String sendPacket(String serverName, int port, Transfaereable
 * transferObject) { String sResponse = null; Socket client = null;
 * ObjectOutputStream serverOutputSteam = null; ObjectInputStream
 * serverInputStream = null; try { System.out.println("Connecting to " +
 * serverName + " on port " + port); client = new Socket(serverName, port);
 * System.out.println("Just connected to " + client.getRemoteSocketAddress());
 * 
 * serverOutputSteam = new ObjectOutputStream(client.getOutputStream());
 * serverOutputSteam.writeObject(transferObject); serverOutputSteam.flush();
 * 
 * serverInputStream = new ObjectInputStream(client.getInputStream());
 * Transfaereable readObject = (Transfaereable) serverInputStream.readObject();
 * 
 * if (readObject instanceof Response) { Response response = (Response)
 * readObject; System.out.println(response.getCode());
 * System.out.println(response.getMessage()); } } catch (IOException e) {
 * e.printStackTrace(); } catch (ClassNotFoundException e) {
 * e.printStackTrace(); } finally { CommonUtility.close(serverOutputSteam);
 * CommonUtility.close(serverInputStream); if (null != client) { try {
 * client.close(); } catch (IOException e) { e.printStackTrace(); } } } return
 * sResponse; }
 * 
 * }
 */