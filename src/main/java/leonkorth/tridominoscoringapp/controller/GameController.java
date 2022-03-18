package leonkorth.tridominoscoringapp.controller;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerDraw;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import leonkorth.tridominoscoringapp.service.GameActionReversingService;
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
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private GameActionReversingService gameActionReversingService;

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

    @PostMapping("/game/addPoints")
    public String getPlayerPoints(@ModelAttribute("playerMove") PlayerMove playerMove, Model model){

        gameService.addPoints(playerMove);

        extractedModel(model);

        return "game";
    }

    private void extractedModel(Model model) {
        model.addAttribute("playerWhoseTurnItIs", gameService.getPlayerWhoseTurnItIs());
        model.addAttribute("playerNamesAndTotalPoints", gameService.getPlayerAndPoints(ListType.TOTAL));
        model.addAttribute("playerNamesAndAllPoints", gameService.getAllPlayersAllPoints(GameService.SortType.REVERSED));
        model.addAttribute("playerNames",playerService.getAllPlayers());
        model.addAttribute("playerMove",new PlayerMove());
        model.addAttribute("playerDraw",new PlayerDraw());
        model.addAttribute("playerCount", gameService.getPlayerAndPoints(ListType.TOTAL).size());
        model.addAttribute("playerNamesAndSpecialPoints", gameService.getAllPlayersSpecialPoints(ListType.TOTAL,0));
    }

    @PostMapping("/game/addDrawCount")
    public String getPlayerPoints(@ModelAttribute("playerDraw") PlayerDraw playerDraw, Model model){


        gameService.increasePlayerDrawCount(playerDraw);

        extractedModel(model);

        return "game";
    }

    @GetMapping("/game/undo")
    public String undoLastAction(Model model){

        gameService.gameActionReversingService.reverseLastAction();

        extractedModel(model);

        return "game";
    }



}
