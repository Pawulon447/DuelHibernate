package duel.controller;

import duel.model.Fighter;
import duel.model.SaveDTO;
import duel.model.Weapon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Scanner;

public class Repository {
    Game game;
    Configuration con = new Configuration().configure("hib.cfg.xml").addAnnotatedClass(SaveDTO.class); //zrobić to opcjonalne, jak sie nie uda polaczyc z baza wyswietlic komunikat
    SessionFactory sf = con.buildSessionFactory();


    public void showSaves() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        //List<SaveDTO> saveDTOS= session.createQuery("from SaveDTO");
        Query kwerenda = session.createQuery(" from SaveDTO ");
        System.out.println(kwerenda.list());
        tx.commit();
        session.close();
        /*
        for (int i=0;i<kwerenda.list().size();i++){
            System.out.println(kwerenda.list().get(i));
        }*/
        loadFromDB();
    }

    public void loadFromDB() {
        System.out.println("enter number of save you want to load, if you want to cancel loading input ");
        int saveNumber = acquireNumber();
        try {
            SaveDTO saveDTO = acquireSave(saveNumber);
            mapDataIntoObjects(saveDTO);
            /*
            List<Character> characterList = new ArrayList<>();
            Character nulowy = null;
            characterList.add(nulowy);
            System.out.println(characterList.get(5).getAgility());
            */

        } catch (Exception e) {   //TODO zrob wlasny typ wyjatku

            System.out.println("save not found ");
            game.begin();
        }
    }
    public SaveDTO acquireSave(int id) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        SaveDTO saveDTO = (SaveDTO) session.createQuery("from SaveDTO where id ='" + id + "'   ").list().get(0);
        tx.commit();
        session.close();
        return saveDTO;
    }

    public void mapDataIntoObjects(SaveDTO saveDTO) {
        game.playerFighter.setHealth(saveDTO.getHealth());
        game.playerFighter.setFirstName(saveDTO.getFirstName());
        game.playerFighter.setLevel(saveDTO.getLevel());
        game.playerFighter.setMoney(saveDTO.getMoney());
        game.playerFighter.setExperience(saveDTO.getExperience());
        game.playerFighter.getAttributes().setStrength(saveDTO.getStrength());
        game.playerFighter.getAttributes().setAgility(saveDTO.getAgility());
        game.playerFighter.getAttributes().setCharisma(saveDTO.getCharisma());
        game.playerFighter.setWeapon(saveDTO.getWeaponType());
        game.playerFighter.setPlayerControlled(true);
        String[] types = saveDTO.getWeaponTypes().split(",");
        for (int i = 0; i < saveDTO.getWeaponsAmount(); i++) {
            game.playerFighter.getInventory().getWeapons().add(Weapon.findByName(types[i]));//pozbyć sie łancuchów getterów
        }
        game.continueGame();
    }

    public void createSave(Fighter playerFighter) {
        SaveDTO saveDTO = new SaveDTO(playerFighter.getFirstName(), playerFighter.getHealth(), playerFighter.getMoney(), playerFighter.getExperience(), playerFighter.getLevel(), playerFighter.getStrength(), playerFighter.getAgility(), playerFighter.getCharisma(), playerFighter.getWeapon().getType());
        saveDTO.setWeaponsAmount(playerFighter.getInventory().getWeapons().size());
        String types = "";          //TODO wykorzystac string builder
        for (int i = 0; i < saveDTO.getWeaponsAmount(); i++) {
            types += playerFighter.getInventory().getWeapons().get(i).getType();
            types += ",";
            //saveDTO.getWeaponTypes()+=playerCharacter.getInventory().getWeapons().get(i).getType();
            //saveDTO.setWeaponTypes();
        }
        saveDTO.setWeaponTypes(types);
        saveDTO.saveToDatabase();

    }

    private int acquireNumber() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.nextLine(); //clear the invalid input before prompting again
            System.out.print("Please enter natural positive number > ");
        }
        return scanner.nextInt();
    }


    public Repository(Game game) {
        this.game = game;
    }

    public Repository() {
    }
}
