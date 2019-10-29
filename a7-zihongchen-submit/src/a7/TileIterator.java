package a7;

import java.util.Iterator;

public class TileIterator implements Iterator<SubPicture> {
	
	// of SubPicture objects that represent a non-overlapped tiling of this picture. 
	// The first SubPicture produced by this iterator is originated at the upper left 
	// corner of the source picture and is tile_width wide and tile_height tall. 
	// Subsequent SubPicture objects produced by this iterator proceed in the tiling
	// from left to right and then top to bottom. Partial tiles are not produced so if
	// the dimensions of this picture are not a multiple of window_width or window_height,
	// last tile area of a row may not extend all the way to the right edge of the picture
	// and/or the last row of tiles may not extend all the way to the bottom edge of the picture.
	// IllegalArgumentException is thrown if tile_width or tile_heigt are greater
	// then the width or height of the picture.
	
	
	
	
	private Picture source;
	private int width;
	private int height;
	private int x;
	private int y;
	
	
	//constructor 
	public TileIterator(Picture source, int tile_width, int tile_height) {
		if(source==null||tile_width>source.getWidth()||tile_width<1|tile_height>source.getWidth()||tile_height<1) {
			throw new IllegalArgumentException("tile_width or tile_heigt are greater then the width or height of the picture, or the picture is null");
		}
		this.source=source;
		width=tile_width;
		height=tile_height;
		x=y=0;
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
	 * store the picrture  information first;
	 * Then update the picture by x+=width-- this moves the next picture to the right by a distance of width
	 * When the picture exceeds the boundary, x is reset to zero and y+=height;
	 * this will move the picture back to the left and down by height if x exceeds the boundary.
	 * 
	 */
	@Override
	public SubPicture next() {
		if (!hasNext()) {
            throw new RuntimeException("No more");
    }SubPicture p = source.extract(x, y, width, height);
	
	if (x >= 0 && x <= (source.getWidth() - width)) {
		x += width;

	}
    if (x > (source.getWidth() - width) && y <= (source.getHeight() - height)) {
		x = 0;
		y += height;
	}
    return p;

	}

}
