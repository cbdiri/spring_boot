package tn.pi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Optional;


@Controller
public class AccueilController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ExtraitRepository extraitRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/Agent/Accueil")
    public String Accueil(Model model){

        Long currentCin = GlobalVariables.getUser_id();
        System.out.println("l'id  de user est : " + currentCin);
        Client user = clientRepository.findById(currentCin).orElse(null);

      model.addAttribute("client",user);
        return "Agent/Accueil";
    }


    @PostMapping(path = "/Agent/transaction")
    public String Accueil2(@RequestParam("num") long num,@RequestParam("num2") long num2,@RequestParam("sld") double sld,
                       @RequestParam("typeOperation") String typeOperation ,Model model){



        Compte compte = compteRepository.findById(num).orElse(null);

        if(compte == null){
            model.addAttribute("errorMessage1", "Compte postal inexistant !");
            return Accueil(model);
        }
       else if (compte != null) {

            if(typeOperation.equals("versement")){
                double nouveauSolde = compte.getSld() + sld;
                System.out.println(nouveauSolde);
                compte.setSld(nouveauSolde);
                compteRepository.save(compte);
                //--------------------//
                Extrait ex=new Extrait();
                ex.setOpertion("versement");
                ex.setMontant_op(sld);
                ex.setCompte(compte);
                extraitRepository.save(ex);
                model.addAttribute("confirmationMessage", "Opération effectuée avec succès. !");
                return Accueil(model);
            }
            else if (typeOperation.equals("retrait") ) {
                if(compte.getSld()<sld){
                    model.addAttribute("errorMessage2", "Solde insifusant.");
                    return Accueil(model);

                }
                else if(compte.getSld()>sld){
                        double nouveauSolde = compte.getSld() - sld;
                        compte.setSld(nouveauSolde);
                        compteRepository.save(compte);
                        //-------------------//
                        Extrait ex=new Extrait();
                        ex.setOpertion("retrait");
                        ex.setMontant_op(sld);
                        ex.setCompte(compte);
                        extraitRepository.save(ex);
                    model.addAttribute("confirmationMessage", "Opération effectuée avec succès. !");
                    return Accueil(model);
                    }
            }
            else if (typeOperation.equals("virement") ) {
                if(compte.getSld()<sld){
                    model.addAttribute("errorMessage2", "Solde insifusant.");
                    return Accueil(model);
                }
                else if(compte.getSld()>sld){
                    Compte compte2 = compteRepository.findById(num2).orElse(null);
                    if(compte2 == null){
                        model.addAttribute("errorMessage3", "Compte postal déstinataire inexistant !");
                        return Accueil(model);
                    }
                    else if (compte2 != null) {
                        double nouveauSolde = compte.getSld() - sld;
                        compte.setSld(nouveauSolde);

                        double nouveauSolde2 = compte2.getSld() + sld;
                        compte2.setSld(nouveauSolde2);

                        compteRepository.save(compte);
                        //-------------------//
                        Extrait ex=new Extrait();
                        ex.setOpertion("virement out");
                        ex.setMontant_op(sld);
                        ex.setCompte(compte);
                        extraitRepository.save(ex);


                        compteRepository.save(compte2);
                        //-------------------//
                        Extrait ex2=new Extrait();
                        ex2.setOpertion("virement in");
                        ex2.setMontant_op(sld);
                        ex2.setCompte(compte2);
                        extraitRepository.save(ex2);
                        model.addAttribute("confirmationMessage", "Opération effectuée avec succès. !");
                        return Accueil(model);
                    }
                }
            }
        }


        return "Agent/Accueil";
    }



    @GetMapping(path = "/Agent/About")
    public String About(){
        System.out.println(GlobalVariables.getUser_id());
        return "Agent/About";
    }

    @GetMapping(path = "/Agent/Profile")
    public String Profile(Model model){
        Long currentCin = GlobalVariables.getUser_id();
        System.out.println("l'id  de user est : " + currentCin);
        Client user = clientRepository.findById(currentCin).orElse(null);

        model.addAttribute("client",user);

        return "Agent/Profile";
    }
    @PostMapping(path = "/Agent/updateProfile")
    public String updateProfile(@ModelAttribute("client") Client client,Model model) {
        if (!client.getPassword().startsWith("$2a$")) {  client.setPassword(passwordEncoder.encode(client.getPassword()));}
        clientRepository.save(client);
        model.addAttribute("confirmationMessage", "Profil mis à jour avec succès !");
        return Profile(model);
    }

    @GetMapping(path = "/Agent/Logout")
    public String Logout(){

        return "redirect:/login";
    }




}
