package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {



    private List<Player> allPlayers = new ArrayList<>();

    private Map<Player, List<Integer>> allPlayersAllPoints = new LinkedHashMap<>();

    private Map<Player, Integer> allPlayersTotalPoints = new LinkedHashMap<>();

    public List<Player> startGame(List<Player> players){
        allPlayers = List.copyOf(players);

        allPlayers.forEach(p -> allPlayersAllPoints.put(p, List.of()));

        allPlayers.forEach(p -> allPlayersTotalPoints.put(p, 0));

        return allPlayers;
    }

    public GameService addPoints(String input){

        String[] part = input.split("(?<=\\D)(?=\\d)");

        String name = part[0];
        int points = Integer.parseInt(part[1]);

        Player player = getAllPlayers().stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);

        int actualPoints = allPlayersTotalPoints.get(player);

        allPlayersTotalPoints.put(player,actualPoints + points);

        List<Integer> newList = new ArrayList<>(List.copyOf(allPlayersAllPoints.get(player)));
        newList.add(points);

        allPlayersAllPoints.put(player,newList);

        return this;
    };

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public Map<Player, List<Integer>> getAllPlayersAllPoints() {
        return allPlayersAllPoints;
    }

    public Map<Player, Integer> getAllPlayersTotalPoints() {
        return allPlayersTotalPoints;
    }
}
