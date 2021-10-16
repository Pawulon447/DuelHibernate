package duel;

import duel.model.Attributes;
import duel.model.Fighter;
import duel.model.Inventory;
import duel.model.Weapon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class hibDemo {
    public static void main(String[] args) {
        Configuration con = new Configuration().configure("hib.cfg.xml").addAnnotatedClass(Fighter.class).addAnnotatedClass(Weapon.class).addAnnotatedClass(Attributes.class).addAnnotatedClass(Inventory.class);
        SessionFactory sf = con.buildSessionFactory();
        Fighter playerFighter = new Fighter.UserBuilder("Player's character")
                .weapon("club")
                .health(20)
                .build();
        playerFighter.getWeapon().setOwner(playerFighter);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(playerFighter);
        tx.commit();
        session.close();
    }
}
