package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerDraw;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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


    @Test
    @DisplayName("can sort the list in the correct reversed order")
    void returnSortedList(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        PlayerMove pm1 = new PlayerMove();
        PlayerMove pm2 = new PlayerMove();
        PlayerMove pm3 = new PlayerMove();
        PlayerMove pm4 = new PlayerMove();


        pm1.setPoints(22).setName("Leon");
        pm2.setPoints(11).setName("Leon");
        pm3.setPoints(21).setName("Leon");
        pm4.setPoints(33).setName("Leon");

        gameService.startGame(List.of(p1)).addPoints(pm1).addPoints(pm2).addPoints(pm3).addPoints(pm4);

        List<Integer> expected = List.of(33,21,11,22);

        List<Integer> actual = gameService.getAllPlayersAllPoints(GameService.SortType.REVERSED).get(p1);

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("can sort the list in the correct normal order")
    void returnUnsortedList(){

        GameService gameService = new GameService();
        Player p1 = new Player("Leon");
        PlayerMove pm1 = new PlayerMove();
        PlayerMove pm2 = new PlayerMove();
        PlayerMove pm3 = new PlayerMove();
        PlayerMove pm4 = new PlayerMove();


        pm1.setPoints(22).setName("Leon");
        pm2.setPoints(11).setName("Leon");
        pm3.setPoints(21).setName("Leon");
        pm4.setPoints(33).setName("Leon");

        gameService.startGame(List.of(p1)).addPoints(pm1).addPoints(pm2).addPoints(pm3).addPoints(pm4);

        List<Integer> expected = List.of(22,11,21,33);

        List<Integer> actual = gameService.getAllPlayersAllPoints(GameService.SortType.NORMAL).get(p1);

        assertEquals(expected,actual);

    }


    @Test
    @DisplayName("returns the correct amount of draws of a player")
    void returnDrawCountOfPlayer(){

        GameService gameService = new GameService();

        Player p1 = new Player("Leon");
        Player p2 = new Player("Max");

        PlayerDraw pd1 = new PlayerDraw();
        PlayerDraw pd2 = new PlayerDraw();
        pd1.setDrawPlayerName("Max").setPoints(1);
        pd2.setDrawPlayerName("Max").setPoints(1);


        gameService.startGame(List.of(p1,p2)).increasePlayerDrawCount(pd1).increasePlayerDrawCount(pd2);

        int expected = 2;
        int actual = gameService.getAllPlayersSpecialPoints(ListType.TOTAL,0).get(p2);


        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("returns the correct player with the most draws")
    void returnPlayerWithMostCounts(){

        GameService gameService = new GameService();

        Player p1 = new Player("Leon");
        Player p2 = new Player("Max");

        PlayerDraw pd1 = new PlayerDraw();
        PlayerDraw pd2 = new PlayerDraw();
        pd1.setDrawPlayerName("Max").setPoints(1);
        pd2.setDrawPlayerName("Max").setPoints(1);


        gameService.startGame(List.of(p1,p2)).increasePlayerDrawCount(pd1).increasePlayerDrawCount(pd2);

        Map<Player, Integer> expected = Map.of(p2,2);
        Map<Player, Integer> actual = gameService.getAllPlayersSpecialPoints(ListType.LOSER,0);

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("returns the correct player with the fewest draws")
    void returnPlayerWithFewestCounts(){

        GameService gameService = new GameService();

        Player p1 = new Player("Leon");
        Player p2 = new Player("Max");

        PlayerDraw pd1 = new PlayerDraw();
        PlayerDraw pd2 = new PlayerDraw();
        pd1.setDrawPlayerName("Max").setPoints(1);
        pd2.setDrawPlayerName("Max").setPoints(1);


        gameService.startGame(List.of(p1,p2)).increasePlayerDrawCount(pd1).increasePlayerDrawCount(pd2);

        Map<Player, Integer> expected = Map.of(p1,0);
        Map<Player, Integer> actual = gameService.getAllPlayersSpecialPoints(ListType.WINNER,0);

        assertEquals(expected,actual);
    }
}

