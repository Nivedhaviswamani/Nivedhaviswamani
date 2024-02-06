package ms;

import java.util.*;
import java.sql.*;
public class Admin {
	String email,s,status;
	public void setEmail(String email)
	{
		this.email=email;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
	public String getEmail()
	{
		return email;
	}
	public String getStatus()
	{
		return status;
	}
	public Admin(String email)
	{
		this.email=email;
		s="";
	}
	public void adminWork()
	{
		Vehicle car=new Car();
		Vehicle bike=new Bike();
		boolean flag;
		Scanner sc=new Scanner(System.in);
		boolean flag1=true;
		while(flag1) {
		System.out.println("Enter 1-to add vehicles,2-sort vehicle,3-search vehicle,4-update vehicle,5-view vehicle,6-to give service,7-to logout");
		int n=sc.nextInt();
		switch(n)
		{
		case 1:
			s=s+"added vehicle";
			flag=true;
			while(flag) {	
			System.out.println("Do u want to add car(c) or bike(b):");
			char c=sc.next().charAt(0);
			if(Character.toUpperCase(c)=='C')
				car.add();
			else if(Character.toUpperCase(c)=='B')
				bike.add();
			System.out.print("Do you want to add again(y):");
			char ans=sc.next().charAt(0);
			if(ans!='y')
			flag=false;
			}
			break;
		case 2:
			s=s+",sorted vehicle";
			flag=true;
			while(flag) {
			System.out.println("Do u want to sort car(c) or bike(b) or all(a):");
			char c=sc.next().charAt(0);
			if(Character.toUpperCase(c)=='C')
				car.sort();
			else if(Character.toUpperCase(c)=='B')
				bike.sort();
			else if(Character.toUpperCase(c)=='A')
				{bike.sort();car.sort();}
			System.out.print("Do you want to sort and see again(y):");
			char ans=sc.next().charAt(0);
			if(ans!='y')
			flag=false;
			}
			break;
	case 3:
		s=s+",searched vehicle";
		flag=true;
		while(flag) {
		System.out.println("Do u want to search car(c) or bike(b) or all(a):");
		char c=sc.next().charAt(0);
		if(Character.toUpperCase(c)=='C')
			car.search();
		else if(Character.toUpperCase(c)=='B')
			bike.search();
		else if(Character.toUpperCase(c)=='B')
		{	bike.search();car.search();}
		System.out.print("Do you want to search and see again(y):");
		char ans=sc.next().charAt(0);
		if(ans!='y')
		flag=false;
		}
		break;
	case 4:
		s=s+",updated vehicle";
		flag=true;
		while(flag) {
		System.out.println("Do u want to update car(c) or bike(b):");
		char c=sc.next().charAt(0);
		if(Character.toUpperCase(c)=='C')
			car.modify();
		else if(Character.toUpperCase(c)=='B')
			bike.modify();
		System.out.print("Do you want to modify again(y):");
		char ans=sc.next().charAt(0);
		if(ans!='y')
		flag=false;
		}
		break;
	case 5:
		s=s+",viewed vehicle";
		flag=true;
		while(flag) {
		System.out.println("Do u want to view car(c) or bike(b) or all(a):");
		char c=sc.next().charAt(0);
		if(Character.toUpperCase(c)=='C')
		{
			System.out.println("Do you want to view 1-serviced cars,2-unserviced cars,3-rented cars,4-not even rented once cars");
			int temp=sc.nextInt();
			if(temp==1)
			car.show();
			else if(temp==2)
				car.show("unserviced");
			else if(temp==3)
				car.show("rented");
			else if(temp==4)
				car.show("not rented");
			else if(temp==5)
			{
				car.show();
				car.show("unserviced");
			}
			else
			{
				System.out.println("Enter corect input:");
			}
		}
		else if(Character.toUpperCase(c)=='B')
		{
			System.out.println("Do you want to view 1-serviced bikes,2-unserviced bikes,3-rented bikes,4-not even rented once bikes,5-all");
			int temp=sc.nextInt();
			if(temp==1)
			bike.show();
			else if(temp==2)
				bike.show("outofservice");
			else if(temp==3)
				bike.show("rented");
			else if(temp==4)
				bike.show("notrented");
			else if(temp==5)
			{
				bike.show();
				bike.show("unserviced");
			}
			else
			{
				System.out.println("Enter corect input:");
			}
		}
		System.out.print("Do you want to search and see again(y):");
		char ans=sc.next().charAt(0);
		if(ans!='y')
		flag=false;
		}
		break;
	case 6:
		s=s+",give vehicle for service";
		bike.giveToService();
		break;
	case 7:
		setStatus(s);
		long millis = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
        Connections c=new Connections();
        try(Connection conn=c.connect()){
        try(Statement stmt=conn.createStatement())
        {
        	String q1="Insert into admin_logout_status values('"+getEmail()+"','"+timestamp+"','"+getStatus()+"');";
        	int rowAffected=stmt.executeUpdate(q1);
        	if(rowAffected>0)
        		System.out.println("Your Status updated");
        	else
        		System.out.println("Your Status not updated");
        	
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		flag1=false;
	}
		}
	}
}
