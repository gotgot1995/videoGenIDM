import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
	public static void appendLine(String s, PrintWriter writer) {
		try {
			writer.append(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<File> getFilesList(String path) {
		File f = new File(path);
		List<File> files = Arrays.asList(f.listFiles());
		return files;
	}
}
