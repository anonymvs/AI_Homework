
public class InputNeuron extends Neuron{
	private double start_input = -1;
	
	
	public InputNeuron() {
		this.layerid = 0;
		//System.out.println("InputNeuron");
	}

	@Override
	public double generateOutput() {
		if(start_input != -1) {
			//System.out.println("One input neuron's output: " + output);
			return output;
		}
		return -1	;
	}

	@Override
	public double generateDelta() {
		return 0;
	}

	@Override
	public void deriveNeuronAttrib() {
		return;
	}

	@Override
	public void updateNeuronAttrib(double mu, double expectedResult) {
		return;
	}

	@Override
	public void init_input(double input) {
		start_input = input;
		this.output = start_input;
		//System.out.println("Initialization of a Input neuron");
	}

	public double getOutput() {
		return output;
	}


}
