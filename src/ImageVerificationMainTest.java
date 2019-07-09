
import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

/**
 * Test for <code>ImageVerificationMain</code>
 *
 */
public class ImageVerificationMainTest {

	@Test
	public void testGetContentTypeFromByte_for_NULL() {
		String result = null;
		try {
			result = ImageVerificationMain.getImageTypeFromByte(null);
		} catch (IOException e) {
			System.err.println(e);
		}
		assertEquals(result, null);
	}

	@Test
	public void testGetContentTypeFromByte_for_Text() {
		String result = null;
		try {
			InputStream imageInputStream = new ByteArrayInputStream("this is a text".getBytes());
			result = ImageVerificationMain.getImageTypeFromByte(imageInputStream);
		} catch (IOException e) {
			System.err.println(e);
		}
		assertEquals(result, null);
	}

	@Test
	public void testGetContentTypeFromByte_for_PNG() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		String result = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "png", os);
			InputStream imageInputStream = new ByteArrayInputStream(os.toByteArray());
			result = ImageVerificationMain.getImageTypeFromByte(imageInputStream);
		} catch (IOException e) {
			System.err.println(e);
		}
		assertEquals(result, "image/png");
	}

	@Test
	public void testGetContentTypeFromByte_for_GIF() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		String result = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "gif", os);
			InputStream imageInputStream = new ByteArrayInputStream(os.toByteArray());
			result = ImageVerificationMain.getImageTypeFromByte(imageInputStream);
		} catch (IOException e) {
			System.err.println(e);
		}
		assertEquals(result, "image/gif");
	}

	@Test
	public void testGetContentTypeFromByte_for_JPEG() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		String result = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "jpeg", os);
			InputStream imageInputStream = new ByteArrayInputStream(os.toByteArray());
			result = ImageVerificationMain.getImageTypeFromByte(imageInputStream);
		} catch (IOException e) {
			System.err.println(e);
		}
		assertEquals(result, "image/jpeg");
	}

}
