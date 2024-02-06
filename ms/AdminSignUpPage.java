package ms;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
public class AdminSignUpPage extends sign{
public AdminSignUpPage(String role)
{
	super(role);
	super.tablename="admin";
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
			System.out.println("welcome Admin!");
			Admin ad=new Admin(getEmail());
			ad.adminWork();
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
		System.out.println("Welcome Admin!");
		System.out.println("Signned Up SuccessFully");
		Admin ad=new Admin(ans);
		}
	}
}

protected String signUp()
{
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter your email id:");
	String email1=sc.next();
	setEmail(email1);
	Boolean flag=true;
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
	String sql="Insert into admin values('"+email+"','"+password+"');";
 
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
