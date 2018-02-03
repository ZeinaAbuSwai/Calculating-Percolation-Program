package Graph;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Node {

	//private variables.
	private String name;
	private LinkedHashSet<String> neig;
	private HashMap<String, Double> price;
	
	//Constructor
	public Node(LinkedHashSet<String> neig, String name) {
		this.name = name;
		this.neig = neig;
		this.price = new HashMap<>();
	}
	//////// getters ////////
	public String getName() {
		return name;
	}

	public HashMap<String, Double> getPrice() {
		return price;
	}

	public LinkedHashSet<String> getNeig() {
		return neig;
	}


	/////// functions ///////
	public void setNeigPrice(Double nodePrice, String nodeName){
		this.price.put(nodeName, nodePrice);
	}
	
//	@Override
//	public String toString() {
//		return this.name+" - "+Arrays.toString(neig.toArray()) +" ~> " + Arrays.toString(price.values().toArray()) +"\n";
//	}
}

