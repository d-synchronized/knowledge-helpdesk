# knowledge-helpdesk

Step 1 : 

Clean install the repository 
mvn clean install

Step 2 -:

Deploy to the server and add and remove and start the server on http://localhost:8080/helpdesk

Step 3 -:

Execute the java goal to raise a ticket.
mvn exec:java 
This will raise a ticket in an in memory database.

Step 4 

Go to browser and toggle for user type developer ; 

Follow the steps from the bpmn file.