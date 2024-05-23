package tn.pi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.pi.entites.Client;
import tn.pi.entites.Compte;
import tn.pi.entites.Extrait;
import tn.pi.repository.ClientRepository;
import tn.pi.repository.CompteRepository;
import tn.pi.repository.ExtraitRepository;

import java.util.List;

@Controller
public class CompteController {

     @Autowired
     private  CompteRepository compteRepository;

     @Autowired
     private ClientRepository clientRepository;

    @Autowired
    private ExtraitRepository extraitRepository;


     @GetMapping("/Agent/Compte")
     public String Compte(Model model) {
          List<Compte> comptetList = compteRepository.findAll();
          model.addAttribute("comptes",comptetList);

          return "Agent/Compte";
     }

    @GetMapping("/Agent/AddCompte")
    public String Compte2(Model model) {


        return "Agent/addCompte";
    }

     @PostMapping("/Agent/AddCompte")
     public String AddCompte(@RequestParam("cin") int cin, @RequestParam("sld") double sld, Model model)  {

          System.out.println(cin);
          System.out.println(sld   );
          Client user = clientRepository.findByCin(cin);
         //System.out.println(user.getId());
         if(user == null){
             model.addAttribute("errorMessage", "CIN Client inexistant.. Veuillez réessayer.");
             return Compte2(model);
         }
         else if (user != null) {

               Compte compte = new Compte();
               compte.setSld(sld);
               compte.setClient(user);
               compteRepository.save(compte);
               model.addAttribute("confirmationMessage", "Compte Ajouté avec succès !");
               return Compte(model);
          }


          return "redirect:/Compte";
     }


    @GetMapping("/Agent/AfficherCompte")
    public String AfficherCompte(Model model) {


        return "Agent/AfficherCompte";
    }

    @PostMapping("/Agent/AfficherComptePArCin")
    public String AfficherCompte2(@RequestParam("cin") int cin, Model model) {

        Client user = clientRepository.findByCin(cin);
        // System.out.println(user.getId());
        if(user == null){
            model.addAttribute("errorMessage", "CIN Client inexistant. Veuillez réessayer.");
            return  AfficherCompte(model);
        }
        else if (user != null) {
            List<Compte> compteList = compteRepository.findByClientId(user.getId());

                model.addAttribute("comptes", compteList);

            return "Agent/AfficherCompte";
        }
        return "Agent/AfficherCompte";
    }


    @PostMapping("/Agent/AfficherComptePArnum")
    public String AfficherCompte3(@RequestParam("num") long num, Model model) {

        List<Compte> compteList = compteRepository.findByNum(num);

        if(compteList.size() == 0){
            model.addAttribute("errorMessage2", "Numéro Compte inexistant. Veuillez réessayer.");
            return  AfficherCompte(model);
        }
        else if (compteList.size() >= 1) {
            System.out.println(compteList);
            model.addAttribute("comptes", compteList);
            return "Agent/AfficherCompte";
        }

        return "Agent/AfficherCompte";
    }


    @GetMapping("/Agent/Extrait")
    public String Extrait(Model model) {



        return "Agent/Extrait";
    }
    @PostMapping("/Agent/Extrait")
    public String Extrait(@RequestParam("num") long num, Model model) {

        List<Compte> compteList = compteRepository.findByNum(num);
        if(compteList.size() == 0){
            model.addAttribute("errorMessage", "Numéro Compte inexistant. Veuillez réessayer.");
            return Extrait(model);
        }
        else if (compteList.size() >= 1) {

            List<Extrait> Extrairs = extraitRepository.findByCompte_Num(num);
            model.addAttribute("extrais",Extrairs);
        }

        return "Agent/Extrait";
    }

}
