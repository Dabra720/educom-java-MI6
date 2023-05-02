package nu.educom.MI6;

import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {
//    protected Connection conn;
    protected Session session = HibernateUtil.openSession();

    public void insertLoginAttempt(LoginAttempt login){
//        try{
//            session = HibernateUtil.openSession();
            System.out.println("Trying to insert login attempt..");
            session.beginTransaction();
            session.persist(login);
            session.getTransaction().commit();
//        }finally{
//            session.close();
//        }

//
    }

    public Agent readAgentByServiceNumber(int serviceNr){
//        try{
//            session = HibernateUtil.openSession();
            Agent agent = session.createQuery("from Agent WHERE serviceNumber = :serviceNr", Agent.class)
                    .setParameter("serviceNr", serviceNr)
                    .uniqueResultOptional().orElse(null);

//        }finally{
//            session.close();
//        }
        return agent;
    }

    public List readFailedLoginAttempts(Agent agent) {
        List<LoginAttempt> loginAttempts;
        LocalDateTime lastLoginDateTime;
        try {
//            session = HibernateUtil.openSession();
            lastLoginDateTime = session.createQuery("FROM LoginAttempt WHERE service_nr=:servnr AND success=true ORDER BY id DESC", LoginAttempt.class)
                    .setParameter("servnr", agent.getServiceNumber())
                    .setMaxResults(1)
                    .getSingleResult()
                    .getLoginDateTime();

        } catch (NoResultException e) {
            System.out.println("Geen succesvolle login attempts gevonden");
            lastLoginDateTime = LocalDateTime.MIN;
        }

        try {
            loginAttempts = session.createQuery("FROM LoginAttempt WHERE service_nr=:servnr AND login_time>:since", LoginAttempt.class)
                    .setParameter("servnr", agent.getServiceNumber())
                    .setParameter("since", lastLoginDateTime)
                    .getResultList();
        } catch (NoResultException e) {
            System.out.println("Helemaal geen attempts gevonden");
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


    public List<LoginAttempt> readFailedLoginAttempts2(Agent agent) {

        List<LoginAttempt> failedLoginAttempts;
        LocalDateTime since;
        // Find the latest successful login attempt
        LoginAttempt lastSuccessAttempt = session.createQuery("FROM LoginAttempt la WHERE service_nr = :agentId AND success = true ORDER BY id DESC", LoginAttempt.class)
                .setParameter("agentId", agent.getServiceNumber()).setMaxResults(1).uniqueResultOptional().orElse(null);

        if (lastSuccessAttempt != null) {
            since = lastSuccessAttempt.getLoginDateTime();
        } else {
            since = LocalDateTime.MIN;
        }

        failedLoginAttempts = session.createQuery("FROM LoginAttempt WHERE service_nr = :agentId AND login_time > :since", LoginAttempt.class)
                .setParameter("agentId", agent.getServiceNumber())
                .setParameter("since", since)
                .getResultList();
        return failedLoginAttempts;
    }

}
