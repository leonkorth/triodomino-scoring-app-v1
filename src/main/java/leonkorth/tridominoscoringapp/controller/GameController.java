package leonkorth.tridominoscoringapp.controller;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerDraw;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import leonkorth.tridominoscoringapp.service.GameService;
import leonkorth.tridominoscoringapp.service.ListType;
import leonkorth.tridominoscoringapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @GetMapping("/game")
    public String startGame(Model model){


        gameService.startGame(playerService.getAllPlayers());

        model.addAttribute("playerNames",playerService.getAllPlayers());
        model.addAttribute("playerMove",new PlayerMove());
        model.addAttribute("playerDraw",new PlayerDraw());
        model.addAttribute("playerWhoseTurnItIs", new Player(" "));
        model.addAttribute("playerCount", gameService.getPlayerAndPoints(ListType.TOTAL).size());





        return "game";
    }

    @PostMapping("/game")
    public String getPlayerPoints(@ModelAttribute("playerMove") PlayerMove playerMove, Model model){



        gameService.addPoints(playerMove);

        model.addAttribute("playerWhoseTurnItIs", gameService.getPlayerWhoseTurnItIs());
        model.addAttribute("playerNamesAndTotalPoints", gameService.getPlayerAndPoints(ListType.TOTAL));
        model.addAttribute("playerNamesAndAllPoints", gameService.getAllPlayersAllPoints(GameService.SortType.REVERSED));
        model.addAttribute("playerNames",playerService.getAllPlayers());
        model.addAttribute("playerMove",new PlayerMove());
        model.addAttribute("playerDraw",new PlayerDraw());
        model.addAttribute("playerCount", gameService.getPlayerAndPoints(ListType.TOTAL).size());

        return "game";
    }

}
