package test;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.io.*;

class test {
	
	public void CreateJFrame() {
         JFrame jf = new JFrame("这是一个JFrame窗体");        // 实例化一个JFrame对象
         jf.setVisible(true);        // 设置窗体可视
         jf.setSize(500, 350);        // 设置窗体大小
	     jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        // 设置窗体关闭方式
	}
	
	public static void main(String[] args) {
//		System.out.println("Helllo world!");

//		new Hello().CreateJFrame();        // 调用CreateJFrame()方法
		
		//跨包读文件
//		File file =new File("src/JDBCTest/bigFile.png");
//		System.out.println(readFile(file));
	}
	
	public static String readFile(File file) {
		StringBuilder result =new StringBuilder();
		try {
			BufferedReader br =new BufferedReader(new FileReader(file));
			String s =null;
			while((s =br.readLine()) != null) { //一次读一行内容
				result.append(System.lineSeparator() +s);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return result.toString();
	}
	
	public void javaReflect() {
		
	}
}