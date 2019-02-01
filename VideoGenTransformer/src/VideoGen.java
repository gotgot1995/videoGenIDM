import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.URI;

import fr.istic.videoGen.AlternativesMedia;
import fr.istic.videoGen.MandatoryMedia;
import fr.istic.videoGen.MediaDescription;
import fr.istic.videoGen.OptionalMedia;
import fr.istic.videoGen.VideoGeneratorModel;

public class VideoGen {
	private List<String> myPlaylist = new ArrayList<String>();
	
	public VideoGen(String specPath) {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(specPath));

		videoGen.getMedias().forEach(media -> {
			if(media instanceof MandatoryMedia) {
				MandatoryMedia m = (MandatoryMedia) media;
				myPlaylist.add(m.getDescription().getLocation());
			} else if (media instanceof OptionalMedia) {
				OptionalMedia o = (OptionalMedia) media;
				Random r = new Random();
				if(r.nextBoolean()) {
					myPlaylist.add(o.getDescription().getLocation());
				}
			} else if (media instanceof AlternativesMedia) {
				AlternativesMedia altMedia = (AlternativesMedia) media;
				Random r = new Random();
				MediaDescription pickedMedia = altMedia.getMedias().get(r.nextInt(altMedia.getMedias().size()));
				this.myPlaylist.add(pickedMedia.getLocation());
			}
		});
	}

	public List<String> getPlaylist() {
		return this.myPlaylist;
	}


}



