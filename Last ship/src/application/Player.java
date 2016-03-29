package application;

public class Player extends GameObject {

	private int vidas;
	private int bombas;
	private long puntos;
	
	
	public Player(int x, int y, int vidas, int bombas, long puntos) {
		super(x, y);
		this.vidas = vidas;
		this.bombas = bombas;
		this.puntos = puntos;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getBombas() {
		return bombas;
	}

	public void setBombas(int bombas) {
		this.bombas = bombas;
	}

	public long getPuntos() {
		return puntos;
	}

	public void setPuntos(long puntos) {
		this.puntos = puntos;
	}

    
}
