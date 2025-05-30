package citeU;

import java.util.ArrayList;
import java.io.Serializable;

public class MaisonInternationale extends Maison implements Serializable {
	private static final long serialVersionUID = 1L;

	public MaisonInternationale(String nom, String localisation, String nomDirecteur) {
		super(nom, localisation, nomDirecteur);

	}

}