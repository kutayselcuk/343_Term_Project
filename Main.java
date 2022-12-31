import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		List<List<String>> list = readValues();
		//System.out.println(list.get(0));
		List<Integer> valueList = new ArrayList<Integer>();
		List<Integer> weightList = new ArrayList<Integer>();
		for (int i = 1; i < list.size(); i++) {
			valueList.add(Integer.parseInt(list.get(i).get(4)));
			weightList.add(Integer.parseInt(list.get(i).get(5)));
		}
		List<List<String>> list1 = readSequential();
		List<ArrayList<Double>> sequential_data = new ArrayList<ArrayList<Double>>();
		for (int i = 1; i < list1.size(); i++) {
			ArrayList<Double> row = new ArrayList<>();
			for (int j = 1; j < list1.get(0).size(); j++) {
				row.add(Double.parseDouble(list1.get(i).get(j)));
			}
			sequential_data.add(row);
		}
		/* 
		System.out.println(sequential_data.get(0).get(1));
		System.out.println("value liset size: " + valueList.size());
		System.out.println("valuelist out: " + valueList.get(0));
		System.out.println("seq data size: " + sequential_data.size());
		System.out.println("seq out: " + sequential_data.get(0));
		*/

		//Knapsack solver for first part
		int W = 1800036; // Total capacity of thr album in milliseconds
		Album OptimizedAlbum = AlbumOptimizer(valueList, weightList, W, valueList.size(), list, sequential_data);
		OptimizedAlbum.trackListSorter_IndVal();
		//System.out.println("first version length: " + OptimizedAlbum.getTracks().size()); first and second version lengths are used to compare lengths after organization
		OptimizedAlbum.albumOrganizer(sequential_data.get(0).size());
		//System.out.println("second version length: " + OptimizedAlbum.getTracks().size());

		OptimizedAlbum.display();
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		
	}

	public static List<List<String>> readValues() throws IOException {
		try {
			List<List<String>> data = new ArrayList<>();// list of lists to store data
			String file = "term_project_value_data.csv";// file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// Reading until we run out of lines
			String line = br.readLine();
			while (line != null) {
				List<String> lineData = Arrays.asList(line.split(","));// splitting lines
				data.add(lineData);
				line = br.readLine();
			}

			/*
			// printing the fetched data
			for (List<String> list : data) {
				for (String str : list)
					System.out.print(str + " ");
				System.out.println();
			}
			*/
			br.close();
			return data;
		} catch (Exception e) {
			System.out.print(e);
			List<List<String>> data = new ArrayList<>();// list of lists to store data
			return data;
		}

	}

	public static List<List<String>> readSequential() throws IOException {
		try {
			List<List<String>> data = new ArrayList<>();// list of lists to store data
			String file = "term_project_sequential_data.csv";// file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// Reading until we run out of lines
			String line = br.readLine();
			while (line != null) {
				List<String> lineData = Arrays.asList(line.split(","));// splitting lines
				data.add(lineData);
				line = br.readLine();
			}

			/* 
			// printing the fetched data
			for (List<String> list : data) {
				for (String str : list)
					System.out.print(str + " ");
				System.out.println();
			}
			*/
			br.close();
			return data;
		} catch (Exception e) {
			System.out.print(e);
			List<List<String>> data = new ArrayList<>();// list of lists to store data
			return data;
		}

	}

	public static Album AlbumOptimizer(List<Integer> v, List<Integer> w, int W, int length, List<List<String>> list, List<ArrayList<Double>> sequential_data){
		
		//creating relative values
		ArrayList<Track> allTracks = new ArrayList<>();
		List<Double> relativeValues = new ArrayList<Double>();
		for(int i = 0; i < v.size(); i++){
			double value = v.get(i);
			double duration = w.get(i);
			double durationCredit = duration/1000*0.02;
			double relativeValue = value + durationCredit;
			relativeValues.add(relativeValue);

			double[] currentDoubleArray = new double[sequential_data.get(i).size()];
				for(int j = 0; j < sequential_data.get(i).size(); j++){
					currentDoubleArray[j] = sequential_data.get(i).get(j);
				}

			allTracks.add(new Track(i, w.get(i), relativeValues.get(i), v.get(i), currentDoubleArray));
		}
		Album allTracksAlbum = new Album(allTracks, 0, 0,0);
		allTracksAlbum.trackListSorter_RelVal();;

		int total_duration = 0;
		int total_popularity = 0;
		ArrayList<Track> includedTracks = new ArrayList<>();
		int index_counter = 0;

		while(total_duration + allTracks.get(index_counter).getDuration() <= W){
			includedTracks.add(allTracks.get(index_counter));
			total_duration += allTracks.get(index_counter).getDuration();
			total_popularity += allTracks.get(index_counter).getIndividualValue();
			index_counter += 1;
		}

		Album OptimizedAlbum = new Album(includedTracks, total_popularity, 0,W);
		OptimizedAlbum.trackListSorter_IndVal();
		return OptimizedAlbum;
	}
}
