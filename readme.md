<h1>REST application Hotel booking</h1>

<h2>Technical requirements</h2>
With capabilities (each represented by separate endpoint):
<ol>
<li>View list of available rooms (room have a number, category, price, additional options like breakfast, cleaning with additional cost) for specified dates.</li>
<li>View rooms filtered by category.</li>
<li>Create user.</li>
<li>User can book the room for specified days.</li>
<li>User can view his booking.</li>
<li>User can get the total price of the booking (room for dates period + cost of additional options).</li>
<li>View all bookings for the hotel.</li>
</ol>
<h2>Instructions for launching</h2>
<ol>
<li>clone the repository to your pc</li>
<li>open the project in your IDEA</li>
<li>launch the application by run WebApplication.main</li>
<li>open the browser in the page http: // localhost: 8080 / h2web /, connect to the database with the next params:
<ul>
<li>Driver Class: org.h2.Driver</li>
<li>JDBC URL: jdbc:h2:~/app_db</li>
<li>User Name: sa</li>
</ul>
</li>
<li>copy the code from the file booking.sql from folder resources and run the script to add tables and data</li>
<li>you can start checking the health of the application through any program that imitates the client, for example Postman</li>
</ol>
<h2>Explanation for the REST API</h2>
REST API has 3 controllers, like UserController, RoomController and BookingController
<h4>UserController</h4>
<ol>
<li>create user:  
<ul>
<li>url: http://localhost:8080/users</li>
<li>method: post</li>
<li>expected params: json string that contains next fields: username, email, password. Forexample
 {"username":"root","password":"111111","email":"root@gmail.com"}
</li>
<li>return: json string that contains data for created user</li>
</ul>
</li>
</ol>
<h4>RoomController</h4>
<ol>
<li>get rooms:  
<ul>
<li>url: http://localhost:8080/rooms</li>
<li>method: get</li>
<li>return: json string that contains array with data for all existed rooms</li>
</ul>
</li>
<li>get rooms by category:  
<ul>
<li>url: http://localhost:8080/rooms/category/{id}</li>
<li>method: get</li>
<li>return: json string that contains array with data for all existed rooms with specified category</li>
</ul>
</li>
</ol>
<h4>BookingController</h4>
<ol>
<li>get bookings:  
<ul>
<li>url: http://localhost:8080/bookings</li>
<li>method: get</li>
<li>return: json string that contains array with data for all existed bookings</li>
</ul>
</li>
<li>get booking by id:  
<ul>
<li>url: http://localhost:8080/bookings/booking/{id}</li>
<li>method: get</li>
<li>return: json string that contains data for booking with specified id</li>
</ul>
</li>
<li>create booking:  
<ul>
<li>url: http://localhost:8080/bookings</li>
<li>method: post</li>
<li>expected params: json string that contains next fields: startDate, endDate, user, room (startDate, endDate have timesatamp format). Forexample
 {"startDate":1520164926937,"endDate":"1520165926937","user":{"id":1},"room":{"id":1}}
</li>
<li>return: json string that contains data for created booking</li>
</ul>
</li>
<li>get bookings by user id:  
<ul>
<li>url: http://localhost:8080/bookings/user/{id}</li>
<li>method: get</li>
<li>return: json string that contains data for booking for specified user id</li>
</ul>
</li>
<li>get total price of bookings by user id:  
<ul>
<li>url: http://localhost:8080/bookings/user/{id}/price</li>
<li>method: get</li>
<li>return: json string that contains double value of total price of the all bookings for concrete user</li>
</ul>
</li>
</ol>