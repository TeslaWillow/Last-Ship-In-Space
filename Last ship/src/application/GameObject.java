package application;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class GameObject {
	//Game object es la generalizacion de cada objeto en el juego, osea todos los objetos en el juego tienen estos atributos y metodos
    private int x;
    private int y;
    private ImagenAnimada imagen;
    private double alto_imagen;
    private double ancho_imagen;
    private Image[] imageArray;
    private Boolean Intangible;

    public GameObject(){}

    public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
    }

    public void Sprites(String nombre,int cantidad){
    imagen = new ImagenAnimada();
    imageArray = new Image[cantidad];
	for (int i = 0; i < imageArray.length; i++)
	    imageArray[i] = new Image( nombre + i + ".png" );//del nombre concana el numero y el sprite
	imagen.frames = imageArray;
	imagen.duracion = 0.100;//la duracion entre cada frame
	this.setSprites(imagen);
	this.setAlto_imagen(imageArray[0].getWidth());
	this.setAncho_imagen(imageArray[0].getHeight());
	}

    public double getAlto_imagen() {
		return alto_imagen;
	}

	public void setAlto_imagen(double alto_imagen) {
		this.alto_imagen = alto_imagen;
	}

	public double getAncho_imagen() {
		return ancho_imagen;
	}

	public void setAncho_imagen(double ancho_imagen) {
		this.ancho_imagen = ancho_imagen;
	}

	public void setSprites(ImagenAnimada Sprites){
    	this.imagen = Sprites;
    }

    public ImagenAnimada getSprites(){
    	return imagen;
    }

	public Rectangle hitboxObject(){/*Este metodo sirve para agregar a cada objeto su "cuadro" para que sea detectado cuando entra en el cuadro de otro objeto*/
    	  return new Rectangle(this.x,this.y,imageArray[0].getWidth() - 10,imageArray[0].getHeight());/*necesita la pocision en x y y y para el tamaño se necesita el tamaño de la imagen para que 
    	  se adapte al sprite como un cuadro que la envuelve y eso se puede ajustar ocmo por ejemplo con el -10*/
      }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}





}
