package org.example;

import org.example.configuration.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean(Communication.class);

        String cookie = communication.getUsersHeaders();
        User user1 = new User((long)3,"James","Brown", (byte)23);
        User user2 = new User((long)3, "Thomas",  "Shelby", (byte)23);

        communication.saveUser(user1, cookie);

        communication.updateUser(user2, cookie);

        communication.deleteUser(cookie);













    }
}
