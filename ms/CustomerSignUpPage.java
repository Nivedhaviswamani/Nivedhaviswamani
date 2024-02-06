package ms;

import java.sql.Statement;
import java.util.Scanner;

public class CustomerSignUpPage extends sign{
	String name,address;
	long depositoryAmount,phno;
	public CustomerSignUpPage(String role)
	{
		super(role);
		super.tablename="customer";
	}
	protected void setName(String name)
	{
		this.name=name;
	}
	protected void setAddress(String address)
	{
		this.address=address;
	}
	protected void setPhno(long phno)
	{
		this.phno=phno;
	}
	protected boolean setDepositoryAmount(long depositoryAmount)
	{
		if(depositoryAmount>=300000) {
		this.depositoryAmount=depositoryAmount;
		return true;
		}
		else
		{
			return false;
		}
	}
	public void signUpOrSignIn()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1 for Sign In,2 for sign Up,3 for exit");
		int input=sc.nextInt();
		if(input==1)
		{
			String ans=signIn();
			if(ans.equals(""))
			{
				System.out.println("Couldn't signIn try again");
			}
			else if(ans.equals("exist"))
			{
				System.out.println("Wrong username or password");
	        	System.out.println("Try Again");
			}
			else
			{
				System.out.println("Signned In Successfully");
				System.out.println("welcome Customer!");
				Customer ad=new Customer(ans);
				ad.dashboard();
			}
		}
		else if(input==2)
		{
			String ans=signUp();
			if(ans.equals("")){
				System.out.println("Failed to create a account.");
			}
			else 
			{
			System.out.println("Welcome Customer!");
			System.out.println("Signned Up SuccessFully");
			Customer ad=new Customer(ans);
			}
		}
	}
	protected String signUp()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter your full name:");
		String name1=sc.nextLine();
		setName(name1);
		System.out.print("Enter your phno:");
		long phno1=sc.nextLong();
		setPhno(phno1);
		System.out.println("Enter your permanent address:");
		sc.nextLine();
		String address1=sc.nextLine();
		setAddress(address1);
		boolean flag=true;
		while(flag) {
		System.out.println("How Many Rs are you depositing ?(minimum should be 30000):");
		long amount=sc.nextLong();
		flag=setDepositoryAmount(amount);
		}
		System.out.print("Enter your email id:");
		String email1=sc.next();
		setEmail(email1);
		flag=true;
		while(flag) {
	System.out.print("Enter a password:");
		String pass1=sc.next();
		System.out.println("Re enter the password");
		String pass2=sc.next();
		if(pass1.equals(pass2)) {
		setPassword(pass1);
		flag=false;
		}else
		{
			System.out.println("password doesn't match.please refill it");
		}
		}
		String id="";
		String sql="Insert into customer values('"+email+"','"+password+"','"+name+"','"+address+"','"+phno+"','"+depositoryAmount+"',0);";
	 
	        try (Statement statement = conn.createStatement()) {
	            	 int rowsAffected = statement.executeUpdate(sql);

	                 if (rowsAffected > 0) {
	                	 id=getEmail();
	                 } else {
	                     id="";
	                 }
	        } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		return id;
		}
	}
