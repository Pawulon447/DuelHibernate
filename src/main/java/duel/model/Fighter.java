package duel.model;


import duel.view.Story;

import java.util.Random;
import java.util.Scanner;


public class Fighter {

    private int id;
    private String firstName; // required
    private double health;
    private double money;
    private String stance = "neutral";
    private double experience;
    private int level = 1;
    private boolean playerControlled;
    //@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Inventory inventory = new Inventory();
    //private List<Weapon> weapons =new ArrayList<Weapon>();
    Attributes attributes = new Attributes(); //zmienic pola na prywatne
    private Weapon weapon;
    private Story story;


    public static class UserBuilder {
        private final String firstName;
        private Weapon weapon;
        private double health;
        private boolean playerControlled;

        public UserBuilder(String firstName) {
            this.firstName = firstName;
        }

        public UserBuilder health(double health) {
            this.health = health;
            return this;
        }

        public UserBuilder playerControlled(boolean playerControlled) {
            this.playerControlled = playerControlled;
            return this;
        }

        public UserBuilder weapon() {
            this.weapon = Weapon.FISTS;
            return this;
        }

        public UserBuilder weapon(String type) {
            this.weapon = Weapon.findByName(type);
            return this;
        }

        /*public UserBuilder weapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }*/

        public Fighter build() {
            Fighter fighter = new Fighter(this);
            return fighter;
        }
    }

    private Fighter(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.health = builder.health;
        this.weapon = builder.weapon;
    }

    public void spendMoney(double amount) {
        money = money - amount;
    }

    public void acquireMoney(double amount) {
        money = money + amount;
    }

    public void acquireExperience(double amount) {
        experience = experience + amount;
        checkLvLUp();

    }

    public void restorePlayerHealth() {
        if (getLevel() > 1) {
            setHealth(20 + 5 * getLevel());
        } else setHealth(20);
    }

    void checkLvLUp() {
        if (experience >= 100) {
            System.out.println("You have leveled up!");
            experience -= 100;
            level++;
            health = health + 5;
            attributes.increaseAttribute();
        }
    }

    public void receiveWeaponDamage(Fighter attacker) {
        double receivedDamage = attacker.weapon.getDamage() + attacker.getStrength();
        System.out.println(firstName + " receives " + receivedDamage + " damage ");
        health = health - (receivedDamage);
        stance = "neutral";
    }

    public void receiveKickDamage() {
        System.out.println(firstName + " receive 1 damage ");
        health = health - 1;
        stance = "neutral";
    }

    public void receiveCriticalKick() {
        System.out.println(firstName + " receives 5 damage ");
        health = health - 5;
        stance = "neutral";
    }

    public void receiveCriticalDamage(Fighter attacker) {
        double receivedCritDamage = (attacker.weapon.getDamage() * attacker.weapon.getCritModifier()) + attacker.attributes.getStrength();
        System.out.println(firstName + " receives " + receivedCritDamage + " damage ");
        health = health - receivedCritDamage;
        stance = "neutral";
    }

    public void chooseWeapon() {
        System.out.println("choose a weapon: ");
        System.out.println("1. sword");
        System.out.println("2. spear");
        System.out.println("3. axe");
        switch (acquireNumber()) {
            case 1: {
                System.out.println("you have chosen sword.");
                inventory.getWeapons().add(weapon);
                weapon = Weapon.findByName("sword");
                break;
            }
            case 2: {
                System.out.println("you have chosen spear");
                inventory.getWeapons().add(weapon);
                weapon = Weapon.SPEAR;
                break;
            }
            case 3: {
                System.out.println("you have chosen axe");
                inventory.getWeapons().add(weapon);
                weapon = Weapon.AXE;
                break;
            }
            default:
                System.out.println("choose available option ");
                break;
        }
    }

    public void setWeapon(String weaponType) {
        weapon = Weapon.findByName(weaponType);
    }

    public void openInventory() {
        if (inventory.getWeapons().size() == 0) {
            System.out.println("your inventory is empty");
        } else {
            for (int i = 0; i < inventory.getWeapons().size(); i++) {
                System.out.println(i + 1 + " " + inventory.getWeapons().get(i));
            }
            System.out.println("Do you wish to equip different weapon? 1 yes 2 no ");
            switch (acquireNumber()) {
                case 1:
                    pickWeaponFromInventory();
                case 2:
                    break;
            }
        }
    }

    public void pickWeaponFromInventory() {
        System.out.println("enter number of weapon from above");
        int chosenNumber = (acquireNumber());
        while (chosenNumber < 0 || chosenNumber > inventory.getWeapons().size()) { // TODO test it
            System.out.println("input number of weapon in your inventory");
            chosenNumber = acquireNumber();
        }
        inventory.getWeapons().add(weapon);
        weapon = Weapon.findByName(inventory.getWeapons().get(chosenNumber - 1).getType());
        inventory.getWeapons().remove(chosenNumber - 1);
    }

    public void randomizeWeapon() {
        switch (random(2)) {
            case 0:
                weapon = Weapon.findByName("sword");
                break;
            case 1:
                weapon = Weapon.findByName("spear");
                break;
            case 2:
                weapon = Weapon.findByName("axe");
                break;
            default:
                System.out.println("randomize weapon error");
                break;
        }
    }

    private int acquireNumber() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter natural positive number > ");
        }
        return scanner.nextInt();
    }

    private static int random(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

    @Override
    public String toString() {
        return "Character: " + this.firstName + " weapon " + this.weapon + " health " + this.health + " money " + this.money + " level " + this.level + this.attributes + "\n";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setPlayerControlled(boolean playerControlled) {
        this.playerControlled = playerControlled;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void changeHealth(double zmiana) {
        this.health += zmiana;
    }

    public void setStance(String stance) {
        this.stance = stance;
    }

    public int getCharisma() {
        return attributes.getCharisma();
    }

    public int getStrength() {
        return attributes.getStrength();
    }

    public int getAgility() {
        return attributes.getAgility();
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public int getLevel() {
        return level;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isPlayerControlled() {
        return playerControlled;
    }

    public String getStance() {
        return stance;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Story getStory() {
        return story;
    }
}