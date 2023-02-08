package projectBank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;



public class CustomerLogin 
{
	public SeleniumControls sc;
	public ArrayList al;
	public HashMap hm;
	public ArrayList alint;
	public Logger log;
	public Map sh;
	public CustomerLogin(ArrayList al, SeleniumControls sc, HashMap alert_hm, Map sh)
	{
		this.al = al;
		this.sc = sc;
		this.sh=sh;
		this.hm = alert_hm;
		this.alint= new ArrayList();
		this.alint.add("1000"); this.alint.add("2000"); this.alint.add("3213");
		this.log=Logger.getLogger(CustomerLogin.class.getName());
		this.log.info(this.alint);		
	}
	
	/*This Method handles the given below following steps:
	 *  ButtonClick click for customer login button.
	 *  pick the user name from added user list.
	 *  after picking the user name click the loginButton 
	 *  vailidating the the user name with present inthe welcome note or not. 
	 * 	printing the user name , account num and currency on the console and logging file.
	 * 	checking the user name and account details from banking details
	 * 
	 */
	public void first_user_selection() throws InterruptedException
	{
		boolean customerLoginButton=this.sc.buttonClick(((Map)this.sh.get("CustomerLogin")).get("customerLoginButton").toString(), 10);
		if(customerLoginButton==true)
		{
			for(Object data: this.al)
			{
				ArrayList listInfo=(ArrayList)data;
				boolean custOptionSelection=this.sc.selectCustomer(((Map)this.sh.get("CustomerLogin")).get("opSelection").toString(), listInfo.get(0)+" "+listInfo.get(1), 20);
				if(custOptionSelection==true)
				{
					boolean loginButton=this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("CustomerLogin")).get("loginButton").toString(), 10);
					if(loginButton==true)
					{
						boolean msg=this.sc.welcomeMsgVailidation(((Map)this.sh.get("CustomerLogin")).get("msg1").toString(), ((Map)this.sh.get("CustomerLogin")).get("msg2").toString(), ((Map)this.sh.get("CustomerLogin")).get("msg3").toString(), this.hm, 20);
						if(msg==true)
						{
							this.log.info("selecting user name and present welcome note both are same..And also account num also verified correctly");
							HashMap hmp=this.sc.acDetails(((Map)this.sh.get("CustomerLogin")).get("acDetails").toString(), 20);
							this.log.info(hmp);
							System.out.println();
						}
					}
					else
					{
						this.log.error("Login button is not clicked");
					}				
				}
				else
				{
					this.log.error("Selecting an user is failed");
				}
				this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("CustomerLogin")).get("logout").toString(), 10);
			}
		}
		else
		{
			this.log.error("Customer Login button not clicked. it was not working");
		}
	}
	
	/* This method is deals with the given flow:-
	 * 	ButtonClick click for customer login button.
	 *  pick the user name from added user list.
	 *  after picking the user name click the loginButton
	 *  vailidating the the user name with present inthe welcome note or not.
	 *  storing the before balance.
	 *  click on deposit button.
	 *  Enter some amount inthe input box.
	 *  click on credit button at the bottom.
	 *  checking the before balance and after balance whether updated or not 
	 * 	after successfully updated then click on logout button
	 * 
	 */
	public void depositAmount() throws InterruptedException
	{
		for(Object data: this.al)
		{
			ArrayList listInfo=(ArrayList)data;
			boolean custOptionSelection=this.sc.selectCustomer(((Map)this.sh.get("depositAmount")).get("custOptionSelection").toString(), listInfo.get(0)+" "+listInfo.get(1), 20);
			if(custOptionSelection==true)
			{
				boolean loginButton=this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("depositAmount")).get("loginButton").toString(), 10);
				if(loginButton==true)
				{
					HashMap hmp= this.sc.acDetails(((Map)this.sh.get("depositAmount")).get("acDetal").toString(), 20);
					this.log.info(listInfo.get(0)+" "+listInfo.get(1)+" Before Balance: "+hmp.get("Balance"));
					boolean depositBox=this.sc.buttonClick(((Map)this.sh.get("depositAmount")).get("depositBox").toString(), 10);
					if(depositBox==true)
					{			
						for(Object ar: this.alint)
						{
							this.log.info("Amount added again: "+ar);
							boolean enterAm=this.sc.sendKeys(((Map)this.sh.get("depositAmount")).get("enterAm").toString(), ar.toString(), 5);
							if(enterAm==true)
							{
								this.log.info("Amount Entered");
								boolean depositBu=this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("depositAmount")).get("depositBx").toString(), 10);
								if(depositBu==true)
								{
									this.log.info("Deposit Successful to : "+listInfo.get(0)+" "+listInfo.get(1));
									System.out.println();
									HashMap mp=this.sc.acDetails(((Map)this.sh.get("depositAmount")).get("acDetails").toString(), 20);
									this.log.info(listInfo.get(0)+" "+listInfo.get(1)+" After Deposit Balance is: "+mp.get("Balance"));
									if(Integer.parseInt(hmp.get("Balance").toString()) < Integer.parseInt(mp.get("Balance").toString()))
									{
										this.log.info("Deposit amount updated successfull to :"+listInfo.get(0)+" "+listInfo.get(1));
										System.out.println();
									}
									else
									{
										this.log.error("Deposit amount update failed");
									}
								}
								else
								{
									this.log.error("Deposit failed");
								}
							}
							else
							{
								this.log.error("Amount not not picked");
							}
						}
												
					}
					else
					{
						this.log.error("Deposit button not clicked");
					}
				}
				else
				{
					this.log.error("Login button is not clicked");
				}				
			}
			else
			{
				this.log.error("Selecting an user is failed");
			}
			Thread.sleep(3000);
			this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("depositAmount")).get("logout").toString(), 10);
		}	
	}
	
	/*This method is deals with the given flow:-
	 * 	ButtonClick click for customer login button.
	 *  pick the user name from added user list.
	 *  after picking the user name click the loginButton
	 *  vailidating the the user name with present inthe welcome note or not.
	 *  storing the before balance in one vaariable.
	 *  click on withdrawn button.
	 *  Enter some amount inthe input box.
	 *  click on withdrawn button at the botton.
	 *  verify the before balance and after balance whether balance is successfully withdrawn or not
	 *  after successfully withdrawn process is completed,then click on logout button.
	 * 
	 */
	public void withdrawlAmount() throws InterruptedException
	{
		for(Object data: this.al)
		{
			ArrayList listInfo=(ArrayList)data;
			boolean custOptionSelection=this.sc.selectCustomer(((Map)this.sh.get("withdrawlAmount")).get("selectCustomer").toString(), listInfo.get(0)+" "+listInfo.get(1), 20);
			if(custOptionSelection==true)
			{
				boolean loginButton=this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("withdrawlAmount")).get("loginButton").toString(), 10);
				if(loginButton==true)
				{
					HashMap hmp1= this.sc.acDetails(((Map)this.sh.get("withdrawlAmount")).get("acDetaisls").toString(), 20);
					this.log.info(listInfo.get(0)+" "+listInfo.get(1)+" Before Balance: "+hmp1.get("Balance"));
					boolean withDrawButton=this.sc.boxclick(((Map)this.sh.get("withdrawlAmount")).get("withDrawButton").toString(), 5);
					if(withDrawButton==true)
					{
						for(Object am: this.alint)
						{
							boolean amPick=this.sc.sendKeys(((Map)this.sh.get("withdrawlAmount")).get("amPick").toString(), am.toString(), 5);
							if(amPick==true)
							{
								boolean with=this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("withdrawlAmount")).get("with").toString(), 5);
								if(with==true)
								{
									boolean suc=this.sc.successfull(((Map)this.sh.get("withdrawlAmount")).get("successful").toString(), 20);
									if(suc==true)
									{
										HashMap mp1=this.sc.acDetails(((Map)this.sh.get("withdrawlAmount")).get("map").toString(), 30);
										this.log.info(listInfo.get(0)+" "+listInfo.get(1)+" After Deposit Balance is: "+mp1.get("Balance"));
										if(Integer.parseInt(hmp1.get("Balance").toString()) > Integer.parseInt(mp1.get("Balance").toString()))
										{
											this.log.info("Withdrawl amount updated successfull to : "+listInfo.get(0)+" "+listInfo.get(1));
											System.out.println();
										}
										else
										{
											this.log.error("Withdrawl amount update failed");
										}
									}
									else
									{
										this.log.error("Transastion Failed");
									}
								}
								else
								{
									this.log.error("After amount entered u need not submit withdrawl process button. But it is not clicked");
								}
							}
							else
							{
								this.log.error("Withdrawl amount not picked inthe amount box");
							}
						}
					}
					else
					{
						this.log.error("Withdrawl button not clicked");
					}				
				}
				else
				{
					this.log.error("Login button not cliked");
				}
			}
			else
			{
				this.log.error("Customer Name not Picked from Dropdown");
			}
			Thread.sleep(3000);
			this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("withdrawlAmount")).get("logout").toString(), 5);
		}		
	}
	
	/*This method is deals with the given flow:-
	 * 	ButtonClick click for customer login button.
	 *  pick the user name from added user list.
	 *  after picking the user name click the loginButton
	 *  storing the before balance in one vaariable.
	 *  click on the transaction button.
	 *  calculating the table information based on credit and deposit transactions list.
	 *  vailidating the before balance and transactions balance both are matched or not.
	 *  
	 */ 
	public void transactionsHistory() throws InterruptedException
	{
		for(Object data: this.al)
		{
			ArrayList listInfo=(ArrayList)data;
			boolean custOptionSelection=this.sc.selectCustomer(((Map)this.sh.get("transactionsHistory")).get("custOptionSelection").toString(), listInfo.get(0)+" "+listInfo.get(1), 30);
			if(custOptionSelection==true)
			{
				boolean loginButton=this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("transactionsHistory")).get("loginButton").toString(), 10);
				if(loginButton==true)
				{
					HashMap hmp1= this.sc.acDetails(((Map)this.sh.get("transactionsHistory")).get("acDetails").toString(), 20);
					this.log.info(listInfo.get(0)+" "+listInfo.get(1)+" Before Balance: "+hmp1.get("Balance"));
					boolean transactionButton=this.sc.boxclick(((Map)this.sh.get("transactionsHistory")).get("transactionButton").toString(), 5);
					if(transactionButton==true)
					{
						int cdAmount=this.sc.tableTransaction(((Map)this.sh.get("transactionsHistory")).get("cdAmount").toString());
						if(Integer.parseInt(hmp1.get("Balance").toString())==cdAmount)
						{
							this.log.info("Transancations balance matched with Account Balance");
						}
						else
						{
							this.log.error("Transancations balance not matched with Account Balance");
						}
					}
					else
					{
						this.log.error("Transaction Button not clicked");
					}
				}
				else
				{
					this.log.error("Login button not clicked");
				}
			}
			else
			{
				this.log.error("Customer Name not picked from Dropdown");
			}
			this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("transactionsHistory")).get("logout").toString(), 10);
		}
		
	}
	
	/*This method is deals with the given flow:-
	 * 	ButtonClick click for customer login button.
	 *  pick the user name from added user list.
	 *  after picking the user name click the loginButton.
	 *  click on the transaction button.
	 *  click on reset button inside the transaction button.
	 *  After successfully reset the transaction history click on the login button.	 * 
	 */
	public void deleteTransactions() throws InterruptedException
	{
		for(Object data: this.al)
		{
			ArrayList listInfo=(ArrayList)data;
			boolean custOptionSelection=this.sc.selectCustomer(((Map)this.sh.get("deleteTransactions")).get("custOptionSelection").toString(), listInfo.get(0)+" "+listInfo.get(1), 20);
			if(custOptionSelection==true)
			{
				boolean loginButton=this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("deleteTransactions")).get("loginButton").toString(), 10);
				if(loginButton==true)
				{
					boolean transactionButton=this.sc.boxclick(((Map)this.sh.get("deleteTransactions")).get("transactionButton").toString(), 10);
					if(transactionButton==true)
					{
						boolean resetButton=this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("deleteTransactions")).get("resetButton").toString(), 10);
						if(resetButton==true)
						{
							this.log.info("Transactions History was deleted Successfully ...Test case is Passed");
						}
						else
						{
							this.log.error("Transactions History was not deleted, Test Case is Failedd");
						}
					}
					else
					{
						this.log.error("Transacations button not clicked");
					}
				}
				else
				{
					this.log.error("Login Button not Clicked");
				}
			}
			else
			{
				this.log.error("Customer name not picked from Dropdown");
			}
			this.sc.buttonClickwithcssSelectors(((Map)this.sh.get("deleteTransactions")).get("logout").toString(), 10);
		}
	}
	
	
	
}
