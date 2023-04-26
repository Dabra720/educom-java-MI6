package nu.educom.MI6;

import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseRepository {
    protected Connection conn;
    protected Session session = HibernateUtil.openSession();

    public void insertLoginAttempt(LoginAttempt login){
        System.out.println("Trying to insert login attempt..");
        session.beginTransaction();
        session.persist(login);
        session.getTransaction().commit();
//
    }

    public Agent readAgentByServiceNumber(int serviceNr){
        Session session = HibernateUtil.openSession();

        Agent agent = session.createQuery("from Agent WHERE serviceNumber = :serviceNr", Agent.class)
                .setParameter("serviceNr", serviceNr)
                .uniqueResultOptional().orElse(null);
        return agent;
    }

    public List readFailedLoginAttempts(Agent agent) {
        List<LoginAttempt> loginAttempts = null;
        try {
        int maxId = session.createQuery("FROM LoginAttempt WHERE service_nr=:servnr AND success=true ORDER BY id DESC", LoginAttempt.class)
                .setParameter("servnr", agent.getServiceNumber())
                .setMaxResults(1)
                .getSingleResult()
                .getId();


            loginAttempts = session.createQuery("FROM LoginAttempt WHERE service_nr=:servnr AND id>:id", LoginAttempt.class)
                    .setParameter("servnr", agent.getServiceNumber())
                    .setParameter("id", maxId)
                    .getResultList()
            ;
        }catch(NoResultException e){
            System.out.println(e);
        }

        if(loginAttempts == null){
            loginAttempts = new ArrayList<>();
        }

        // TIMEDIFF() bestaat niet in HQL, dus voor nu nog even nativequery
//        loginAttempts = session.createNativeQuery(
//                "select l.id, l.service_nr, l.timestamp, l.success from login_attempts l " +
//                "left join (" +
//                "select service_nr, timestamp as last_success " +
//                "from login_attempts " +
//                "where success=1 AND service_nr=? " +
//                "order by id desc " +
//                "limit 1" +
//                ") max on max.service_nr=l.service_nr " +
//                "where timediff(l.timestamp, max.last_success) > 0 AND l.service_nr=?", LoginAttempt.class)
//                .setParameter(1, agent.getServiceNumber())
//                .setParameter(2, agent.getServiceNumber())
//                .getResultList();
        return loginAttempts;
    }

}
