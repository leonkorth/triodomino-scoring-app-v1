package leonkorth.tridominoscoringapp.model;

public class PlayerDraw {

    private int drawCount;
    private String drawName;

    public PlayerDraw(){};


    public int getDrawCount() {
        return drawCount;
    }

    public PlayerDraw setPoints(int points) {
        this.drawCount = points;
        return this;
    }

    public String getDrawName() {
        return drawName;
    }

    public PlayerDraw setDrawName(String drawName) {
        this.drawName = drawName;
        return this;
    }

    @Override
    public String toString() {
        return drawName + drawCount;
    }
}
