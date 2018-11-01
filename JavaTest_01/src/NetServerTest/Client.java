package NetServerTest;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private static String fileName = "src/NetServerTest/result.txt";
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 9999);
			System.out.println("-----------客户端启动-----------");
			
			PrintStream pStream = new PrintStream(socket.getOutputStream());
			System.out.print("发送：");
			Scanner sn = new Scanner(System.in);
			String str = sn.nextLine();
			pStream.println(str);
			
			InputStream iStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(iStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			FileWriter fileWriter = new FileWriter(fileName,true);
			String result = new ReadFile().readFile(fileName);
			
			String string;
			while ((string = bufferedReader.readLine()) != null) {
				System.out.println("服务器推送：" + string);
				if (!result.contains(string)) {
					fileWriter.write(string + "\n");
				}
			}
			
			socket.shutdownInput();
			pStream.close();
			socket.close();
			fileWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
