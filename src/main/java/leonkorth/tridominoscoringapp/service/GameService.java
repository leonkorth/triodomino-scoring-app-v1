package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameService {

    @Autowired
    GameActionReversingService gameActionReversingService = new GameActionReversingService();

    public enum SortType{
        REVERSED,
        NORMAL
    }

    private static <T> Collector<T, ?, Stream<T> > reverseStream()
    {
        return Collectors
                .collectingAndThen(Collectors.toList(),
                        list -> {
                            Collections.reverse(list);
                            return list.stream();
                        });
    }

    private Player lastPlayer;

    private List<Player> allPlayers = new ArrayList<>();

    private Map<Player, List<Integer>> allPlayersAllPoints = new LinkedHashMap<>();

    private Map<Player, Integer> allPlayersTotalPoints = new LinkedHashMap<>();

    private Map<Player, List<Integer>> allPlayersSpecialPoints = new LinkedHashMap<>();


    public GameService startGame(List<Player> players){

        allPlayers = List.copyOf(players);

        allPlayers.forEach(p -> allPlayersAllPoints.put(p, List.of()));

        allPlayers.forEach(p -> allPlayersSpecialPoints.put(p, List.of(0,0,0))); /* gezogen, zumachen, brücke */

        allPlayers.forEach(p -> allPlayersTotalPoints.put(p, 0));

        return this;
    }

    public GameService addPoints(PlayerAction playerMove){

        gameActionReversingService.addAction(playerMove);

        String name = playerMove.getPlayerName();
        int points = playerMove.getNumber();

        Player player = getAllPlayers().stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);

        lastPlayer = player;
        int actualPoints = allPlayersTotalPoints.get(player);

        allPlayersTotalPoints.put(player,actualPoints + points);

        List<Integer> newList = new ArrayList<>(List.copyOf(allPlayersAllPoints.get(player)));
        newList.add(points);

        allPlayersAllPoints.put(player,newList);

        return this;
    };

    public GameService increasePlayerDrawCount(PlayerAction playerDraw){

        gameActionReversingService.addAction(playerDraw);

        String name = playerDraw.getPlayerName();
        int drawCount = playerDraw.getNumber();

        Player player = getAllPlayers().stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);

        List<Integer> oldSpecialPoints = new ArrayList<>(List.copyOf(allPlayersSpecialPoints.get(player)));

        oldSpecialPoints.set(0, oldSpecialPoints.get(0) + drawCount);

        allPlayersSpecialPoints.put(player, oldSpecialPoints);

        return this;
    }

    public List<Player> getAllPlayers() {
        if(allPlayers.isEmpty()) return List.of();
        return allPlayers;
    }

    public Map<Player, List<Integer>> getAllPlayersAllPoints(SortType sortType) {
        switch (sortType){
            case NORMAL -> {
                return allPlayersAllPoints;
            }
            case REVERSED -> {

                Map<Player,List<Integer>> newMap = new LinkedHashMap<>();
                for(var entry : allPlayersAllPoints.entrySet()){

                    List<Integer> sorted = entry.getValue()
                            .parallelStream()
                            .collect(reverseStream())
                            .toList();

                    newMap.put(entry.getKey(),sorted);
                }
                return newMap;
            }
        }
        return null;
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

        if(index < 0) return new Player(" ");
        else if(index + 1 == allPlayers.size()) return allPlayers.get(0);
        else return allPlayers.get(index + 1);

    }

    public Map<Player, Integer> getAllPlayersSpecialPoints(ListType listType, int index) {

        Map <Player, Integer> newMap = new LinkedHashMap<>();

        for(var entry : allPlayersSpecialPoints.entrySet()){
            newMap.put(entry.getKey(),entry.getValue().get(index));
        }

        switch (listType){
            case TOTAL -> {
                return newMap;
            }
            case WINNER -> {
                int minValue = (Collections.min((newMap.values())));
                for(Map.Entry<Player, Integer> entry : newMap.entrySet()){
                    if(entry.getValue() == minValue) return Map.of(entry.getKey(),entry.getValue());
                }
            }
            case LOSER -> {
                int maxValue = (Collections.max(newMap.values()));
                for(Map.Entry<Player, Integer> entry : newMap.entrySet()){
                    if(entry.getValue() == maxValue) return Map.of(entry.getKey(),entry.getValue());
                }
            }
            default -> {
                return Map.of();
            }
        }
        return Map.of();
    }


    public void setLastPlayer(Player lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public void setAllPlayers(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public void setAllPlayersAllPoints(Map<Player, List<Integer>> allPlayersAllPoints) {
        this.allPlayersAllPoints = allPlayersAllPoints;
    }

    public void setAllPlayersTotalPoints(Map<Player, Integer> allPlayersTotalPoints) {
        this.allPlayersTotalPoints = allPlayersTotalPoints;
    }

    public void setAllPlayersSpecialPoints(Map<Player, List<Integer>> allPlayersSpecialPoints) {
        this.allPlayersSpecialPoints = allPlayersSpecialPoints;
    }
}
