package projectBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.yaml.snakeyaml.Yaml;

public class BankmanagerOperations 
{
	public SeleniumControls sc;
	public ArrayList al;
	public HashMap hm;
	public Logger log;
	public Map sh;
	public BankmanagerOperations() throws FileNotFoundException
	{
		try 
		{
			File fl= new File("src\\main\\resources\\Properties.yaml");
			FileReader fr = new FileReader(fl);
			Yaml yl=new Yaml();
			this.sh=yl.load(fr);	
			PropertyConfigurator pc= new PropertyConfigurator();
			pc.configure("src/main/resources/log4j.properties");
		} 
		catch (FileNotFoundException e) 
		{
			this.log.error("Missing Configuration File");
		}		
		this.log= Logger.getLogger(BankmanagerOperations.class.getName());
		this.sc= new SeleniumControls(this.sh.get("url").toString(), this.log, this.sh);
		this.al= new ArrayList();
		ArrayList<String> row2 = new ArrayList<String>();
		row2.add("Mitchell"); row2.add("Roy"); row2.add("547432");
		ArrayList<String> row3= new ArrayList<String>();
		row3.add("Richardson"); row3.add("John"); row3.add("510085");
		this.al.add(row2); this.al.add(row3);
		ArrayList<String> row4 = new ArrayList<String>();
		row4.add("Williams"); row4.add("Roberta"); row4.add("504368");
		ArrayList<String> row5 = new ArrayList<String>();
		row5.add("Smith"); row5.add("James"); row5.add("355241");
		this.al.add(row4); this.al.add(row5);
		this.log.info(this.al);
		 this.hm = new HashMap();
		 
	}
	/*This Method is hadles the given following steps:-
	 * 
	 * Calling the buttonClick Method from seleniumControls class for CLICK ON BANK MANAGER LOGIN 
	 * After successfully login we need to select add Customer.
	 * For this iam calling the buttonClick metod again from seleniumcontrol class, and stored in addCustomer variable.
	 * after click add customer button i need to enter some dummy customer details through sendkeys method from seleniumControls class.
	 * verifying whether customer added successfully or not .
	 * hadles the alert pop up messages for each user and printing the customer ID's
	 * 
	 */
	public void addCustomer() throws InterruptedException
	{
		//Calling the buttonClick method from seleniumControls class for Bank Click button 
		boolean bankClick=this.sc.buttonClick(((Map)this.sh.get("addCustomer")).get("bankClick").toString(), 10);
		if(bankClick==true)
		{			
			for(Object row:al)
			{			
				boolean addCustomer=this.sc.buttonClick(((Map)this.sh.get("addCustomer")).get("addCustomerbuttonClick").toString(), 10);
				if(addCustomer==true)
				{
					ArrayList inner_al = (ArrayList)row;
					this.sc.sendKeys(((Map)this.sh.get("addCustomer")).get("firstName").toString(), inner_al.get(0).toString(), 5);
					this.sc.sendKeys(((Map)this.sh.get("addCustomer")).get("lastName").toString(), inner_al.get(1).toString(), 5);
					this.sc.sendKeys(((Map)this.sh.get("addCustomer")).get("postCode").toString(), inner_al.get(2).toString(), 5);
					boolean addClick=this.sc.buttonClick(((Map)this.sh.get("addCustomer")).get("addClick").toString(), 5);				
					if(addClick==true)
					{
						String alert=this.sc.alerts(inner_al.get(0).toString());						
					}
				}					
				else
				{
					this.log.error("Add Customer box was not clicked");
				}
			}
		}
		else
		{
			this.log.error("Bank Manager Login button was not working.");
		}		
	}	
	
