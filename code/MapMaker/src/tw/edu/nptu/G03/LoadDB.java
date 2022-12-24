package tw.edu.nptu.G03;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class LoadDB {
    int savecount = 1;

    LoadDB(Frame frame, Page panel) {
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

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // call new mapmaker panel
        if (frame.activePage2 != null) {
            frame.remove(frame.activePage2);
            frame.activePage2 = null;
        }
        if (frame.activePage != null) {
            frame.remove(frame.activePage);
        }
        frame.activePage = new Page(frame);
        frame.add(frame.activePage);
        frame.pages.add(frame.activePage);
        StatusPanel.currpage = StatusPanel.pagenum = frame.pages.size();
        frame.activePage.addcheck = true;
        frame.activePage.status = Status.Actived;

        // loading
        Query<Contact> query = session.createQuery("from MapMaker"); // 這是對應entity
        List<Contact> list = query.list();
        int Pr = 0, Pg = 0, Pb = 0;
        int flag = -1;
        for (Contact c : list) {
            int x, y, w, h, Ur, Ug, Ub;
            x = c.getUnitX();
            y = c.getUnitY();
            w = c.getUnitW();
            h = c.getUnitH();
            Ur = c.getUnitR();
            Ug = c.getUnitG();
            Ub = c.getUnitB();
            Pr = c.getPanelR();
            Pg = c.getPanelG();
            Pb = c.getPanelB();

            UnitForLoad UnitForLoad = new UnitForLoad(frame.activePage);
            UnitForLoad.setBounds(x, y, w, h);
            UnitForLoad.setBackground(new Color(Ur, Ug, Ub));
            frame.activePage.add(UnitForLoad);
            frame.activePage.com = UnitForLoad;
            frame.activePage.units.add(UnitForLoad);
            if (flag == -1) {
                confirm con = new confirm(frame);// 找顏色
                for (int i = 0 ; i <= 9 ; i++) {
                    if (Ur == con.coloru[i][0]) {
                        if (Ug == con.coloru[i][1]) {
                            if (Ub == con.coloru[i][2]) {
                                flag = i;
                            }
                        }
                    }
                }
                con.unitcolor(flag);
            }
        }
        frame.activePage.setBackground(new Color(Pr, Pg, Pb));
        frame.repaint();
        frame.validate();
    }
}