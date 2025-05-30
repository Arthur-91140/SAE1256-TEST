package citeU;

import java.io.Serializable;

public class Cafet extends Restauration implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public Cafet(String nom, int capacite) {
        super(nom, capacite);
    }
}