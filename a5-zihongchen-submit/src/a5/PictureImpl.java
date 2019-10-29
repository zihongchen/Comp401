package a5;

import java.util.Iterator;

public abstract class PictureImpl implements Picture {

	private String _caption;

	public PictureImpl(String caption) {
		if (caption == null) {
			throw new IllegalArgumentException("caption should not be null");
		}

		_caption = caption;
		
	}


	

	@Override
	public String getCaption() {
		return _caption;
	}

	@Override
	public void setCaption(String caption) {
		if (caption == null) {
			throw new IllegalArgumentException("caption is null");
		}

		_caption = caption;
	}

	public SubPicture extract(int x, int y, int width, int height) {
		
		return new SubPictureImpl(this, x, y, width, height);
	}

	public Iterator<Pixel> sample(int init_x, int init_y, int dx, int dy) {
		return new SampleIterator(this, init_x, init_y, dx, dy);
	}

	public Iterator<SubPicture> window(int window_width, int window_height) {
		return new WindowIterator(this, window_width, window_height);
	}

	public Iterator<SubPicture> tile(int tile_width, int tile_height) {
		return new TileIterator(this, tile_width, tile_height);
	}

	public Iterator<Pixel> zigzag() {
		return new ZigZagIterator(this);
	}
	
	public static Pixel[][] copyPixelArray(Pixel[][] pixel_array) {

		if (pixel_array == null) {
			throw new IllegalArgumentException("pixel_array is null");
		}
		int width = pixel_array.length;

		if (width == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x] == null) {
				throw new IllegalArgumentException("pixel_array includes null columns");
			}
		}

		int height = pixel_array[0].length;
		if (height == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x].length != height) {
				throw new IllegalArgumentException("Columns in picture are not all the same height.");
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (pixel_array[x][y] == null) {
					throw new IllegalArgumentException("pixel_array includes null pixels");
				}
			}
		}

		Pixel[][] copy = new Pixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				copy[x][y] = pixel_array[x][y];
			}
		}

		return copy;
	}
	

}
