package org.txto.test;

import java.io.FileNotFoundException;

import org.grammaticalframework.pgf.*;

public class Test {

	public static void main(String[] args) {
        PGF gr = null;
        try {
            gr = PGF.readPGF("Food.pgf");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (PGFError e) {
            e.printStackTrace();
            return;
        }
        
        Concr fre = gr.getLanguages().get("FoodFre");
        	
		try {
			
			System.out.println("\nCatégorie: " + gr.getStartCat() + "\n\n");

			Iterable<TokenProb> complete = fre.complete(gr.getStartCat(),               // catégorie, dans ce cas-ci la catégorie de départ de la grammaire
					                                    "cette pizza italienne est",    // la phrase jusqu'à date
					                                    "t");                           // le début du prochain mot

			for(TokenProb tokenProb : complete) {
				System.out.println(tokenProb.getToken() + "       " + tokenProb.getProb());
			}

		} catch (ParseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}