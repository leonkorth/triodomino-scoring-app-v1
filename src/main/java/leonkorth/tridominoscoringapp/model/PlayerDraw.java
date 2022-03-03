package leonkorth.tridominoscoringapp.model;

public class PlayerDraw {

    private int drawCount = 1;
    private String drawPlayerName;

    public PlayerDraw(){}


    public int getDrawCount() {
        return drawCount;
    }

    public PlayerDraw setPoints(int points) {
        this.drawCount = points;
        return this;
    }

    public String getDrawPlayerName() {
        return drawPlayerName;
    }

    public PlayerDraw setDrawPlayerName(String drawPlayerName) {
        this.drawPlayerName = drawPlayerName;
        return this;
    }

    @Override
    public String toString() {
        return drawPlayerName + drawCount;
    }
}
