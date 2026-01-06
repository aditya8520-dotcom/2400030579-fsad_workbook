package com.fsad.main;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fsad.entity.Product;
import com.fsad.util.HibernateUtil;

public class MainHQLApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Sort by price ascending
        Query<Product> q1 =
            session.createQuery("from Product order by price asc", Product.class);
        q1.list().forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

        // Pagination
        Query<Product> q2 =
            session.createQuery("from Product", Product.class);
        q2.setFirstResult(0);
        q2.setMaxResults(3);
        q2.list().forEach(p -> System.out.println(p.getName()));

        // Aggregate
        Long count =
            session.createQuery("select count(*) from Product", Long.class)
                   .uniqueResult();
        System.out.println("Total Products: " + count);

        session.close();
    }
}
