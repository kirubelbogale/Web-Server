package ca.cmpt213.asn5.model;

public class Tokimon {
    static private long total;
    private long id;
    private String name;
    private int weight;
    private double height;
    private String ability;
    private int strength;
    private String colour;

    public Tokimon(String name, int strength) {
        total++;
        this.id = total;
        this.name = name;
        this.strength = strength;
    }

    public Tokimon(String nm, int wt, double ht, String pwr, int str, String clr) {
        total++;
        this.id = total;
        name = nm;
        weight = wt;
        height = ht;
        ability = pwr;
        strength = str;
        colour = clr;
    }

    // add getters and setters as needed

    public void setID(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStrength() { return strength; }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}

