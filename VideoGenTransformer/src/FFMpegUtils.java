import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FFMpegUtils {
	public static void concatVideo(List<String> playList) {
		File file = new File("tmpList.txt");
		try {
			file.createNewFile();
			
			playList.forEach(video -> {
				FileUtils.appendLine(String.format("file '%s'", video), file);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		runCommand(String.format("ffmpeg -f concat -safe 0 -i tmpList.txt -c copy output.mp4"));
	}
	
	public static void runCommand(String command) {
		try {
			Process p = Runtime.getRuntime().exec(command);
			
			showStandardOutput(p);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	private static void showStandardOutput(Process p) throws IOException {
		BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(p.getInputStream()));
		
		BufferedReader stdError = new BufferedReader(new 
            InputStreamReader(p.getErrorStream()));
		
		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s;
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}
		   
		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
		    System.out.println(s);
		}
	}
}
