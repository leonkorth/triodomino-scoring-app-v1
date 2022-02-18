package leonkorth.tridominoscoringapp.controller;

import leonkorth.tridominoscoringapp.service.GameService;
import leonkorth.tridominoscoringapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinalController {

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;


    @GetMapping("/final")
    public String endGame(Model model){


        model.addAttribute("playerNamesAndTotalPoints", gameService.getAllPlayersTotalPoints());
        model.addAttribute("winner", gameService.getWinnerAndPoints());
        model.addAttribute("loser", gameService.getLoserAndPoints());


        return "final";
    }



}
