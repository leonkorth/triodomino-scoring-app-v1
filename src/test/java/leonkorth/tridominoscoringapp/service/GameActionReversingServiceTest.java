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
    @DisplayName("saves the actions correctly")
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
    @DisplayName("reverses the actions correctly")
    void reverseActions(){

        GameService gameService = new GameService();

        GameActionReversingService gameActionReversingService = new GameActionReversingService();

        Player p1 = new Player("Leon");

        PlayerAction pa1 = new PlayerMove().setPlayerName("Leon").setNumber(10);
        PlayerAction pa2 = new PlayerDraw().setPlayerName("Leon");
        PlayerAction pa3 = new PlayerMove().setPlayerName("Leon").setNumber(11);


        gameService.startGame(List.of(p1)).addPoints(pa1).increasePlayerDrawCount(pa2).addPoints(pa3);
        gameActionReversingService.reverseLastAction();

        List<PlayerAction> expectedActions = List.of(pa1, pa2);
        List<PlayerAction> actualActions = gameActionReversingService.getAllActions();

        Map<Player, List<Integer>> expectedAllPlayersAllPoints = Map.of(p1, List.of(10));
        Map<Player, List<Integer>> actualAllPlayersAllPoints = gameService.getAllPlayersAllPoints(GameService.SortType.NORMAL);

        Map<Player, Integer> expectedTotalPoints = Map.of(p1, 10);
        Map<Player, Integer> actualTotalPoints = gameService.getPlayerAndPoints(ListType.TOTAL);

        assertEquals(expectedActions,actualActions);
        assertEquals(expectedAllPlayersAllPoints,actualAllPlayersAllPoints);
        assertEquals(expectedTotalPoints,actualTotalPoints);
    }
}
