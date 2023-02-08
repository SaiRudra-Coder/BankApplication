package projectBank;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumControls 
{
	public WebDriver web;
	public Logger log;
	public Map sh;
	
	/*This is the seleniumControls class constructor which is hadles the given below actions
	 * 	Setup the chrome web browser.
	 * 	passing the Logger log class object inthe parameters and initialize the log
	 * create object for chromeoptios.
	 * set headless as true to disable visibility of the browser
	 * pass that chrome options reference variable to chrome driver constructor
	 * 
	 */
	public SeleniumControls(String url, Logger log, Map sh)
	{
		this.log=log;
		this.web = this.initiateDriver();
		this.web.get(url);
		this.log.info(url);
		
		this.sh=sh;
		this.log.info("Driver is navigating to URL");
		this.web.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(this.sh.get("implicitWait").toString())));
		
		
			
		this.log=Logger.getLogger(SeleniumControls.class.getName());
	}
	
	public WebDriver initiateDriver()
	{
		try
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.setHeadless(true);
			web= new ChromeDriver(option);
			this.log.info("Driver is Started");
			web.manage().window().maximize();
			
		}
		catch (NoSuchFrameException e) 
		{
			this.log.error("Windows/WebDriver is not updated");
			System.exit(0);
		}
		return web;
	}
	
	/*This method handels the all buttons in the bank site	 * 	 
	 */
	public boolean buttonClick(String xpath, int wait_sec) throws InterruptedException
	{
		WebElement ele=this.explicientWaits(xpath, false, wait_sec);
		if(ele!=null)
		{
			ele.click();
			return true;
		}
		return false;		
	}
	/*This method also hadels  the all box buttons in the xyz bank service	 * 
	 */
	public boolean boxclick(String path, int waits) throws InterruptedException
	{
		WebElement ele=this.explicientWaits(path, false, waits);
		if(ele!=null)
		{
			ele.click();
			return true;
		}
		return false;
	}
	
	/*This method handels the button clicks only for cssSelectors	 * 
	 */
	public boolean buttonClickwithcssSelectors(String cssSelector, int waits)
	{	
		WebElement ele=this.explicientWaits(cssSelector, true, waits);
		if(ele!=null)
		{
			ele.click();
			return true;
		}
		return false;
	}
	
	// This is for passing the sendkeys into the input box
	public  boolean sendKeys(String xpath, String sendkeys, int wait_sec) throws InterruptedException
	{	
		WebElement ele=this.explicientWaits(xpath, false, wait_sec);
		try 
		{
			if(ele!=null)
			{
				ele.sendKeys(sendkeys);
				return true;
			}
		}
		catch (ElementNotInteractableException e1)
		{
			this.log.error("The element is present on the web page, but the element cannot be selected");			
		}
		return false;
	}
	
	//All explicientwaits hadeled  by this method only with exception	 handlers
	public WebElement explicientWaits(String xpath, boolean css, int wait_sec)
	{
		try 
		{
			WebDriverWait wt= new WebDriverWait(this.web, Duration.ofSeconds(wait_sec));
			if(css==false)
			{
				wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				WebElement ele=this.web.findElement(By.xpath(xpath));
				return ele;
			}
			else 
			{
				wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpath)));
				WebElement ele=this.web.findElement(By.cssSelector(xpath));
				return ele;
			}
			
		} 
		catch (InvalidSelectorException  e) 
		{
			this.log.error("Selecting Incorrect Selector");
			return null;
		}
		catch (NoSuchElementException e1)
		{
			this.log.error("The locators are unable to find or access elements on the web page");
			return null;
		}
		catch (TimeoutException e2) 
		{
			this.log.error("The command did not execute or complete within wait time");
			return null;
		}		
	}
	
	//This method for clear the sendkeys from inputbox for every new sedkeys entered
	public boolean clear(String xpath, int wait_sec)
	{
		WebElement ele = this.explicientWaits(xpath,false, wait_sec);
		if (ele != null)
		{
			ele.clear();
			return true;
		}
		return false;
	}
	
	//vailidating the tables data and added list data both are same or not 
	// vailidation based on user first name, last name and postcode
	public boolean vailidation(String path1, boolean searchVail, String FirstName, String LastName, String postCode)
	{
		boolean status=false;
		try 
		{
			for(WebElement row: this.web.findElements(By.xpath(path1)))
			{
				List<WebElement> cols=row.findElements(By.tagName("td"));
				this.log.info(cols.get(0).getText());
				if(searchVail==false)
				{
					if(cols.get(0).getText().equalsIgnoreCase(FirstName))
					{
						status= true;
					}
					else
					{
						status=false;
					}
				}
				else if(searchVail==true)
				{
					if(cols.get(0).getText().equalsIgnoreCase(FirstName) && cols.get(1).getText().equalsIgnoreCase(LastName) && cols.get(2).getText().equalsIgnoreCase(postCode))
					{
						status = true;
					}
					else
					{
						status=false;
					}
				}			
			}
			return status;
		} 
		catch (InvalidSelectorException  e) 
		{
			this.log.error("Selecting Incorrect Selector");
			return false;
		}
		catch (NoSuchElementException e1)
		{
			this.log.error("The locators are unable to find or access elements on the web page");
			return false;
		}		
	}
	
	//This method handels the popup alerts messages and printing the alert msg and close the popup
	public String alerts(String username) throws InterruptedException, NoAlertPresentException
	{				
		try 
		{
			Alert al=this.web.switchTo().alert();		
			Thread.sleep(1000);
			this.log.info(username+" - "+al.getText());
			al.accept();
			return al.getText().split(": ")[1];
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	//This method  for user selection from added list in Bankmanager Login
	public boolean selectCustomer(String selectXpath, String userSelect, int waits)
	{		
		WebElement selection=this.explicientWaits(selectXpath,false,  waits);
		if(selection!=null)
		{
			Select sel= new Select(selection);
			sel.selectByVisibleText(userSelect);
			return true;
		}
		return false;			
	}
	
	/*
	 * This method handles currency selection process in BankMangerLogin
	 *
	 */
	public boolean selectCurrency(String selectXpath, int waits)
	{
		WebElement selection=this.explicientWaits(selectXpath,false, waits);
		if(selection!=null)
		{
			Select sel= new Select(selection);
			sel.selectByVisibleText("Rupee");
			return true;
		}
		return false;
	}
	
	/*
	 * This Method handle for closing the webDriver after all test cases completed 
	 */
	public boolean close()
	{
		try 
		{
			this.web.close();
			return true;
		} 
		catch (Exception e) 
		{
			this.log.error("Browser not closed");
			return false;
		}		
	}
	
	/*
	 * This method hadles:
	 *  vailidating the selecting user name and welcome note name after login
	 *  verifying the both names are same or not in CustomerLogin
	 *  verifiying account num correctly or not 
	 */
	public boolean welcomeMsgVailidation(String cssSelector, String cssSelector1, String cssSelector3, HashMap usrInfo, int waits)
	{
		boolean welcom_stats=false;
		boolean acc_status = false;
		WebElement msg=this.explicientWaits(cssSelector,true, waits);
		String welcome_name = null;
		String account_num = null;
		boolean return_status = false;
		if(msg!=null)
		{
			this.log.info("Customer Name: "+msg.getText());
			welcome_name=msg.getText().toString();
			welcom_stats = true;
		}
		WebElement num=this.explicientWaits(cssSelector1,true, waits);
		if(num != null)
		{
			this.log.info("Customer Account Number: "+num.getText());
			account_num= num.getText().toString();
			acc_status = true;
		}
		if(welcom_stats == true && acc_status == true)
		{
			String acn = usrInfo.get(welcome_name).toString();
			return_status = account_num.equalsIgnoreCase(acn);
		}
		return return_status;
		
	}
	
	/*
	 * This method is hadles with Customer Account numbers 
	 * separate the Customer name and account number with help of split method 
	 */
	public HashMap acDetails(String path, int waits)
	{		
		WebElement values=this.explicientWaits(path,true, waits);
		if(values!=null)
		{
			String[] info=values.getText().toString().split(" , ");//acc num: 124
			HashMap hm = new HashMap();
			for(String da: info)
			{
				String[] data= da.split(" : ");
				// data[0] data[1]
				try {
					hm.put(data[0].strip(), Integer.parseInt(data[1].strip()));
					}
				catch(Exception e)
					{
						hm.put(data[0].strip(), data[1].strip());
					}
				}
			return hm;
		}
		return null;
	}		
	
	//This method handels verify the whether the customer successfully added or not 
	public boolean successfull(String path, int waits)
	{
		WebElement msg=this.explicientWaits(path, true, waits);
		if(msg!=null)
		{
			if(msg.getText().equalsIgnoreCase("Successful"))
			{
				return true;
			}
			return true;
		}
		return false;
		
	}
	
	//This method is handels the calculating the table information based on credit and deposit transactions list.
	//  vailidating the before balance and transactions balance both are matched or not.
	public int tableTransaction(String xpath)
	{
		int table_bal = 0;
		for(WebElement row: this.web.findElements(By.xpath(xpath)))
		{
			List<WebElement> cols=row.findElements(By.tagName("td"));
			if(cols.get(2).getText().equalsIgnoreCase("credit"))
			{
				table_bal = table_bal + Integer.parseInt(cols.get(1).getText().toString());
			}
			else if(cols.get(2).getText().equalsIgnoreCase("debit"))
			{
				table_bal = table_bal - Integer.parseInt(cols.get(1).getText().toString());
			}			
		}
		return table_bal;
	}
	
}
