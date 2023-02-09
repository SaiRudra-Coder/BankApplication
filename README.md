# BankApplication

URL for Dummy Bank Application - [Navigate to site](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login)

### TestCases - Bank Manager Login

| S.No | Test Case Name | Steps |
-------|----------------|-------|
| 1 | Add Customer | 1. Click on Bank Manager Login<br/>2. Select Add Customer<br/>3. Enter some dummy details<br/>4. Click on Add Customer<br/>5. Verify whether the customer added successfully or not<br/>6. Display the customer Id to output console<br/>7. Close the alert box<br/>8. Repeat (3-7) steps for another 5 different customers |
| 2 | Validate Customers | 1. Click on customers button<br/>2. In table check the details of added users (from above test case) exists or not<br/>3. If all the users exist in the table, check the search bar is working on not by searching different customer details which you have added<br/>4. If search bar is working fine test case pass otherwise failed |
| 3 | Open Account | 1. Click on open account<br/>2. Select the one customer from your added list<br/>3. Select the currency type<br/>4. click on process button<br/>5. Validate whether account created or not<br/>6. If account created display the account number to the output console<br/>7. close the popup window<br/>8. Repeat the same for other users |

### TestCases - Customer Login

| S.No | Test Case Name | Steps |
-------|----------------|-------|
| 1 | User Login Validation | 1. Select Frist User Name from your added user list<br/>2. Click on Login Button<br/>3. After clicking login select Entered username present in the Welcome note or not<br/>4. Display the Account Number, Balance and Currency Type to output console<br/>5. Validate username and account number from banking details<br/>6. Repeat the same for other users |
| 2 | Validate Deposit Feature | 1. Store Balance in a variable<br/>2. Click on Deposit button<br/>3. Enter the amount in the input box<br/>4. click on deposit button at the bottom<br/>5. check whether deposit successful or not<br/>6. compare the previous balance and current balance whether it is changed or not<br/>7. Repeat (1-6) steps 4 times with different amounts and validate the balance amounts<br/>8. Repeat the same for different users |
| 3 | Validate Withdraw Feature | 1. First Store available Balance<br/>2. Click on Withdrawl button<br/>2. Enter the amount in the input box<br/>3. click on withdraw button at the bottom<br/>4. check whether withdrawl successful or not<br/>5. compare the previous balance and current balance whether it is changed or not<br/>6. Repeat the same for other users |
| 4 | Verify Transactions | 1. Verify all the transcations (amounts) you made are available or not<br/>2. calculate the credit total amount<br/>3. calculate the debit total amount<br/>4. calculate the difference between credit and debit amounts and compare the final result with available balance<br/>5. Display the transactions to output console |
| 5 | Validate Delete Transactions | 1. Click on Reset Button inside transactions<br/>2. check the transactions whethere they are deleted or not<br/>3. If deleted test case pass otherwise failed<br/>4. Repeat the same for all users |
| 6 | User Logout Validation | 1. After completion of all the above testcases<br/>2. Click on Logout Button<br/>3. It has to redirect to User selection Page<br/>4. If redirected test case pass other wise failed |

