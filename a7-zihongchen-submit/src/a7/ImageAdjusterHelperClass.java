package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterHelperClass extends JPanel implements ChangeListener {
	private PictureView picture_view;
	private JPanel picturePanel;
	private JSlider Blur;
	private JSlider Brightness;
	private JSlider Saturation;
	private Picture source;
	
// the  contructor creates a top panel that consists of two subpanels. One panel is the pictureView in the center. On the south of the topPanel,
// there are three sliders controlling the blur, brightness, saturation of the picture.
// the ticks of those sliders are set, and changeListeners  are added

	public ImageAdjusterHelperClass(Picture picture) {
		setLayout(new BorderLayout());
		source = picture;

		picture_view = new PictureView(picture.createObservable());
		picturePanel = new JPanel();
		picturePanel.setLayout(new BorderLayout());
		picturePanel.add(picture_view, BorderLayout.CENTER);
		add(picturePanel, BorderLayout.CENTER);
		JLabel title_label = new JLabel(picture.getCaption());
		add(title_label, BorderLayout.SOUTH);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0, 1));

		Blur = new JSlider(0, 5, 0);
		Saturation = new JSlider(-100, 100, 0);
		Brightness = new JSlider(-100, 100, 0);

		Blur.setPreferredSize(new Dimension(200, 70));
		Saturation.setPreferredSize(new Dimension(200, 70));
		Brightness.setPreferredSize(new Dimension(200, 70));// creating sliders

		Blur.setMajorTickSpacing(1);
		Blur.setPaintTicks(true);
		Blur.setPaintLabels(true);

		Saturation.setMajorTickSpacing(25);
		Saturation.setPaintTicks(true);
		Saturation.setPaintLabels(true);

		Brightness.setMajorTickSpacing(25);
		Brightness.setPaintTicks(true);
		Brightness.setPaintLabels(true);
	
		Blur.addChangeListener(this);
		Saturation.addChangeListener(this);
		Brightness.addChangeListener(this);

		slider_panel.add(Blur);
		slider_panel.add(Saturation);
		slider_panel.add(Brightness);

		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(3, 1));
		JLabel label1 = new JLabel("Blur");
		JLabel label2 = new JLabel("Saturation");
		JLabel label3 = new JLabel("Brightness");
		temp.add(label1);
		temp.add(label2);
		temp.add(label3);

		top_panel.add(temp, BorderLayout.WEST);
		top_panel.add(slider_panel, BorderLayout.CENTER);
		add(top_panel, BorderLayout.SOUTH);

	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 * this method will update the picture based on values provided by sliders;
	 * when there is an event, this method first gets all three values provided by the slider;
	 * Since dealWithSaturation, dealWithBrightness, dealWithBlur all return an array
	 * First, the method copys the internal array of the source;
	 * then the array is modified by three methods above;
	 * The modified array will form a picture and finally be a pictureview.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		double brightnessIndex = Brightness.getValue();
		double saturationIndex = Saturation.getValue();
		int blurIndex = Blur.getValue();

		Pixel[][] arrayToModify = dealWithBrightness(brightnessIndex, copyPictureArray());
		arrayToModify = dealWithBlur(blurIndex, arrayToModify);
		arrayToModify = dealWithSaturation(saturationIndex, arrayToModify);
		Picture temp = new MutablePixelArrayPicture(arrayToModify, "Stephanie");
		picture_view.setPicture(temp.createObservable());
		
		/*(picturePanel.removeAll();
		picture_view = new PictureView(temp.createObservable());
		picturePanel.add(picture_view, BorderLayout.CENTER);
		picturePanel.revalidate();
		*///picturePanel.repaint();

	}

	
	// input is the brightness index from the slider and an Pixel array whose pixels' brightness would be modified.
	//when the index is smaller than zero-, the brightness of pixels in the array would be decreased by calling method darken() from Pixel.Vice versa;
	

	public Pixel[][] dealWithBrightness(double brightnessIndex, Pixel[][] arrayToModify) {
		if (brightnessIndex > 0) {
			System.out.println(arrayToModify[0][0].getRed());
			for (int i = 0; i < arrayToModify.length; i++) {
				for (int j = 0; j < arrayToModify[i].length; j++) {
					arrayToModify[i][j] = arrayToModify[i][j].lighten(brightnessIndex / 100);
				}

			}
			System.out.println(arrayToModify[0][0].getRed());
			return arrayToModify;
		} else if (brightnessIndex < 0) {
			for (int i = 0; i < arrayToModify.length; i++) {
				for (int j = 0; j < arrayToModify[i].length; j++) {
					arrayToModify[i][j] = arrayToModify[i][j].darken(brightnessIndex * (-1) / 100);
				}

			}

			return arrayToModify;
		}

		return arrayToModify;

	}
	//input is the blur index from the slider and an Pixel array that is going to be blured;
	//for each pixel in the array, the bluring method average the red, green, blue values of pixels in a square whose length is blurIndex around that pixels separately. 
	
	
	public Pixel[][] dealWithBlur(int blurIndex, Pixel[][] arrayToModify) {
		if(blurIndex==0) {
			return arrayToModify;
		}
		for (int x = 0; x < arrayToModify.length; x++) {
			for (int y = 0; y < arrayToModify[x].length; y++) {
				double averageBlue = 0;
				double averageRed = 0;
				double averageGreen = 0;
				int numOfPixelCounted = 0;
				for (int j = x - blurIndex; j < x + blurIndex; j++) {
					for (int k = y - blurIndex; k < y + blurIndex; k++) {
						try {
							Pixel temp = arrayToModify[j][k];
							averageBlue += temp.getBlue();
							averageGreen += temp.getGreen();
							averageRed += temp.getRed();
							numOfPixelCounted++;
						} catch (Exception e) {

						}

					}
				}
				arrayToModify[x][y]= new ColorPixel(averageRed/numOfPixelCounted,averageGreen/numOfPixelCounted,averageBlue/numOfPixelCounted);

			}

		}
		return arrayToModify;

	}
	//input is the saturation index from the slider and an Pixel array that is going to be saturated;
	// depending on the saturationIndex provided by the slider, we use different formulas to saturate red green blue values of each pixel separately
	
	public Pixel[][] dealWithSaturation(double saturationIndex, Pixel[][] arrayToModify) {
		if (saturationIndex > 0) {
			for (int i = 0; i < arrayToModify.length; i++) {
				for (int k = 0; k < arrayToModify[i].length; k++) {
					Pixel j = arrayToModify[i][k];
					double lc = Math.max(Math.max(j.getBlue(), j.getRed()), j.getGreen());
					double jBlue = j.getBlue() * ((lc + ((1.0 - lc) * (saturationIndex / 100.0))) / lc);
					double jRed = j.getRed() * ((lc + ((1.0 - lc) * (saturationIndex / 100.0))) / lc);
					double jGreen = j.getGreen() * ((lc + ((1.0 - lc) * (saturationIndex / 100.0))) / lc);
					arrayToModify[i][k] = new ColorPixel(jRed, jGreen, jBlue);
				}
			}

			return arrayToModify;

		} else if (saturationIndex < 0) {
			for (int i = 0; i < arrayToModify.length; i++) {
				for (int k = 0; k < arrayToModify[i].length; k++) {
					Pixel j = arrayToModify[i][k];
					double jBlue = j.getBlue() * (1.0 + (saturationIndex / 100.0))
							- (j.getIntensity() * saturationIndex / 100.0);
					double jRed = j.getRed() * (1.0 + (saturationIndex / 100.0))
							- (j.getIntensity() * saturationIndex / 100.0);
					double jGreen = j.getGreen() * (1.0 + (saturationIndex / 100.0))
							- (j.getIntensity() * saturationIndex / 100.0);
					arrayToModify[i][k] = new ColorPixel(jRed, jGreen, jBlue);
				}

			}

			return arrayToModify;

		} else
			return arrayToModify;

	}
	// this method returns a copy of array from the source picture;
	
	public Pixel[][] copyPictureArray() {
		Pixel[][] temp = new Pixel[source.getWidth()][source.getHeight()];
		for (int i = 0; i < source.getWidth(); i++) {
			for (int j = 0; j < source.getHeight(); j++) {
				temp[i][j] = source.getPixel(i, j);
			}
		}
		return temp;

	}

}
