import java.util.List;

public class Album {
	
	// list of items to put in the bag to have the maximal value
	public List<Track> tracks;
	// maximal value possible
	public int totalValue;
	
	public Album(List<Track> tracks, int value) {
		this.tracks = tracks;
		this.totalValue = value;
	}
	
	public void display() {
		if (tracks != null  &&  !tracks.isEmpty()){
			System.out.println("\nKnapsack solution");
			System.out.println("Value = " + totalValue);
			System.out.println("Items to pick :");
			
			for (Track track : tracks) {
				System.out.println("- " + track.getId());
			}
		}
	}

}
