package NetServerTest;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;

public class ReadFile {
	
	/** 
     * 以行为单位读取文件
     */  
    public String readFile(String fileName) {  
        File file = new File(fileName);  
        BufferedReader reader = null;  
        StringBuffer fileString = new StringBuffer("");
        try {  
//            System.out.println("以行为单位读取文件内容，一次读一整行：");  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
//                System.out.println("line " + line + ": " + tempString);  
                line++;  
                fileString.append(tempString);
            }  
            reader.close();  
            
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
        return fileName;
    }  
}
