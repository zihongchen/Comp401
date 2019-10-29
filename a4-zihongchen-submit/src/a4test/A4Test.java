package a4test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a4.*;

public class A4Test {

	static public String[] getTestNames() {
		String[] test_names = new String[15];

		test_names[0] = "testImmutableArrayPictureConstructor2dPixelArray";
		test_names[1] = "testMutableArrayPictureConstructor2dPixelArray";
		test_names[2] = "testImmutablePictureGetCaption";
		test_names[3] ="testMutablePictureGetCaption";
		test_names[4] ="testMutablePictureSetCaption";
		test_names[5] ="testImmutablePictureSetCaption";
		test_names[6] ="testExtractOfImmutablePicture";
		test_names[7] ="testImmutablePictureSetCaption";
		test_names[8] ="testExtractOfMutablePicture";	
		test_names[9] ="testGetSourceOfImmutablePicture";

		return test_names;
	}

	// Initialize different pixel amounts.
	Pixel red = new ColorPixel(1, 0, 0);
	Pixel green = new ColorPixel(0, 1, 0);
	Pixel blue = new ColorPixel(0, 0, 1);
	Pixel orange = new ColorPixel(0.9, 0.6, 0.1);
	Pixel yellow = new ColorPixel(0.9, 1, 0.1);
	Pixel randomColor = new ColorPixel(0.545, 0.65, 0.332);
	// Valid Pixel 2d Arrays
	Pixel[][] rgbPicture = { { red, red, red }, { green, green, green }, { blue, blue, blue } };
	Pixel[][] randomPicture = { { blue, randomColor, randomColor, yellow }, { green, red, green, randomColor } };
	// Invalid Pixel 2d Arrays
	Pixel[][] noWidthPicture = { {}, {} };
	Pixel[][] noHeightPicture = { {} };
	Pixel[][] differentHeightPicture = { { red, blue, green }, { red, green, blue }, { red, green } };
	Pixel[][] includesNullRowsPicture = { { red, red }, null, { blue, blue } };
	Pixel[][] includesNullPixelsPicture = { { randomColor, blue, red, blue }, { green, green, randomColor, blue },
			{ blue, blue, randomColor, null } };
	
	
	
	
	
	
	
	Pixel[][] good = { { red, red, red,orange }, { green, green, green,blue }, { blue, blue, blue,randomColor },{ blue, blue, blue,red }, { red, green, blue,green },{ randomColor, blue, red, blue } };

