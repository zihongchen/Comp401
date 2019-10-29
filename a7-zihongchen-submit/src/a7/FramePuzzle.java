package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class FramePuzzle {
	public static void main(String[]args) throws IOException{
	Picture p = A7Helper.readFromURL("https://cdn.newsapi.com.au/image/v1/9fdbf585d17c95f7a31ccacdb6466af9");
	p.setCaption("Stephanie");
	FramePuzzleHelperClass simple_widget = new 	FramePuzzleHelperClass (p);
	
	JFrame main_frame = new JFrame();
	main_frame.setTitle("Assignment 7 PixelInspector");
	main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel top_panel = new JPanel();
	top_panel.setLayout(new BorderLayout());
	top_panel.add(simple_widget, BorderLayout.CENTER);
	main_frame.setContentPane(top_panel);
	
	main_frame.pack();
	main_frame.setVisible(true);
	}
}
