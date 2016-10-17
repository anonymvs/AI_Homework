import java.util.ArrayList;

public class OutputNeuron extends Neuron {
	
	public OutputNeuron() {
		//System.out.println("OutputNeuron");
	}

	@Override
	public double generateOutput() {
		double sum = 0;
		for(int i = 0; i < this.prevLayer.size(); i++) {
			sum += this.prevLayer.get(i).getOutput() * this.input.get(i);

			//System.out.println(this.prevLayer.get(i).getOutput() + "*" + this.input.get(i));
		}
		output = sum + this.input.get(input.size()-1);

		//System.out.println("result: " + output);
		return output;

	}

	@Override
	public double generateDelta() {
		delta = 1;
		return delta;
	}

	@Override
	public void deriveNeuronAttrib() {
		if(delta == -1) {generateDelta();}
		for(int i = 0; i < input.size()-1; i++) {
			double dw = delta * prevLayer.get(i).output;
			input.set(i, dw);
		}
		double db = delta;
		input.set(input.size()-1, db);
		//System.out.println("OUTPUTNEURON: weights + bias: " + input);
	}

	@Override
	public void updateNeuronAttrib(double mu, double expectedResult) {
		double epsilon = (expectedResult - this.getOutput());
		delta = epsilon;
		for(int i = 0; i < input.size()-1; i++) {
			double dw = input.get(i) + 2 * mu * delta * prevLayer.get(i).output;
			input.set(i, dw);
		}
		double db = input.get(input.size()-1) + 2 * mu * delta;
		input.set(input.size()-1, db);
		//System.out.println("HIDDENNEURON: weights + bias" + input);
	}


	@Override
	public void init_input(double input) {
		return;
	}
}