	/*This method is handels with the given following steps:-
	 * 
	 *  ButtonClick click for customer button
	 *  vailidation mathod: is check the details of added users from above test case excists or not
	 *  Enter the user details in search bar 
	 *  check the user user name in table whether table is filtered as based on given user name or not 
	 * 	search bar is working successfully then test case is passed
	 * 
	 */
	public void customersVailidation() throws InterruptedException
	{
		boolean custBox=this.sc.buttonClick(((Map)this.sh.get("customersVailidation")).get("custBox").toString(), 10);
		if(custBox==true)
		{
			int count =6;
			for(Object data: al)
			{
				ArrayList in = (ArrayList)data;
				boolean addedStatus =this.sc.vailidation(((Map)this.sh.get("customersVailidation")).get("vailidation").toString().replace("val", Integer.toString(count)), true, in.get(0).toString(), in.get(1).toString(), in.get(2).toString());
				if(addedStatus==true)
				{
					this.log.info("Added Successfully to list");				
					count++;
				}
				else if (addedStatus==false)
				{
					this.log.error("Customer datails not added to list");
				}
				boolean search =this.sc.sendKeys(((Map)this.sh.get("customersVailidation")).get("flName").toString(), in.get(0).toString(), 5);
				if(search==true)
				{
					this.log.info(in.get(0).toString());
					boolean filterSearch=this.sc.vailidation(((Map)this.sh.get("customersVailidation")).get("vailidation").toString().replace("[val]", ""), false, in.get(0).toString(), in.get(1).toString(), in.get(2).toString());
					if (filterSearch==true)
					{
						this.log.info("Test cases passed");
					}
					else if(filterSearch==false)
					{
						this.log.error("Test cases failed");
					}
				}	
				this.sc.clear(((Map)this.sh.get("customersVailidation")).get("clear").toString(), 2);
			}			
		}
	}
	
	/*This method is handels with the given following steps:-
	 * 	buttonClick method is click for Open Account
	 * 	selectCustomer method is for selecting customer from added list
	 * 	selectCurrency method is for select type of the currency
	 * 	click the process button
	 * 	Alert method handels the account creation for customer then close the alert popup 
	 */
	public void openAccount() throws InterruptedException
	{
		boolean openButton=this.sc.buttonClick(((Map)this.sh.get("openAccount")).get("openButton").toString(), 10);
		if(openButton==true)
		{			
			for(Object data: al)
			{
				ArrayList dataInfo=(ArrayList)data;
				boolean selectedOp=this.sc.selectCustomer(((Map)this.sh.get("openAccount")).get("selectOp").toString(), dataInfo.get(0)+" "+dataInfo.get(1), 20);
				if(selectedOp==true)
				{
					boolean selCurrency=this.sc.selectCurrency(((Map)this.sh.get("openAccount")).get("selCurrency").toString(), 20);
					if(selCurrency==true)
					{
						boolean click=this.sc.boxclick(((Map)this.sh.get("openAccount")).get("click").toString(), 10);
						if(click==true)
						{
							String alert=this.sc.alerts(dataInfo.get(0).toString());
							this.hm.put(dataInfo.get(0).toString()+" "+dataInfo.get(1).toString(), alert);
							
						}
						else
						{
							this.log.error("Process button not working");
						}
					}
					else
					{
						this.log.error("Currrency not selected");
					}
				}
				else
				{
					this.log.error("Customer data not picked");
				}
			}
			this.sc.buttonClickwithcssSelectors(".home", 20);
		}
		else
		{
			this.log.error("Open Account Button is not working");
		}
	}
	public static void main(String[] args) throws InterruptedException, FileNotFoundException 
	{
		BankmanagerOperations bp = new BankmanagerOperations();
		bp.addCustomer();
		bp.customersVailidation();
		bp.openAccount();
		CustomerLogin cl= new CustomerLogin(bp.al, bp.sc, bp.hm, bp.sh);
		cl.first_user_selection();
		cl.depositAmount();
		cl.withdrawlAmount();
		cl.transactionsHistory();
		cl.deleteTransactions();
		cl.sc.close();
	}

}
