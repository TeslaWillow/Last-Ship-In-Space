package application;

import javafx.scene.image.Image;

public class ImagenAnimada {
	
	/*Esta clase controla los sprites*/
	
	protected Image[] frames;
    protected double duracion;
     
    public Image getFrame(double timpo)
    {
        int index = (int)((timpo % (frames.length * duracion)) / duracion);
        return frames[index];
    }
}
