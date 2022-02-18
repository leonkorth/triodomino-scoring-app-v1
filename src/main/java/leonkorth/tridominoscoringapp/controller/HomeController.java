package leonkorth.tridominoscoringapp.controller;

import leonkorth.tridominoscoringapp.model.Player;
import leonkorth.tridominoscoringapp.model.PlayerMove;
import leonkorth.tridominoscoringapp.service.GameService;
import leonkorth.tridominoscoringapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public String home(Model model){

        return "home";
    }



    @GetMapping("/game")
    public String startGame(Model model){


        model.addAttribute("playerNames",playerService.getAllPlayers());
        model.addAttribute("playerMove",new PlayerMove());

        gameService.startGame(playerService.getAllPlayers());



        return "game";
    }


    @GetMapping("/final")
    public String endGame(Model model){


        model.addAttribute("playerNames",playerService.getAllPlayers());
        model.addAttribute("playerNamesAndTotalPoints", gameService.getAllPlayersTotalPoints());


        return "final";
    }


    @PostMapping("/game")
    public String getPlayerPoints(@ModelAttribute("playerMove") PlayerMove playerMove, Model model){

        gameService.addPoints(playerMove.toString());

        model.addAttribute("playerNamesAndTotalPoints", gameService.getAllPlayersTotalPoints());
        model.addAttribute("playerNamesAndAllPoints", gameService.getAllPlayersAllPoints());
        model.addAttribute("playerNames",playerService.getAllPlayers());
        model.addAttribute("playerMove",new PlayerMove());

        return "game";
    }


    @PostMapping ("/addPlayer")
    public String names(@RequestParam("playerName") String playerName, Model model){

        model.addAttribute("playerNames", playerService.addPlayer(new Player(playerName)));

        return "home";
    }


}
