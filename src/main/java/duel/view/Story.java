package duel.view;


import duel.model.Fighter;

import java.util.Scanner;

public class Story {
    //public static Story INSTANCE = new Story();
    Fighter fighter1;
    Boolean strengthRouteCheck;
    Boolean agilityRouteCheck;
    Boolean charismaRouteCheck;

    public Story(Fighter fighter1) {
        this.fighter1 = fighter1;
        this.strengthRouteCheck = false;
        this.agilityRouteCheck = false;
        this.charismaRouteCheck = false;
    }

    public void chapter1() {
        System.out.println("Welcome to arena, soon your first fight begins, get ready.");

    }

    public void chapter2() {
        System.out.println("You have won your first fight.");

    }

    public void checkStoryProgress() {
        int winThreshold = 3;
        checkStrengthRoute(winThreshold);
        checkAgilityRoute(winThreshold);
        checkCharismaRoute(winThreshold);
        checkMoneyRoute(winThreshold);

        if (fighter1.getStrength() < winThreshold && fighter1.getAgility() < winThreshold && fighter1.getCharisma() < winThreshold && fighter1.getMoney() <= 33 * winThreshold) {

            System.out.println("no escape in sight yet...");
        }


    }

    private void checkStrengthRoute(int winThreshold) {
        if (fighter1.getStrength() >= winThreshold) {
            if (!strengthRouteCheck) {
                strengthRouteIntro();
                strengthRouteCheck = true;
            }
            strengthRoute();
        }

    }

    private void strengthRouteIntro() {
        System.out.println("Your impressive strength invokes fear and respect among gladiators. And favour of the crowd");
        System.out.println("One night a stranger enters your cell offering you a place in emperor's bodyguard " +
                "It's not true freedom you dream of but its better than certain death on the arena ");
    }

    private void strengthRoute() {
        System.out.println("Will you accept emperor's offer? 1 yes 2 no");
        switch (acquireNumber()) {
            case 1: {
                strengthEndGame();
                break;
            }
            case 2: {
                System.out.println("You should reconsider this offer...");
                break;
            }
            default:
                System.out.println("choose available option");
        }
    }

    private void strengthEndGame() {
        System.out.println("You begin new life in emperor's guard. You escaped death on arena but that doesn't mean safety. " +
                "People on the court often end worse than arena. Beware...   ");
        System.exit(0);
    }

    private void checkAgilityRoute(int winThreshold) {
        if (fighter1.getAgility() >= winThreshold) {
            if (!agilityRouteCheck) {
                agilityRouteIntro();
                agilityRouteCheck = true;
            }
            agilityRoute();

        }
    }

    private void agilityRouteIntro() {
        System.out.println("Your agility induces favour of the crowd and envy of fellow gladiators. " +
                "You can move swiftly and silently, if you wish you can attempt an escape. ");
    }

    private void agilityRoute() {
        System.out.println("Do you want to escape? 1 yes 2 no");
        switch (acquireNumber()) {
            case 1: {
                agilityEndGame();
                break;
            }
            case 2: {
                System.out.println("Probably a wise choice...");
                break;
            }
            default:
                System.out.println("choose available option");
        }
    }

    private void agilityEndGame() {
        System.out.println("One night you silently knock out guard escorting you to your cell and slip out of prison  ");
        System.out.println("Your will owner surely begin a chase after you, but for now you are free");
        System.exit(0);
    }

    private void checkCharismaRoute(int winThreshold) {
        if (fighter1.getCharisma() >= winThreshold) {
            if (!charismaRouteCheck) {
                CharismaRouteIntro();
                charismaRouteCheck = true;
            }
            CharismaRoute();
        }
    }


    private void CharismaRouteIntro() {
        System.out.println("Your charisma allows you to rally crowd, fellow gladiators and even some guards to your cause ");
        System.out.println("If you wish you can start a slave revolt");

    }

    private void CharismaRoute() {
        System.out.println("Do you wish to start a slave revolt? 1 yes 2 no");
        switch (acquireNumber()) {
            case 1: {
                charismaEndGame();
                break;
            }
            case 2: {
                System.out.println("Probably a wise choice...");
                break;
            }
            default:
                System.out.println("choose available option");
        }
    }

    private void charismaEndGame() {
        System.out.println("As time passes you set up your revolt " +
                "Eventually you invoke the need for freedom among your fellow slaves that no man can suppress. You start a great uprising, thousands of slaves reject their bonds and leave the city.  ");
        System.out.println("Surely this revolt wont go unanswered, and a rebel's fate can be terrible. But for now you are free again. Enjoy it while you can.");
        System.exit(0);
    }

    private void checkMoneyRoute(int winTreshold) {
        if (fighter1.getMoney() >= winTreshold * 33) {
            moneyRoute();
        }

    }

    private void moneyRoute() {
        System.out.println("You have enough gold to buy your freedom. Do you wish to do it? 1 yes 2 no");
        switch (acquireNumber()) {
            case 1: {
                MoneyEndGame();
                break;
            }
            case 2: {
                System.out.println("You should look into this offer...");
                break;
            }
            default:
                System.out.println("choose available option");
        }

    }

    private void MoneyEndGame() {
        System.out.println("Thanks to your bravery, skill and luck you have succeeded in regaining your freedom, congratulations! ");
        System.exit(0);
    }

    private int acquireNumber() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter natural positive number > ");
        }
        return scanner.nextInt();
    }

}
