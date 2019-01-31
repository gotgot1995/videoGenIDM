import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
	public static void appendLine(String s, File f) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f.getAbsolutePath(), "UTF-8");
			writer.append(s);
			writer.close();
		} catch (Exception e) {
			// 
		}
	}
	
	public static List<File> getFilesList(String path) {
		File f = new File(path);
		List<File> files = Arrays.asList(f.listFiles());
		return files;
	}
}
