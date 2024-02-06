package ms;

import java.sql.*;
public class Connections {

	public Connection connect() {
		String url="jdbc:mysql://localhost:3307/vehiclerentalsystem";
		String username="root";
		String password="";
		Connection connection=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,username,password);  
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
		return connection;
	}

}
