package com.lab.epam.dao;

import com.lab.epam.dao.imp.*;
import com.lab.epam.entity.*;
import com.lab.epam.service.ServiceCategory;
import com.lab.epam.service.ServiceUser;

/**
 * Created by Admin on 10.06.2015.
 */
public class MyTestClass {

    public static void main(String[] args) throws PersistException{



        MySqlCategoryDao mc = new MySqlCategoryDao();
        ServiceCategory sc = new ServiceCategory();
        ServiceUser su = new ServiceUser();
       /* MySqlPlaceDao mp = new MySqlPlaceDao();
        MySqlPlaceResponseDao mpr = new MySqlPlaceResponseDao();
        MySqlRoleDao mr = new MySqlRoleDao();
        MySqlStatusDao ms = new MySqlStatusDao();
        MySqlWayResponseDao mwr = new MySqlWayResponseDao();
        MySqlUserDao mu = new MySqlUserDao();
        MySqlWayDao mw = new MySqlWayDao();
        mc.getAll();
        System.out.println(sc.getAll());
        System.out.println(mc.getByPK(1));
        System.out.println(mc.getAllWithoutDeleted());
        mc.delete(new Category(2, "jkhckj", true));
        System.out.println(mp.getAll());
        System.out.println(mpr.getAll());
        System.out.println(mr.getAll());
        System.out.println(ms.getAll());
        System.out.println(mwr.getAll());
        System.out.println(mu.getAll());
        System.out.println(mw.getAll());*/
       // sc.create(new Category("sdvfsd"));
       su.create(new User.Builder("Подгорная","Ольга","niceapple91","olya.podgornayaoi@gmail.com","password","0982713501",2).rating(2).status(1).build());
        System.out.println(su.getByPK(8));
    }
}
