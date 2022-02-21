package leonkorth.tridominoscoringapp.model;

public class PlayerMove {

    private int points;
    private String name;

    public PlayerMove(){};


    public int getPoints() {
        return points;
    }

    public PlayerMove setPoints(int points) {
        this.points = points;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlayerMove setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return name + points;
    }
}
