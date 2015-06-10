package com.lab.epam.dao;

import com.lab.epam.dao.imp.MySqlDaoFactory;
import com.lab.epam.dao.imp.MySqlWayResponseDao;
import com.lab.epam.entity.*;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;

import java.util.List;

/**
 * Created by Admin on 10.06.2015.
 */
public class MyTestClass {

    public static void main(String[] args) throws PersistException{
        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        ConnectionPool con = daoFactory.getContext();//ConnectionManager.getConnection();
        GenericDao<PlaceResponse, Integer> dao = daoFactory.getDao(con, PlaceResponse.class);
        GenericDao<Role, Integer> da1 = daoFactory.getDao(con, Role.class);
        GenericDao<Status, Integer> da2 = daoFactory.getDao(con, Status.class);
        GenericDao<User, Integer> da3 = daoFactory.getDao(con, User.class);
        GenericDao<Way, Integer> da4 = daoFactory.getDao(con, Way.class);
        GenericDao<WayResponse, Integer> da5 = daoFactory.getDao(con, WayResponse.class);
       System.out.println(dao.getAll());/**
        System.out.println(da1.getAll());
        Role cat1 = new Role(20,"lo",true);
        System.out.println(da1.create(cat1));
        System.out.println(da1.getAll());

        System.out.println(da3.getAll());
        User cat3 = new User(20,1,"lo","sdv","lol","jh", "jlk", "bjhm", "vjh", 20,true);
        System.out.println(da3.create(cat3));
        System.out.println(da3.getAll());


        PlaceResponse cat = new PlaceResponse(20,"lo",1,20,20,true);
        System.out.println(dao.create(cat));
        System.out.println(dao.getAll());


        System.out.println(da2.getAll());
        Status cat2 = new Status(20,"lo",true);
        System.out.println(da2.create(cat2));
        System.out.println(da2.getAll());


        System.out.println(da4.getAll());
        Way cat4 = new Way(20,1,"lo","jbksb",true);
        System.out.println(da4.create(cat4));
        System.out.println(da4.getAll());
*/
        System.out.println(da5.getAll());
        WayResponse cat5 = new WayResponse(20,"lo",1,1,20,true);
        System.out.println(da5.create(cat5));
        System.out.println(da5.getAll());

    }
}
