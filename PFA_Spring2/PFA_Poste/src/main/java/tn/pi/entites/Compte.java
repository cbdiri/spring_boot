package tn.pi.entites;

import jakarta.persistence.*;
import lombok.*;
import tn.pi.entites.Client;

@Entity
@Table(name="Compte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compte {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

     private double sld;



    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
