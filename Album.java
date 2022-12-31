import java.util.ArrayList;
import java.util.List;

public class Album {

	// list of items to put in the album to have the maximal value
	public ArrayList<Track> tracks;
	// maximal value possible
	public int totalValue;
	public double totalSeqValue;
	public int capacity;


	public Album(ArrayList<Track> tracks, int totalValue, double totalSeqValue, int capacity) {
		this.tracks = tracks;
		this.totalValue = totalValue;
		this.totalSeqValue = totalSeqValue;
		this.capacity = capacity;
	}
	
	public void albumOrganizer(int length){

		ArrayList<Track> tempTracksList = tracks;
		ArrayList<Integer> IDList = tracksIdList();
		ArrayList<Integer> usedIDs = new ArrayList<>();
		ArrayList<Track> organizedTrackList = new ArrayList<>();
	
		Track firstTrack = tracks.get(0);
		Track lastTrack = tracks.get(1);

		organizedTrackList.add(firstTrack);
		usedIDs.add(firstTrack.getId());
		usedIDs.add(lastTrack.getId());

		tempTracksList.remove(0);
		tempTracksList.remove(0);

		while(tempTracksList.size() != 0){

			double currentMaxSeqValue = 0;
			int currentMaxID = 0;

			Track previousLastTrack = organizedTrackList.get(organizedTrackList.size()-1);
			//System.out.println("id: " + previousLastTrack.track_id); --> if you want to see current last track and next previous track, in line 50 you can see sequantial value comperison between this track and leftover tracks that are not getting in the organized album
			double[] currentSeqArray = previousLastTrack.getSequentialValue();

			for(int i = 0; i < length; i++){
				if(IDList.contains(i) && currentMaxSeqValue <= currentSeqArray[i] && !usedIDs.contains(i)){
					currentMaxID = i;
					//System.out.println("current seq array length: " + currentSeqArray.length + "i and index: " + i); --> if you want to see which tracks are considered
					currentMaxSeqValue = currentSeqArray[i];
				}
			}

			totalSeqValue += currentMaxSeqValue;

			Track nexTrack = getTrackWithID(currentMaxID);
			usedIDs.add(currentMaxID);
			organizedTrackList.add(nexTrack);
			tempTracksList.remove(nexTrack);
		}

		totalSeqValue += organizedTrackList.get(organizedTrackList.size()-1).getSequentialValue()[lastTrack.track_id];
		organizedTrackList.add(lastTrack);
		tracks = organizedTrackList;
	}

	public ArrayList<Integer> tracksIdList(){

		ArrayList<Integer> tracksIdList = new ArrayList<>();
		for(Track track : tracks){
			tracksIdList.add(track.getId());
		}
		return tracksIdList;
	}

	public Track getTrackWithID(int id){
		for(Track track : tracks){
			if(id == track.getId()){
				return track;
			}
		}
		return null;
	}

	public void trackListSorter(){
		
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


	public int totalDuration(){
		int totalMinute = 0;
		for (Track track : tracks) {
			totalMinute += track.getDuration();
		}
		return totalMinute;
	}

	public void display() {
		double totalDuration = totalDuration();
		double emptySecondsCost = (capacity-totalDuration)/1000*0.02;

		if (tracks != null  &&  !tracks.isEmpty()){
			System.out.println("\nAlbum Solution");
			System.out.println("Popularity = " + (totalValue-emptySecondsCost));
			System.out.println("Total Minute: " + (totalDuration));
			System.out.println("Total Sequantial Value: " + totalSeqValue);
			System.out.println("\nTracks to pick:");

			int i = 1;
			for (Track track : tracks) {
				System.out.println("Queue No: " + i + " | ID: " + track.getId() + " | Value: " + track.getIndividualValue());
				i++;
			}
		}
	}

}
