package ms;
import java.util.*;
public class Customer {
	protected String email;
public Customer(String email)
{
this.email=email;
}
public String getEmail()
{
	return this.email;
}
public void dashboard()
{
	
	Scanner sc=new Scanner(System.in);
	boolean flag=true;
	while(flag)
	{
		System.out.print("Enter 1 to view vehicles,2 to add or remove car,3 to rent a vehicle,4 to return a vehicle,5 to logout");
		int input=sc.nextInt();
		if(input==1)
		{
			Car c=new Car();
			Bike b=new Bike();
			c.show();
			b.show();
		}
		else if(input==2)
		{
			boolean f=true;
			while(f)
			{
			System.out.print("Enter 1 to add to car,2 to remove from cart,3 to exit");
			int ip=sc.nextInt();
			if(ip==1)
			{
				Cart c1=new Cart(getEmail());
				c1.addCart();
			}
			else if(ip==2)
			{
				Cart c1=new Cart(getEmail());
				c1.removeFromCart();
			}
			else if(ip==3)
			{
				f=false;
			}
			}
		}
		else if(input==3)
		{
			Rent r=new Rent(getEmail());
			r.rent();
		}
		else if(input==4)
		{
			Rent r=new Rent(getEmail());
			r.returnTheRentedVehicle();
		}
		else if(input==5)
		{
			flag=false;
		}
		
	}
}
}
