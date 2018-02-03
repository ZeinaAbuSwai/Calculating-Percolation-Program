package Graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Search {

	//Variables
	private static String fileName = "words.txt";
	private static String START = "s";
	private static String END = "q";
	private static Double totalPercent;
	private static boolean FLAG = false;

	// main //
	public static void main(String[] args) {
		// this graph is directional
		Graph graph = new Graph();
		readFile(graph, fileName);

		int loop = 0;
		// looping through the graph to every termination node.
		while(loop < graph.getLeaves().size()){
			totalPercent = 0.0;
			LinkedList<String> visited = new LinkedList<>();
			visited.add(START);
			END = graph.getLeaves().get(loop);
			depthAlgo(graph, visited);
			loop++;
			if(totalPercent > 0){
				//System.out.println("\n       The chance of getting FROM: "+START+" TO: "+END+" is: "+roundNumber(totalPercent));
				//System.out.println();
			}
		}// if its a leaf or a none excising node.
		if(FLAG == false){
			if(graph.getLeaves().contains(START)){
				System.out.println("The requested node is a leaf.");
			}else{
				System.out.println(new Exception("ERROR! The input word does NOT appear in the graph."));
			}
		}

	}// Rounding double numbers
	private static double roundNumber(double num) {
		num = num*10000;
		num = (double) Math.round(num);
		return num = num /10000;
	}

	// Using some sort of DFS.
	private static void depthAlgo(Graph graph, LinkedList<String> visited) {
		LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
		// examine adjacent nodes
		for (String node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(END)) {
				visited.add(node);
				printPath(visited, graph);
				visited.removeLast();
				break;
			}
		}
		for (String node : nodes) {
			if (visited.contains(node) || node.equals(END)) {
				continue;
			}
			visited.addLast(node);
			depthAlgo(graph, visited);
			visited.removeLast();
		}
	}

	private static void printPath(LinkedList<String> visited, Graph graph) {
		Node temp;
		String nextName = "";
		Double numerator = 1.0,sum = 0.0, total = 1.0;
		String[] nodeNames = null;
		Double[] nodePrices = null;
		for (int i=0; i<visited.size(); i++) {   
			if(i<visited.size()-1){
				temp = graph.getMap().get(visited.get(i));
				nextName = visited.get(i+1); 
				nodeNames =  temp.getPrice().keySet().toArray(new String[0]);
				nodePrices =  temp.getPrice().values().toArray(new Double[0]);
				for (int j = 0; j < nodePrices.length; j++) {
					sum += nodePrices[j];
					if(nodeNames[j].equals(nextName))
						numerator = nodePrices[j];
				}
			}
			System.out.print(visited.get(i));
			System.out.print(" ~> ");
			if(sum != 0){
				total *= numerator/sum;
			}
			sum=0.0;
		}
//		System.out.println(roundNumber(total));
		System.out.println();
		totalPercent += total;
		FLAG = true;
	}

	private static void readFile(Graph g , String filePath){
		String[] splits;
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();

			while (line != null) {
				splits = line.split(":");
				g.addEdge(splits[0], splits[2], splits[1]);
				line = br.readLine();
			}
		}catch (Exception e) {
		}
	}
}