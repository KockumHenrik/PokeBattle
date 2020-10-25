package Objects;

public class classCharacter {
	public String name;
	public int HP;
	public int ATK;
	public int DEF;
	public int SPD;
	
	public classCharacter(String name, int HP, int ATK, int DEF, int SPD) {
		this.name = name;
		this.HP = HP;
		this.ATK = ATK;
		this.DEF = DEF;
		this.SPD = SPD;
	}
	
	public String getName() {
		return name;
	}
	public int getATK() {
		return ATK;
	}
	public int getDEF() {
		return DEF;
	}
	public int getHP() {
		return HP;
	}	
	public int getSPD() {
		return SPD;
	}
}
