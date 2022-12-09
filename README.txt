Hi,

I tried to keep it simple, I didn’t put all the tasks requirements because Im not a full stack or front end developer but I tried to
Do all the backend task, due time I couldn’t. But the code is good to see how I do my things.

I used java 17 and springboot in order to keep the code simple and easy to read, I added some unitary tests(not for all situations, just for you see how I write my tests cases) and a Dockerfile for dockerizer the app.

The code is using spring jpa to keep the interaction with database easy and it uses H2 to save the data in memory. You can see the properties of the connection in application.properties file and you can access the h2-console by this link: http://localhost:8080/h2-console/

We have 5 endpoints in this app. 3 for the event and 2 for the tickets.

Event endpoints: 

GET 
localhost:8080/event/
This endpoint will return all events registered with its respective IDs.

POST
localhost:8080/event/
Body: 
{
    "name":"event1",
    "date": "2022-01-01",
    "initialNumberOfTickets": 100
}
This endpoint will create and event.

PUT
localhost:8080/event/{eventId}/add-ticket/{ticketQuantity}
This endpoint will add new tickets to an event.

All the endpoints will return the http code requested in the task and if necessary, a response body.

Ticket endpoints:

GET
localhost:8080/ticket/redeem/{ticketID}
This endpoint checks if the ticket is redeemed.

POST
localhost:8080/ticket/redeem/{ticketID}
This endpoint redeem the ticket.


