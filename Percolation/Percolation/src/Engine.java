import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;


public class Engine {
	private ArrayList<String> subGraphs = new ArrayList<String>(); //contains all the subgraphs
	private HashMap<Integer, List<Integer>> edgesMap = new HashMap<Integer, List<Integer>>(); //contains the edges
	private ArrayList<Double> conductivity = new ArrayList<Double>();
	private ArrayList<Double> probability = new ArrayList<Double>();
	public HashMap<Double, Double> finalVals = new HashMap<Double, Double>(); //final result of conductivity and prob of getting each value
	
	public int renormalizStep = 5;	
	public double R = 0.0;
	public double G = 0.0;
	
	/**
	 * Main
	 */
	public HashMap<Double, Double> mainFunction(double p, int dimension) {
	//	double p = 0.5;
	//	int dimension = 3;
		
		int edges = 0;
		if(dimension == 2)
			edges = 5;
		else if(dimension == 3)
			edges = 13;
		else if(dimension == 4)
			edges = 25;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < edges; i++) { 
			map.put(i, i);
		}
		subGraphs(edges, map);
		buildHashMap(dimension);
		createMatrix(edges, subGraphs, dimension, p, 1);
		calculateVals();
		
		
		//System.out.println(finalVals.toString());
		
		//renormalization(p);
		
