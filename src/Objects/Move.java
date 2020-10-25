package Objects;

public class Move {
	public String name;
	public int power;
	public int pp;
	
	public Move(String name, int power, int pp) {
		this.name = name;
		this.power = power;
		this.pp = pp;
	}
	
	public String getName() {
		return name;
	}
	public int getPower() {
		return power;
	}
	public int getPp() {
		return pp;
	}
}
