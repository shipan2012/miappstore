package example.xfsp.miappstore.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
	/** 关闭流 */
	public static boolean closeQuietly(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
			}
		}
		return true;
	}
}
