# OrderManagementApp
Assessment for Kühne Nagel internship


	MODEL DESCRIPTION: (this will have its own evaluation in points)
	- An Order is made of N OrderLine and is related to a Customer, and has a date of submission
	- Each OrderLine is made of a Product and a quantity
	- A Product has a name, skuCode, unitPrice
	- A Customer has a registrationCode, fullName, email, telephone

	DAO LAYER:
	- You can implement it with JDBC and write all methods with relevant SQL (basic)
	- You can use Spring + Hibernate										 (more points)
	- You can use Spring Data 												 (most points)

	SERVICE LAYER:
	- Implement OrderService, ProductService, CustomerService class with all business logic.

	API:
	- Provide api for following services:
	- Create customer
	- Create product
	- Create order
	- Search all orders by date

	BENEFICIAL FEATURES (TO GET MORE POINTS)

	- Search Orders by product	(using both JPQL and Criteria/Specification queries)
	- Search Orders by customer (using both JPQL and Criteria/Specification queries)
	- Setup Liquibase for database generation/updates
	- Change quantity of products in an order line
	
	TEST DATA:
	- You can generate test data to insert in DB with Mockaroo tool
	- Unit test will give you some additional points
	
	
	TECH STACK: 
	1) you can use just Java, implement only logic in <<Service>> classes (basic)
	2) You can use Spring and Spring MVC with JDBC or Hibernate		  (more points)
	3) You can use SpringBoot and SpringData (JPA)					  (most points)

