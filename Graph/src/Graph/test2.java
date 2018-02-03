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

public class test2 {

		
	    private static final String INFINITY = "Invalid";
	    private static Set<LinkedList<String>> tracks = new HashSet<LinkedList<String>>();	/*
	    			this is set of linkedlist, in a word it's result
	    			what about u?
	    			result of what 
	    			s->a->b->t this is linkedlist, we have  anumber of this, so I use set
	    			okay?
	    			yes ok
	    		    which part?	*/
	    
		public static void main(String[] args) {

			
			
			/////////////////here///////////////////////////////////////////

			
		    
		    
		
		    int f=0;
		    for (Vector<String> arch : vecNodes) {
		    	for(int i = 0; i < arch.size(); i++) {
		    		Vector<String> j=vecNodes[mapNode.get(arch.elementAt(i))]; 
		    		for(int b=0;b<j.size();b++) {
		    			vecNodes[f].addAll(j);
		    		}
		    	}
		    	f++;
		    }
		    BFS();// this method what do the path right ? 
		    
		    Iterator<LinkedList<String>> it = tracks.iterator();
		    Writer writer = null;

			try {
			    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("OUTPUT.txt")));
			    while(it.hasNext()){
			    	LinkedList<String> track = it.next();
			    	for(int i = 0 ; i < track.size() ; i++) {
			    		System.out.print(track.get(i) + " ");
			    		writer.write(track.get(i) + " ");
			    	}
			    	System.out.println("with Probabilty is : " +(double)n * track.size() + "%" );
			    	writer.write("with Probabilty is : " +(double)n * track.size()  + "% \n");
			    }
		    
			} catch (IOException ex) {
				  // report
			} finally {
			   try {writer.close();} catch (Exception ex) {/*ignore*/}
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
		
		// yes its breadth first search as u know
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
	}
