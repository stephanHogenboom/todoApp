# The to do application

The to do application is simple webapp that allows a user to manage a todo list.

## Setup the database first  

This application uses a postgresql database for persistent data. Therefor a instance of postgresql 
should be available on the server that runs this application. If the service is a window machine
postgresql can be downloaded at https://www.postgresql.org/.  After downloading postresql, open the pgAdmin4 app (which came togheter with postgres) and create a new database named: taskservice.   Then add an new login/ Group roles and name the user: taskservice. Give this user the password: taskservice (defintion tab)  and the privilege to login (privilege tab). You can also add an user with a different name/ password. If you do so, don't forget to also change the values of these fields in the 
/task-service/src/main/resources/application.properties file, before starting the application.   

If the server is a unix/ linux machine it is easier to use docker to run a instance of postgres.
To run postgres locally through docker open a terminal and type the following line(replace the words between <> with the actual values):    $ sudo docker run --name <SERVICE_NAME>-postgres -p 5432:5432 -e POSTGRES_USER=<SERVICE_NAME> -e POSTGRES_PASSWORD=<SERVICE_NAME> -d postgres

For the default setting this would be:  
$ sudo docker run --name <taskservice>-postgres -p 5432:5432 -e POSTGRES_USER=<taskservice> -e POSTGRES_PASSWORD=<taskservice> -d postgres

you can change the POSTGRES_USER and POSTGRES_PASSWORD to a value of your liking. If you do so, don't forget to also change the values of these fields in the /task-service/src/main/resources/application.properties file, before starting the application


## Start the task service:
    $ cd task-service
    $ mvn clean install
    $ java -cp target/my-app-1.0-SNAPSHOT.jar com.hogenboom.taskservice.WebApplication

## Next run the tests:

    $ cd <task-service-system-test>
    $ mvn test  
    

## approaching the gui

You can now look at the front-end by typing localhost:8080/todo in your browser
