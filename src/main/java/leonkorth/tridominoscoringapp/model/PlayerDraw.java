package leonkorth.tridominoscoringapp.model;

public class PlayerDraw implements PlayerAction {


    private int drawCount = 1;
    private String drawPlayerName;

    public PlayerDraw(){}

    @Override
    public String getPlayerName() {
        return drawPlayerName;
    }

    @Override
    public int getNumber() {
        return drawCount;
    }

    @Override
    public PlayerAction setNumber(int number) {
        drawCount = number;
        return this;
    }

    @Override
    public PlayerAction setPlayerName(String name) {
        drawPlayerName = name;
        return this;
    }

    @Override
    public String toString() {
        return drawPlayerName + drawCount;
    }
}
