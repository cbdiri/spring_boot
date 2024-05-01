package tn.pi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entites.Extrait;

import java.util.List;

public interface ExtraitRepository  extends JpaRepository<Extrait,Long> {

    List<Extrait> findByCompte_Num(Long compteNum);
}
