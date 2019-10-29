package a3;

public class VerticalStackPicture implements Picture{
	
	private Pixel[][] pixel_array;
	// ArrayList <Pixel[]> k;
	private int width;
	private int height;
	private Picture left;
	private Picture right;
	// Creates new object using values provided by pixel_array, matching in size.
	public VerticalStackPicture(Picture left, Picture right) {
		if(left==null||right==null||left.getWidth()!=right.getWidth()) {
			throw new IllegalArgumentException("Hello, it is me!");
		}
		
		this.left=left;
		this.right=right;
		this.width=left.getWidth();
		this.height=left.getHeight()+right.getHeight();
		pixel_array=new Pixel[width][height];
		for (int i = 0; i < left.getWidth(); i++) {
			for (int j = 0; j < left.getHeight(); j++) {
				pixel_array[i][j] = left.getPixel(i, j); // all values will change if you change the initial value

			}
		}
		
		for (int i = 0; i < right.getWidth(); i++) {
			for (int j = 0; j < right.getHeight(); j++) {
				pixel_array[i][j+left.getHeight()] = right.getPixel(i, j); // all values will change if you change the initial value

			}
		}
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if ( x < 0 || y < 0 ||x > width - 1 || y > height - 1) {
			throw new IllegalArgumentException("x or y are not in range");
		}


		// TODO Auto-generated method stub
		return pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if ( x < 0 || y < 0 ||x > width - 1 || y > height - 1||p==null) {
			throw new IllegalArgumentException("x or y are not in range");
		}
		pixel_array[x][y] = p;
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("No hommie");
		}
		pixel_array[x][y] = pixel_array[x][y].blend(p, factor);

		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {// not validater
		if ( ax > width - 1 || ay > height - 1|| bx > width - 1 || by > height - 1||p==null) {
			throw new IllegalArgumentException("No hommie");
		}
		if(ax<0) ax=0;
		if(ay<0) ay=0;
		if(bx<0) bx=0;
		if(by<0) by=0;
		for (int i = ax; i <= bx; i++) {
			for (int j = ay; j <= by; j++) {
				pixel_array[i][j] = p; // do not change p;

			}

		}
		return this;

	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if ( ax > width - 1 || ay > height - 1|| bx > width - 1 || by > height - 1||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("No hommie");
		}
		if(ax<0) ax=0;
		if(ay<0) ay=0;
		if(bx<0) bx=0;
		if(by<0) by=0;
		for (int i = ax; i <= bx; i++) {
			for (int j = ay; j <= by; j++) {
				pixel_array[i][j] = pixel_array[i][j].blend(p, factor);

			}

		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius <=0||p==null) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if ((i - cx) * (i - cx) + (j - cy) * (j - cy) <= radius * radius) {
					pixel_array[i][j] = p;
				}

			}
		}
		return this;

	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius <= 0||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if ((i - cx) * (i - cx) + (j - cy) * (j - cy) <= radius * radius) {
					pixel_array[i][j] = pixel_array[i][j].blend(p, factor);
				}

			}
		}
		return this;
	}

}
