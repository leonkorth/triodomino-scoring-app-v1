package leonkorth.tridominoscoringapp.controller;

import leonkorth.tridominoscoringapp.service.GameService;
import leonkorth.tridominoscoringapp.service.ListType;
import leonkorth.tridominoscoringapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinalController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;


    @GetMapping("/final")
    public String endGame(Model model){


        model.addAttribute("playerNamesAndTotalPoints", gameService.getPlayerAndPoints(ListType.TOTAL));
        model.addAttribute("winner", gameService.getPlayerAndPoints(ListType.WINNER));
        model.addAttribute("loser", gameService.getPlayerAndPoints(ListType.LOSER));
        model.addAttribute("playerWithMostDraws",gameService.getAllPlayersSpecialPoints(ListType.LOSER,0));
        model.addAttribute("playerWithFewestDraws",gameService.getAllPlayersSpecialPoints(ListType.WINNER,0));

        return "final";
    }



}
