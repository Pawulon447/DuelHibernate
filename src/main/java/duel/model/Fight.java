package duel.model;

import java.util.Random;
import java.util.Scanner;

public class Fight {
    Fighter fighter1;
    Fighter fighter2;

    public Fight(Fighter fighter1, Fighter opponent1) {
        this.fighter1 = fighter1;
        this.fighter2 = opponent1;
    }

    public void beginFight() {
        while (fighter1.getHealth() > 0 && fighter2.getHealth() > 0) {
            System.out.println("==========new turn==========");
            clearScreen();
            showMenu();                                                   //wydzielic metody z udzialem gracza
            chooseAction(fighter2, fighter1);
            if (checkFightEnd()) {
                return;
            }
            botChooseAction(fighter1, fighter2);
            if (checkFightEnd()) {
                return;
            }
            System.out.println("***your health  " + fighter1.getHealth());
            System.out.println("***opponent health " + fighter2.getHealth());
            if (fighter1.getHealth() <= 0) {
                System.out.println("you have been defeated!");
                System.exit(0);
            }
        }
    }

    private boolean checkFightEnd() {
        if (fighter2.getHealth() <= 0) {
            if (fighter1.isPlayerControlled()) {
                playerFightRewards();
            }
            return true;
        } else return false;
    }

    private void playerFightRewards() {
        System.out.println("You have defeated the enemy!");
        fighter1.restorePlayerHealth();
        fighter1.acquireMoney(20 + 10 * fighter1.getCharisma());
        fighter1.acquireExperience(100);
    }

    public boolean botFight() {
        while (fighter1.getHealth() > 0 && fighter2.getHealth() > 0) {
            System.out.println("==========new turn==========");
            clearScreen();
            botChooseAction(fighter2, fighter1);
            if (fighter2.getHealth() <= 0) {
                System.out.println(fighter1.getFirstName() + "has defeated his opponent! ");
                return true;
            }
            botChooseAction(fighter1, fighter2);
            System.out.println(fighter1.getFirstName() + " health: " + fighter1.getHealth());
            System.out.println("***opponent's health " + fighter2.getHealth());
            if (fighter1.getHealth() <= 0) {
                System.out.println("your warrior has lost ");
                return false;
            }
        }
        return false;
    }

    private void showMenu() {
        System.out.println("choose your move:");
        System.out.println("1. strike with weapon");
        System.out.println("2. Defensive(parry)");
        System.out.println("3. Defensive(dodge)");
        System.out.println("4. Kick ");
    }

    private int acquireNumber() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter natural positive number > ");
        }
        return scanner.nextInt();
    }

    private void chooseAction(Fighter target, Fighter attacker) {
        switch (acquireNumber()) {
            case 1: {
                System.out.println(attacker.getFirstName() + " strikes with " + attacker.getWeapon().getType() + "...");
                checkHit(target, attacker);
                attacker.setStance("neutral");
                break;
            }
            case 2: {
                System.out.println(attacker.getFirstName() + " takes defensive...");
                attacker.setStance("parry");
                break;
            }
            case 3: {
                System.out.println(attacker.getFirstName() + " takes defensive...");
                attacker.setStance("dodge");
                break;
            }
            case 4: {
                System.out.println(attacker.getFirstName() + " kicks...");
                kick(target, attacker);
                attacker.setStance("neutral");
                break;
            }
            default:
                System.out.println("choose available option ");
        }
    }

    private void botChooseAction(Fighter target, Fighter attacker) {
        //zlikwidować magic numbers
        int opponentMove = random(20);
        if (opponentMove <= 4) {
            System.out.println(attacker.getFirstName() + " strikes with " + attacker.getWeapon().getType() + "...");
            checkHit(target, attacker);
            attacker.setStance("neutral");
        }
        if (opponentMove <= 9 && opponentMove >= 5) {
            System.out.println(attacker.getFirstName() + " takes defensive...");
            attacker.setStance("parry");
        }
        if (opponentMove <= 14 && opponentMove >= 10) {
            System.out.println(attacker.getFirstName() + " takes defensive...");
            attacker.setStance("dodge");
        }
        if (opponentMove <= 19 && opponentMove >= 15) {
            System.out.println(attacker.getFirstName() + " kicks...");
            kick(target, attacker);
            attacker.setStance("neutral");
        }
    }

    private void kick(Fighter target, Fighter attacker) {
        int randomizeHit = random(10);
        randomizeHit += attacker.getAgility();
        randomizeHit = updateKickChance(target, randomizeHit, attacker);
        randomizeHit = limitValue(randomizeHit);
        if (randomizeHit == 0) {
            System.out.println("Dodge and counterattack! " + attacker.getFirstName() + "receives 5 damage");
            attacker.changeHealth(-5);
        }
        if (randomizeHit >= 1 && randomizeHit <= 3) {
            System.out.println("miss!");
        }
        if (randomizeHit >= 4 && randomizeHit <= 8) {
            System.out.println("Hit!");
            target.receiveKickDamage();
        }
        if (randomizeHit == 9) {
            System.out.println("Critical kick!! ");
            target.receiveCriticalKick();
        }
    }

    private int updateKickChance(Fighter target, int randomizeAttack, Fighter attacker) {
        if (target.getStance() == "parry") {
            randomizeAttack += 3;
        }
        if (target.getStance() == "dodge") {
            randomizeAttack -= 3;
        }
        randomizeAttack += attacker.getAgility();
        return randomizeAttack;
    }

    private int limitValue(int limitedValue) {
        if (limitedValue > 9) {
            limitedValue = 9;
        }
        if (limitedValue < 0) {
            limitedValue = 0;
        }
        return limitedValue;
    }

    private void checkHit(Fighter target, Fighter attacker) {
        int randomizeHit = random(10);
        randomizeHit = updateHitChance(randomizeHit, target);
        randomizeHit = limitValue(randomizeHit);
        if (randomizeHit == 0) {
            System.out.println("Enemy counterattack! " + attacker.getFirstName() + " receives 5 damage! ");
            attacker.changeHealth(-5);
        }
        if (randomizeHit >= 1 && randomizeHit <= 3) {
            System.out.println("miss!");
        }
        if (randomizeHit >= 9 - attacker.getWeapon().getCritChance()) {
            System.out.println("Critical hit!!");
            target.receiveCriticalDamage(attacker);
            return;
        }
        if (randomizeHit >= 4 && randomizeHit <= 8) {
            System.out.println("Hit!");
            target.receiveWeaponDamage(attacker);
        }
    }

    private int updateHitChance(int randomizeAttack, Fighter target) {
        if (target.getStance() == "parry") {
            randomizeAttack -= 3;
            randomizeAttack -= target.getWeapon().getDefenseBonus();
        }
        if (target.getStance() == "dodge") {
            randomizeAttack += 3;
        }
        return randomizeAttack;
    }

    private static int random(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}