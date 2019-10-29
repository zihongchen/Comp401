package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class A7SetupTest {
	public static void main(String[] args) throws IOException {
		Picture p = A7Helper.readFromURL("https://cdn2.unrealengine.com/Infinity+Blade%2FTab+Images%2FInfinity-Blade-1-1400x800-54cf33f8f4e249759bf6b8b23c41a3d47dc8982d.png");
		p.setCaption("Stephanie");
		SimplePictureViewWidget simple_widget = new SimplePictureViewWidget(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Simple Picture View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(simple_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}