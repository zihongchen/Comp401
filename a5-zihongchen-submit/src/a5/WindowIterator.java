package a5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WindowIterator implements Iterator<SubPicture> {
	// window(int window_width, int window_height) creates and returns an iterator
	// of SubPicture objects extracted from this picture. The returned iterator
	// begins
	// with a SubPicture object originating in the upper left corner (i.e., at
	// coordinate
	// (0,0)) with width and height of window_width and window_height. Subsequent
	// SubPicture objects produced by the iterator represent "sliding" the window to
	// the
	// right until the right edge is encountered at which point the window is reset
	// to
	// the left edge and sliding down one row. This continues until the last
	// SubPicture
	// produced by the iterator which represents the window slid until its lower
	// right corner
	// is at the lower right corner of the source picture.
	//
	// IllegalArgumentException is thrown if window_width or window_height is
	// greater
	// than the width or height of the source picture respectively.
	private Picture source;
	private int width;
	private int height;
	private int x;
	private int y;

	// constructor
	public WindowIterator(Picture source, int window_width, int window_height) {
		if (source == null || window_width > source.getWidth() || window_width < 1 | window_height > source.getWidth()
				|| window_height < 1) {
			throw new IllegalArgumentException(
					"window_width or window_height is greater than the width or height of the source picture respectively");
		}
		this.source = source;
		width = window_width;
		height = window_height;
		x = 0;
		y = 0;

	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 * return true if x and y are within the boundary
	 */
	@Override
	public boolean hasNext() {
		if (x >= 0 && x <= (source.getWidth() - width) && y >= 0 && y <= (source.getHeight() - height)) {
			return true;
		}
		return false;
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 * get picture information that is going to return first, then update index:
	 * 
	 * move the picture  to right by 1 if x is within the limit.
	 * move the picture back to the left and down by 1 if x exceeds the boundary.
	 */
	public SubPicture next() {
		if (!hasNext()) {
            throw new RuntimeException("No more");}
		SubPicture p = source.extract(x, y, width, height);
		
		if (x >= 0 && x <= (source.getWidth() - width)) {
			x += 1;

		}
	    if (x > (source.getWidth() - width) && y <= (source.getHeight() - height)) {
			x = 0;
			y += 1;
		}
	    return p;

		
		
	}

	}
