import java.util.List;

public class Album {

	// list of items to put in the album to have the maximal value
	public List<Track> tracks;
	// maximal value possible
	public int totalValue;


	public Album(List<Track> tracks, int value) {
		this.tracks = tracks;
		this.totalValue = value;
		
	}

	public int totalMinute(){
		int totalMinute = 0;
		for (Track track : tracks) {
			totalMinute += track.getDuration();
		}
		return totalMinute;
	}

	public void display() {
		double totalMinute = totalMinute();
		if (tracks != null  &&  !tracks.isEmpty()){
			System.out.println("\nAlbum Solution");
			System.out.println("Popularity = " + totalValue);
			System.out.println("Total Minute: " + (totalMinute/60000));
			System.out.println("Tracks to pick :");

			for (Track track : tracks) {
				System.out.println("- " + track.getId());
			}
		}
	}

}
