package ms;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Cart {
	protected String emailid,table_name,numberPlate;
	public Cart(String email)
	{
		this.emailid=email;
	}
	public void setEmailId(String emailid)
	{
		this.emailid=emailid;
	}
	public void setTableName(String table_name)
	{
		this.table_name=table_name;
	}
	public void setNumberPlate(String numberPlate)
	{
		this.numberPlate=numberPlate;
	}
	public String getEmailId()
	{
		return emailid;
	}public String getTableName()
	{
		return table_name;
	}
	public String getNumberPlate()
	{
		return numberPlate;
	}
	
	protected void addCart()
	{
		Scanner sc=new Scanner(System.in);
		Bike b=new Bike();
		Car c=new Car();
		b.show();
		c.show();
		System.out.print("Enter 1.to add car,2.To add bike:");
		int input=sc.nextInt();
		String table_name1=input==1?"car":(input==2?"bike":"");
		setTableName(table_name1);
		System.out.print("Enter the number plate you want to add (choose from above):");
		sc.nextLine();
		String number_plate=sc.nextLine();
		setNumberPlate(number_plate);
		String query = "SELECT number_plate FROM " + getTableName() + " WHERE number_plate= '" + getNumberPlate() + "' and available_status='yes';";
		String numPlate1="";

	    try {
	        Connections conn = new Connections();
	        Connection connection = conn.connect();

	        try (Statement statement = connection.createStatement()) {
	            try (ResultSet resultSet = statement.executeQuery(query)) {
	                while (resultSet.next()) {
	                 numPlate1 = resultSet.getString(1); 
	               }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        connection.close();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    if(number_plate.equals(numPlate1)) {
		String q1="Insert into cart values('"+getEmailId()+"','"+getNumberPlate()+"');";
		 try {
		        Connections conn = new Connections();
		        Connection connection = conn.connect();

		        try (Statement statement = connection.createStatement()) {
		            	 int rowsAffected = statement.executeUpdate(q1);

		                 if (rowsAffected > 0) {
		                     System.out.println("cart added successfully!");
		                 } else {
		                     System.out.println("Failed to add in cart.wrong plate_number");
		                 }
		         
		        } 
		         
		        
		        connection.close();
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
			
			}
	    else
	    	System.out.print("Enter the correct plate_number");
	}
	protected void removeFromCart()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number plate you want to remove:");
		String number_plate=sc.nextLine();
		String q1="delete from cart where number_plate='"+number_plate+"' and emailid='"+emailid+"';";
		 try {
		        Connections conn = new Connections();
		        Connection connection = conn.connect();

		        try (Statement statement = connection.createStatement()) {
		            	 int rowsAffected = statement.executeUpdate(q1);

		                 if (rowsAffected > 0) {
		                     System.out.println("Removed successfully!");
		                 } else {
		                     System.out.println("Failed to remove from cart.wrong plate_number");
		                 }
		         
		        } 
		         
		        
		        connection.close();
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
			
	}
	}


