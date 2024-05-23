package tn.pi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entites.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByEmailAndPassword(String email, String password);

    Client findByEmail(String email);

    Client findByCin(int cin);
}
