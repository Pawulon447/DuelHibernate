package duel.model;

import java.util.ArrayList;
import java.util.List;

public class OpponentsPool {

    List<Fighter> fighterList = new ArrayList<Fighter>();

    public void createOpponents() {
        Fighter opponent1 = new Fighter.UserBuilder("convict ")
                .weapon("club")
                .health(10)
                .build();
        fighterList.add(opponent1);

        Fighter gladiator1 = new Fighter.UserBuilder("Bet on gladiator")
                .weapon("sword")
                .health(20)
                .build();
        fighterList.add(gladiator1);

        Fighter gladiator2 = new Fighter.UserBuilder("Gladiator")
                .weapon("sword")
                .health(20)
                .build();
        fighterList.add(gladiator2);

        Fighter opponent2 = new Fighter.UserBuilder("Gladiator")
                .weapon("sword")
                .health(20)
                .build();
        fighterList.add(opponent2);

    }

    public OpponentsPool(List<Fighter> fighterList) {
        this.fighterList = fighterList;
    }

    public OpponentsPool() {
        createOpponents();
    }

    public List<Fighter> getCharacterList() {
        return fighterList;
    }
}
