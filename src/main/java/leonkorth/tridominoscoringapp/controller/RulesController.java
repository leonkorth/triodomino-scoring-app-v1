package leonkorth.tridominoscoringapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RulesController {

    @GetMapping("/rules")
    public String home(Model model){

        return "rules";
    }
}
