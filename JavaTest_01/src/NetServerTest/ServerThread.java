package NetServerTest;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket sk;
	public ServerThread(Socket sk){
	    this.sk= sk;
	}
	
	public void run() {
	     BufferedReader br=null;
	     try{
	         br  = new BufferedReader(new InputStreamReader(sk.getInputStream()));
	         String line = br.readLine();
	         System.out.println("客户端："+line);
	         String[] split = line.split(":");
	         String[] split1 = split[split.length - 1].split(",");
	         sk.shutdownInput();
	
	         OutputStream os = sk.getOutputStream();
	
	         PrintStream bw = new PrintStream(os);
	
	          //给客户端返回信息
	          for(int i=0;i<split1.length;i++) {
	        	  int index = Integer.parseInt(split1[i])%Server.sentence.size();
	              bw.print(Server.sentence.get(index) + "\n");	              
	          }
	          bw.flush();
	          br.close();
	          sk.close();
	          
	     }catch(IOException e){
	           e.printStackTrace();
	     }
	}
}
