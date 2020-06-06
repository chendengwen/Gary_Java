package JDBCTest;

import java.io.*;
import java.sql.Statement;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.Properties;;

public class MySQLDemo_DBUtils {
	
	public static void main(String[] args) {
//		test_insert();
//		test_insert_lob();
		test_read_lob();
	}
	
	//测试一般数据写入
	private static void test_insert() {
		
		DBUtils dbUtils = new DBUtils();
		dbUtils.openConnection();
		String sql = "insert into websites(name,id,url,country) values(?,?,?,?)";
		
		Object[] params = new Object[4];
		params[0] = new String("test07");
		params[1] = new Integer(8);
		params[2] = new String("www.qiyi.com");
		params[3] = new String("英国");
		
		try {
			ResultSet resultSet = dbUtils.getInsertObjIDs(sql, params);
			
			if (resultSet != null && resultSet.first()) {
				System.out.println(resultSet.getInt(1));
			}
			dbUtils.close(resultSet);
			dbUtils.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//测试lob数据写入
	private static void test_insert_lob(){
		
		String sql = "insert into websites(name,id,url,country,image)" + "values(?,?,?,?,?)";
		Object [] params = new Object[5];
		params[0]=null;
		params[1]=null;
		params[2]=new String("just a test");
		params[3]=new Float(5);
		
		FileInputStream fileInputStream = null;
		File file = new File("src/JDBCTest/bigFile.png"); //测试写图片
		
		try {
			DBUtils dbUtils = new DBUtils();
			dbUtils.openConnection();
			fileInputStream = new FileInputStream(file);
			params[4]= fileInputStream;
			
			ResultSet resultSet = dbUtils.getInsertObjIDs(sql, params);
			
			if (resultSet != null && resultSet.first()) {
				System.out.println(resultSet.getInt(1));
			}
			
			dbUtils.close(resultSet);
			dbUtils.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private static void test_read_lob() {
		String sql = "select * from websites";
		
		try {
			DBUtils dbUtils = new DBUtils();
			dbUtils.openConnection();
			ResultSet resultSet = dbUtils.execQuery(sql);
			if (null != resultSet) {
				while (resultSet.next()) {
					System.out.println(resultSet.getString("name"));
					System.out.println(resultSet.getString("url"));
					
					int userID = resultSet.getInt("id");
					
					byte[] buf = new byte[256];
					Blob blob = resultSet.getBlob("image");
					if (null != blob) {
						File file = new File("src/JDBCTest/" + userID + ".png");
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						InputStream inputStream = blob.getBinaryStream();
						
						for (int i = inputStream.read(buf); i != -1;) {
							fileOutputStream.write(buf);
							i = inputStream.read(buf);							
						}
						
						inputStream.close();
						fileOutputStream.close();
					}
				}
			}
			
			dbUtils.close(resultSet);
			dbUtils.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
