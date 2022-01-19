package nikitagru.restaraunt.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagename;

    private String text;
}
