package ms;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Vehicle {
	protected String type,name,number_plate,available_status,table_name;
	protected long rental_charge,covered_km,rent_count;
	String emailid;
	String query;
	Connection connection=null;
	public Vehicle()
	{
		 try {
		        Connections conn = new Connections();
		        connection = conn.connect();
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	protected String getName()
	{
		return name;
	}
	protected String getNumberPlate()
	{
		return number_plate;
	}
	protected String getAvailableStatus()
	{
		return available_status;
	}
	protected long getRentalCharge()
	{
		return rental_charge;
	}
	protected long getCoveredKm()
	{
		return covered_km;
	}
	protected long getRentCount()
	{
		return rent_count;
	}
	protected String getType()
	{
		return type;
	}
	protected String getTableName()
	{
		return table_name;
	}
	protected void setName(String name)
	{
		this.name=name;
	}
	protected void setNumberPlate(String number_plate)
	{
		this.number_plate=number_plate;
	}
	protected void setAvailableStatus(String available_status)
	{
		this.available_status=available_status;;
	}
	protected void setRentalCharge(long rental_charge)
	{
		this.rental_charge=rental_charge;
	}
	protected void setRentCount(long rent_count)
	{
		this.rent_count=rent_count;
	}
	protected void setCoveredKm(long covered_km)
	{
		this.covered_km=covered_km;
	}
	protected void setType(String type)
	{
		this.type=type;
	}
	protected void setTableName(String table_name)
	{
		this.table_name=table_name;
	}
	public void setEmailId(String emailid)
	{
		this.emailid=emailid;
	}
	public String getEmailId()
	{
		return emailid;
	}
	protected void add()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter vehicle number_pate:");
		String number_plate1=sc.nextLine();
		setNumberPlate(number_plate1);
		System.out.print("Enter vehicle name:");
		String name1=sc.nextLine();
		setName(name1);
		System.out.print("Enter rental_charge:");
		long rental_charge1=sc.nextLong();
		setRentalCharge(rental_charge1);
		String q1 = "INSERT INTO " +getTableName()+"(number_plate,name,rental_charge) VALUES ('" + getNumberPlate() + "','" + getName() + "','" + getRentalCharge() + "');";
		

		        try (Statement statement = connection.createStatement()) {
		            	 int rowsAffected = statement.executeUpdate(q1);

		                 if (rowsAffected > 0) {
		                     System.out.println("Vehicle Details added!");
		                 } else {
		                     System.out.println("sorry couldn't add");
		                 }
		         
		        } 
		    
		    catch (Exception e) {
		        e.printStackTrace();
		    }
			
	}
	protected void search()
	{
		Scanner s1=new Scanner(System.in);
		System.out.print("Enter 1.search by name,2.search by number_plate,3.To exit:");
		int input=s1.nextInt();
		if(input==1)
		{
			System.out.print("Enter the name:");
			s1.nextLine();
			String name1=s1.nextLine();
			setName(name1);
			query = "SELECT * FROM " + getTableName() + " WHERE name= '" + getName() + "' and available_status='yes';";
		}
		if(input==2)
		{
			System.out.print("Enter the number_plate:");
			s1.nextLine();
			String number_plate1=s1.nextLine();
			setNumberPlate(number_plate1);
			query = "SELECT * FROM " + getTableName() + " WHERE number_plate= '" + getNumberPlate() + "' and available_status='yes';";
		}
		if(input==1 || input==2 ) {

		        try (Statement statement = connection.createStatement()) {
		            try (ResultSet resultSet = statement.executeQuery(query)) {
		            	System.out.println("------------------------------------------------------------------------------------");
		            	System.out.println("Number plate          |     name           |   reental Charge     |");
		            	System.out.println("------------------------------------------------------------------------------------");
		                while (resultSet.next()) {
		                   String temp_number_plate = resultSet.getString(1); 
		                   setNumberPlate(temp_number_plate);
		                   String temp_name = resultSet.getString(2); 
		                   setName(temp_name);
		                   long temp_rental_charge=resultSet.getLong(3);
		                   setRentalCharge(temp_rental_charge);
		                   long temp_covered_km=resultSet.getLong(4);
		                   setCoveredKm(temp_covered_km);
		                   //long count=resultSet.getLong(6);
		                   //setRentCount(count);
		                   System.out.println("| "+getNumberPlate()+"      |      "+getName()+"      |     "+getRentalCharge()+"  |");
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		      
		    
		    }
	}
	
	public void sort()
	{
		Scanner s1=new Scanner(System.in);
		System.out.print("Enter 1.sort by name,2.sort by number_plate,3.To sort by Rental price.4.To exit:");
		int input=s1.nextInt();
		if(input==1)
		{
			query = "SELECT * FROM " + getTableName() + " WHERE  available_status='yes' order by name;";
		}
		else if(input==2)
		{
			query = "SELECT * FROM " + table_name + " WHERE available_status='yes' order by number_plate;";
		}
		else if(input==3)
		{
			query = "SELECT * FROM " + table_name + " WHERE available_status='yes' order by rental_charge;";
		}
		if(input==1 || input==2 || input==3) {
		  

		        try (Statement statement = connection.createStatement()) {
		            try (ResultSet resultSet = statement.executeQuery(query)) {
		                	System.out.println("------------------------------------------------------------------------------------");
			            	System.out.println("Number plate          |     name           |   reental Charge     |");
			            	System.out.println("------------------------------------------------------------------------------------");
			                while (resultSet.next()) {
			                   String temp_number_plate = resultSet.getString(1); 
			                   setNumberPlate(temp_number_plate);
			                   String temp_name = resultSet.getString(2); 
			                   setName(temp_name);
			                   long temp_rental_charge=resultSet.getLong(3);
			                   setRentalCharge(temp_rental_charge);
			                   long temp_covered_km=resultSet.getLong(4);
			                   setCoveredKm(temp_covered_km);
			                   System.out.println("| "+getNumberPlate()+"      |      "+getName()+"      |     "+getRentalCharge()+"  |");
		                 
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        
		        }
	}
	public void show()
	{
		query = "SELECT * FROM " + getTableName() + " WHERE  available_status='yes';";
	        try (Statement statement = connection.createStatement()) {
	            try (ResultSet resultSet = statement.executeQuery(query)) {
	            	System.out.println("------------------------------------------------------------------------------------");
	            	System.out.println("Number plate          |     name           |   rental Charge     |    Rent Count");
	            	System.out.println("------------------------------------------------------------------------------------");
	                while (resultSet.next()) {
	                   String temp_number_plate = resultSet.getString(1); 
	                   setNumberPlate(temp_number_plate);
	                   String temp_name = resultSet.getString(2); 
	                   setName(temp_name);
	                   long temp_rental_charge=resultSet.getLong(3);
	                   setRentalCharge(temp_rental_charge);
	                   long temp_covered_km=resultSet.getLong(4);
	                   setCoveredKm(temp_covered_km);
	                   long temp_rent_count=resultSet.getLong(6);
	                   setRentCount(temp_rent_count);
	                   System.out.println("| "+getNumberPlate()+"      |      "+getName()+"      |     "+getRentalCharge()+"  |"+getRentCount()+"   |");}
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
}
	public void show(String input)
	{
		if(input.equalsIgnoreCase("outofservice"))
		query = "SELECT * FROM " + getTableName() + " WHERE  available_status='no';";
		else if(input.equalsIgnoreCase("rented"))
			query = "SELECT * FROM " + getTableName() + " WHERE  rent_count>0;";
		else if(input.equalsIgnoreCase("notrented"))
			query = "SELECT * FROM " + getTableName() + " WHERE  rent_count=0;";

	        try (Statement statement = connection.createStatement()) {
	            try (ResultSet resultSet = statement.executeQuery(query)) {
	            	System.out.println("------------------------------------------------------------------------------------");
	            	System.out.println("Number plate          |     name           |   rental Charge     |    rent count");
	            	System.out.println("------------------------------------------------------------------------------------");
	                while (resultSet.next()) {
	                   String temp_number_plate = resultSet.getString(1); 
	                   setNumberPlate(temp_number_plate);
	                   String temp_name = resultSet.getString(2); 
	                   setName(temp_name);
	                   long temp_rental_charge=resultSet.getLong(3);
	                   setRentalCharge(temp_rental_charge);
	                   long temp_covered_km=resultSet.getLong(4);
	                   setCoveredKm(temp_covered_km);
	                   long temp_rent_count=resultSet.getLong(6);
	                   setRentCount(temp_rent_count);
	                   System.out.println("| "+getNumberPlate()+"      |      "+getName()+"      |     "+getRentalCharge()+"  |"+getRentCount()+"    |");}
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
}
	protected void delete()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number plate of vehicle that you want to delete:");
		String num=sc.next();
		setNumberPlate(num);
		query="delete FROM "+getTableName()+" where number_Plate='"+getNumberPlate()+"';";
		 try (Statement statement = connection.createStatement()) {
        	 int rowsAffected = statement.executeUpdate(query);

             if (rowsAffected > 0) {
                 System.out.println(getNumberPlate()+"Deleted!");
             } else {
                 System.out.println("sorry couldn't delete.Enter the available number plate");
             }
     
    } 
	
catch (Exception e) {
    e.printStackTrace();
}

	}
	protected void modify()
	{
		char c;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number plate you want to update:");
		String numberplate=sc.next();
		setNumberPlate(numberplate);
		System.out.println("Do you want to update name:(y/n)");
		c=sc.next().charAt(0);
		if(c=='y')
		{
			System.out.println("Enter new name:");
			String ss=sc.next();
			
			setName(ss);
			System.out.print(getTableName()+" "+getName());
			query="update "+getTableName()+" set name='"+getName()+"' where number_Plate='"+getNumberPlate()+"';";
			 try (Statement statement = connection.createStatement()) {
	        	 int rowsAffected = statement.executeUpdate(query);

	             if (rowsAffected > 0) {
	                 System.out.println("Name updated!");
	             } else {
	                 System.out.println("sorry couldn't update.Enter the available number plate");
	             }
	     
	    } 
		
	catch (Exception e) {
	    e.printStackTrace();
	}
		}
		System.out.println("Do you want to update rental_charge:(y/n)");
		c=sc.next().charAt(0);
		if(c=='y')
		{
			System.out.println("Enter the new rental_charge:");
			long ss=sc.nextLong();
			setRentalCharge(ss);
			query="update "+getTableName()+" set rental_charge='"+getRentalCharge()+"' where number_Plate='"+getNumberPlate()+"';";
			 try (Statement statement = connection.createStatement()) {
	        	 int rowsAffected = statement.executeUpdate(query);

	             if (rowsAffected > 0) {
	                 System.out.println("Rental charge updated!");
	             } else {
	                 System.out.println("sorry couldn't update.Enter the available number plate");
	             }
	     
	    } 
		
	catch (Exception e) {
	    e.printStackTrace();
	}
		}
		System.out.println("Do you want to update covered km:(y/n)");
		c=sc.next().charAt(0);
		if(c=='y')
		{
			System.out.println("Do you want to add km(y) or reset(n):");
			char temp=sc.next().charAt(0);
			if(temp=='y')
			{
				System.out.println("How many km you want to add");
				long t=sc.nextLong();
				String q1 = "SELECT covered_km FROM " + getTableName() + " WHERE number_plate='"+getNumberPlate()+"'";

				try (Statement statement = connection.createStatement()) {
		            try (ResultSet resultSet = statement.executeQuery(q1)) {
		                while (resultSet.next()) {
		                   long temp_covered_km=resultSet.getLong(1);
		                   setCoveredKm(temp_covered_km);
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		      t=t+getCoveredKm();
		      setCoveredKm(t);
			}
			else
			{
				System.out.println("Enter the km covered:");
				long ss=sc.nextLong();
				setCoveredKm(ss);	
			}
			
			query="update "+getTableName()+" set covered_km='"+getCoveredKm()+"' where number_Plate='"+getNumberPlate()+"';";
			 try (Statement statement = connection.createStatement()) {
	        	 int rowsAffected = statement.executeUpdate(query);

	             if (rowsAffected > 0) {
	                 System.out.println("km updated!");
	             } else {
	                 System.out.println("sorry couldn't update.Enter the available number plate");
	             }
	     
	    } 
		
	catch (Exception e) {
	    e.printStackTrace();
	}
		}
		System.out.println("Do you want to update Available Status:(y/n)");
		c=sc.next().charAt(0);
		if(c=='y')
		{
			String q1 = "SELECT available_status FROM " + getTableName() + " WHERE number_plate='"+getNumberPlate()+"'";

			try (Statement statement = connection.createStatement()) {
	            try (ResultSet resultSet = statement.executeQuery(q1)) {
	                while (resultSet.next()) {
	                   String available_status=resultSet.getString(1);
	                   setAvailableStatus(available_status);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			System.out.println("your current available status is:"+getAvailableStatus());
			if(getAvailableStatus().equals("yes"))
				setAvailableStatus("no");
			else
				setAvailableStatus("yes");
			query="update "+getTableName()+" set available_status='"+getAvailableStatus()+"' where number_Plate='"+getNumberPlate()+"';";
			 try (Statement statement = connection.createStatement()) {
	        	 int rowsAffected = statement.executeUpdate(query);

	             if (rowsAffected > 0) {
	                 System.out.println("Available status  updated!");
	             } else {
	                 System.out.println("sorry couldn't update.Enter the available number plate");
	             }
	     
	    } 
		
	catch (Exception e) {
	    e.printStackTrace();
	}
		}
		System.out.println("Do you want to update No.Of.Times a vehicle rented:(y/n)");
		c=sc.next().charAt(0);
		if(c=='y')
		{
			System.out.println("Do you want to add count(y) or reset(n):");
			char temp=sc.next().charAt(0);
			if(temp=='y')
			{
				System.out.println("How many count you want to add");
				long t=sc.nextLong();
				String q1 = "SELECT rent_count FROM " + getTableName() + " WHERE number_plate='"+getNumberPlate()+"'";

				try (Statement statement = connection.createStatement()) {
		            try (ResultSet resultSet = statement.executeQuery(q1)) {
		                while (resultSet.next()) {
		                   long count=resultSet.getLong(1);
		                   setRentCount(count);
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		      t=t+getRentCount();
		      setRentCount(t);
			}
			else
			{
				System.out.println("Enter the rent count:");
				long ss=sc.nextLong();
				setRentCount(ss);	
			}
			
			query="update "+getTableName()+" set rent_count='"+getRentCount()+"' where number_Plate='"+getNumberPlate()+"';";
			 try (Statement statement = connection.createStatement()) {
	        	 int rowsAffected = statement.executeUpdate(query);

	             if (rowsAffected > 0) {
	                 System.out.println("count updated!");
	             } else {
	                 System.out.println("sorry couldn't update.Enter the available number plate");
	             }
	     
	    } 
		
	catch (Exception e) {
	    e.printStackTrace();
	}
		}
	}
	protected void updateDetails(String numberPlate,String table,long km)
	{
		setNumberPlate(numberPlate);
		setTableName(table);
		setCoveredKm(km);
		try (Statement statement = connection.createStatement()) {
    		String q1="update "+getTableName()+" set available_status='yes',rent_count=rent_count+1,covered_km=covered_km"+getCoveredKm()+" where number_plate='"+getNumberPlate()+"';";
    		//String qq3="update rentdetails charged_amount="+getChargedAmount()+" where emailid='"+getEmailId()+"';";
    		int rowsAffected3= statement.executeUpdate(q1);
    		//int rowsAffected2= statement.executeUpdate(qq3);
             if (rowsAffected3> 0) {
                 System.out.println("updated successfully!");
             } else {
                 System.out.println("Didn't update");
             }
             checkForService(getTableName(),getNumberPlate());
     
    }
catch (Exception e) {
    e.printStackTrace();
}
	}
	public void checkForService(String table,String num)
	{
		setTableName(table);
		setNumberPlate(num);
		String q1="select covered_km from "+getTableName()+"where number_plate='"+getNumberPlate()+"';";
		
		try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(q1)) {
                while (resultSet.next()) 
				{
					long km=resultSet.getLong(1);
					String q2="update "+getTableName()+" set available_status='no' where number_plate='"+getNumberPlate()+"';";
					if((getTableName().equals("car") && km>=3000)||(getTableName().equals("car") && km>=1500))
					{
						int rowsAffected=statement.executeUpdate(q2);
						if(rowsAffected>0)
						{
							System.out.print("checked for services");
						}
						else
							System.out.print("couldn't service");
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void giveToService()
	{
		String q1="update "+getTableName()+" set available_status='yes' and covered_km=0 where available_status='no';";
		
		try (Statement statement = connection.createStatement()) {
			int rowsAffected=statement.executeUpdate(q1);
			if(rowsAffected>0)
			{
				System.out.print("Given for services");
			}
			else
				System.out.print("Didn't give for  service");
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	}
	

