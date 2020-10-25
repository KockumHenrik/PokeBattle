package Objects;

public class MoveSet {
	public Move[] set = new Move[4];
	public int size = 0;
	
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
			for(int i = index; i < size-1; i++) {
				set[i] = set[i+1];
				set[i+1] = null;
			}
		}
		
	}
}
