package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Petya", "Petrov", (byte) 25);
        System.out.println("User с именем Petya добавлен в таблицу");
        userService.saveUser("Vasya", "Vasilyev", (byte) 37);
        System.out.println("User с именем Vasya добавлен в таблицу");
        userService.saveUser("Ivan", "Ivanov", (byte) 82);
        System.out.println("User с именем Ivan добавлен в таблицу");
        userService.saveUser("Evgeniy", "Evgenyev", (byte) 18);
        System.out.println("User с именем Evgeniy добавлен в таблицу");

        System.out.println();

        List<User> usersList = userService.getAllUsers();
        for (User element : usersList) {
            System.out.println(element);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
