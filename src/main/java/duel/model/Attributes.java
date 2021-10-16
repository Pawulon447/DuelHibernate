package duel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Scanner;


public class Attributes {

    private int attributeID;
    private int Strength;
    private int Agility;
    private int Charisma;

    public int getStrength() {
        return Strength;
    }

    public int getAgility() {
        return Agility;
    }

    public int getCharisma() {
        return Charisma;
    }

    public void setStrength(int strength) {
        Strength = strength;
    }

    public void setAgility(int agility) {
        Agility = agility;
    }

    public void setCharisma(int charisma) {
        Charisma = charisma;
    }

    public void increaseAttribute() {
        System.out.println("choose atribute to increase: ");
        System.out.println("1. Strength");
        System.out.println("2. Agility");
        System.out.println("3. Charisma");
        switch (acquireNumber()) {
            case 1: {
                Strength++;
                break;
            }
            case 2:
                Agility++;
                break;
            case 3:
                Charisma++;
                break;
            default:
                System.out.println("choose available option ");
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

    @Override
    public String toString() {
        return "Atributes{" +
                "Strength=" + Strength +
                ", Agility=" + Agility +
                ", Charisma=" + Charisma +
                '}';
    }

}
