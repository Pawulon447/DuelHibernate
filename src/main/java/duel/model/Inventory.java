package duel.model;


import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private int invId;

    private Fighter owner;

    private List<Weapon> weapons = new ArrayList<Weapon>();

    public int getInvId() {
        return invId;
    }

    public void setInvId(int invId) {
        this.invId = invId;
    }

    public Fighter getOwner() {
        return owner;
    }

    public void setOwner(Fighter owner) {
        this.owner = owner;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
}
