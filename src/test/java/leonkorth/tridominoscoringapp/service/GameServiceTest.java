package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTest {


    @Test
    @DisplayName("adds the points correctly")
    void addPoints(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        Player p2 = new Player("Peonie");

        PlayerMove playerMoveL1 = new PlayerMove();
        PlayerMove playerMoveL2 = new PlayerMove();

        playerMoveL1.setName("Leon").setPoints(100);
        playerMoveL2.setName("Leon").setPoints(200);


        PlayerMove playerMoveP1 = new PlayerMove();
        PlayerMove playerMoveP2 = new PlayerMove();

        playerMoveP1.setName("Peonie").setPoints(111);
        playerMoveP2.setName("Peonie").setPoints(111);


        gameService.startGame(List.of(p1,p2));

        gameService.addPoints(playerMoveL1).addPoints(playerMoveL2).addPoints(playerMoveP1).addPoints(playerMoveP2);

        int expectedL = 300;
        int expectedP = 222;

        int actualL = gameService.getPlayerAndPoints(ListType.TOTAL).get(p1);
        int actualP = gameService.getPlayerAndPoints(ListType.TOTAL).get(p2);

        assertEquals(expectedL,actualL);
        assertEquals(expectedP,actualP);

    }

    @Test
    @DisplayName("can deal with negative numbers")
    void subtractPoints(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");

        PlayerMove playerMoveL1 = new PlayerMove();
        PlayerMove playerMoveL2 = new PlayerMove();

        playerMoveL1.setName("Leon").setPoints(100);
        playerMoveL2.setName("Leon").setPoints(-50);

        gameService.startGame(List.of(p1));

        gameService.addPoints(playerMoveL1).addPoints(playerMoveL2);

        int expected = 50;
        int actual = gameService.getPlayerAndPoints(ListType.TOTAL).get(p1);

        assertEquals(expected,actual);


    }

    @Test
    @DisplayName("returns the correct winner")
    void returnWinner(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        Player p2 = new Player("Peonie");

        PlayerMove playerMoveL1 = new PlayerMove();
        PlayerMove playerMoveL2 = new PlayerMove();

        playerMoveL1.setName("Leon").setPoints(100);
        playerMoveL2.setName("Leon").setPoints(200);


        PlayerMove playerMoveP1 = new PlayerMove();
        PlayerMove playerMoveP2 = new PlayerMove();

        playerMoveP1.setName("Peonie").setPoints(111);
        playerMoveP2.setName("Peonie").setPoints(111);


        gameService.startGame(List.of(p1,p2));

        gameService.addPoints(playerMoveL1).addPoints(playerMoveL2).addPoints(playerMoveP1).addPoints(playerMoveP2);

        String expectedName = "Leon";
        int expectedPoints = 300;
        int expectedLengths = 1;


        String actualName = gameService.getPlayerAndPoints(ListType.WINNER).keySet().stream().findFirst().orElse(new Player(" ")).getName();
        int actualPoints = gameService.getPlayerAndPoints(ListType.WINNER).values().stream().findFirst().orElse(0);
        int actualLength = gameService.getPlayerAndPoints(ListType.WINNER).size();

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

        PlayerMove playerMoveL1 = new PlayerMove();
        PlayerMove playerMoveL2 = new PlayerMove();

        playerMoveL1.setName("Leon").setPoints(100);
        playerMoveL2.setName("Leon").setPoints(200);


        PlayerMove playerMoveP1 = new PlayerMove();
        PlayerMove playerMoveP2 = new PlayerMove();

        playerMoveP1.setName("Peonie").setPoints(111);
        playerMoveP2.setName("Peonie").setPoints(111);


        gameService.startGame(List.of(p1,p2));

        gameService.addPoints(playerMoveL1).addPoints(playerMoveL2).addPoints(playerMoveP1).addPoints(playerMoveP2);

        String expectedName = "Peonie";
        int expectedPoints = 222;
        int expectedLengths = 1;


        String actualName = gameService.getPlayerAndPoints(ListType.LOSER).keySet().stream().findFirst().orElse(new Player(" ")).getName();
        int actualPoints = gameService.getPlayerAndPoints(ListType.LOSER).values().stream().findFirst().orElse(0);
        int actualLength = gameService.getPlayerAndPoints(ListType.LOSER).size();

        assertEquals(expectedName,actualName);
        assertEquals(expectedPoints,actualPoints);
        assertEquals(expectedLengths,actualLength);

    }

    @Test
    @DisplayName("returns the correct player whose turn it is ")
    void returnPlayerWhoseTurnItIs(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        Player p2 = new Player("Peonie");

        PlayerMove playerMoveL1 = new PlayerMove();
        PlayerMove playerMoveL2 = new PlayerMove();

        playerMoveL1.setName("Leon").setPoints(100);
        playerMoveL2.setName("Leon").setPoints(200);


        PlayerMove playerMoveP1 = new PlayerMove();

        playerMoveP1.setName("Peonie").setPoints(111);

        gameService.startGame(List.of(p1,p2));

        gameService.addPoints(playerMoveL1).addPoints(playerMoveP1).addPoints(playerMoveL1);

        String expected = "Peonie";
        String actual = gameService.getPlayerWhoseTurnItIs().getName();

        assertEquals(expected,actual);

    }


    @Test
    @DisplayName("returns the correct player whose turn it is if all players have same number of moves")
    void returnPlayerWhoseTurnItIsWithSameMoves(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        Player p2 = new Player("Peonie");

        PlayerMove playerMoveL1 = new PlayerMove();
        PlayerMove playerMoveL2 = new PlayerMove();

        playerMoveL1.setName("Leon").setPoints(100);
        playerMoveL2.setName("Leon").setPoints(200);


        PlayerMove playerMoveP1 = new PlayerMove();
        PlayerMove playerMoveP2 = new PlayerMove();

        playerMoveP1.setName("Peonie").setPoints(111);
        playerMoveP2.setName("Peonie").setPoints(111);

        gameService.startGame(List.of(p1,p2));

        gameService.addPoints(playerMoveL1).addPoints(playerMoveP1).addPoints(playerMoveL1).addPoints(playerMoveP2);

        String expected = "Leon";
        String actual = gameService.getPlayerWhoseTurnItIs().getName();

        assertEquals(expected,actual);

    }
}
