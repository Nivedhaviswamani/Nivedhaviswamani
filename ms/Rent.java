package ms;
import java.util.*;
import java.sql.*;
public class Rent {
	String emailid,table_name,numberPlate;
	long charged_amount,coveredKm;
	 Connection connection=null;
	public Rent(String emailid)
	{
		this.emailid=emailid;
		  Connections conn = new Connections();
	      connection = conn.connect(); 
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
	public void setChargedAmount(long charged_amount)
	{
		this.charged_amount=charged_amount;
	}
	public void setCoveredKm(long coveredKm)
	{
		this.coveredKm=coveredKm;
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
	public long getChargedAmount()
	{
		return charged_amount;
	}
	public long getCoveredKm()
	{
		return coveredKm;
	}
	
	protected void rent()
	{
		int number=-1;
		  String q4="select no_of_vehicles_rented from customer where emailid='"+getEmailId()+"';";

	        try (Statement statement = connection.createStatement()) {
	            try (ResultSet resultSet = statement.executeQuery(q4)) {
	                while (resultSet.next()) {
	                   number = resultSet.getInt(1); 
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    catch (Exception e) {
	        e.printStackTrace();
	    }   
	        
	      if(number==0) {  
		String numPlate1="";
		Scanner sc=new Scanner(System.in);
		Bike b=new Bike();
		Car c=new Car();
		b.show();
		c.show();
		System.out.print("Enter 1.to rent car,2.To rent bike:");
		int input=sc.nextInt();
		String table_name1=input==1?"car":(input==2?"bike":"");
		setTableName(table_name1);
		System.out.print("Enter the number plate you want to add (choose from above):");
		sc.nextLine();
		String number_plate1=sc.nextLine();
		setNumberPlate(number_plate1);
		String query = "SELECT number_plate FROM " + getTableName() + " WHERE number_plate= '" + getNumberPlate() + "';";


	  
	        long millis=System.currentTimeMillis();  
	    	        java.sql.Date date=new java.sql.Date(millis); 

	        try (Statement statement = connection.createStatement()) {
	            try (ResultSet resultSet = statement.executeQuery(query)) {
	                while (resultSet.next()) {
	                numPlate1 = resultSet.getString(1); 
	               }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	      
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    if(getNumberPlate().equals(numPlate1)) {
	    	System.out.print("press (y) to confirm");
	    	char confirm=sc.next().charAt(0);
	    	if(confirm=='y'|| confirm=='Y')
	    	{
	    		
	    	        String q2="select rental_charge from "+table_name+" where number_plate='"+getNumberPlate()+"';";

	    		        try (Statement statement = connection.createStatement()) {
	    		            try (ResultSet resultSet = statement.executeQuery(q2)) {
	    		                while (resultSet.next()) {
	    		                   long charged_amount1 = resultSet.getInt(1); 
	    		                   setChargedAmount(charged_amount1);
	    		                }
	    		            }
	    		        } catch (SQLException e) {
	    		            e.printStackTrace();
	    		        }
	    		    catch (Exception e) {
	    		        e.printStackTrace();
	    		    }    
		        try (Statement statement = connection.createStatement()) {
		        //	long millis=System.currentTimeMillis();  
	    	      //  java.sql.Date date=new java.sql.Date(millis); 
		        		String q1="Insert into RentDetails values('"+date+"','"+getEmailId()+"','"+getNumberPlate()+"','"+getChargedAmount()+"');";
		        		String q3="update "+getTableName()+" set available_status='no' where number_plate='"+getNumberPlate()+"';";
		        		String q5="update customer set no_of_vehicles_rented=1 where emailid='"+getEmailId()+"';";
		            	 int rowsAffected = statement.executeUpdate(q1);
		            	 int rowsAffected2= statement.executeUpdate(q3);
		            	 int rowsAffected3= statement.executeUpdate(q5);
		                 if (rowsAffected > 0 && rowsAffected2 > 0 && rowsAffected3> 0) {
		                     System.out.println("Rented successfully!");
		                 } else {
		                     System.out.println("Failed to rent");
		                 }
		         
		         
		        }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
			
			}
	    }
	    else
	    	System.out.print("Enter the correct plate_number");
	      }
	      else
	      {
	    	  System.out.print("you already rented a vehicle");
	      }
	}
	public void returnTheRentedVehicle()
	{
		Scanner sc=new Scanner(System.in);
		long cm=0,fine=5000;
		 String q1="select * from rentdetails where emailid='"+getEmailId()+"';";
		 long dummyTime = 3600000;
		 java.sql.Time date=new Time(dummyTime);
	        try (Statement statement = connection.createStatement()) {
	            try (ResultSet resultSet = statement.executeQuery(q1)) {
	                while (resultSet.next()) {
	                   date=resultSet.getTime(1);
	                  String emailid=resultSet.getString(2);
	                  setEmailId(emailid);
	                  String numberplate=resultSet.getString(3);
	                  setNumberPlate(numberplate);
	                  cm=resultSet.getLong(4);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    catch (Exception e) {
	        e.printStackTrace();
	    }   
	        setCoveredKm(500);
	        long extraKm=0,damageAmount=0;
	        double per=0.0;
	        long millis=System.currentTimeMillis();  
	    	        java.sql.Date dateToday=new java.sql.Date(millis);
	    	     long timeDifferenceInMillis = dateToday.getTime() - date.getTime();
	                long differenceInDays = timeDifferenceInMillis / (24 * 60 * 60 * 1000); 
	                if(differenceInDays==2)
	                {
	                	cm=cm*2;
	                }
	                System.out.println("Did you covered more than 500km:(y/n");
	                char c=sc.next().charAt(0);
	                if(c=='y' || c=='Y')
	                {	System.out.println("How many extra km you covered:");
	                	long a=sc.nextLong();
	                	setCoveredKm((500+a));
	                	extraKm=(long)(cm*0.15);}
	                System.out.println("Did you damages the car:(y/n");
	                c=sc.next().charAt(0);
	                if(c=='y' || c=='Y')
	                {
	                	System.out.print("Enter (L) to print low damage,(M) to print medium damage,(H) to print hight damage");
	                	c=sc.next().charAt(0);
	                	if(c=='l' || c=='L')
	                		per=0.20;
	                	else if(c=='m' || c=='M')
	                		per=0.50;
	                	else if(c=='h' || c=='H')
	                		per=0.75;
	                	damageAmount=(long)(fine*per);
	                	
	                }
	                cm=cm+damageAmount+extraKm;
	                setChargedAmount(cm);
	                System.out.println(cm+" "+extraKm+" "+damageAmount+" "+differenceInDays);
	               boolean con=pay();
	               if(con) {
    try (Statement statement = connection.createStatement()) {
    		String qq5="update customer set no_of_vehicles_rented=0 where emailid='"+getEmailId()+"';";
    		String qq3="update rentdetails charged_amount="+getChargedAmount()+" where emailid='"+getEmailId()+"';";
    		int rowsAffected3= statement.executeUpdate(qq5);
    		int rowsAffected2= statement.executeUpdate(qq3);
             if (rowsAffected3> 0 && rowsAffected2>0) {
                 System.out.println("returned the vehicle and paid successfully!");
             } else {
                 System.out.println("Didn't return");
             }
     
     
    }
catch (Exception e) {
    e.printStackTrace();
}
	               }
	               else 
	               {
	            	   System.out.print("Didn't pay");
	               }
	               Vehicle vh;
	               if(getTableName()=="car")
	               {
	            	   vh=new Car();
	            	   vh.updateDetails(getNumberPlate(),getTableName(),getCoveredKm());
	               }
	               else
	               {
	            	   vh=new Bike();
	            	   vh.updateDetails(getNumberPlate(),getTableName(),getCoveredKm());
	               }
	}
	protected boolean pay()
	{
	 	
		Scanner sc=new Scanner(System.in);
		boolean con=false;
        System.out.println("you have to pay "+getChargedAmount()+" 1.to pay by cash,2.decrease from depository:");
        int p=sc.nextInt();
        if(p==1)
        {
        	System.out.print("Give the amount:");
        	long amt=sc.nextLong();
        	if(amt==getChargedAmount())
        	{	System.out.print("Paid");con=true;}
        	else {
        		System.out.print("Didn't Pay");
        		con=false;}
        }
        else if(p==2)
        {
        	   try (Statement statement = connection.createStatement()) {
           		String qq5="update customer depository_amount=depository_amount-"+getChargedAmount()+" where emailid='"+getEmailId()+"';";
           		int rowsAffected3= statement.executeUpdate(qq5);
                    if (rowsAffected3> 0) {
                        System.out.println("returned successfully!");
                    } else {
                        System.out.println("Didn't return");
                    }
            
            
           }
       catch (Exception e) {
           e.printStackTrace();
       }
        }
        System.out.print("Do you want back your depository amount:(y/n)");
        char ans=sc.next().charAt(0);
        if(ans=='y' || ans=='Y')
        {
        	 try (Statement statement = connection.createStatement()) {
            		String qq5="update customer depository_amount=0 where emailid='"+getEmailId()+"';";
            		int rowsAffected3= statement.executeUpdate(qq5);
                     if (rowsAffected3> 0) {
                         System.out.println("returned your amount successfully!");
                     } else {
                         System.out.println("Didn't return");
                     }
             
             
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        }
        else
        {
        	System.out.print("ok!");
        }
        return con;
	}
}

	
	