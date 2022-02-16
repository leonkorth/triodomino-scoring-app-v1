package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerServiceTest {

    @Test
    @DisplayName("it's not possible to add a player with an empty name")
    void addEmptyNamePlayer(){

        Player p1 = new Player("");

        PlayerService playerService = new PlayerService();

        playerService.addPlayer(p1);

        int expected = 0;

        int actual = playerService.getAllPlayers().size();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("it's not possible to add a player with the same name as another existing player")
    void addSameNamePlayer(){

        Player p1 = new Player("Leon");
        Player p2 = new Player("Leon");

        PlayerService playerService = new PlayerService();

        playerService.addPlayer(p1);
        playerService.addPlayer(p2);

        int expected = 1;

        int actual = playerService.getAllPlayers().size();

        assertEquals(expected,actual);
    }


}
