package a5;

import java.util.Iterator;

public class PixelArrayPicture extends PictureImpl implements Picture {
	protected String _caption;
	protected Pixel[][] _pixels;

	public PixelArrayPicture(Pixel[][] parray, String caption) {
		super(caption);

		_caption = caption;
		_pixels = copyPixelArray(parray);
	}

	@Override
	public int getWidth() {
		return _pixels.length;
	}

	@Override
	public int getHeight() {
		return _pixels[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		return _pixels[x][y];
	}

	public Picture paint(int x, int y, Pixel p) {
		return paint(x, y, p, 1.0);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}

		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("factor is out of bounds");
		}

		_pixels[x][y] = _pixels[x][y].blend(p, factor);

		return this;
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return paint(ax, ay, bx, by, p, 1.0);
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		int min_x = (ax < bx) ? ax : bx;
		int max_x = (ax > bx) ? ax : bx;
		int min_y = (ay < by) ? ay : by;
		int max_y = (ay > by) ? ay : by;

		min_x = (min_x < 0) ? 0 : min_x;
		min_y = (min_y < 0) ? 0 : min_y;
		max_x = (max_x > getWidth() - 1) ? getWidth() - 1 : max_x;
		max_y = (max_y > getHeight() - 1) ? getHeight() - 1 : max_y;// to make min and max fit the size of the picture

		Picture result = this;
		for (int x = min_x; x <= max_x; x++) {
			for (int y = min_y; y <= max_y; y++) {
				result = result.paint(x, y, p, factor);
			}
		}
		return result;
	}

	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return paint(cx, cy, radius, p, 1.0);
	}

	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		if (radius < 0) {
			throw new IllegalArgumentException("radius is negative");
		}

		int min_x = (int) (cx - (radius + 1));
		int max_x = (int) (cx + (radius + 1));
		int min_y = (int) (cy - (radius + 1));
		int max_y = (int) (cy + (radius + 1));

		min_x = (min_x < 0) ? 0 : min_x;
		min_y = (min_y < 0) ? 0 : min_y;
		max_x = (max_x > getWidth() - 1) ? getWidth() - 1 : max_x;
		max_y = (max_y > getHeight() - 1) ? getHeight() - 1 : max_y;

		Picture result = this;
		for (int x = min_x; x <= max_x; x++) {
			for (int y = min_y; y <= max_y; y++) {
				if (Math.sqrt((cx - x) * (cx - x) + (cy - y) * (cy - y)) <= radius) {
					result = result.paint(x, y, p, factor);
				}
			} // know how to use your own method instead of writing again, so that inheritance
				// could be useful
		}
		return result;
	}

	public Picture paint(int x, int y, Picture p) {
		return paint(x, y, p, 1.0);
	}

	public Picture paint(int x, int y, Picture p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of range");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}

		Picture result = this;
		for (int px = 0; px < p.getWidth() && px + x < getWidth(); px++) {
			for (int py = 0; py < p.getHeight() && py + y < getHeight(); py++) {
				result = result.paint(px + x, py + y, p.getPixel(px, py), factor);
			}
		}
		return result;
	}

	

}