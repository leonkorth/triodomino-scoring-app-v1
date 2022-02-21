package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    private Player lastPlayer;

    private List<Player> allPlayers = new ArrayList<>();

    private Map<Player, List<Integer>> allPlayersAllPoints = new LinkedHashMap<>();

    private Map<Player, Integer> allPlayersTotalPoints = new LinkedHashMap<>();

    public List<Player> startGame(List<Player> players){
        allPlayers = List.copyOf(players);

        allPlayers.forEach(p -> allPlayersAllPoints.put(p, List.of()));

        allPlayers.forEach(p -> allPlayersTotalPoints.put(p, 0));

        return allPlayers;
    }

    public GameService addPoints(PlayerMove playerMove){


        String name = playerMove.getName();
        int points = playerMove.getPoints();

        Player player = getAllPlayers().stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);

        lastPlayer = player;

        int actualPoints = allPlayersTotalPoints.get(player);

        allPlayersTotalPoints.put(player,actualPoints + points);

        List<Integer> newList = new ArrayList<>(List.copyOf(allPlayersAllPoints.get(player)));
        newList.add(points);

        allPlayersAllPoints.put(player,newList);

        return this;
    };

    public List<Player> getAllPlayers() {
        if(allPlayers.isEmpty()) return List.of();
        return allPlayers;
    }

    public Map<Player, List<Integer>> getAllPlayersAllPoints() {
        return allPlayersAllPoints;
    }

    public Map<Player, Integer> getPlayerAndPoints(ListType listType){

        switch(listType){
            case TOTAL -> {
                return allPlayersTotalPoints;
            }
            
            case LOSER -> {
                int minValue = (Collections.min(allPlayersTotalPoints.values()));
                for(Map.Entry<Player, Integer> entry : allPlayersTotalPoints.entrySet()){
                    if(entry.getValue() == minValue) return Map.of(entry.getKey(), entry.getValue());
                }
                return Map.of();
            }
            
            case WINNER -> {
                int maxValue = (Collections.max(allPlayersTotalPoints.values()));
                for(Map.Entry<Player, Integer> entry : allPlayersTotalPoints.entrySet()) {
                    if(entry.getValue() == maxValue) return Map.of(entry.getKey(),entry.getValue());
                }
                return Map.of();
            }
            default -> {
                return Map.of();
            }
        }
    }

    public Player getPlayerWhoseTurnItIs(){

        List<Player> allPlayers = List.copyOf(getAllPlayers());

        int index = allPlayers.indexOf(lastPlayer);

        if(index < 0) return new Player("Test");
        else if(index + 1 == allPlayers.size()) return allPlayers.get(0);
        else return allPlayers.get(index + 1);

    }
}
