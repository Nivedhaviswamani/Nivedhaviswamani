package ms;
import java.sql.*;
import java.util.*;
public class sign {
	protected String email="",password="",tablename="",role="";
	Connection conn=null;
	
	public sign(String role)
	{
		this.role=role;
		try {
		Connections obj=new Connections();
		conn=obj.connect();
		}
		catch(Exception e)
		{
			
		}
		
	}
	protected void setEmail(String email)
	{this.email=email;}
	protected String getEmail()
	{return email;}
	protected void setPassword(String password)
	{this.password=password;}
	protected String getPassword()
	{return password;}
	protected String signIn()
	{
		String id="";
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your email:");
		String tempEmail=sc.next();
		setEmail(tempEmail);
		System.out.println("Enter the password:");
		String tempPassword=sc.next();
		setPassword(tempPassword);
		String sql1="Select * from "+tablename+" where emailid='"+email+"';";
		try(Statement stmt=conn.createStatement())
		{
			try(ResultSet rs=stmt.executeQuery(sql1))
			{
				while(rs.next())
				{
					tempEmail=rs.getString(1);
					tempPassword=rs.getString(2);
					if(tempPassword.equals(password))
                    {
                    id=getEmail();
                   
                    }
                    else
                    
                    id="exist";
                    	}
					
				}
			
			catch(Exception e) {
			e.printStackTrace();}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return id;
	}
	

}
