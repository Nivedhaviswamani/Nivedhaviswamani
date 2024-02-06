package ms;
import java.util.*;
public class Main2 {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		String pass="1234";
		String role="";
		boolean flag=true;
		while(flag==true)
		{
			System.out.println("Enter 1-for admin singUp/signIn,2-for customer singUp/signIn,3-to exit");
			int whom=sc.nextInt();
		if(whom==1)
		{
			role="admin";
			System.out.println("Enter password to enter as admin");
			String password=sc.next();
			if(password.equals(pass)) {
			AdminSignUpPage ad=new AdminSignUpPage(role);
			ad.signUpOrSignIn();
			}
			else
			{
				System.out.println("Wrong Password");
			}
		}
		else if(whom==2)
		{
			role="customer";
			CustomerSignUpPage cus=new CustomerSignUpPage(role);
			cus.signUpOrSignIn();
		}
		else if(whom==3)
		{
			System.out.print("thank you! visit again!");
			flag=false;
		}
		else
		{
			System.out.println("select correct option");
		}
		}
	}

}
