import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		if(args.length < 1) {
			throw new IllegalArgumentException("Utilisation: commande [dossier video]");
		}
		
		List<File> files = FileUtils.getFilesList(args[0]);
		File folder = new File(args[0]);
		Optional<File> videogenFile =
				files.stream().filter(f -> f.getName().endsWith(".videogen")).findFirst();
		
		if(!videogenFile.isPresent()) {
			throw new RuntimeException("Impossible de trouver le fichier .videogen");
		}
		
		VideoGen videoGen = new VideoGen(videogenFile.get().getPath());
		List<String> videosList = videoGen.getPlaylist().stream().map(s -> {
			return folder.getAbsolutePath() + "/" + s;
		}).collect(Collectors.toList());
		
		FFMpegUtils.concatVideos(videosList);
	}
}
