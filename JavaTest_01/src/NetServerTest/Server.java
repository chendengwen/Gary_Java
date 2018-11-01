package NetServerTest;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.List;
import java.util.ArrayList;
import java.io.*;


public class Server {
	
	public static List<String> sentence;
	private static String fileName = "src/NetServerTest/English900.txt";
	
	public static void main(String[] args) throws IOException {
		InetAddress inetAddress = InetAddress.getLocalHost();
		String hostName = inetAddress.getHostName();
		String hostAddress = inetAddress.getHostAddress();
		System.out.println("服务器name = " + hostName + "address = " + hostAddress + "-----------");
		
		sentence = new ArrayList<>();
		System.out.println("-----------服务器启动-----------");
		
		FileReader fileReader = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fileReader);
		
		String inputLine = null;
		while ((inputLine = br.readLine()) != null) {
			sentence.add(inputLine);
		}
		
		ServerSocket ss = new ServerSocket(9999);
		while (true) {
			Socket socket = ss.accept();
			ServerThread st = new ServerThread(socket);
			st.start();
		}
	}

}