		return finalVals;
	}
	
	/**
	 * Fills in the hashmap where the keys are the edges and the values are the relevant vertices.
	 * @param dimension - dimension of the grid.
	 */
	private void buildHashMap(int dimension) {
		if(dimension == 2)
			buildHashMapDim2();
		else if(dimension == 3)
			buildHashMapDim3();
		else if(dimension == 4)
			buildHashMapDim4();
	}
	
	/**
	 * Fills in the hashmap where the keys are the edges and the values are the relevant
	 * vertices for the 2x2 grid.
	 */
	private void buildHashMapDim2() {
		List<Integer> zero = new ArrayList<Integer>();
		zero.add(0);
		zero.add(1);
		edgesMap.put(0, zero);
		List<Integer> one = new ArrayList<Integer>();
		one.add(1);
		one.add(3);
		edgesMap.put(1, one);
		List<Integer> two = new ArrayList<Integer>();
		two.add(1);
		two.add(2);
		edgesMap.put(2, two);
		List<Integer> three = new ArrayList<Integer>();
		three.add(0);
		three.add(2);
		edgesMap.put(3, three);
		List<Integer> four = new ArrayList<Integer>();
		four.add(2);
		four.add(3);
		edgesMap.put(4, four);
	}

	/**
	 * Fills in the hashmap where the keys are the edges and the values are the relevant
	 * vertices for the 3x3 grid.
	 */
	private void buildHashMapDim3() {
		List<Integer> zero = new ArrayList<Integer>();
		zero.add(0);
		zero.add(1);
		edgesMap.put(0, zero);
		List<Integer> one = new ArrayList<Integer>();
		one.add(0);
		one.add(2);
		edgesMap.put(1, one);
		List<Integer> two = new ArrayList<Integer>();
		two.add(0);
		two.add(3);
		edgesMap.put(2, two);
		List<Integer> three = new ArrayList<Integer>();
		three.add(1);
		three.add(4);
		edgesMap.put(3, three);
		List<Integer> four = new ArrayList<Integer>();
		four.add(1);
		four.add(2);
		edgesMap.put(4, four);
		List<Integer> five = new ArrayList<Integer>();
		five.add(2);
		five.add(5);
		edgesMap.put(5, five);
		List<Integer> six = new ArrayList<Integer>();
		six.add(2);
		six.add(3);
		edgesMap.put(6, six);
		List<Integer> seven = new ArrayList<Integer>();
		seven.add(3);
		seven.add(6);
		edgesMap.put(7, seven);
		List<Integer> eight = new ArrayList<Integer>();
		eight.add(5);
		eight.add(6);
		edgesMap.put(8, eight);
		List<Integer> nine = new ArrayList<Integer>();
		nine.add(5);
		nine.add(4);
		edgesMap.put(9, nine);
		List<Integer> ten = new ArrayList<Integer>();
		ten.add(4);
		ten.add(7);
		edgesMap.put(10, ten);
		List<Integer> eleven = new ArrayList<Integer>();
		eleven.add(5);
		eleven.add(7);
		edgesMap.put(11, eleven);
		List<Integer> twelve = new ArrayList<Integer>();
		twelve.add(6);
		twelve.add(7);
		edgesMap.put(12, twelve);
	}
	
	/**
	 * Fills in the hashmap where the keys are the edges and the values are the relevant
	 * vertices for the 4x4 grid.
	 */
	private void buildHashMapDim4() {
		List<Integer> zero = new ArrayList<Integer>();
		zero.add(0);
		zero.add(1);
		edgesMap.put(0, zero);
		List<Integer> one = new ArrayList<Integer>();
		one.add(0);
		one.add(2);
		edgesMap.put(1, one);
		List<Integer> two = new ArrayList<Integer>();
		two.add(0);
		two.add(3);
		edgesMap.put(2, two);
		List<Integer> three = new ArrayList<Integer>();
		three.add(0);
		three.add(4);
		edgesMap.put(3, three);
		List<Integer> four = new ArrayList<Integer>();
		four.add(1);
		four.add(5);
		edgesMap.put(4, four);
		List<Integer> five = new ArrayList<Integer>();
		five.add(1);
		five.add(2);
		edgesMap.put(5, five);
		List<Integer> six = new ArrayList<Integer>();
		six.add(2);
		six.add(6);
		edgesMap.put(6, six);
		List<Integer> seven = new ArrayList<Integer>();
		seven.add(2);
		seven.add(3);
		edgesMap.put(7, seven);
		List<Integer> eight = new ArrayList<Integer>();
		eight.add(3);
		eight.add(7);
		edgesMap.put(8, eight);
		List<Integer> nine = new ArrayList<Integer>();
		nine.add(3);
		nine.add(4);
		edgesMap.put(9, nine);
		List<Integer> ten = new ArrayList<Integer>();
		ten.add(4);
		ten.add(8);
		edgesMap.put(10, ten);
		List<Integer> eleven = new ArrayList<Integer>();
		eleven.add(5);
		eleven.add(9);
		edgesMap.put(11, eleven);
		List<Integer> twelve = new ArrayList<Integer>();
		twelve.add(5);
		twelve.add(6);
		edgesMap.put(12, twelve);
		List<Integer> thirteen = new ArrayList<Integer>();
		thirteen.add(6);
		thirteen.add(10);
		edgesMap.put(13, thirteen);
		List<Integer> fourteen = new ArrayList<Integer>();
		fourteen.add(6);
		fourteen.add(7);
		edgesMap.put(14, fourteen);
		List<Integer> fifteen = new ArrayList<Integer>();
		fifteen.add(7);
		fifteen.add(11);
		edgesMap.put(15, fifteen);
		List<Integer> sixteen = new ArrayList<Integer>();
		zero.add(7);
		zero.add(8);
		edgesMap.put(16, sixteen);
		List<Integer> seventeen = new ArrayList<Integer>();
		one.add(8);
		one.add(12);
		edgesMap.put(17, seventeen);
		List<Integer> eighteen = new ArrayList<Integer>();
		two.add(9);
		two.add(13);
		edgesMap.put(18, eighteen);
		List<Integer> nineteen = new ArrayList<Integer>();
		three.add(9);
		three.add(10);
		edgesMap.put(19, nineteen);
		List<Integer> twenty = new ArrayList<Integer>();
		four.add(10);
		four.add(13);
		edgesMap.put(20, twenty);
		List<Integer> twentyone = new ArrayList<Integer>();
		five.add(10);
		five.add(11);
		edgesMap.put(21, twentyone);
		List<Integer> twentytwo = new ArrayList<Integer>();
		six.add(11);
		six.add(13);
		edgesMap.put(22, twentytwo);
		List<Integer> twentythree = new ArrayList<Integer>();
		seven.add(11);
		seven.add(12);
		edgesMap.put(23, twentythree);
		List<Integer> twentyfour = new ArrayList<Integer>();
		eight.add(12);
		eight.add(13);
		edgesMap.put(24, twentyfour);
	}
	
	/**
	 * Creates matrix and calculates values of conductivity and probability for each possible subgraph.
	 * @param n - number of edges in the main graph.
	 * @param list - list of all the subgraphs.
	 * @param minEdges - minimal number of edges that need to be found to have conductivity.
	 * @param p - probability value.
	 * @param g - conductivity value.
	 */
	private void createMatrix(int n, ArrayList<String> list, int minEdges, double p, double g) {
		int arrayD = 0;
		if(n == 5)
			arrayD = 4;
		else if(n == 13)
			arrayD = 8;
		else if(n == 14)
			arrayD = 14;
		double[][] matrix = new double[arrayD][arrayD];
		String[] currentEdges;
		int currentEdgesNum;
		List<Integer> tmp = new ArrayList<Integer>();
		int i = 0;
		while(i < list.size()) {
			currentEdges = list.get(i).split(" ");
			currentEdgesNum = currentEdges.length;
			if(currentEdgesNum < minEdges) {
				probability.add(Math.pow(p, currentEdgesNum) * Math.pow((1 - p), (n - currentEdgesNum)));
				conductivity.add(0.0);
				i++;
				continue;
			}
			else {
				if(n - currentEdgesNum == 0)
					probability.add(Math.pow(p, currentEdgesNum));
				else
					probability.add(Math.pow(p, currentEdgesNum) * Math.pow((1 - p), (n - currentEdgesNum)));
				for(int z = 0; z < currentEdgesNum; z++) {
					tmp = edgesMap.get(Integer.parseInt(currentEdges[z]));
					matrix[tmp.get(0)][tmp.get(1)] = -1 * g;
					matrix[tmp.get(1)][tmp.get(0)] = -1 * g;
					matrix[tmp.get(0)][tmp.get(0)] += (1 * g);
					matrix[tmp.get(1)][tmp.get(1)] += (1 * g);
				}
				calculate(matrix, n);
				clear(matrix);
				i++;
			}
		}
	}

	/**
	 * Calculates the solution to the linear system that suits the matrix.
	 * @param matrix - the matrix in the linear system we need to solve.
	 * @param n - numbe of edges.
	 */
	private void calculate(double[][] matrix, int n) {
		RealMatrix a = new Array2DRowRealMatrix(matrix);
		RealVector b = new ArrayRealVector(new double[] {});
		if(n == 5)
			b = new ArrayRealVector(new double[] {-1, 0, 0, 1});
		else if(n == 13)
			b = new ArrayRealVector(new double[] {-1, 0, 0, 0, 0, 0, 0, 1});
		else if(n == 25)
			b = new ArrayRealVector(new double[] {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1});
		DecompositionSolver solver = new SingularValueDecomposition(a).getSolver();
		RealVector solution = solver.solve(b);
		double cond = 0.0;
		if(n == 5)
			cond = -1 / (solution.getEntry(0) - solution.getEntry(3));
		else if(n == 13)
			cond = -1 / (solution.getEntry(0) - solution.getEntry(7));
		else if(n == 25)
			cond = -1 / (solution.getEntry(0) - solution.getEntry(13));
		double val = Math.round(cond * 100.0) / 100.0;
		if(val > 1)
			val = 0;
		conductivity.add(val);
	}
	
	/**
	 * Calculates the final values of conductivity and the probability to get each one.
	 */
	private void calculateVals() {
		double cond, prob;
		for(int i = 0; i < conductivity.size(); i++) {
			cond = conductivity.get(i);
			prob = probability.get(i);
			if(finalVals.containsKey(cond))
				finalVals.put(cond, finalVals.get(cond) + prob);
			else
				finalVals.put(cond, prob);
		}
		probability.clear();
		conductivity.clear();
	}
	
	/**
	 * Finds all the subgraphs for a given graph
	 * @param edgesNum - number of edges in the graph
	 * @param edges - edges in the graph
	 */
	void subGraphs(int edgesNum, HashMap<Integer, Integer> edges){
		String ans = "";
		for (int i = 0; i < (1<<edgesNum); i++){
			for (int j = 0; j < edgesNum; j++)
				if ((i & (1 << j)) > 0)
					ans += edges.get(j) + " ";
			subGraphs.add(ans);
			ans = "";
		}
	}

	/**
	 * Renormalization step.
	 * @param p - probability value.
	 */
	public void renormalization(double p) {
		for(int i = 0; i <= renormalizStep; i++) {
			//System.out.println(finalVals.toString());
			R = Math.pow(p, 5) + (5 * Math.pow(p, 4) * (1 - p)) + (8 * Math.pow(p, 3) * Math.pow((1-p), 2)) + (2 * Math.pow(p, 2) * Math.pow((1-p), 3));
			double sum = 0;
			for(double key : finalVals.keySet()) {// only the ones that get us to the other side
				if(key == 0)
					continue;
				sum += (Math.log(key) * finalVals.get(key));	
			}
			
			G = Math.exp((1/R) * sum);
			createMatrix(5, subGraphs, 2, R, G);
			finalVals.clear();
			calculateVals();
		}
	}
	
	/**
	 * USED FOR TESTING.
	 * Prints a given matrix.
	 * @param matrix - the matrix to print
	 */
	private void print(double[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " , ");
			}
			System.out.println("\n");
		}
		System.out.println("------------------------------------------");
	}
	
	/**
	 * Clears matrix values(sets values to 0)
	 * @param arr - the matrix to clear
	 */
	private static void clear(double arr[][]) {
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr.length; j++)
				arr[i][j] = 0;
	}
}
