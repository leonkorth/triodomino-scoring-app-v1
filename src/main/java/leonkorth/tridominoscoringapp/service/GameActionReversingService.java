package leonkorth.tridominoscoringapp.service;


import leonkorth.tridominoscoringapp.model.PlayerAction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameActionReversingService {



    private List<PlayerAction> allActions = new ArrayList<>();

    public GameActionReversingService addAction(PlayerAction playerAction){
        allActions.add(playerAction);
        return this;
    }

    public GameActionReversingService reverseLastAction() {
        return this;
    }

    public List<PlayerAction> getAllActions() {
        return this.allActions;
    }
}
