import java.util.ArrayList;
import java.util.List;

public class Album {

	// list of items to put in the album to have the maximal value
	public ArrayList<Track> tracks;
	// maximal value possible
	public int totalValue;


	public Album(ArrayList<Track> tracks, int totalValue) {
		this.tracks = tracks;
		this.totalValue = totalValue;
		
	}
	
	public void AlbumOrganizer(){
		
	}

	public void trackListSorter(){
		//ArrayList<Track> sortedTracksArray = new ArrayList<>();
		Track tempTrack = new Track(0,0,0,null);

		for(int i = 0; i < tracks.size(); i++){
			for(int j = i+1; j < tracks.size(); j++){
				if(tracks.get(i).getIndividualValue() < tracks.get(j).getIndividualValue()){
					tempTrack = tracks.get(i);
					tracks.set(i, tracks.get(j));
					tracks.set(j, tempTrack);
				}
			}
		}
	}

	public List<Track> getTracks() {
		return tracks;
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
				System.out.println("- " + track.getId() + " with value: " + track.getIndividualValue());
			}
		}
	}

}
