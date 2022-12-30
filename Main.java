import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		List<List<String>> list = readValues();
		System.out.println(list.get(0));
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
		System.out.println(sequential_data.get(0).get(1));
		System.out.println("value list size: " + valueList.size());
		System.out.println("value list out: " + valueList.get(0));
		System.out.println("seq data size: " + sequential_data.size());
		System.out.println("seq out: " + sequential_data.get(0));
		
		//Knapsack solver for first part
		int W = 1800000; // Total capacity of thr album in milliseconds
		Album OptimizedAlbum = AlbumOptimizer(valueList, weightList, W, valueList.size(), list, sequential_data);
		OptimizedAlbum.trackListSorter();
		
		
		OptimizedAlbum.display();
		
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

			// printing the fetched data
			for (List<String> list : data) {
				for (String str : list)
					System.out.print(str + " ");
				System.out.println();
			}
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

			// printing the fetched data
			for (List<String> list : data) {
				for (String str : list)
					System.out.print(str + " ");
				System.out.println();
			}
			br.close();
			return data;
		} catch (Exception e) {
			System.out.print(e);
			List<List<String>> data = new ArrayList<>();// list of lists to store data
			return data;
		}

	}

	public static Album AlbumOptimizer(List<Integer> valueList, List<Integer> weightList, int W, int length, List<List<String>> list, List<ArrayList<Double>> sequential_data) {

		int[][] BottomUpMatrix = new int[length + 1][W + 1];

		// First line of algorithm is assigned to 0
		for (int i = 0; i <= W; i++) {
			BottomUpMatrix[0][i] = 0;
		}

		for (int i = 1; i <= length; i++) {
			// We iterate on each capacity
			for (int j = 0; j <= W; j++) {

				if (weightList.get(i - 1) > j) {
					BottomUpMatrix[i][j] = BottomUpMatrix[i - 1][j];
				}

				else {
					// We maximize value at this rank in the matrix
					BottomUpMatrix[i][j] = Math.max(BottomUpMatrix[i - 1][j], valueList.get(i - 1) + BottomUpMatrix[i - 1][j - weightList.get(i - 1)]);
				}

			}
		}

		int currentCapacity = BottomUpMatrix[length][W];
		int w = W;
		ArrayList<Track> includedTracks = new ArrayList<>(); // List will be used to create optimized Album object to return

		for (int i = length; i > 0 && currentCapacity > 0; i--) {

			if (currentCapacity != BottomUpMatrix[i - 1][w]) {
				
				double[] currentDoubleArray = new double[sequential_data.get(i).size()];
				for(int j = 0; j < sequential_data.get(i).size(); j++){
					currentDoubleArray[j] = sequential_data.get(i).get(j);
				}
				
				includedTracks.add(new Track(Integer.parseInt(list.get(i).get(0)), Integer.parseInt(list.get(i).get(5)), Integer.parseInt(list.get(i).get(4)), currentDoubleArray));
				// We remove items value and weight
				currentCapacity -= valueList.get(i - 1);
				w -= weightList.get(i - 1);
			}
			
		}
		
		Album OptimizedAlbum = new Album(includedTracks, BottomUpMatrix[length][W]);
		return OptimizedAlbum;
	}
}
