package a5;

import java.util.Iterator;
/*
 * constructor 
 */
public class ZigZagIterator implements Iterator<Pixel> {
	private Boolean turn;
	private int x;
	private int y;
	private Picture source;

	public ZigZagIterator(Picture source) {
		if (source == null)
			throw new IllegalArgumentException("null source");
		this.source = source;
		turn = false;
		x = 0;
		y = 0;

	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 * 
	 */
	@Override
	public boolean hasNext() {
		if (x < source.getWidth() && y < source.getHeight()) {
			return true;
		} else
			return false;
	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 * this method has two states
	 * the state 1 is to zigzag down untill the left or the bottom of picture is reached. As long as the iterator reaches the boundary of 
	 * picture. it switch its state to state 2. The state 2 is to zigzag from the bottom to the top, and the iterator will also switch the 
	 * state when it reaches the top or the right of the picture.
	 * 
	 */
	@Override
	public Pixel next() {
		if (!hasNext()) {
			throw new RuntimeException("no pixels any more");
		}
		if(source.getWidth()==1) {// check if the picture has width 1;
			Pixel p=source.getPixel(0, y);
			y+=1;
			return p;
		}

		if (x == 0 && y == 0) {
			x += 1;
			return source.getPixel(0, 0);
		}
		if(source.getHeight()==1) {//check if the picture has height 1;
			Pixel p= source.getPixel(x, 0);
			x+=1;
			return p;
		}
		

		if (!turn) {
			Pixel p = source.getPixel(x, y);// this is the pixel that is going to return; then update the x and y
											// information
			if (x > 0&& x<source.getWidth()&& y < source.getHeight() - 1) {// what will happen before reaching the left boundary
				x--;
				y++;

			} else if (x == 0 && x<source.getWidth()&& y < source.getHeight() - 1) {

				y++;
				turn = !turn; // update after reaching the left boundary

			} else if (y == source.getHeight() - 1&& x<source.getWidth()) {// what will happen when the iterator reaches the bottom
				x++;
				turn = !turn;

			}

			return p;

		} else if (turn) {
			Pixel p = source.getPixel(x, y);// this is the pixel that is going to return; then update the x and y
			// information
			if (y > 0 && x < source.getWidth() - 1) {
				x++;
				y--;

			} else if (y == 0 && x < source.getWidth() - 1) {// what will hapen when the iterator reaches the top
				x++;

				turn = !turn;

			} else if (x == source.getWidth() - 1) {// what will happen when the iterator reaches left boundry

				y++;
				turn = !turn;

			}

			return p;

		}
		return null;

	}

}
