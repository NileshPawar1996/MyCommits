# Advanced-Java projects/assignments by Nilesh Ghule

## Music App MVC
* Simple music application implemented in Spring Web MVC using Thymeleaf Views.
* Major features
	* Spring Web MVC
	* State Mangaement (HttpSession & Session scoped beans)
	* Thymeleaf views
	* MySQL / H2 database
	* File upload on Mongo Atlas
	* Deployed in herokuapp (https://boot-gaana-app.herokuapp.com/)

## Music App REST API
* Simple music application REST APIs implemented in Spring Boot.
* Major features
	* Spring Boot REST APIs
		* User registration & authentication
		* User validation & exception handling (registration)
		* Standard response generation using ResponseEntity (status = success/fail/error)
		* Explore music - Albums, Artists and Songs
		* Add music - Artist, Album and Song (with thumbnail and song mp3 upload on server disk)
		* Download thumbnail and song 
		* React user portal build is deployed in static folder.
			* http://localhost:8080/
	* Pending/Issues
		* Security/JWT features

## Music App Client (React)
* React frontend application that invokes Spring Boot REST APIs.
* Admin Panel
	* Upload album, artist and songs (with thumbnail & mp3 files).
* User Portal
	* Explore Albums, Artists and Songs
	* Play song
* Pending/Issues
	* Song not pausing.
	* Authentication/Security not implemented
