package Objects;

import java.util.ArrayList;

public class MoveSet {
	public Move[] set = new Move[4];
	public int size = 0;
	
	public ArrayList<Move> toMoves(){
		ArrayList<Move> temp = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			temp.add(set[i]);
		}
		return temp;
	}
	
	public ArrayList<String> toNameOfMoves(){
		ArrayList<String> temp = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			temp.add(set[i].getName());
		}
		return temp;
	}
	
	public void add(Move move) {		
		set[size] = move;
		size++;
	}
	
	public void remove(int index) {
		if(size == 0) {			
		}
		else if(index > size) {
		}
		else {
			int i;
			for(i = index; i < size-1; i++) {
				set[i] = set[i+1];				
			}
			set[i] = null;
			size--;			
		}		
	}
	
	public String toString() {
		return String.format("[%s , %s , %s , %s]", set[0].getName(), set[1].getName(), set[2].getName(), set[3].getName());
	}
}
