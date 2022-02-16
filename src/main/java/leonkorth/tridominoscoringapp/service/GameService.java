package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {



    private List<Player> allPlayers = new ArrayList<>();

    private Map<Player, List<Integer>> allPlayersAllPoints = new HashMap<>();

    private Map<Player, Integer> allPlayersTotalPoints = new HashMap<>();

    public List<Player> startGame(List<Player> players){
        allPlayers = List.copyOf(players);

        allPlayers.forEach(p -> allPlayersAllPoints.put(p, List.of()));

        allPlayers.forEach(p -> allPlayersTotalPoints.put(p, 0));

        return allPlayers;
    }

    public void addPoints(){};




}
