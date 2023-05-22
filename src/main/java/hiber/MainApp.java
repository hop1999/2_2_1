package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Vasya", "Vasyn", "123@mail.ru");
        User user2 = new User("Vasyok", "Vasykov", "123@yandex.ru");
        User user3 = new User("Vasyliy", "Vasiliev", "123@gmail.com");
        User user4 = new User("Vasyana", "Vasyanov?", "123@rumbler.com");

        Car car1 = new Car("Ferrari", 1);
        Car car2 = new Car("Bugghati", 2);
        Car car3 = new Car("Aventodor", 3);
        Car car4 = new Car("Telejka", 4);

        user1.setmCar(car1);
        user2.setmCar(car2);
        user3.setmCar(car3);
        user4.setmCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        System.out.println("---------------------------------------------------");
        User us = userService.getByModel("Telejka", 4);
        System.out.println("Id = " + us.getId());
        System.out.println("First Name = " + us.getFirstName());
        System.out.println("Last Name = " + us.getLastName());
        System.out.println("Email = " + us.getEmail());

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.mCar());
            System.out.println("1. _____________________________________________");
        }
        context.close();
    }
}