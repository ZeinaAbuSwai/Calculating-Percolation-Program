package Graph;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class calculations {
	private static HashMap<Integer, String> edgesMap = new HashMap<Integer, String>(); //contains the edges
	private static HashMap<Integer, String> subMap = new HashMap<Integer, String>(); //contains the subGraphs
	private static String startNode; //source node
	private static String endNode; //target node
	private static String t = null; //used in subGraphs function
	private static String[] nodes; 
	private static int n; // number of nodes
	private static HashMap< String,Integer> mapNode = new HashMap<String,Integer>(); //map with the nodes
	private static Vector<String>[] vecNodes; //array of vectors
	private static boolean[] marked;  //array of boolean which shows the visited state
    private static String[] nodeTo;  //array of String which shows the above
    private static Set<LinkedList<String>> tracks = new HashSet<LinkedList<String>>();
    private static final String INFINITY = "Invalid";
	
	public static void main(String[] args) {startNode="s";endNode="t";
		singleSub("s a a b b t");/*
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter the source node: ");
	    startNode = scanner.nextLine();
	    System.out.print("Enter the target node: ");
	    endNode = scanner.nextLine();
	    System.out.println("Enter the edges (each one in a seperate line, with one space between each 2 letters): ");
	    String input ;
	    int j = 0;
	    while(true) {
	    	input = scanner.nextLine();
	    	if(input.length() < 3) 
	    		break;
	    	edgesMap.put(j, input);
	    	j++;
	    }*/
//	    subGraphs(j, edgesMap);
//	    singleSub(subMap.get(32));
	}
	
	/**
	 * finds all the sub-graphs of a given graph
	 * @param n - the number of edges in the main graph
	 * @param map - the edges and their indexes map
	 */
	private static void subGraphs(int n, HashMap<Integer, String> map){
		int y = 0;
		subMap.compute(y, (k,v)->v==null?" ":v+" ");
		y++;
		for (int i = 0; i < (1<<n); i++){
			for (int j = 0; j < n; j++) {
				t = map.get(j);
				if ((i & (1 << j)) > 0) {
					//puts the subgraph into subMap
					subMap.compute(y, (k,v)->v==null?t+" ":v+t+" ");
				}
			}
			y++;
		}
	}
	
	/**
	 * Runs BFS on a single subgraph
	 * @param subGraph - the subGraph
	 */
	private static void singleSub(String subGraph) {
		String[] temp = subGraph.split(" ");
		Set<String> set = new HashSet<String>();
		for(String cell : temp) 
		    set.add(cell);
		n = set.size();
		nodes = new String[n];
		int i = 0;
		for(String cell : set) { 
		    nodes[i] = cell;
		    i++;
		}
		
		for(int j = 0  ; j < n ; j ++) 
	    	mapNode.put(nodes[j], j);
		vecNodes = new Vector[n];
	    for(int j = 0 ; j < n ; j ++) 
	    	vecNodes[j] = new Vector<String>();

	    marked = new boolean[n];
	    nodeTo = new String[n];
	    
	    for(int j = 0; j < temp.length; j+=2) 
	    	vecNodes[mapNode.get(temp[j])].add(temp[j + 1]); 
	    int f = 0;
	    for (Vector<String> arch : vecNodes) {
	    	for(int k = 0; k < arch.size(); k++) {
	    		Vector<String> j = vecNodes[mapNode.get(arch.elementAt(k))]; 
	    		for(int b = 0; b < j.size(); b++) {
	    			vecNodes[f].addAll(j);
	    		}
	    	}
	    	f++;
	    }
	    BFS();
	    
	    Iterator<LinkedList<String>> it = tracks.iterator();
	    while(it.hasNext()) {
	    	LinkedList<String> track = it.next();
		    for(int j = 0 ; j < track.size() ; j++) 
		    		System.out.print(track.get(j) + " ");
		    System.out.println();
		}
	    
	}
	
	public static void BFS() {
		Queue queue = new LinkedList();
        for (int v = 0; v < n; v++)
            nodeTo[v] = INFINITY;
        nodeTo[mapNode.get(startNode)] = "Root";
        marked[mapNode.get(startNode)] = true;
        marked[mapNode.get(endNode)] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            String v = (String) queue.remove();
            if(vecNodes[mapNode.get(v)].contains(endNode)) {
            	addTrack(v);
            }
            for (String arch : vecNodes[mapNode.get(v)]) {
                if (!marked[mapNode.get(arch)]) {
                	nodeTo[mapNode.get(arch)] = v;
                	for (String arch2 : vecNodes[mapNode.get(arch)])
                		if(vecNodes[mapNode.get(arch2)].contains(endNode)) {
                			nodeTo[mapNode.get(arch2)] = arch;
                			marked[mapNode.get(arch2)] = true;
                		}
                    marked[mapNode.get(arch)] = true;
                    queue.add(arch);
                }
            }
        }
	}
	
	public static void addTrack(String v) {
		LinkedList<String> track= new LinkedList<String>();
		track.add(endNode);
		while(nodeTo[mapNode.get(v)] != "Root") {
			track.addFirst(v);
			v =nodeTo[mapNode.get(v)];
		}
		track.addFirst(startNode);
		tracks.add(track);
	}
	
}