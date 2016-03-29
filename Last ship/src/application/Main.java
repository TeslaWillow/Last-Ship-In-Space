package application;

import java.util.ArrayList;

import javax.print.attribute.standard.Media;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {

	boolean up,down,left,right = false;
	int i = 0;
	int j = 0;
	MediaPlayer mediaplayer;
	Media media;
	final int aceleracion = 5;
	final int Alto_hada = 25;
	final int Ancho_hada = 26;
	private Timeline tm;
	private AnimationTimer timer;
	public Double t;
    public ControladorVistaPrincipal controladorprincipal;
    //private Image[] imageArray;
    private GameObject player,enemy;

	@Override
	public void start(Stage primaryStage) {
		try {
			//----------------------------CARGAS PRELIMINARES-----------------------------------------------
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Vistaprincipal.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Danmaku: Last Ship In The Space");
			primaryStage.setScene(scene);
			controladorprincipal = loader.getController();
			controladorprincipal.setMain(this);//Se crea una Instancia en el Controlador para poder acceder a los datos en el MAIN
			primaryStage.show();
			//---------------------------EVENTOS EN EL STAGE --------------------------------
			player = new Player(450/2,600-35,3,3,0);//Un nuevo jugador se crea con (x,y,vidas,bombas, y tangible(si se puede tocar para morir))
			enemy = new Enemy(100,100,15);//Un nuevo enemigo se crea con (x,y, vida)
			/*
			ImagenAnimada hada = new ImagenAnimada();
			imageArray = new Image[18];
			for (int i = 0; i < imageArray.length; i++)
			    imageArray[i] = new Image( "imgs/hada_" + i + ".png" );
			hada.frames = imageArray;
			hada.duracion = 0.100;*/

			player.Sprites("imgs/hada_", 18);//se envian los parametros de los sprites y un for los recorre todos y la cantidad de sprites
			enemy.Sprites("imgs/hada_", 18);

		    Canvas canvas = new Canvas(root.getWidth(), root.getHeight());//canvas es un lienzo en el que se puede dibujar
		    /*la llamada al root es para que la canva tenga el mismo tamaño que la pantalla*/
		    System.out.println("x = " + primaryStage.getWidth() + " y = " + primaryStage.getHeight());
		    System.out.println("x = " + root.getWidth() + " y = " + root.getHeight());//el tamaño del root(scene) no es igual al del stage
		    root.getChildren().add( canvas );//se añade al scene el canvas
		    GraphicsContext gc = canvas.getGraphicsContext2D();
		    final long startNanoTime = System.nanoTime();//el tiempo inicial antes de la animacion
		    //---------------------------------------
		    ArrayList<String> input = new ArrayList<String>(); // arrego que almacena las teclas

	        scene.setOnKeyPressed(
	            new EventHandler<KeyEvent>()
	            {
	                public void handle(KeyEvent e)
	                {
	                    String code = e.getCode().toString();
                        controladorprincipal.mostrarTeclas(code);
                        //controladorprincipal.mostrarTeclas(code);

	                    if ( !input.contains(code) )
	                        input.add( code );//se almacena las teclas en el input
	                }
	            });

	        scene.setOnKeyReleased(
	                new EventHandler<KeyEvent>()
	                {
	                    public void handle(KeyEvent e)
	                    {
	                        String code = e.getCode().toString();//obtiene el nombre de la tecla precionada
	                        input.remove( code );//se elimina del arreglo al soltar la tecla
	                    }
	                });

		    //--------------------------- ANIMACION --------------------
	       tm = new Timeline();//Time line aun falta investigar
	       tm.setCycleCount(Timeline.INDEFINITE);
	       tm.setAutoReverse(true);
		   timer = new AnimationTimer(){//aca empieza un hilo para la animacion
				@Override
				public void handle(long currentNanoTime){//aca se maneja el tiempo actual en la animacion

					t = (currentNanoTime - startNanoTime) / 1000000000.0;//control de tiempo
		            double x = 250 + 128 * Math.cos(t) + 0 * Math.sin(t);//algebra para mover en x
		            double y = 350 + 128 * Math.sin(t) + 0 * Math.sin(t);
                    enemy.setX((int)x);
                    enemy.setY((int)y);// al enemigo se le envian las cordenadas 

		            gc.clearRect(0, 0, root.getWidth(), root.getHeight());//para limpiar el lienzo y que no quede sucio con el rastro de movimiento

		            gc.drawImage(enemy.getSprites().getFrame(t), enemy.getX(), enemy.getY());
		            gc.drawImage(player.getSprites().getFrame(t),
		            		player.getX() ,
		            		player.getY() ,
		            		player.getAncho_imagen() ,
		            		player.getAlto_imagen());

		            //---------------------Condiciones-------------------------
		            if (input.contains("LEFT") && i >= 0){//si el input <arreglo anterior> tiene el string LEFT(siempre devulve mayusculas) y i >= 0 osea para que no salga de la pantalla
		            	i = 0;
	                    gc.drawImage(player.getSprites().getFrame(t),
	                    		i = player.getX() - 1*aceleracion,
	                    		player.getY() ,player.getAncho_imagen(),
	                    		player.getAlto_imagen() );
	                    player.setX(i);
		            }
		            if (input.contains("RIGHT") && i < root.getWidth() - 35){
		            	i = 0;
	                    gc.drawImage( player.getSprites().getFrame(t),
	                    		i = player.getX() + 1*aceleracion,
	                    		player.getY() ,player.getAncho_imagen() ,
	                    		player.getAlto_imagen());
                        player.setX(i);
		            }
		            if (input.contains("DOWN") && j < root.getHeight() - 35){
		            	j = 0;
	                    gc.drawImage( player.getSprites().getFrame(t),
	                    		player.getX(),
	                    		j = player.getY() + 1*aceleracion,
	                    		player.getAncho_imagen() ,
	                    		player.getAlto_imagen() );
	                    player.setY(j);
		            }
		            if (input.contains("UP") && j >= 0){
		            	j = 0;
	                    gc.drawImage( player.getSprites().getFrame(t),
	                    		player.getX(),
	                    		j = player.getY() - 1*aceleracion ,
	                    		player.getAncho_imagen() ,
	                    		player.getAlto_imagen() );
	                    player.setY(j);

		            }
		            if (input.contains("ESCAPE")){
			            System.exit(0);//esto cierra el juego a la fuerza con ESC
			        }
		            //Si el rectangoX toca a las dimenciones del rectangulo Y
		           if(player.hitboxObject().intersects(enemy.hitboxObject().getBoundsInParent())){
		        	   //Pasa algo...
		        	   controladorprincipal.definirMensaje("Tiempo:" + Double.toString(t));
		           }
				}

			};

		} catch(Exception e) {
			e.printStackTrace();
		}
		tm.play();
		timer.start();
	}

	/*public Rectangle getBounds_Hada(){
		return new Rectangle(player.getX(),
				player.getY(),
				player.getAncho_imagen() - 10,
				player.getAlto_imagen());
	}*/

	/*public Rectangle getBounds_Enemigo(){
		return new Rectangle(40,30,player.getAncho_imagen() - 10,player.getAlto_imagen());
	}*/

	public static void main(String[] args) {
		launch(args);
	}


}
