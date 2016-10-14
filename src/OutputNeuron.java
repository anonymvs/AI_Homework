
public class OutputNeuron extends Neuron {
	
	public OutputNeuron() {
		System.out.println("OutputNeuron");
	}

	@Override
	public double generateOutput() {
		double sum = 0;
		for(int i = 0; i < this.prevLayer.size(); i++) {
			sum += this.prevLayer.get(i).getOutput() * this.input.get(i);
		}
		double temp = sum + this.input.get(input.size()-1);
		if(temp > 0) {
			output = temp;
			System.out.println("result: " + output);
			return output;
		} else {
			output = 0;
			System.out.println("result: 0 mivel aktivációs fos");
			return output;
		}

	}

	@Override
	public void init_input(double input) {
		return;
	}
}
