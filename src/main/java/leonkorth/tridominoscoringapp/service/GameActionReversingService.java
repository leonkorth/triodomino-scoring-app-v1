package leonkorth.tridominoscoringapp.service;


import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerAction;
import leonkorth.tridominoscoringapp.model.PlayerDraw;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameActionReversingService {

    GameService gameService;

    public GameActionReversingService(GameService gameService) {
        this.gameService = gameService;
    }

    private List<PlayerAction> allActions = new ArrayList<>();

    public GameActionReversingService addAction(PlayerAction playerAction){
        allActions.add(playerAction);
        return this;
    }

    public GameActionReversingService reverseLastAction() {

        if(allActions.isEmpty()) return this;

        int lastActionIndex = allActions.size() - 1;

        PlayerAction lastAction = allActions.get(lastActionIndex);

        String playerName = lastAction.getPlayerName();
        int number = lastAction.getNumber();

        allActions.remove(lastActionIndex);

        Player player = gameService.getAllPlayers().stream().filter(x -> x.getName().equals(playerName)).findAny().orElse(null);

        if(lastAction.getClass().equals(PlayerMove.class)){

            Map<Player, Integer> totalPoints = gameService.getPlayerAndPoints(ListType.TOTAL);

            if(totalPoints.isEmpty()) return this;

            totalPoints.put(player, totalPoints.get(player) - number);
            gameService.setAllPlayersTotalPoints(Map.copyOf(totalPoints));


            Map<Player, List<Integer>> allPointsAllPlayers = new  LinkedHashMap<>(Map.copyOf(gameService.getAllPlayersAllPoints(GameService.SortType.NORMAL)));
            List<Integer> allPointsForLastPlayer = gameService.getAllPlayersAllPoints(GameService.SortType.NORMAL).get(player);

            if(allPointsForLastPlayer.isEmpty()) return this;

            allPointsForLastPlayer.remove(allPointsForLastPlayer.size() -1);

            allPointsAllPlayers.put(player, allPointsForLastPlayer);

            gameService.setAllPlayersAllPoints(allPointsAllPlayers);

        }
        else if(lastAction.getClass().equals(PlayerDraw.class)){

                Map<Player, List<Integer>> allPlayersSpecialPoints =  new LinkedHashMap<>(Map.copyOf(gameService.getAllPlayersSpecialPoints()));
                List<Integer> lastPlayerSpecialPoints = allPlayersSpecialPoints.get(player);
                lastPlayerSpecialPoints.set(0, lastPlayerSpecialPoints.get(0) - number);

                gameService.setAllPlayersSpecialPoints(allPlayersSpecialPoints);

        }
        return this;
    }

    public List<PlayerAction> getAllActions() {
        return this.allActions;
    }
}
