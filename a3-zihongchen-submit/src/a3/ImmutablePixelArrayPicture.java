package a3;

public class ImmutablePixelArrayPicture implements Picture {
	private Pixel[][] pixel_array;
	// ArrayList <Pixel[]> k;
	private int width;
	private int height;

	// Creates new object using values provided by pixel_array, matching in size.
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
		if (pixel_array==null||pixel_array.length==0) {//no width
			throw new IllegalArgumentException("Incorrect Value");
		}
		
		for(int i=0;i<pixel_array.length;i++){
			if( pixel_array[i]==null)//no height
				throw new IllegalArgumentException("Incorrect Value");
			if( pixel_array[0].length!=pixel_array[i].length)//height different
					throw new IllegalArgumentException("Incorrect Value");
			if( pixel_array[i].length==0)//no height
				throw new IllegalArgumentException("Incorrect Value");
			// no// no point

			}
		for(int i=0;i<pixel_array.length;i++){
		for (int j = 0; j <pixel_array[0].length ; j++) {
			if(pixel_array[i][j] ==null) {
				throw new IllegalArgumentException("Incorrect Value");// all values will change if you change the initial value
			}
			}
					
		}
		Pixel[][]temp=new Pixel[pixel_array.length][];
		for(int i=0;i<pixel_array.length;i++) {
			temp[i]=pixel_array[i].clone();
		}
				this.pixel_array = temp;
				this.width=pixel_array.length;
				this.height=pixel_array[0].length;
		// k=(ArrayList[][]) pixel_array;
	}

	// Creates new object by providing geometry of picture and an initial value for
	// all pixels.
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		//width <= 0 || height <= 0||
		if (width <= 0 || height <= 0||initial_value==null) {
			throw new IllegalArgumentException("Incorrect Value");
		}
		this.width = width;
		this.height = height;
		pixel_array = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixel_array[i][j] = initial_value; // all values will change if you change the initial value

			}
		}

	}

	// Creates new object by providing geometry of picture.
	// Initial value of all pixels should be medium gray (i.e., a grayscale pixel
	// with intensity 0.5)
	public ImmutablePixelArrayPicture(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Incorrect Value");
		}
		this.width = width;
		this.height = height;
		pixel_array = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixel_array[i][j] = new GrayPixel(0.5);

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
		//x <= 0 || y <= 0 ||
		if ( x < 0 || y < 0 ||x > width - 1 || y > height - 1) {
			throw new IllegalArgumentException("x or y are not in range");
		}

		// TODO Auto-generated method stub
		return pixel_array.clone()[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if ( x < 0 || y < 0 ||x > width - 1 || y > height - 1||p==null) {
			throw new IllegalArgumentException("you can do it");
		}
		Pixel[][]temp=new Pixel[pixel_array.length][];
		for(int i=0;i<pixel_array.length;i++) {
			temp[i]=pixel_array[i].clone();
		}
		temp[x][y] = p;
		return new MutablePixelArrayPicture(temp);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("No hommie");
		}
		Pixel[][]temp=pixel_array.clone();
		temp[x][y] = pixel_array[x][y].blend(p, factor);
		return new MutablePixelArrayPicture(temp);
		

	
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
			for (int j = ay; j<= by; j++) {
				temp[i][j] = p; // do not change p;

			}

		}
		return new MutablePixelArrayPicture(temp);

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
				temp[i][j] = pixel_array[i][j].blend(p, factor);

			}

		}
		return new ImmutablePixelArrayPicture(temp);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius <=0||p==null) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		Pixel[][]temp=pixel_array.clone();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if ((i - cx) * (i - cx) + (j - cy) * (j - cy) <= radius * radius) {
					temp[i][j] = p;
				}

			}
		}
		return new MutablePixelArrayPicture(temp);

	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius <= 0||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		Pixel[][]temp=pixel_array.clone();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j< height; j++) {
				if ((i - cx) * (i - cx) + (j - cy) * (j - cy) <= radius * radius) {
					temp[i][j] = pixel_array[i][j].blend(p, factor);
				}

			}
		}
		return new MutablePixelArrayPicture(temp);
	}
}
