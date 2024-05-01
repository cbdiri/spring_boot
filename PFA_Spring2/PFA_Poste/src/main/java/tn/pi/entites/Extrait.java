package tn.pi.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="Extrait")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Extrait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_op;

    private  String opertion;

    private  double montant_op;

    @ManyToOne
    @JoinColumn(name = "Compte_num")
    private Compte compte;

    @PrePersist
    protected void onCreate() {
        date_op = new Date();
    }
}
