package a5;

import java.util.Iterator;

public class SampleIterator implements Iterator<Pixel> {
	// sample(int init_x, int init_y, int dx, int dy) creates and returns an
	// iterator
	// of Pixel objects from this picture. The returned iterator will iterate over
	// the pixel starting at (init_x, init_y) and proceeding left to right by dx
	// columns.
	// When the end of the row is encountered, the iteration picks up again at the
	// init_x column down by dy rows.
	//
	// IllegalArgumentException is thrown if init_x or init_y are not within the
	// bounds
	// of the picture, or if dx and dy are not positive.
	private int startX;
	private int startY;
	private Picture source;
	private int dx;
	private int dy;
	private int x;
	private int y;

	// constructor
	public SampleIterator(Picture source, int init_x, int init_y, int dx, int dy) {
		if (init_x >= source.getWidth() || init_x < 0 || init_y >= source.getWidth() || init_y < 0
				|| dx <= 0 | dy <= 0) {
			throw new IllegalArgumentException("Illegal parameter");
		}
		this.source = source;
		startX = init_x;
		startY = init_y;
		x = init_x;
		y = init_y;
		this.dx = dx;
		this.dy = dy;

	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 * return true if x and y are in the boundary where x = source.getWidth() - dx and y = source.getHeight() - dy are the last pixel to return;
	 */
	@Override
	public boolean hasNext() {
		if (x >= 0 && x <= source.getWidth() - dx && y >= 0 && y <= source.getHeight() - dy) {
			return true;
		} else
			return false;

	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 *
	 *Store the pixel information that is going to return first
	 *then update the index x by dx. When x is out of the boundary of picture, set x back to its initial value, then update y untill hasNext() returns false;
	 *
	 *
	 */
	@Override
	public Pixel next() {
		if (!hasNext()) {
			throw new RuntimeException("No pixels any more");
		}
		Pixel p = source.getPixel(x, y);
		if ( x < (source.getWidth())) {
			x += dx;
		}
		if (x >= (source.getWidth())) {
			
			x = startX;
			y += dy;
		}
		return p;
	}

}


