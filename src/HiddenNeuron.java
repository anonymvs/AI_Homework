
public class HiddenNeuron extends Neuron{
	
	public HiddenNeuron() {
		//System.out.println("HiddenNeuron");
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
			//System.out.println("HIDDEN NEURON result in layer number: " + inLayerNumber + "result: " + output + "||| Prev layercnt: " + this.prevLayer.size());
			return output;
		} else {
			output = 0;
			//System.out.println("HIDDEN NEURON result in layer number: " + inLayerNumber + "--> aktivált fos" + "||| Prev layercnt: " + this.prevLayer.size());
			return output;
		}
	}

	@Override
	public double generateDelta() {
		double sum = 0;
		for (int i = 0; i < nextLayer.size(); i++) {
			sum += nextLayer.get(i).getDelta() * nextLayer.get(i).input.get(inLayerNumber);
			//System.out.println(inLayerNumber);
		}
		double sumsi = 0;
		for (int i = 0; i < prevLayer.size(); i++) {
			sumsi += input.get(i) * prevLayer.get(i).output;
		}
		double si = input.get(input.size()-1) + sumsi;
		double fdsi = 0;
		if(si > 0) fdsi = 1;
		delta = sum * fdsi;
		//System.out.println("HIDDENNEURON: delta: " + delta);
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
		//System.out.println("HIDDENNEURON: weights + bias" + input);
	}

	@Override
	public void updateNeuronAttrib(double mu, double expectedResult) {
		if(delta == -1) {generateDelta();}
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
