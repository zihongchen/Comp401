package a3;

public class TransformedPicture  implements Picture{
	private Picture source;
	private PixelTransformation xform;
	private Pixel [][] pixel_array;
	// ArrayList <Pixel[]> k;
	private int width;
	private int height;

	
	public TransformedPicture (Picture source, PixelTransformation xform) {
		if (xform==null||source==null) {
			throw new IllegalArgumentException("Incorrect Value");
		}
		this.source=source;
		this.xform=xform;
		this.width=source.getWidth();
		this.height=source.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//pixel_array[i][j] = source.getPixel(i, j); // all values will change if you change the initial value

			}
		}
	}
	
	// Creates new object using values provided by pixel_array, matching in size.
	
	

	// Creates new object by providing geometry of picture.
	// Initial value of all pixels should be medium gray (i.e., a grayscale pixel
	
	

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
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
			throw new IllegalArgumentException("x or y are not in range");
		}
		return xform.transform(source.getPixel(x, y));

		// TODO Auto-generated method stub
		
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (x <= 0 || y <= 0 || x > width - 1 || y > height - 1||p==null) {
			throw new IllegalArgumentException("x or y are not in range");
		}
		Pixel[][]temp=pixel_array.clone();
		temp[x][y] = p;
		return new ImmutablePixelArrayPicture(temp);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("No hommie");
		}

		//pixel_array[x][y] = pixel_array[x][y].blend(p, factor);
		Pixel[][]temp=pixel_array.clone();
		temp[x][y] = temp[x][y].blend(p, factor);
		return new ImmutablePixelArrayPicture(temp);
		
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
		Pixel[][]temp=pixel_array.clone();
		for (int i = ax; i <= bx; i++) {
			for (int j = ay; j <= by; j++) {
				temp[i][j] = p; // do not change p;

			}

		}
		return new ImmutablePixelArrayPicture(temp);

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
		Pixel[][]temp=pixel_array.clone();
		for (int i = ax; i <= bx; i++) {
			for (int j = ay; j <= by; j++) {
				temp[i][j] = temp[i][j].blend(p, factor);

			}

		}
		return new ImmutablePixelArrayPicture(temp);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius <= 0||p==null) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		Pixel[][]temp=pixel_array.clone();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j< height; j++) {
				if ((i - cx) * (i - cx) + (j - cy) * (j - cy) <= radius * radius) {
					temp[i][j] = p;
				}

			}
		}
		return new ImmutablePixelArrayPicture(temp);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius <= 0||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		Pixel[][]temp=pixel_array.clone();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if ((i - cx) * (i - cx) + (j - cy) * (j - cy) <= radius * radius) {
					temp[i][j] = temp[i][j].blend(p, factor);
				}

			}
		}
		return new ImmutablePixelArrayPicture(temp);
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	


