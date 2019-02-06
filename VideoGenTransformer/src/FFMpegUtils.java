import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class FFMpegUtils {
	public static void concatVideos(List<String> playList) {
		File f = new File(playList.get(0));
		File file = new File(f.getParent() + "/tmpList.txt");
		
		try {
			file.createNewFile();
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			
			playList.forEach(video -> {
				FileUtils.appendLine(String.format("file '%s'\n", video), writer);
			});
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String outputPath = file.getParentFile().getAbsolutePath() + "/output.mp4";
		runCommand(String.format("ffmpeg -f concat -safe 0 -i %s -c copy %s", file.getAbsolutePath(), outputPath));
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
