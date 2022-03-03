package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerDraw;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameService {

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

    public GameService increasePlayerDrawCount(PlayerDraw playerDraw){

        System.out.println(playerDraw);

        String name = playerDraw.getDrawPlayerName();
        int drawCount = playerDraw.getDrawCount();

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

        if(index < 0) return new Player("Test");
        else if(index + 1 == allPlayers.size()) return allPlayers.get(0);
        else return allPlayers.get(index + 1);

    }

    public Map<Player, List<Integer>> getAllPlayersSpecialPoints() {
        return allPlayersSpecialPoints;
    }
}
