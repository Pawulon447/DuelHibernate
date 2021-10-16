package duel.controller;

import duel.model.*;
import duel.model.Fighter;

import duel.view.Story;

import java.util.Scanner;

public class Game {


    OpponentsPool opponents = new OpponentsPool();


    Fighter playerFighter = new Fighter.UserBuilder("Player's character")
            .weapon("club")
            .health(20)
            .build();
    Story story = new Story(playerFighter);


    public Game() {

    }

    public void begin() {
        beginMenu();
        switch (acquireNumber()) {
            case 1: {
                beginNewGame();
                break;
            }
            case 2: {
                createRepository();
                break;
            }
            default:
                System.out.println("choose available option");
                begin();
        }
    }
    public void createRepository(){
        try{
            Repository repository=new Repository(this);
            repository.showSaves();
        }catch(Exception e){
            System.out.println("cant connect with database");
            begin();
        }
    }
    public void beginMenu() {
        System.out.println("Welcome to Duel, to continue type and enter number of available option");
        System.out.println("1. New game");
        System.out.println("2. Load save");
    }

    public void beginNewGame() {
        System.out.println("Input your character name ");
        playerFighter.setFirstName(acquireString());
        //opponents.createOpponents();
        playerFighter.setPlayerControlled(true);
        story.chapter1();
        System.out.println(playerFighter);
        System.out.println(opponents.getCharacterList().get(0));
        Fight fight1 = new Fight(playerFighter, opponents.getCharacterList().get(0));
        fight1.beginFight();
        story.chapter2();
        System.out.println(playerFighter);
        continueGame();
    }

    public void continueGame() {
        playerFighter.setStory(story);
        Activity activity1 = new Activity(playerFighter, opponents);
        while (playerFighter.getHealth() > 0) {
            activity1.activityMenu();
            activity1.chooseActivity();
            System.out.println(playerFighter);
            if (playerFighter.getHealth() <= 0) {
                System.out.println("Game over!");
                System.exit(0);
                return;
            }
            //story.checkStoryProgress();
        }
    }

    private int returnCheckedNumber() {
        int number = 0;
        try {
            number = acquireNumber();
        } catch (Exception e) { // TODO własny typ exception
            System.out.println("input number");
        }
        return number;
    }

    private int acquireNumber() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.nextLine(); //clear the invalid input before prompting again
            System.out.print("Please enter natural positive number > ");
        }
        return scanner.nextInt();
    }


    private String acquireString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
