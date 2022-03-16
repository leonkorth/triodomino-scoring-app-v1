package leonkorth.tridominoscoringapp.model;

public interface PlayerAction {

    String getPlayerName();

    int getNumber();

    PlayerAction setNumber(int number);

    PlayerAction setPlayerName(String name);

}
