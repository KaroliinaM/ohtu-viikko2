/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import ohtu.data_access.InMemoryUserDao;
import ohtu.data_access.UserDao;
import ohtu.io.ConsoleIO;
import ohtu.io.IO;
import ohtu.services.AuthenticationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author kape
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

        App application = ctx.getBean(App.class);
        application.run();
//        UserDao dao = new InMemoryUserDao();
//        IO io = new ConsoleIO();
//        AuthenticationService auth = new AuthenticationService(dao);
//        new App(io, auth).run();
    }

}
