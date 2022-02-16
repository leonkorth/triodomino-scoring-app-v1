package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTest {

    @Test
    @DisplayName("splits the input correctly and adds point")
    void splitInput(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");

        String input = "Leon100";

        gameService.startGame(List.of(p1));

        gameService.addPoints(input);

        int expected = 100;

        int actual = gameService.getAllPlayersTotalPoints().get(p1);

        assertEquals(expected,actual);
    }


}
