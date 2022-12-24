package tw.edu.nptu.G03;

import java.io.Serializable;  // dont delete
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SaveDB {
    int savecount = 1;

    SaveDB(Page p) {
        // p.getParent();
        savecount = 1;
        // loads configuration and creates a session factory
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://web.csie2.nptu.edu.tw:3306/cbb109148_G03");
        settings.put(Environment.USER, "cbb109148");
        settings.put(Environment.PASS, "CBB109148");
        settings.put(Environment.SHOW_SQL, "true");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Contact.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

        p.units.forEach(n -> {
            try {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                System.out.println((Contact) session.load(Contact.class, Integer.valueOf(savecount)));  // 得到錯誤用的
                Contact contact2 = (Contact) session.load(Contact.class, Integer.valueOf(savecount));
                contact2.setUnitX(n.getX());
                contact2.setUnitY(n.getY());
                contact2.setUnitW(n.getWidth());
                contact2.setUnitH(n.getHeight());
                contact2.setUnitR(n.getBackground().getRed());
                contact2.setUnitG(n.getBackground().getGreen());
                contact2.setUnitB(n.getBackground().getBlue());
                contact2.setPanelR(p.getBackground().getRed());
                contact2.setPanelG(p.getBackground().getGreen());
                contact2.setPanelB(p.getBackground().getBlue());
                
                session.update(contact2);
                session.getTransaction().commit();
            }

            catch (org.hibernate.ObjectNotFoundException e) {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                Contact contact1 = new Contact(savecount, n.getX(), n.getY(), n.getWidth(),
                        n.getHeight(),
                        n.getBackground().getRed(), n.getBackground().getGreen(),
                        n.getBackground().getBlue(),
                        p.getBackground().getRed(), p.getBackground().getGreen(),
                        p.getBackground().getBlue());


                session.save(contact1);
                session.getTransaction().commit();
            } finally {
                SaveDB.this.savecount += 1;
            }
        });
        while (true) {
            try {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                System.out.println((Contact) session.load(Contact.class, Integer.valueOf(savecount)));  // 得到錯誤用的
                Contact contact7 = (Contact) session.load(Contact.class, Integer.valueOf(savecount));
                session.delete(contact7);
                session.getTransaction().commit();
                session.close();
            }
            catch (org.hibernate.ObjectNotFoundException e) { 
                break;
            } finally {
                SaveDB.this.savecount += 1;
            }
        }
    }
    
}