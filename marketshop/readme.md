# 'Danco MarketShop'  : checkout your goods with the Cashier simulator ! 



 Overview : The application checks out a list of goods by calculating the total price. Some discounts might be applied to the base price according to a list of rules. 

Actors and Use Cases:

Actor 1: User 

Use cases:
 - drawing up a list of items with prices and discounts and create file to be loaded in the system.
 - adding up new items to be checked out by typing one or more item names separated by space ' ' (Ex. A B C D)
 - viewing the printed receipts of the final amount to be paied, with all the detailed transactions.
 - keep on adding new items or exit by typing char 'n'.
 
Actor 2: Caschier system:

use cases:
 - accepting a PricingRules.csv file for loading the list of goods with price and discount (if provided)
 - retrieving items' price from PricingRules.csv
 - applying discount based on rules.
 - calculating the total price 
 
 

###1. configuration - Set up Pricing rules for each items to be checked out

- Use the PricingRules.csv file in the default folder $Project/in
- Or add a new custom file with a row for each item according to the following structure: 
ItemName, Price, Discounted Price rule 

Example : MioItem, 0.5, 3 for 1.3

###2. Run

Default configuration java -jar marketshop-0.0.1-SNAPSHOT.jar

Custom configuration (Specify the path of the PricingRules to be imported and a rows separator)
 java -jar marketshop-0.0.1-SNAPSHOT.jar my/folder/path/MyPricingRulesFile.csv
 
 NB: the folder is to be in the same root of the executable.
 
 A list of items has to be typed in input :
 
EX: 

	A B C D A A 
  
  
NB : Because of a moody Employer, the price rules are bound to change so often. Therefore, to deal with a continual shifting of the price rules the cashier approach calculates ricorsively the total amount of the items by reloading each time the PricingRules. 

	A B C D A A 
	C C
	A
	D D A A
	
	n

###3. Testing (Automated mode) : Run Unit Tests and check Code Coverage
  For running tests use maven  :

	maven clean test


###4. Remaining Tasks :  

- Integration Tests

- UI 







