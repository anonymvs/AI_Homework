import java.util.ArrayList;

public abstract class Neuron {
	public ArrayList<Neuron> prevLayer = new ArrayList<>();
	public double output = -1;
	protected int layerid;
	protected ArrayList<Double> input;
	
	public void setInput(ArrayList<Double> arg) {
		input = arg;
		/*
		for(int i = 0; i < input.size(); i++) {
			System.out.print(input.get(i));
		}
		System.out.println();
		*/

	}

	public void setLayerid(int id) {
		layerid = id;
		//System.out.println(layerid);
	}

	public void setPrevList(ArrayList<Neuron> arg) {
		prevLayer = arg;
	}

	public double getOutput() {
		if(output == -1) return this.generateOutput();
		return this.output;
	}

	public void reset() {
		this.output = -1;
	}

	public abstract double generateOutput();

	public abstract void init_input(double input);
}
