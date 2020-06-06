package JDBCTest;

import java.sql.*;
import java.util.Properties;
import java.io.*;

public class DBUtils {
	
	private final String dbConnFile = "JDBCTest/jdbc.properties"; //同包读文件
	// File file =new File("src/JDBCTest/bigFile.png"); //跨包读文件
	private Connection connection = null;
	private String dbDriver;    //定义驱动  
	private String dbURL;        //定义URL  
	private String userName;    //定义用户名  
	private String password;    //定义密码

	//从配置文件取数据库链接参数  
	private void loadConnProperties() {
		Properties props = new Properties();
		try {
			//通过类加载器读取
			props.load(DBUtils.class.getClassLoader().getResourceAsStream(dbConnFile));
			//通过字节码对象读取
			//String filePath = DBUtils.class.getResource(“jdbc.properties”).getPath();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//从配置文件中取得相应的参数并设置类变量
		this.dbDriver = props.getProperty("driver");
		this.dbURL = props.getProperty("url");
		this.userName = props.getProperty("username");
		this.password = props.getProperty("password");
	}
	
	public boolean openConnection() {
		try {
			loadConnProperties();
			Class.forName(dbDriver);
			this.connection = DriverManager.getConnection(dbURL, userName, password);
			return true;
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.err.println("db: " + e.getMessage());
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("db: " + e.getMessage());
		}
		
		return false;
	}
	
	protected void finalize() throws Exception {
		try {
			if (null != connection) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 查询并得到结果集
	public ResultSet execQuery(String sql) throws Exception {
		ResultSet resultSet = null;
		try {
			if (null == connection) {
				throw new Exception("Database not connected!");
			}
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultSet;
	}
	
	// 插入一条新纪录，并获取标识列的值
	public ResultSet getInsertObjIDs(String insertSql) throws Exception {
		ResultSet resultSet = null;
		try {
			if (null == connection) {
				throw new Exception("Database not connected!");
			}
			Statement statement = connection.createStatement();
			statement.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultSet;
	}
	
	//以参数SQL模式插入新纪录，并获取标识列的值
	public ResultSet getInsertObjIDs(String insertSql, Object[] params) throws Exception {
		ResultSet resultSet = null;
//		PreparedStatement preparedStatement = null;
		try {
			if (null == connection) {
				throw new Exception("Database not connected!");
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			if (null != preparedStatement) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setObject(i+1, params[i]);
				}
			}
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultSet;
	}
	
	// 插入、更新、删除
	public int execCommand(String sql) throws Exception {
		int flag = 0;
		try {
			if (null == connection) {
				throw new Exception("Database not connected!");
			}
			
			Statement statement = connection.createStatement();
			flag = statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	
	// 释放资源-ResultSet
	public void close(ResultSet resultSet) throws Exception {
		try {
			Statement statement = resultSet.getStatement();
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 释放资源-Statement
	public void close(Statement statement) throws Exception {
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 释放资源-Statement
	public void close() throws SQLException, Exception {
		if (null!=connection) {
			connection.close();
			connection = null;
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
