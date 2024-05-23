package tn.pi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.pi.entites.Client;
import tn.pi.repository.ClientRepository;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping(path = "/Agent/Clients")
    public String index(Model model){
        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("clients",clientList);
        return "Agent/clients";
    }

    @GetMapping("/Agent/ajouterClient")
    public String afficherFormulaireAjoutClient(Model model) {
        return "Agent/addClient";
    }

    @PostMapping("/Agent/ajouterClient")
    public String ajouterClient(@ModelAttribute Client client,Model model) {

        if (!client.getPassword().startsWith("$2a$")) {  client.setPassword(passwordEncoder.encode(client.getPassword()));}
        clientRepository.save(client);

        model.addAttribute("confirmationMessage", "Client Ajouter avec succès !");
        return afficherFormulaireAjoutClient(model);
    }

    @GetMapping("/Agent/EditClient")
    public String EditClient(Model model) {

        return "Agent/EditClient";
    }

    @PostMapping("/Agent/EditClient")
    public String EditClient2(@RequestParam("cin") int cin,Model model)  {

        System.out.println(cin);
        Client user = clientRepository.findByCin(cin);
        if(user == null){
            model.addAttribute("errorMessage", "Client inexistant. Veuillez réessayer.");
            return EditClient(model);
        }
      else {
            System.out.println(user);
            model.addAttribute("client", user);
            return EditClient(model);
        }


    }

    @PostMapping(path = "/Agent/updateClient")
    public String updateClient(@ModelAttribute("client") Client client,Model model) {
        if (!client.getPassword().startsWith("$2a$")) {  client.setPassword(passwordEncoder.encode(client.getPassword()));}

        clientRepository.save(client);
        model.addAttribute("confirmationMessage", "Profil mis à jour avec succès !");

        return EditClient(model);
    }
}
