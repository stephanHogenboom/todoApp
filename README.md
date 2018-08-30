# The to do application

The to do application is simple webapp that allows a user to manage a todo list.

This application uses a postgresql database for persistent data. Therefor a instance of postgresql 
should be available on the server that runs this application. If the service is a window machine
postgresql can be downloaded at https://www.postgresql.org/.

If the server is a unix/ linux machine it is easier to use docker to run a instance of postgres.

To run postgres locally through docker open a terminal and type the following line(replace the words between <> with the actual values) :  
sudo docker run --name <SERVICE_NAME>-postgres -p 5432:5432 -e POSTGRES_USER=<SERVICE_NAME> -e POSTGRES_PASSWORD=<SERVICE_NAME> -d postgres

For the default setting this would be:  
sudo docker run --name <taskservice>-postgres -p 5432:5432 -e POSTGRES_USER=<taskservice> -e POSTGRES_PASSWORD=<taskservice> -d postgres


## Start the loading service:

    [$ sudo mkdir /var/log/loading-service]
    $ cd task-service
    $ mvn clean install
    $ java -cp target/my-app-1.0-SNAPSHOT.jar com.hogenboom.taskservice.WebApplication

## Next run the tests:

    $ cd <task-service-system-test>
    $ mvn test
    