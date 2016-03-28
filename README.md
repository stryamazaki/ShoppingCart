# Shopping Cart
This project is to create a Java application which does a back-end part of shopping cart.

## Already Implemented APIs
1. read tours from the data storage
2. read promotion rules from the data storage
3. read a tour for a given tour ID from the data storage
4. add a tour to a shopping cart at a time
5. calculate total price (promotion rules to be applied)

## Data Structure
1. Tour  
-ID(Text)  
-Price(Number)  
-Promotion Rule ID(Number)  

2. Promotion Rule  
-ID(Number)  
-Name(Text)  
-Rule Attribute 1(Text)  
-Rule Attribute 2(Text)  

## Current Promotion Rules
A promotion rule can be applied to different tours. However, a tour can only use 1 promotion rule.

1. Bulk Discount Rule - Once a specified number (Rule Attribute 2) of the applied tours are purchased, a specified amount (Rule Attribute 1) of the discount will be applied to every purchased tour.

2. Buy Another Get Free Rule - For every purchase of a specified tour (Rule Attribute 1), one purchase of the applied tour will be free. 

3. Buy Many Get Free Rule - Where a specified number (Rule Attribute 1) of the applied tours are purchased, only the price of a specified number (Rule Attribute 2) of the applied tours will be charged. 

## Architecture
1. Presentation Layer (Business Delegate only)  
-ShoppingCartBusinessDelegate.java

2. Business Logic Layer  
-ShoppingCartServiceIF.java  
-PromotionRuleCalculatorIF.java  
-ShoppingCartService.java  
-BulkDiscountRuleCalculator.java  
-BuyAnotherGetFreeRuleCalculator.java  
-BuyManyGetFreeRuleCalculator.java  

3. Data Access Layer  
-TourDaoIF.java  
-PromotionRuleDaoIF.java  
-InFileGenericDao.java  
-InFileTourDao.java  
-InFilePromotionRuleDao.java  
-PropertyFilesHelper.java

4. Factories  
-AbstractServiceFactory.java  
-AbstractDaoFactory.java  
-AbstractPromotionsRuleCalculatorFactory.java  
-ServiceFactory.java  
-DaoFactory.java  
-PromotionsRuleCalculatorFactory.java

5. Entities  
-Tour.java  
-PromotionRule.java

6. Data Storage  
-tours.properties  
-promotionRules.properties

## Design Patterns
1. Business Delegate
2. DAO
3. Template
4. Factory method
5. Strategy
6. Singleton
7. Transfer Object

## TODOs
1. Validation checks to be added and the validation errors to be notified to user
2. Unexpected errors to be logged in a log file and to be notified to IT support and also to be advised to user, that they need IT support.
3. Appropriate error messages will replace wherever labelled APPROPREATE MESSAGE

## Enhancement
1. More meta data to be added to Tour entity (i.e. Tour details including media files, Maximum number of purchase per day, Operating days, Non-operating dates and more)
2. A new entity Tour Item to be created to record purchases
3. More APIs to be implemented to add a purchasing tours functionality with transaction management
4. All APIs to be published as Webservices with appropriate security
5. Performance tuning to be done with applying concurrency and so on

## Installation
NOTE: The application only compiles and runs with JDK 7 onwards  
1. Import it into your Maven capable IDE  
2. Built it

## Usage
Use the APIs in ShoppingCartBusinessDelegate class (Please refer to ShoppingCartBusinessDelegateTest.java) 

## Contributing

To be edited

## History

To be edited

## Credits

To be edited

## License

N/A
