package duel.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public enum Weapon {

    SWORD("sword", 5, 0, 2, 1),
    SPEAR("spear", 4, 1, 4, 0),
    AXE("axe", 6, 0, 2, 0),
    CLUB("club", 3, 0, 2, 0),
    FISTS("fists", 2, 0, 2, 0);

    private int IdWeapon;
    private final String type;
    private final double damage;
    private final int critChance;
    private final int critModifier;
    private final int defenseBonus;

    private Fighter owner;


    Weapon(String type, double damage, int critChance, int critModifier, int defenseBonus) {
        this.type = type;
        this.damage = damage;
        this.critChance = critChance;
        this.critModifier = critModifier;
        this.defenseBonus = defenseBonus;
    }

    private static final Map<String, Weapon> WEAPON_BY_NAME = new HashMap<>();

    static {
        for (Weapon value : Weapon.values()) {
            WEAPON_BY_NAME.put(value.type, value);
        }
    }

    public static Weapon findByName(String name) {
        return WEAPON_BY_NAME.get(name);
    }

    public void setType(String type) {
        findByName(type);
    }

    public String getType() {
        return type;
    }

    public double getDamage() {
        return damage;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getCritModifier() {
        return critModifier;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public Fighter getOwner() {
        return owner;
    }

    public void setOwner(Fighter owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return
                type.toUpperCase(Locale.ROOT) + '\'' +
                        ", damage=" + damage +
                        ", critChance=" + critChance +
                        ", critModifier=" + critModifier +
                        ", defenseBonus=" + defenseBonus +
                        '}';
    }
}
