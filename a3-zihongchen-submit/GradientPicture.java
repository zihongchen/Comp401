package a3;

public class GradientPicture implements Picture{

	// ArrayList <Pixel[]> k;
	private int width;
	private int height;
	private Pixel upper_left;
	private Pixel upper_right;
	private Pixel lower_left;
	private  Pixel lower_right;

	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {
		if (width <= 0 || height <= 0||upper_left==null||upper_right==null||lower_left==null||lower_right==null) {
			throw new IllegalArgumentException("Incorrect Value");
		}
		this.width=width;
		this.height=height;
		this.upper_left=upper_left;
		this.upper_right=upper_right;
		this.lower_left=lower_left;
		this.lower_right=lower_right;
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
		if (x < 0 || y < 0 ||x > width - 1 || y > height - 1) {
			throw new IllegalArgumentException("you can do it");
		}
		

		double factorX=(double)(x)/(width-1);
		Pixel blendX=upper_left.blend(upper_right, factorX);
		Pixel blendX2=lower_left.blend(lower_right, factorX);
		double factorY=(double)(y)/(height-1);
		Pixel wanted=blendX.blend(blendX2, factorY);
		

		// TODO Auto-generated method stub
		return wanted;
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if ( x < 0 || y < 0 ||x > width - 1 || y > height - 1||p==null) {
			throw new IllegalArgumentException("x or y are not in range");
		}
		Pixel [][]temp=new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				temp[i][j]=getPixel(i,j);
		}
		
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(temp.clone());
		sb.paint(x, y, p);
		return sb;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("No hommie");
		}

		Pixel [][]temp=new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				temp[i][j]=getPixel(i,j);
		}
		
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(temp.clone());
		sb.paint(x, y, p,factor);
		return sb;
		
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
		Pixel [][]temp=new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				temp[i][j]=getPixel(i,j);
		}
		
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(temp.clone());
		sb.paint(ax,ay,bx,by,p);
		return sb;

	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if ( ax > width - 1 || ay > height - 1|| bx > width - 1 || by > height - 1||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("No hommie");
		}
		Pixel [][]temp=new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				temp[i][j]=getPixel(i,j);
		}
		
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(temp.clone());
		sb.paint(ax,ay,bx,by,p,factor);
		return sb;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius <=0||p==null) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		Pixel [][]temp=new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				temp[i][j]=getPixel(i,j);
		}
		
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(temp);
		sb.paint(cx,cy,radius,p);
		return sb;

	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius <= 0||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		Pixel [][]temp=new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				temp[i][j]=getPixel(i,j);
		}
		
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(temp.clone());
		sb.paint(cx,cy,radius,p,factor);
		return sb;
}

}

	

	


