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

    @Test
    @DisplayName("adds the points correctly")
    void addPoints(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        Player p2 = new Player("Peonie");

        String inputL1 = "Leon100";
        String inputL2 = "Leon200";

        String inputP1="Peonie111";
        String inputP2="Peonie111";


        gameService.startGame(List.of(p1,p2));

        gameService.addPoints(inputL1).addPoints(inputP1).addPoints(inputL2).addPoints(inputP2);

        int expectedL = 300;
        int expectedP = 222;

        int actualL = gameService.getAllPlayersTotalPoints().get(p1);
        int actualP = gameService.getAllPlayersTotalPoints().get(p2);

        assertEquals(expectedL,actualL);
        assertEquals(expectedP,actualP);


    }


    @Test
    @DisplayName("returns the correct winner")
    void returnWinner(){
        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        Player p2 = new Player("Peonie");

        String inputL1 = "Leon100";
        String inputL2 = "Leon200";

        String inputP1="Peonie111";
        String inputP2="Peonie111";


        gameService.startGame(List.of(p1,p2));

        gameService.addPoints(inputL1).addPoints(inputP1).addPoints(inputL2).addPoints(inputP2);

        String expectedName = "Leon";
        int expectedPoints = 300;
        int expectedLengths = 1;



        String actualName = gameService.getWinnerAndPoints().keySet().stream().findFirst().orElse(new Player(" ")).getName();
        int actualPoints = gameService.getWinnerAndPoints().values().stream().findFirst().orElse(0);
        int actualLength = gameService.getWinnerAndPoints().size();

        assertEquals(expectedName,actualName);
        assertEquals(expectedPoints,actualPoints);
        assertEquals(expectedLengths,actualLength);

    }

    @Test
    @DisplayName("returns the correct loser")
    void returnLoser(){
        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        Player p2 = new Player("Peonie");

        String inputL1 = "Leon100";
        String inputL2 = "Leon200";

        String inputP1="Peonie111";
        String inputP2="Peonie111";


        gameService.startGame(List.of(p1,p2));

        gameService.addPoints(inputL1).addPoints(inputP1).addPoints(inputL2).addPoints(inputP2);

        String expectedName = "Peonie";
        int expectedPoints = 222;
        int expectedLengths = 1;



        String actualName = gameService.getLoserAndPoints().keySet().stream().findFirst().orElse(new Player(" ")).getName();
        int actualPoints = gameService.getLoserAndPoints().values().stream().findFirst().orElse(0);
        int actualLength = gameService.getLoserAndPoints().size();

        assertEquals(expectedName,actualName);
        assertEquals(expectedPoints,actualPoints);
        assertEquals(expectedLengths,actualLength);

    }
}
