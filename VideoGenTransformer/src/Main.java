import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		if(args.length < 1) {
			throw new IllegalArgumentException("Utilisation: commande [dossier video]");
		}
		
		List<File> files = FileUtils.getFilesList(args[0]);
		Optional<File> videogenFile =
				files.stream().filter(f -> f.getName().endsWith(".videogen")).findFirst();
		
		if(!videogenFile.isPresent()) {
			throw new RuntimeException("Impossible de trouver le fichier .videogen");
		}
		
		System.out.println(videogenFile.get().getPath());
	}
}
