import java.util.ArrayList;

public class Neuron {
	public ArrayList<Neuron> nrl = new ArrayList<Neuron>();
	public double output;
	protected int layerid;
	protected ArrayList<Double> input;
	
	public void setInput(ArrayList<Double> arg) {
		input = arg;
		for(int i = 0; i < input.size(); i++) {
			System.out.print(input.get(i));
		}
		System.out.println();
	}
}
