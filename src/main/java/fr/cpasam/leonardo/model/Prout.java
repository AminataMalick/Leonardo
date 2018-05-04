package fr.cpasam.leonardo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Prout {
    @Id
    @GeneratedValue
    public long id;

    public String text;

    public Prout() {
    }
}
