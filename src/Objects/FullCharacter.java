package Objects;

public class FullCharacter {
	public classCharacter character;
	public MoveSet moveSet;
	
	public FullCharacter(classCharacter character, MoveSet moveSet) {
		this.character = character;
		this.moveSet = moveSet;
	}
	
	public classCharacter getCharacter() {
		return character;
	}
	
	public MoveSet getMoveSet() {
		return moveSet;
	}
}
