# BankApplication

Xyz bank is a Web based application which provides the banking services to the customers for saving the amount, Credit the amount,debit the amount, and Transactions history.

The bank application link mentioned below. If you click on the given link it will navigate you to the xyz bank application.

URL for XYZ Bank Application - [Navigate to site](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login)

This application provides the two different logins one for Bank Manager Operations and another for Customers.

### <ins>Bank Manager Operations</ins>
- This option provides three features ADD CUSTOMER, OPEN ACCOOUNT, and CUSTOMERS
- ADD CUSTOMER: 
  - This feature provides the service to add new Customers by adding the user details in the input box such as First Name,Last Name and PostCode
  - After scuccessfully entered user datails then it shows an alert message which displays **_customer added successfully with customer id xxxx_**
- OPEN ACCOUNT: 
  - This feature Opens account for new user which is already added in the customer added list.
  - Select the Customer name from added list and select type of currency then click the process button.
  - Then It shows an alert message which displays **_Account created successfully with account Number :xxxx_**
- CUSTOMERS:
  - This feature displays the list of the added customers details
  - Here validating the Customer whether added or not in the list based on the Search bar.

Below are testcases related to Bank Manager Operations

### TestCases - Bank Manager Login

| S.No | Test Case Name | Steps |
-------|----------------|-------|
| 1 | Add Customer | 1. Click on Bank Manager Login<br/>2. Select Add Customer<br/>3. Enter some dummy details<br/>4. Click on Add Customer<br/>5. Verify whether the customer added successfully or not<br/>6. Display the customer Id to output console<br/>7. Close the alert box<br/>8. Repeat (3-7) steps for another 5 different customers |
| 2 | Validate Customers | 1. Click on customers button<br/>2. In table check the details of added users (from above test case) exists or not<br/>3. If all the users exist in the table, check the search bar is working on not by searching different customer details which you have added<br/>4. If search bar is working fine test case pass otherwise failed |
| 3 | Open Account | 1. Click on open account<br/>2. Select the one customer from your added list<br/>3. Select the currency type<br/>4. click on process button<br/>5. Validate whether account created or not<br/>6. If account created display the account number to the output console<br/>7. close the popup window<br/>8. Repeat the same for other users |


### <ins>Customer Operations</ins>
1. After successfully creating an account then click on the Customer Login Operation
2. Select the user name from drop down which is already existed in the customer added list (from Bank Manager Operation performed earlier) and login
3. After login this operation it displays the welcome note along with user name and user Accounnt number.

- This Customer option provides thee features DEPOSIT, WITHDAWL,and TRANSACTIONS.
- DEPOSIT
  - This feature provides depositing the amount into User account
  - User can add the amount in input box then click on the deposit button
  - After click on the button it displays the deposit successful message and added balance amount.
- WITHDAWL
  - This feature provides withdraw amount from the User account
  - User can withdraw the amount by specifiying amount in input box then click on the withdrawl button
  - After click on the button it displays the withdrawl successful message and deduct balance amount.
- TRANSACTIONS
  - This feature provides list of transactions done by User account
  - User can view the transaction based on date selected filter

Below are testcases related to Customer Operations

### TestCases - Customer Login

| S.No | Test Case Name | Steps |
-------|----------------|-------|
| 1 | User Login Validation | 1. Select Frist User Name from your added user list<br/>2. Click on Login Button<br/>3. After clicking login select Entered username present in the Welcome note or not<br/>4. Display the Account Number, Balance and Currency Type to output console<br/>5. Validate username and account number from banking details<br/>6. Repeat the same for other users |
| 2 | Validate Deposit Feature | 1. Store Balance in a variable<br/>2. Click on Deposit button<br/>3. Enter the amount in the input box<br/>4. click on deposit button at the bottom<br/>5. check whether deposit successful or not<br/>6. compare the previous balance and current balance whether it is changed or not<br/>7. Repeat (1-6) steps 4 times with different amounts and validate the balance amounts<br/>8. Repeat the same for different users |
| 3 | Validate Withdraw Feature | 1. First Store available Balance<br/>2. Click on Withdrawl button<br/>2. Enter the amount in the input box<br/>3. click on withdraw button at the bottom<br/>4. check whether withdrawl successful or not<br/>5. compare the previous balance and current balance whether it is changed or not<br/>6. Repeat the same for other users |
| 4 | Verify Transactions | 1. Verify all the transcations (amounts) you made are available or not<br/>2. calculate the credit total amount<br/>3. calculate the debit total amount<br/>4. calculate the difference between credit and debit amounts and compare the final result with available balance<br/>5. Display the transactions to output console |
| 5 | Validate Delete Transactions | 1. Click on Reset Button inside transactions<br/>2. check the transactions whethere they are deleted or not<br/>3. If deleted test case pass otherwise failed<br/>4. Repeat the same for all users |
| 6 | User Logout Validation | 1. After completion of all the above testcases<br/>2. Click on Logout Button<br/>3. It has to redirect to User selection Page<br/>4. If redirected test case pass other wise failed |

