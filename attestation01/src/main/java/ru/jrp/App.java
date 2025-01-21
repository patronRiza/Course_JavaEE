package ru.jrp;

import ru.jrp.entity.Devices;
import ru.jrp.entity.Request;
import ru.jrp.entity.User;
import ru.jrp.repositories.UserRepository;
import ru.jrp.repositories.DeviceRepository;
import ru.jrp.repositories.RequestRepository;
import ru.jrp.repositories.impl.DeviceRepositoryImpl;
import ru.jrp.repositories.impl.UserRepositoryImpl;
import ru.jrp.repositories.impl.RequestRepositoryImpl;

public class App {

    private static final UserRepository USER_REPOSITORY = new UserRepositoryImpl();
    private static final DeviceRepository DEVICE_REPOSITORY = new DeviceRepositoryImpl();
    private static final RequestRepository REQUEST_REPOSITORY = new RequestRepositoryImpl();

    public static void main(String[] args) {

        //SQL requests for table Users
        /*int createUser = USER_REPOSITORY.create(new User("rizvan@mail.com", "password", "89872666371"));
        System.out.println(createUser);*/

        /*var getAllUsers = USER_REPOSITORY.getAll();
        System.out.println(getAllUsers);*/

        /*int updateUser = USER_REPOSITORY.update(new User("rizvan@mail.com", "password1", "89872666371"), 11L);
        System.out.println(updateUser);*/

        //int deleteUser = USER_REPOSITORY.delete(11L);


        //SQL requests for table Devices
        /*int saveDevice = DEVICE_REPOSITORY.save(new Devices(6L, "Проектор", "BENq", "BQ4566845"));
        System.out.println(saveDevice);*/

        /*var getAllDevice = DEVICE_REPOSITORY.getAll();
        System.out.println(getAllDevice);*/

        /*int updateDevice = DEVICE_REPOSITORY.update(new Devices(6L, "Проектор", "BENq", "BQ4566845"), true, 11L);
        System.out.println(updateDevice);*/

        //int deleteDevice = DEVICE_REPOSITORY.delete(11L);


        //SQL requests for table Requests
        /*int saveRequest = REQUEST_REPOSITORY.save(new Request(10L, "Проектор", "issue"));
        System.out.println(saveRequest);*/

        /*var getAllRequest = REQUEST_REPOSITORY.getAll();
        System.out.println(getAllRequest);*/

        /*int updateRequest = REQUEST_REPOSITORY.update("in_progress", 8L , 12L);
        System.out.println(updateRequest);*/

        //int deleteRequest = REQUEST_REPOSITORY.delete(12L);
    }
}
