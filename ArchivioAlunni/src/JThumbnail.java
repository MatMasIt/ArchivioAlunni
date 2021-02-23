import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JThumbnail extends JLabel {
	/*
	 * JThumbnail
	 *  A custom made "JClass" to implement ImageIcon in a JLabel without too much hassle.
	 *  Includes auto resize-to-fit capabilities.
	 *  https://github.com/MatMasIt/JThumbnail
	 *  Available under MIT License
	 */
	private ImageIcon i;
	private String path, description;
	private int w,h;


/** 
 *
 *  JThumbnail
 *
 * @param path  the path
 * @param description  the description
 * @param w  the width
 * @param h  the height
 */
	public JThumbnail(String path, String description, int w, int h) { 

		this.i = new ImageIcon(path, description);
		this.path = path;
		this.description = description;
		this.w=w;
		this.h=h;
		this.i=scaleImage(this.i,this.w,this.h);
		this.setIcon(this.i);
	}
	

/** 
 *
 * Gets the path
 *
 * @return the path
 */
	public String getPath() { 

		return path;
	}


/** 
 *
 * Sets the path and updates the thumbnail accodringly
 *
 * @param path  the path
 */
	public void setPath(String path) { 

		this.path = path;
		ImageIcon i2 = new ImageIcon(path, this.description);
		this.i = i2;
		this.i=scaleImage(this.i,this.w,this.h);
		this.setIcon(this.i);
	}


/** 
 *
 * Gets the description 
 *
 * @return the description
 */
	public String getDescription() { 

		return description;
	}


/** 
 *
 * Sets the description
 *
 * @param description  the description
 */
	public void setDescription(String description) { 

		this.description = description;
		ImageIcon i2 = new ImageIcon(this.path, description);
		this.i = i2;
		this.i=scaleImage(this.i,this.w,this.h);
		this.setIcon(this.i);
	}


/** 
 *
 * Gets the Width
 *
 * @return the  Width
 */
	public int getW() { 

		return w;
	}


/** 
 *
 * Sets the Width
 *
 * @param w  the witdh
 */
	public void setW(int w) { 

		this.w = w;
		this.i=scaleImage(this.i,this.w,this.h);
		this.setIcon(this.i);
	}


/** 
 *
 * Gets the Height
 *
 * @return the  Heigth
 */
	public int getH() { 

		return h;
	}


/** 
 *
 * Sets the Height
 *
 * @param h  the height
 */
	public void setH(int h) { 

		this.h = h;
		this.i=scaleImage(this.i,this.w,this.h);
		this.setIcon(this.i);
	}


/** 
 *
 * Scale image
 *
 * @param icon  the icon
 * @param w  the width
 * @param h  the heigth
 * @return ImageIcon
 */
	public ImageIcon scaleImage(ImageIcon icon, int w, int h) { 

		int nw = icon.getIconWidth();
		int nh = icon.getIconHeight();

		if (icon.getIconWidth() > w) {
			nw = w;
			nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
		}

		if (nh > h) {
			nh = h;
			nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
		}

		return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
	}

}
