package Objects;

public class classCharacter {
	public String name;
	public int HP;
	public int ATK;
	public int DEF;
	
	public classCharacter(String name, int HP, int ATK, int DEF) {
		this.name = name;
		this.HP = HP;
		this.ATK = ATK;
		this.DEF = DEF;
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
}
