package leonkorth.tridominoscoringapp.service;

import leonkorth.tridominoscoringapp.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    public PlayerService(){};


    private List<Player> allPlayers = new ArrayList<>();

    public List<Player> addPlayer(Player player){
        boolean playerIsNew = allPlayers.stream().map(Player::getName).noneMatch(p -> p.equals(player.getName()));
        if(!player.getName().isEmpty() && playerIsNew) allPlayers.add(player);

        return getAllPlayers();
    }

    public List<Player> getAllPlayers(){
        return allPlayers;
    }


}
