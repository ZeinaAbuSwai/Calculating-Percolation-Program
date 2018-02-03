package Graph;
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

public class SubGraphs {
		private static HashMap<Integer, String> mapNode = new HashMap<Integer, String>(); //contains the edges
		private static String startNode; //source node
		private static String endNode; //target node
	    
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
		    System.out.print("Enter the source node: ");
		    startNode = scanner.nextLine();
		    System.out.print("Enter the target node: ");
		    endNode = scanner.nextLine();
		    System.out.println("Enter the edges (each one in a seperate line, with no spaces between the letters): ");
		    String input ;
		    int j = 0;
		    while(true) {
		    	input = scanner.nextLine();
		    	if(input.length() < 2) 
		    		break;
		    	mapNode.put(j, input);
		    	j++;
		    }
//		    for(int i=0;i<j;i++) 
//		    	System.out.println(mapNode.get(i));
		    
		    subGraphs(j, mapNode);
		}
		
		/**
		 * finds all the sub-graphs of a given graph
		 * @param n - the number of edges in the main graph
		 * @param map - the edges and their indexes map
		 */
		static void subGraphs(int n, HashMap<Integer, String> map){
			for (int i = 0; i < (1<<n); i++){
				System.out.print("{ ");
				for (int j = 0; j < n; j++)
					if ((i & (1 << j)) > 0)
						System.out.print(map.get(j) + " ");
				System.out.println("}");
			}
		}

}