package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FramePuzzleHelperClass extends JPanel implements MouseListener, KeyListener {
	private ArrayList<PictureView> content;
	private Picture source;
	private JPanel topPanel;
	private PictureView whitePicture;

	/*
	 * in the constructor, firstly a topPanel where pictureview is set on is
	 * created. using the tile iterator will cut the picture into 5*5 sections.
	 * Wraping those pictures into pictureview, we store them into
	 * ArrayList<PictureView> content; the mouse listeners are added to elements in
	 * the content. this allows us to know build a frame with a picture cut into
	 * 5*5, and know the that when, where, and the source of mouseclicking events.
	 * 
	 */
	public FramePuzzleHelperClass(Picture source) {
		this.setLayout(new BorderLayout());
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(5, 5));
		content = new ArrayList<PictureView>();
		this.source = source;
		TileIterator pic = new TileIterator(source, source.getWidth() / 5, source.getHeight() / 5);
		while (pic.hasNext()) {
			Picture temp = pic.next();
			PictureView temp1 = new PictureView(temp.createObservable());
			temp1.addMouseListener(this);
			temp1.setFocusable(false);
			content.add(temp1);

		}
		addKeyListener(this);
		setFocusable(true);
		this.requestFocus();
		Picture lastOne = source.extract(source.getWidth() - source.getWidth() / 5,
				source.getHeight() - source.getHeight() / 5, source.getWidth() / 5, source.getHeight() / 5);
		lastOne.paint(0, 0, source.getWidth() / 5, source.getHeight() / 5, new GrayPixel(1));
		content.get(24).setPicture(lastOne.createObservable());
		whitePicture = content.get(24);
		for (PictureView l : content) {
			// l.addMouseListener(this); it is not viable because you can't change the
			// component when iterating it
			topPanel.add(l);
		}

		add(topPanel, BorderLayout.CENTER);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/*
	 * this method enables the mouseclicking events around the same column or same
	 * row of the whitepicture tomove the whitepicture to the position where the
	 * mouseclicking event happened. First, we find out the index of the picture
	 * being clicked and the whitepicture; Then we compare if the clicked picture is
	 * on the same row or column with the white picture. if they are on the same
	 * column or on the same row, slide the picture to adapt the change on that
	 * column or row
	 * 
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		ArrayList<PictureView> initialArray = (ArrayList<PictureView>) content.clone();
		int whitePictureIndex = 0;
		int clickedPictureIndex = 0;
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i) == e.getSource()) {
				clickedPictureIndex = i;
			}
		}
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i) == whitePicture) {
				whitePictureIndex = i;
			}
		}
		if (whitePictureIndex == clickedPictureIndex) {
			return;
		}
		// to check if they are on the same column
		// if they are on the same column. the difference between their index must be 5;
		if ((whitePictureIndex - clickedPictureIndex) % 5 == 0) {
			if (clickedPictureIndex > whitePictureIndex) {
				for (int i = whitePictureIndex; i < clickedPictureIndex; i += 5) {
					content.set(i, initialArray.get(i + 5));
				}
				content.set(clickedPictureIndex, initialArray.get(whitePictureIndex));// set the white Picture on the
																						// position of clicked picture;
			} else if (clickedPictureIndex < whitePictureIndex) {
				for (int i = clickedPictureIndex + 5; i <= whitePictureIndex; i += 5) {
					content.set(i, initialArray.get(i - 5));
				}
				content.set(clickedPictureIndex, initialArray.get(whitePictureIndex));// set the white Picture on the
																						// position of clicked picture;
			}
			// the content on the else if checks of they are on the same row.
		} else if (true) {
			for (int k = 0; k <= 20; k += 5) {
				if (clickedPictureIndex < 5 + k && clickedPictureIndex >= k && whitePictureIndex < 5 + k
						&& whitePictureIndex >= k) {

					if (clickedPictureIndex > whitePictureIndex) {

						for (int i = whitePictureIndex; i < clickedPictureIndex; i += 1) {// slide the picture
							content.set(i, initialArray.get(i + 1));
						}
						content.set(clickedPictureIndex, initialArray.get(whitePictureIndex));// set the white Picture
																								// on the position of
																								// clicked picture;
					} else if (clickedPictureIndex < whitePictureIndex) {

						for (int i = clickedPictureIndex + 1; i <= whitePictureIndex; i += 1) {// slide the picture
							content.set(i, initialArray.get(i - 1));
						}
						content.set(clickedPictureIndex, initialArray.get(whitePictureIndex));// set the white Picture
																								// on the position of
																								// clicked picture;
					}

				}
			}
		}

		topPanel.removeAll();
		for (PictureView l : content) {
			// l.addMouseListener(this); it is not viable because you can't change the
			// component when iterating it
			topPanel.add(l);
		}
		topPanel.revalidate();
		topPanel.repaint();

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

	@Override
	public void keyPressed(KeyEvent e) {
		ArrayList<PictureView> initialArray = (ArrayList<PictureView>) content.clone();
		int whitePictureIndex = 0;
		
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i) == whitePicture) {
				whitePictureIndex = i;
			}
		}
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			System.out.println(321321);
			if (whitePictureIndex >= 0 && whitePictureIndex <= 4) {
				return;
			}
			content.set(whitePictureIndex, initialArray.get(whitePictureIndex - 5));
			content.set(whitePictureIndex - 5, initialArray.get(whitePictureIndex));
			break;
		case KeyEvent.VK_DOWN:
			System.out.println(321321);
			if (whitePictureIndex >= 20 && whitePictureIndex <= 24) {
				return;
			}
			content.set(whitePictureIndex, initialArray.get(whitePictureIndex + 5));
			content.set(whitePictureIndex + 5, initialArray.get(whitePictureIndex));
			break;
		case KeyEvent.VK_LEFT:
			System.out.println(321321);
			for (int i = 0; i <= 20; i += 5) {
				if (whitePictureIndex == i) {
					return;
				}
			}
			content.set(whitePictureIndex, initialArray.get(whitePictureIndex - 1));
			content.set(whitePictureIndex - 1, initialArray.get(whitePictureIndex));
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println(321321);
			for (int i = 4; i < 25; i += 5) {
				if (whitePictureIndex == i) {
					return;
				}
			}
			content.set(whitePictureIndex, initialArray.get(whitePictureIndex + 1));
			content.set(whitePictureIndex + 1, initialArray.get(whitePictureIndex));
			break;

		}
		topPanel.removeAll();
		for (PictureView l : content) {
			// l.addMouseListener(this); it is not viable because you can't change the
			// component when iterating it
			topPanel.add(l);
		}
		topPanel.revalidate();
		topPanel.repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
