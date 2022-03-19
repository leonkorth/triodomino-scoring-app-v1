package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerAction;
import leonkorth.tridominoscoringapp.model.PlayerDraw;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameActionReversingServiceTest {

    @Test
    @DisplayName("saves the actions")
    void saveActions(){

        GameService gameService = new GameService();


        Player p1 = new Player("Leon");

        PlayerAction pa1 = new PlayerMove().setPlayerName("Leon").setNumber(10);
        PlayerAction pa2 = new PlayerDraw().setPlayerName("Leon");
        PlayerAction pa3 = new PlayerMove().setPlayerName("Leon").setNumber(11);


        gameService.startGame(List.of(p1));
        gameService.addPoints(pa1).increasePlayerDrawCount(pa2).addPoints(pa3);


        List<PlayerAction> expected = List.of(pa1, pa2, pa3);
        List<PlayerAction> actual = gameService.gameActionReversingService.getAllActions();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("reverses the points")
    void reversePoints(){

        GameService gameService = new GameService();

        Player p1 = new Player("Leon");

        PlayerAction pa1 = new PlayerMove().setPlayerName("Leon").setNumber(10);
        PlayerAction pa2 = new PlayerDraw().setPlayerName("Leon");
        PlayerAction pa3 = new PlayerMove().setPlayerName("Leon").setNumber(11);


        gameService.startGame(List.of(p1)).addPoints(pa1).increasePlayerDrawCount(pa2).addPoints(pa3);
        gameService.gameActionReversingService.reverseLastAction();

        List<PlayerAction> expectedActions = List.of(pa1, pa2);
        List<PlayerAction> actualActions = gameService.gameActionReversingService.getAllActions();

        Map<Player, List<Integer>> expectedAllPlayersAllPoints = Map.of(p1, List.of(10));
        Map<Player, List<Integer>> actualAllPlayersAllPoints = gameService.getAllPlayersAllPoints(GameService.SortType.NORMAL);

        Map<Player, Integer> expectedTotalPoints = Map.of(p1, 10);
        Map<Player, Integer> actualTotalPoints = gameService.getPlayerAndPoints(ListType.TOTAL);

        assertEquals(expectedActions,actualActions);
        assertEquals(expectedAllPlayersAllPoints,actualAllPlayersAllPoints);
        assertEquals(expectedTotalPoints,actualTotalPoints);
    }

    @Test
    @DisplayName("reverses the draws")
    void reverseDraws(){

        GameService gameService = new GameService();

        Player p1 = new Player("Leon");

        PlayerAction pa1 = new PlayerMove().setPlayerName("Leon").setNumber(10);
        PlayerAction pa2 = new PlayerMove().setPlayerName("Leon").setNumber(11);
        PlayerAction pa3 = new PlayerDraw().setPlayerName("Leon");



        gameService.startGame(List.of(p1)).addPoints(pa1).addPoints(pa2).increasePlayerDrawCount(pa3);
        gameService.gameActionReversingService.reverseLastAction();

        List<PlayerAction> expectedActions = List.of(pa1, pa2);
        List<PlayerAction> actualActions = gameService.gameActionReversingService.getAllActions();

        Map<Player, List<Integer>> expectedAllPlayersAllPoints = Map.of(p1, List.of(10,11));
        Map<Player, List<Integer>> actualAllPlayersAllPoints = gameService.getAllPlayersAllPoints(GameService.SortType.NORMAL);

        Map<Player, Integer> expectedTotalPoints = Map.of(p1, 21);
        Map<Player, Integer> actualTotalPoints = gameService.getPlayerAndPoints(ListType.TOTAL);

        Map<Player, Integer> expectedSpecialPoints = Map.of(p1, 0);
        Map<Player, Integer> actualSpecialPoints = gameService.getAllPlayersSpecialPoints(ListType.TOTAL,0);

        assertEquals(expectedActions,actualActions);
        assertEquals(expectedAllPlayersAllPoints,actualAllPlayersAllPoints);
        assertEquals(expectedTotalPoints,actualTotalPoints);
        assertEquals(expectedSpecialPoints,actualSpecialPoints);
    }

    @Test
    @DisplayName("reverses all actions and removes all players")
    void reverseAllActions(){

        GameService gameService = new GameService();

        Player p1 = new  Player("Leon");
        Player p2 = new Player("Eleon");

        PlayerAction pa1 = new PlayerMove().setPlayerName("Leon").setNumber(10);
        PlayerAction pa3 = new PlayerMove().setPlayerName("Leon").setNumber(11);
        PlayerAction pa5 = new PlayerDraw().setPlayerName("Leon");

        PlayerAction pa2 = new PlayerMove().setPlayerName("Eleon").setNumber(20);
        PlayerAction pa4 = new PlayerMove().setPlayerName("Eleon").setNumber(21);
        PlayerAction pa6 = new PlayerDraw().setPlayerName("Eleon");

        gameService.startGame(List.of(p1, p2)).addPoints(pa1).addPoints(pa2).addPoints(pa3)
                .addPoints(pa4).increasePlayerDrawCount(pa5).increasePlayerDrawCount(pa6);

        gameService.gameActionReversingService.reverseAllActionsAllPlayers();

        List<PlayerAction> expectedActions = List.of();
        List<PlayerAction> actualActions = gameService.gameActionReversingService.getAllActions();

        Map<Player, List<Integer>> expectedAllPlayersAllPoints = Map.of();
        Map<Player, List<Integer>> actualAllPlayersAllPoints = gameService.getAllPlayersAllPoints(GameService.SortType.NORMAL);

        Map<Player, Integer> expectedTotalPoints = Map.of();
        Map<Player, Integer> actualTotalPoints = gameService.getPlayerAndPoints(ListType.TOTAL);

        Map<Player, Integer> expectedSpecialPoints = Map.of();
        Map<Player, Integer> actualSpecialPoints = gameService.getAllPlayersSpecialPoints(ListType.TOTAL,0);

        List<Player> expectedPlayers = List.of();
        List<Player> actualPlayers = gameService.getAllPlayers();


        assertEquals(expectedActions,actualActions);
        assertEquals(expectedAllPlayersAllPoints,actualAllPlayersAllPoints);
        assertEquals(expectedTotalPoints,actualTotalPoints);
        assertEquals(expectedSpecialPoints,actualSpecialPoints);
        assertEquals(expectedPlayers,actualPlayers);

    }
}