	@Test
	public void testImmutableArrayPictureConstructor2dPixelArray() {
		try {
			Picture invalidNullPixelArray = new ImmutablePixelArrayPicture(null, "");
			fail("Pixel 2d array cannot be null");
		} catch (IllegalArgumentException e) {
		}

		try {
			Picture noWidth2dPixelsArray = new ImmutablePixelArrayPicture(noWidthPicture, "");
			fail("Pixel array has illegal geometry.");

		} catch (IllegalArgumentException e) {
		}

		try {
			Picture noHeight2dPixelsArray = new ImmutablePixelArrayPicture(noHeightPicture, "");
			fail("Pixel array has illegal geometry.");
		} catch (IllegalArgumentException e) {
		}
		try {
			Picture differentHeight2dPixelsArray = new ImmutablePixelArrayPicture(differentHeightPicture, "");
			fail("Columns in picture are not all the same height.");
		} catch (IllegalArgumentException e) {
		}
		try {
			Picture invalidNullRow2dPixelArray = new ImmutablePixelArrayPicture(includesNullRowsPicture, "");
			fail("Pixel array includes null rows.");
		} catch (IllegalArgumentException e) {
		}
		try {
			Picture invalidNullPixels2dPixelArray = new ImmutablePixelArrayPicture(includesNullPixelsPicture, "");
			fail("Pixel array includes null pixels.");
		} catch (IllegalArgumentException e) {
		}
		try {
			Picture helloha = new ImmutablePixelArrayPicture(rgbPicture, null);
			fail("Caption can not be null!");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testMutableArrayPictureConstructor2dPixelArray() {
		try {
			Picture invalidNullPixelArray = new MutablePixelArrayPicture(null, "");
			fail("Pixel 2d array cannot be null");
		} catch (IllegalArgumentException e) {
		}

		try {
			Picture noWidth2dPixelsArray = new MutablePixelArrayPicture(noWidthPicture, "");
			fail("Pixel array has illegal geometry.");

		} catch (IllegalArgumentException e) {
		}

		try {
			Picture noHeight2dPixelsArray = new MutablePixelArrayPicture(noHeightPicture, "");
			fail("Pixel array has illegal geometry.");
		} catch (IllegalArgumentException e) {
		}
		try {
			Picture differentHeight2dPixelsArray = new MutablePixelArrayPicture(differentHeightPicture, "");
			fail("Columns in picture are not all the same height.");
		} catch (IllegalArgumentException e) {
		}
		try {
			Picture invalidNullRow2dPixelArray = new MutablePixelArrayPicture(includesNullRowsPicture, "");
			fail("Pixel array includes null rows.");
		} catch (IllegalArgumentException e) {
		}
		try {
			Picture invalidNullPixels2dPixelArray = new MutablePixelArrayPicture(includesNullPixelsPicture, "");
			fail("Pixel array includes null pixels.");
		} catch (IllegalArgumentException e) {
		}
		try {
			Picture helloha = new MutablePixelArrayPicture(rgbPicture, null);
			fail("Caption can not be null!");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void testImmutablePictureGetCaption() {
		Picture a = new ImmutablePixelArrayPicture(rgbPicture, "123");
		assertEquals("123", a.getCaption());

		Picture b = new ImmutablePixelArrayPicture(rgbPicture, "Helloworld");
		assertEquals("Helloworld", b.getCaption());

		Picture c = new ImmutablePixelArrayPicture(rgbPicture, "Iamhandsome");
		assertEquals("Iamhandsome", c.getCaption());

	}

	@Test
	public void testMutablePictureGetCaption() {
		Picture d = new MutablePixelArrayPicture(rgbPicture, "123");
		assertEquals("123", d.getCaption());

		Picture e = new MutablePixelArrayPicture(rgbPicture, "Helloworld");
		assertEquals("Helloworld", e.getCaption());

		Picture f = new MutablePixelArrayPicture(rgbPicture, "Iamhandsome");
		assertEquals("Iamhandsome", f.getCaption());

	}

	@Test
	public void testMutablePictureSetCaption() {
		Picture d = new MutablePixelArrayPicture(rgbPicture, "123");

		d.setCaption("123");
		assertEquals("123", d.getCaption());

		Picture e = new MutablePixelArrayPicture(rgbPicture, "Helloworld");

		e.setCaption("123");
		assertEquals("123", e.getCaption());

		Picture f = new MutablePixelArrayPicture(rgbPicture, "Iamhandsome");

		f.setCaption("123");
		assertEquals("123", f.getCaption());

	}

	@Test
	public void testImmutablePictureSetCaption() {
		Picture a = new ImmutablePixelArrayPicture(rgbPicture, "123");

		a.setCaption("123");
		assertEquals("123", a.getCaption());

		Picture b = new ImmutablePixelArrayPicture(rgbPicture, "Helloworld");
		b.setCaption("123");
		assertEquals("123", b.getCaption());

		Picture c = new ImmutablePixelArrayPicture(rgbPicture, "Iamhandsome");

		c.setCaption("123");
		assertEquals("123", c.getCaption());

	}

	@Test
	public void testExtractOfImmutablePicture() {
		Picture a = new ImmutablePixelArrayPicture(good, "123");
		SubPicture c=a.extract(1, 2, 2, 2);
		assertEquals(c.getXOffset(),1);
		assertEquals(c.getYOffset(),2);
		for(int y=2;y<c.getHeight();y+=2) {
			for(int x=2;x<c.getWidth();x+=2) {
				assertEquals(c.getPixel(x, y),good[x][y]);
			}
		}
		
		SubPicture d= a.extract(1, 1, 1, 2);
		assertEquals(d.getXOffset(),1);
		assertEquals(d.getYOffset(),1);
		for(int y=1;y<d.getHeight();y+=2) {
			for(int x=1;x<d.getWidth();x+=1) {
				assertEquals(d.getPixel(x, y),good[x][y]);
			}
		}
		
		
		
		try {
			SubPicture k=a.extract(5, 5, 10, 10);
			fail("illegal");
		}catch(IllegalArgumentException k){
			
		}
		
	 
		
		
	}
	@Test
	public void testExtractOfMutablePicture() {
		Picture a = new MutablePixelArrayPicture(good, "123");
		SubPicture c=a.extract(1, 2, 2, 2);
		assertEquals(c.getXOffset(),1);
		assertEquals(c.getYOffset(),2);
		for(int y=2;y<c.getHeight();y+=2) {
			for(int x=2;x<c.getWidth();x+=2) {
				assertEquals(c.getPixel(x, y),good[x][y]);
			}
		}
		
		SubPicture d= a.extract(1, 1, 3, 3);
		assertEquals(d.getXOffset(),1);
		assertEquals(d.getYOffset(),1);
		
		
		try {
			SubPicture k=a.extract(5, 5, 10, 10);
			fail("illegal");
		}catch(IllegalArgumentException k){
			
		}
		
	 
		
		
	}
	
	@Test
	public void testGetSourceOfImmutablePicture() {
		Picture a = new ImmutablePixelArrayPicture(good, "123");
		SubPicture c=a.extract(1, 2, 2, 2);
		assertEquals(a,c.getSource());
		
		Picture b=new ImmutablePixelArrayPicture(rgbPicture,"");
		SubPicture d=a.extract(1, 2, 2, 2);
		assertEquals(a,d.getSource());
	
	}

}
