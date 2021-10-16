package duel.controller;

import duel.model.Fighter;
import duel.model.Fight;
import duel.model.OpponentsPool;
import duel.model.SaveDTO;

import java.util.Scanner;

public class Activity {
    private Fighter playerFighter;
    private OpponentsPool opponentsPool;

    public Activity(Fighter fighter1, OpponentsPool opponentsPool) {
        this.playerFighter = fighter1;
        this.opponentsPool = opponentsPool;
    }

    public void activityMenu() {
        System.out.println("choose activity: ");
        System.out.println("1. start next fight ");
        System.out.println("2. Watch a fight ");
        System.out.println("3. Go to the store");
        System.out.println("4. Exit game");
        System.out.println("5. Open inventory");
        System.out.println("6. Save game");
        System.out.println("7. Check endgame options");
    }

    public void chooseActivity() {
        switch (acquireNumber()) {
            case 1:
                beginNextFight();
                break;
            case 2:
                watch();
                break;
            case 3:
                openStore();
                break;
            case 4:
                System.exit(0);
                break;
            case 5:
                playerFighter.openInventory();
                break;
            case 6:
                createRepository();
                break;
            case 7:
                playerFighter.getStory().checkStoryProgress();
                break;
            default:
                System.out.println("choose available number ");
        }
    }

    public void createRepository(){
        try{
            Repository repository=new Repository();
            repository.createSave(playerFighter);
        }catch (Exception e){
            System.out.println("couldn't connect to database");
        }


    }


    public void openStore() {
        System.out.println("Welcome to the store. We have sword spear and axe in stock, each for 20 gold each.");
        if (playerFighter.getMoney() < 20) {
            System.out.println("You dont have enough gold. Come back once you have at least 20 gold.");
            return;
        }
        System.out.println("Do you wish to buy a weapon? ");
        switch (acquireNumber()) {
            case 1: {
                playerFighter.spendMoney(20);
                playerFighter.chooseWeapon();
            }
            case 2:
                break;
            default:
                System.out.println("choose available number ");
        }
    }


    public void watch() {
        System.out.println("Do you wish to bet on win? 1 yes 2 no ");
        switch (acquireNumber()) {
            case 1: {
                double betMoney = takeBet();
                if (generateGladiatorsAndStartFight()) {
                    playerFighter.acquireMoney(betMoney * 2);
                }
            }
            case 2:
                generateGladiatorsAndStartFight();
                break;
            default:
                System.out.println("choose available number ");
        }
    }

    public double takeBet() {
        System.out.println("Your money: " + playerFighter.getMoney());
        System.out.println("Choose how much you want to bet: ");
        double bet = 0;
        int acquiredNumber = acquireNumber();
        if (acquiredNumber > 0 && acquiredNumber <= playerFighter.getMoney()) {
            bet = acquiredNumber;
            playerFighter.spendMoney(bet);
            return bet;
        } else System.out.println("Input number bigger than zero ");
        return 0;
    }

    public boolean generateGladiatorsAndStartFight() {
        Fight viewedFight = new Fight(opponentsPool.getCharacterList().get(1), opponentsPool.getCharacterList().get(2));
        if (viewedFight.botFight()) {
            return true;
        } else return false;
    }

    public void beginNextFight() {
        opponentsPool.getCharacterList().get(3).setHealth(playerFighter.getHealth());
        opponentsPool.getCharacterList().get(3).randomizeWeapon();
        Fight fight2 = new Fight(playerFighter, opponentsPool.getCharacterList().get(3));
        fight2.beginFight();
    }

    private int acquireNumber() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter number in natural positive number > ");
        }
        return scanner.nextInt();
    }

}
