package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entites.Client;
import tn.pi.entites.Compte;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte,Long> {




    List<Compte> findByClientId(Long id);

    List<Compte> findByNum(long num);

}
