/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestranetworks.testHibernate;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactoryUtil {


    private static org.hibernate.SessionFactory sessionFactory;


    private HibernateFactoryUtil()
    {
    }

    static
    {
        File file = new File("src/main/resources/hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(file).buildSessionFactory();
    }

    public static SessionFactory getInstance()
    {
        return sessionFactory;
    }

    public static void reset()
    {
        sessionFactory.close();
        File file = new File("hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(file).buildSessionFactory();
    }

    public Session openSession()
    {
        return sessionFactory.openSession();
    }


    public Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public static void close()
    {
        if (sessionFactory != null)
        {
            sessionFactory.close();
        }
        sessionFactory = null;
    }
}
