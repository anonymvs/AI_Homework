
public class InputNeuron extends Neuron{
	private double start_input = -1;
	
	
	public InputNeuron() {
		this.layerid = 0;
		System.out.println("InputNeuron");
	}
	
	public void setInput(double arg) {
		this.start_input = arg;
	}

	@Override
	public double generateOutput() {
		if(start_input != -1)
			return output;
		else
			return -1;
	}

	@Override
	public void init_input(double input) {
		start_input = input;
		this.output = start_input;
	}

	public double getOutput() {
		return generateOutput();
	}


}
