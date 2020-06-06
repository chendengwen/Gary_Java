package JDBCTest;

import java.sql.*;

public class MySQLDemo {

	static final String JDBC_driver = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
	
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		
		String user = "root";
		String password = "Chendw123";
		
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_driver);
			
			// 打开链接
            System.out.println("连接数据库...");
			connection = DriverManager.getConnection(DB_URL, user, password);
			
			// 执行查询
            System.out.println("实例化Statement对象...");
            statement = connection.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";
            ResultSet resultSet = statement.executeQuery(sql);
            
            // 展开结果集数据库
            while (resultSet.next()) {
            	// 通过字段检索
            	int id = resultSet.getInt("id");
            	String name = resultSet.getString("name");
            	String url = resultSet.getString("url");
            	
            	// 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
			}
            
            // 完成后关闭
            resultSet.close();
            statement.close();
            connection.close();
		} catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		} catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
			// 关闭资源
            try{
                if(statement!=null) statement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
	}
	
}
