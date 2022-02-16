package leonkorth.tridominoscoringapp.model;

public class PlayerMove {

    private int points;
    private String name;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + points;
    }
}
