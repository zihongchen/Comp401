package a3;

public class GammaCorrect implements PixelTransformation{
	private double gamma;

	public GammaCorrect (double gamma) {
		this.gamma=gamma;
	}

	@Override
	public Pixel transform(Pixel p) {
		double b = Math.pow(p.getBlue(), (1.0/gamma));
		double r = Math.pow(p.getRed(), (1.0/gamma));
		double g = Math.pow(p.getGreen(), (1.0/gamma));
		Pixel temp=new ColorPixel( r,  g,  b);
		
		return temp;
	}
}
