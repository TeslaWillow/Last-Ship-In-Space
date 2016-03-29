package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ControladorVistaPrincipal implements Initializable {

	@FXML private ImageView viehada;
	@FXML private Label lbl_pantalla;
    private MediaPlayer mp;
    private Media me;
    public Main main;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		eventos();
	}

	public void eventos(){
     String path = new File("src/Sonido/th07_07.wav").getAbsolutePath();
     me = new Media(new File(path).toURI().toString());
     mp = new MediaPlayer(me);
     mp.setAutoPlay(true);
     ImagenAnimada hada = new ImagenAnimada();
     Image[] imageArray = new Image[18];
	 //imagen_hada = new Image("imgs/hada_0.png",true);

	 for (int i = 0; i < imageArray.length; i++){
		    imageArray[i] = new Image( "imgs/hada_" + i + ".png",true);
		    }
		hada.frames = imageArray;
		hada.duracion = 0.100;

	 final long startNanoTime = System.nanoTime();

	 new AnimationTimer(){

			@Override
			public void handle(long currentNanoTime) {

				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
	            double x = 250 + 128 * Math.cos(t) + 0 * Math.sin(t);
	            //double y = 350 + 128 * Math.sin(t) + 0 * Math.sin(t);

	            viehada.setImage(hada.getFrame(t));
				viehada.setX(x);
				viehada.setY(0);
			}

		}.start();
	}
	
	public void definirMensaje(String tiempo){
		lbl_pantalla.setText(tiempo);
	}
	
	public void mostrarTeclas(String teclas){
		System.out.println(teclas);
	}
	
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
