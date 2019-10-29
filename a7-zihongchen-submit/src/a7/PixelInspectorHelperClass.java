package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PixelInspectorHelperClass extends JPanel implements MouseListener {

	private PictureView picture_view;
	private JPanel left_panel;
	private Picture source;
	
	
	//create a top panel and then two subpanels. 
	//the subpanel one- the left_panel is on the west side of top panel, showing x, y ,  rgb, and brightness information 
	// pciture_view  on the center of the top panel, showing the picture information
	// mouseListener is added to the picture_view  object.
	public PixelInspectorHelperClass(Picture picture) {
		setLayout(new BorderLayout());
		source=picture;
		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);	
		left_panel =new JPanel();
		left_panel.setLayout(new GridLayout(0,1));
		add(left_panel,BorderLayout.WEST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("You clicked on the frame at: " + e.getX() + "," + e.getY());
		left_panel.removeAll();
		JLabel label1 = new JLabel("X:"+e.getX());
		JLabel label2 = new JLabel("Y:"+e.getY());
		JLabel label3 = new JLabel("Red:"+source.getPixel(e.getX(), e.getY()).getRed());
		JLabel label4 = new JLabel("Green:"+source.getPixel(e.getX(), e.getY()).getGreen());
		JLabel label5 = new JLabel("Blue:"+source.getPixel(e.getX(), e.getY()).getBlue());
		JLabel label6 = new JLabel("Brightness:"+source.getPixel(e.getX(), e.getY()).getIntensity());
		left_panel.add(label1);
		left_panel.add(label2);
		left_panel.add(label3);
		left_panel.add(label4);
		left_panel.add(label5);
		left_panel.add(label6);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		revalidate();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
