package leonkorth.tridominoscoringapp.controller;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/")
    public String home(Model model){

        return "home";
    }


    @GetMapping("/addPlayer")
    public String names(@RequestParam("playerName") String playerName, Model model){

        model.addAttribute("playerNames", playerService.addPlayer(new Player(playerName)));

        return "home";
    }


}
