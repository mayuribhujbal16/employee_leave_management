package mayuri;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // ✅ Finds hibernate.cfg.xml in src/main/java/ (your teacher's method)
            sessionFactory = new Configuration()
                .configure("/hibernate.cfg.xml")  // Full path from classpath
                .buildSessionFactory();
            System.out.println("✅ Hibernate SessionFactory Created Successfully!");
        } catch (Throwable ex) {
            System.err.println("❌ Hibernate Failed: " + ex.getMessage());
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}