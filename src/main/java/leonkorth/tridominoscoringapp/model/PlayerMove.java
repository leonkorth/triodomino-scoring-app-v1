package leonkorth.tridominoscoringapp.model;

public class PlayerMove implements PlayerAction{

    private int points;
    private String name;

    public PlayerMove(){};


    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public int getNumber() {
        return points;
    }

    @Override
    public PlayerAction setNumber(int number) {
        points = number;
        return this;
    }

    @Override
    public PlayerAction setPlayerName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return name + points;
    }
}
