import java.util.*;
public class ATMinterface{
	String ATMcardnumber;
	int pinnumber;
	String username;
	float balance;
	static String transaction="";
	static public int c=0;
    public	ArrayList<ATMinterface> li = new ArrayList<>();
	ATMinterface()
	{
	}
	ATMinterface(String ATMcardnumber,int pinnumber,String username,float balance)
	{
		this.ATMcardnumber=ATMcardnumber;
		this.pinnumber=pinnumber;
		this.username=username;
		this.balance=balance;
	}
	public String getATMcardnumber()
	{
		return ATMcardnumber;
	}
	public int getpinnumber()
	{
		return pinnumber;
	}
	public String getusername()
	{
		return username;
	}
	public float getbalance()
	{
		return balance;
	}
	public void setcardnumber(String a1)
	{
		ATMcardnumber=a1;
	}
	public void setpin(int p1)
	{
		pinnumber=p1;
	}
	public void setname(String u1)
	{
		username=u1;
	}
	public void setbalance(float b1)
	{
		balance=b1;	
	} 
	public void addValues(String num[], int pin[],String name[], float bal[])
    {
        for (int i = 0; i <4; i++) 
        {
            li.add(new ATMinterface(num[i], pin[i],name[i],bal[i]));
        }
    }
	 public  static void transfer(ATMinterface ob,String[] nums,float bal[])
    {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter Your Account number:");
    	String accountno=sc.nextLine();
    	int i=0;
    	int temp=0;
    	for( i=0;i<nums.length;i++)
    	{
    		if(accountno.equals(nums[i]))
    		{
    			temp=1;
    			break;
    		}	    		
    	}
    	if(temp==0)
    	{
    		System.out.println("Entered Account number is incorrect");
    		return;
    	}
    	System.out.println("Enter amount to transfer :");
        float amount = sc.nextFloat();   
        if(ob.balance >= amount)
        {
            c++;
            ob.balance -= amount;
            System.out.println("Successfully Transferd ");
            c++;
            bal[i]+=amount;
            transaction+="\n"+Float.toString(amount)+" rupees transferred from your account to "+accountno+" \n";
        }
        else
            System.out.println("balance is Insufficient");
    }   
	public static void main(String args[])
	{
		String num[]={"1534226783","2566372189","1543236738","5263278192","3172654782","1234567896","2876541901"};
		int pin[]={5432,1342,1265,1234,2121,1523,1237};
		String name[]={"Keerthi","Sruthi","Rani","Rohini","Rebka","Suji","Moudi"};
		float bal[]={200000,500000,275000,102000,300000,576000,90000};
		ATMinterface data=new ATMinterface();
		data.addValues(num,pin,name,bal);
		System.out.println("Welcome to ATM");
		System.out.println("Please insert your card: ");
		String debitcardnum="";
		Scanner sn=new Scanner(System.in);
		ATMinterface ob=null;
		int no_tries=0;
		while(true)
		{
			System.out.println("Enter Your CardNumber");
			debitcardnum=sn.nextLine();
			for(int i=0;i<4;i++)
			{
				ATMinterface a1=data.li.get(i);
				if(a1.getATMcardnumber().equals(debitcardnum))
				{
					ob=a1;
					break;
				}
			}
			if(ob!=null||no_tries>3)
			{
				break;
			}
			else
			{
				no_tries=0;
				System.out.println("Please Try again, User not recognised");
			}
		}
		int userpin=0;
		System.out.println("Enter pin number:");
	    no_tries=0;
		while(true)
		{
			userpin=sn.nextInt();
			if(ob.getpinnumber()==userpin || no_tries>3)
			{
				break;
			}
			else
			{
				no_tries++;
				System.out.println("Please Try again, User not recognised");
			}
		}
		System.out.println("Welcome "+ob.getusername());
		System.out.println("***Enter Your Option***");   
        System.out.println("1. Check bank balance");
        System.out.println("2. cash Withdraw");
        System.out.println("3. Deposite");
        System.out.println("4. Transfer");
        System.out.println("5. Transaction History"); 
        System.out.println("6. Exit");     
		int option=sn.nextInt();
		while(option<=5)
		{
			if(option==1)
				System.out.println("Your Account balance is "+ob.getbalance());
			else if(option==2)
			{
				System.out.println("Money you like to withdraw");
		        Scanner sc1=new Scanner(System.in);
		        float amount=sc1.nextFloat();
		        if(amount>ob.getbalance())
			        System.out.println("Insufficient balance!!");
		        else
		        {
			        while(amount%10!=0)
                    {
                        System.out.println("Enter amount in terms of 10's");
                        amount = sc1.nextFloat();
                    }
			        float flag=ob.getbalance()-amount;
			        ob.setbalance(flag);
			        System.out.println("Collect your cash");
			        c++;
			        transaction+="\n"+Float.toString(amount)+" rupees withdraw from your account"+"\n";
		        }
			}
			else if(option==3)
			{
				System.out.println("How much money would you like to deposit:");
		        Scanner sc2=new Scanner(System.in);
		        float amount=sc2.nextFloat();
		        float flag=amount+ob.getbalance();
		        ob.setbalance(flag);
		        System.out.println("Your account balance is "+ob.getbalance());
		        c++;
                transaction+="\n"+Float.toString(amount)+ " rupees deposited into your account"+"\n";
			}
			else if(option==4)
			{
				transfer(ob,num,bal);
			}
			else if(option==5)
			{
				if(c>0)
                {
                    System.out.println("You have performed "+c+" transactions");
                    System.out.println("Transaction Details :");
                    System.out.println(transaction);
                }
                else
                {
                    System.out.println("No transactions are performed");
                }
			}
			else
			{
				break;
			}
	        System.out.println("Enter Your Option:");   
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposite");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History"); 
            System.out.println("6. Exit");     
     	    option=sn.nextInt();
		}
		System.out.println("Thank you!!! Have a great day...");
	}
}