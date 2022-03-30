package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class ReginePrimaSoluzione {
	
	List<Integer> soluzione;
	
	public List<Integer> cercaRegine(int N) {    //chiamata
		this.soluzione = new ArrayList<Integer>();
		List<Integer> parziale = new ArrayList<Integer>();
		regine_ricorsiva(parziale, 0, N);
		return this.soluzione;
	}

	private boolean regine_ricorsiva(List<Integer> parziale, int livello, int N) {
		
		if(livello==N) {  // se siamo in caso terminale
			
		//	System.out.println(parziale);
			this.soluzione = new ArrayList<Integer>(parziale);
			return false; //non continuo piu
			
		} else {    //se ho parziale[0] fino a parziale[livello-1] gia decise devo decidere piaziale[livello] tra tutti i valori possibili da 0 a N-1
			
			for(int col=0; col<N; col++) {
				
				if(compatibile(livello, col, parziale)) {      //se col è compatibile con sol parziale
					
					parziale.add(col);
					boolean continua = regine_ricorsiva(parziale,livello+1,N);
					
					if(!continua) {
						return false;
					}
					
					parziale.remove(parziale.size()-1);     //backtracking per lista   //aggiungo pezzi e poi li tolgo
					
				}
			}
			return true;
			
		}
		
	}
	
	private boolean compatibile(int livello, Integer col, List<Integer> parziale) {
		
		if(parziale.indexOf(col) != -1) {
			return false;
		} 
		
		for (int riga=0; riga<parziale.size(); riga++) {
			//regina alle coordinate (R,C)=(riga,parziale.get(riga)
			//confrontare con (R,C)=(livello,col)
			if(riga + parziale.get(riga) == livello + col) {
				return false;
			}
			if(riga - parziale.get(riga) == livello - col) {
				return false;
			}
		}
		return true;
	}
	
}
