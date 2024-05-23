package tn.pi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.pi.entites.Client;
import tn.pi.repository.ClientRepository;

@Configuration
public class PasswordEncodingRunner {

    @Bean
    CommandLineRunner encodeExistingPasswords(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            clientRepository.findAll().forEach(client -> {
                if (!client.getPassword().startsWith("$2a$")) { // Vérifie si le mot de passe n'est pas déjà encodé
                    client.setPassword(passwordEncoder.encode(client.getPassword()));
                    clientRepository.save(client);
                }
            });
        };
    }
}
