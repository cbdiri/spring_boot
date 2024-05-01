package tn.pi.Web;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.pi.GlobalVariables;
import tn.pi.entites.Client;
import tn.pi.entites.Compte;
import tn.pi.entites.Extrait;
import tn.pi.repository.ClientRepository;
import tn.pi.repository.CompteRepository;
import tn.pi.repository.ExtraitRepository;

import java.util.List;

@Controller
public class Client2Controller {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ExtraitRepository extraitRepository;

    @GetMapping(path = "/Index")
    public String Accueil(Model model){

        Long currentCin = GlobalVariables.getUser_id();
        System.out.println("l'id  de user est : " + currentCin);
        Client user = clientRepository.findById(currentCin).orElse(null);

        model.addAttribute("client",user);
        return "Client/Index";
    }


    @GetMapping(path = "/Profile2")
    public String Profile(Model model){
        Long currentCin = GlobalVariables.getUser_id();
        System.out.println("l'id  de user est : " + currentCin);
        Client user = clientRepository.findById(currentCin).orElse(null);

        model.addAttribute("client",user);

        return "Client/Profile2";
    }
    @PostMapping(path = "/updateProfile2")
    public String updateProfile(@ModelAttribute("client") Client client,Model model) {

        clientRepository.save(client);
        model.addAttribute("confirmationMessage", "Profil mis à jour avec succès !");
        return Profile(model);
    }


    @GetMapping("/Consulter")
    public String Consulter(Model model) {


        Client user = clientRepository.findById(GlobalVariables.getUser_id()).orElse(null);


        if (user != null) {
            model.addAttribute("client", user);

            List<Compte> compteList = compteRepository.findByClientId(user.getId());

            model.addAttribute("comptes", compteList);

            return "Client/Consulter";
        }
        return "Client/Consulter";
    }


    @GetMapping("/virement")
    public String virement(Model model) {


        Client user = clientRepository.findById(GlobalVariables.getUser_id()).orElse(null);


        if (user != null) {
            model.addAttribute("client", user);

            List<Compte> compteList = compteRepository.findByClientId(user.getId());

            model.addAttribute("comptes", compteList);

            return "Client/virement";
        }
        return "Client/virement";
    }




    @PostMapping("/virement")
    public String virement2(@RequestParam("numCompte") long num,@RequestParam("num2") long num2,@RequestParam("sld") double sld,Model model) {


        Compte compte = compteRepository.findById(num).orElse(null);
        if (compte != null) {
            if(compte.getSld() <= sld){
                model.addAttribute("errorMessage", "Solde insifusant.");
                return virement(model);

            }
           else if (compte.getSld() > sld) {
                Compte compte2 = compteRepository.findById(num2).orElse(null);
                if (compte2 == null) {
                    model.addAttribute("errorMessage", "Compte postal inexistant. .");
                    return virement(model);
                }
                else if (compte2 != null) {
                    double nouveauSolde = compte.getSld() - sld;
                    compte.setSld(nouveauSolde);

                    double nouveauSolde2 = compte2.getSld() + sld;
                    compte2.setSld(nouveauSolde2);

                    compteRepository.save(compte);
                    //---------------------//
                    Extrait ex=new Extrait();
                    ex.setOpertion("virement out");
                    ex.setMontant_op(sld);
                    ex.setCompte(compte);
                    extraitRepository.save(ex);
                    //------------------------------//
                    compteRepository.save(compte2);
                    //-------------------//
                    Extrait ex2=new Extrait();
                    ex2.setOpertion("virement in");
                    ex2.setMontant_op(sld);
                    ex2.setCompte(compte2);
                    extraitRepository.save(ex2);
                    model.addAttribute("confirmationMessage", "Opération effectuée avec succès. !");
                    return virement(model);
                }
            }
        }

        return "Client/virement";
    }

    @GetMapping(path = "/Extrait2")
    public String Extrait2(Model model){

        Client user = clientRepository.findById(GlobalVariables.getUser_id()).orElse(null);


        if (user != null) {
            model.addAttribute("client", user);

            List<Compte> compteList = compteRepository.findByClientId(user.getId());

            model.addAttribute("comptes", compteList);

        }
        return "Client/Extrait2";
    }

    @PostMapping("/Extrait2")
    public String Extrait2(@RequestParam("numCompte") long num, Model model) {

        Client user = clientRepository.findById(GlobalVariables.getUser_id()).orElse(null);


        if (user != null) {
            model.addAttribute("client", user);

            List<Compte> compteList = compteRepository.findByClientId(user.getId());

            model.addAttribute("comptes", compteList);

        }

        if (num != 0) {
            List<Compte> compteList = compteRepository.findByNum(num);
            System.out.println(compteList);

            List<Extrait> Extrairs = extraitRepository.findByCompte_Num(num);



            model.addAttribute("extrais",Extrairs);
        }

        return "Client/Extrait2";
    }





    @GetMapping(path = "/About3")
    public String About(){
        System.out.println(GlobalVariables.getUser_id());
        return "Client/About3";
    }

    @GetMapping(path = "/Logout2")
    public String Logout(){

        return "redirect:/login";
    }
}
