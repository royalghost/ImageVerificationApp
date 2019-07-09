import java.io.IOException;
import java.io.InputStream;

/**
 * Check if image type is <code>gif</code>, <code>png</code> or
 * <code>jpeg</code>. For all other types of content return null.
 * 
 * This is based on the magic number. See
 * {@link https://en.wikipedia.org/wiki/Magic_number_(programming)}
 * 
 */
public class ImageVerificationMain {

	public static final String getImageTypeFromByte(InputStream is) throws IOException {
		String contentType = null;
		if (is == null)
			return contentType;

		try {
			int b1 = is.read();
			// Check for empty input stream
			if (b1 == -1)
				return contentType;
			int b2 = is.read();
			int b3 = is.read();
			int b4 = is.read();
			int b5 = is.read();
			int b6 = is.read();
			int b7 = is.read();
			int b8 = is.read();
			/**
			 * GIF image files have the ASCII code for "GIF89a" (47 49 46 38 39 61) or
			 * "GIF87a" (47 49 46 38 37 61)
			 */
			if (b1 == 0x47 && b2 == 0x49 && b3 == 0x46 && b4 == 0x38 && (b5 == 0x39 || b5 == 0x37) && b6 == 0x61) {
				contentType = "image/gif";
			/**
			 * PNG image files begin with an 8-byte signature which identifies the file as a
			 * PNG file and allows detection of common file transfer problems: \211 P N G \r
			 * \n \032 \n (89 50 4E 47 0D 0A 1A 0A).
			 */
			} else if (b1 == 0x89 && b2 == 0x50 && b3 == 0x4E && b4 == 0x47 && b5 == 0x0D && b6 == 0x0A && b7 == 0x1A
					&& b8 == 0x0A) {
				contentType = "image/png";
			/**
			 * JPEG image files begin with FF D8 and end with FF D9. JPEG/JFIF files contain
			 * the ASCII code for "JFIF" (4A 46 49 46) as a null terminated string.
			 * JPEG/Exif files contain the ASCII code for "Exif" (45 78 69 66) also as a
			 * null terminated string, followed by more metadata about the file.
			 */
			} else if (b1 == 0xFF && b2 == 0xD8) {
				contentType = "image/jpeg";
			}
		} catch (IOException ex) {
			throw ex;
		}

		return contentType;
	}

}
