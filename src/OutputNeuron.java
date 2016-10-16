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
		for(int i = 0; i < input.size()-2; i++) {
			double dw = delta * input.get(i);
			input.set(i, dw);
		}
		double db = delta;
		input.set(input.size()-1, db);
	}


	@Override
	public void init_input(double input) {
		return;
	}
}
