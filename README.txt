OVE
==============================
This web application is developed with the main objective to facilitate the administrative work for the project managers of the Chalmers Läxhjälpsprojekt (CLP). It is also possible for tutors to have/manage accounts and see their monthly salary, which varies depending on the amount of hours worked.

With this application, it is possible to have an overiew of the different schools with which the CLP collaborate, and the various contact persons connected to each and every one of these schools. Administrators can add, remove, edit and see the tutoring sessions which are to take place, and which tutors will be working at them. 

See also PossibleExpansions.txt for features which could be added to the application.

==============================
To run the project you have to

1. Create a new project in netbeans and chose to create a new WebApplication with existing pom.

2. Create a database with 
	Database name:	OVE_model
	User name:		app
	Password:		app
	
3. Add a new mail session to JavaMail Sessions. Like this:
i. Right click on your GlassFish Server (3.1.2) and then chose "View Domain Admin Console".
ii. Under Resources chose JavaMail Sessions
iii. Follow this: http://javaeenotes.blogspot.se/2010/04/using-javamail-api-with-glassfish-and.html?m=1

4. To run the project as an admin you have press the radiobutton "admin"
when creating a new account. 