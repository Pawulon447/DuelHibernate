package duel.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class SaveDTO {

    @Id
    @GeneratedValue
    private int id;
    private String firstName; // required
    private double health;
    private double money;

    private double experience;
    private int level;
    private boolean playerControlled = true;
    private String stance = "neutral";
    //atributes
    private int strength;
    private int agility;
    private int charisma;
    private String weaponType;

    private int weaponsAmount;
    private String weaponTypes;

    public SaveDTO() {
    }

    public void saveIntoObjects() {
    }

    public void saveToDatabase() {
        Configuration con = new Configuration().configure("hib.cfg.xml").addAnnotatedClass(SaveDTO.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(this);
        tx.commit();
        session.close();
    }

    public SaveDTO(String firstName, double health, double money, double experience, int level, int strength, int agility, int charisma, String weaponType) {
        this.firstName = firstName;
        this.health = health;
        this.money = money;
        this.experience = experience;
        this.level = level;
        this.strength = strength;
        this.agility = agility;
        this.charisma = charisma;
        this.weaponType = weaponType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public int getWeaponsAmount() {
        return weaponsAmount;
    }

    public void setWeaponsAmount(int weaponsAmount) {
        this.weaponsAmount = weaponsAmount;
    }

    public String getWeaponTypes() {
        return weaponTypes;
    }

    public void setWeaponTypes(String weaponTypes) {
        this.weaponTypes = weaponTypes;
    }

    @Override
    public String toString() {
        return "Save nr " + id +
                ", firstName='" + firstName + '\'' +
                ", level=" + level +
                '}';
    }
}
