package application;

public class Enemy extends GameObject {

	private int Hp;

	/*Metodos y atributos del enemigo*/

	public Enemy(int x, int y, int hp) {
		super(x, y);
		Hp = hp;
	}

	public int getHp() {
		return Hp;
	}

	public void setHp(int hp) {
		Hp = hp;
	}



}
