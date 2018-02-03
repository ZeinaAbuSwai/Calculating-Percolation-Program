package Graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
	// Variables
	private Map<String, Node> map = new HashMap<>();
	private ArrayList<String> leaves = new ArrayList<>();
	
	//Getters
	public Map<String, Node> getMap() {
		return map;
	}

	public ArrayList<String> getLeaves() {
		return leaves;
	}

	// functions //
    public void addEdge(String node1, String node2, String price) {
        Node adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new Node(new LinkedHashSet<>(), node1);
            map.put(node1, adjacent);
        }
        adjacent.getNeig().add(node2);
        adjacent.setNeigPrice(Double.parseDouble(price), node2);
        
        // saves ref to the leaves.
        if(leaves.contains(node1)){
    		leaves.remove(node1);
    	}
    	if(map.get(node2) == null && !leaves.contains(node2))
    		leaves.add(node2);
    }

    public LinkedList<String> adjacentNodes(String last) {
    	if(map.get(last) != null){
            LinkedHashSet<String> adjacent = map.get(last).getNeig();
            return new LinkedList<String>(adjacent);
    	}
            return new LinkedList<>();
       
    }
}