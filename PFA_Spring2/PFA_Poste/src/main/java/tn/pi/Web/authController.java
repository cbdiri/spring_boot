package tn.pi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.pi.GlobalVariables;
import tn.pi.entites.Client;
import tn.pi.repository.ClientRepository;

@Controller
public class authController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(path = "/")
    public String auth() {
        return "redirect:/login";
    }

    @GetMapping(path = "/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "Email ou mot de passe incorrect. Veuillez réessayer.");
        }
        return "auth/login";
    }
    @PostMapping(path = "/login")
    public String auth(@RequestParam("username") String username, @RequestParam("password") String password,Model model){

        Client user = clientRepository.findByEmailAndPassword(username,password);

        if (user != null) {
            GlobalVariables.setUser_id(user.getId());

            if(user.isAgent()){return "redirect:/Accueil";}
            else if (!user.isAgent() && user.isValide()) {return "redirect:/Index";}
            else return "redirect:/login";

        } else {
            model.addAttribute("errorMessage", "email password incorrect. Veuillez réessayer.");

            return "auth//login";
        }
    }

    @GetMapping(path = "/About2")
    public String about() {
        return "auth/About2";
    }
}
