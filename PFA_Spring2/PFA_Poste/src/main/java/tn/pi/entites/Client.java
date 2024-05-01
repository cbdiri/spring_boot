package tn.pi.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="CLIENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nom;
    private  String prenom;
    private int cin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datene;
    private int tel;
    private String email;
    private String password;
    private boolean agent;
    private boolean valide;

}
