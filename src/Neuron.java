import java.util.ArrayList;

public abstract class Neuron {
	public ArrayList<Neuron> neurons = new ArrayList<>();
	public ArrayList<Neuron> prevLayer = new ArrayList<>();
	public ArrayList<Neuron> nextLayer = new ArrayList<>();
	public double output = -1;
	protected int layerid;
	protected int inLayerNumber;
	protected ArrayList<Double> input;
	public double delta = -1;
	
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

	public void setInLayerNumber(int num) {
		inLayerNumber = num;
	}

	public void setPrevList(ArrayList<Neuron> arg) {
		prevLayer = arg;
	}

	public void setNextList(ArrayList<Neuron> arg) { nextLayer = arg; }

	public double getOutput() {
		if(output == -1) return this.generateOutput();
		return this.output;
	}

	public double getDelta() {
		if(delta == -1) return this.generateDelta();
		return delta;
	}



	public void reset() {
		this.output = -1;
		this.delta = -1;
	}
	
	public void resetall() {
		for (Neuron n :
				neurons) {
			n.reset();
		}
	}

	public abstract double generateOutput();
	public abstract double generateDelta();

	public abstract void deriveNeuronAttrib();
	public abstract void updateNeuronAttrib(double mu, double expectedResult);

	public abstract void init_input(double input);
}
