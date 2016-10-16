
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
			//System.out.println("HIDDEN NEURON result in layer number: " + layerid + "result: " + output + "||| Prev layercnt: " + this.prevLayer.size());
			return output;
		} else {
			output = 0;
			//System.out.println("HIDDEN NEURON result in layer number: " + layerid + "--> aktivált fos" + "||| Prev layercnt: " + this.prevLayer.size());
			return output;
		}
	}

	@Override
	public double generateDelta() {
		double sum = 0;
		for (int i = 0; i < nextLayer.size()-1; i++) {
			sum += nextLayer.get(i).getDelta() * nextLayer.get(i).input.get(inLayerNumber);
		}
		double sumsi = 0;
		for (int i = 0; i < prevLayer.size(); i++) {
			sumsi = input.get(i) * prevLayer.get(i).getOutput();
		}
		double si = input.get(input.size()-1) + sumsi;
		double fdsi = 0;
		if(si > 0) fdsi = 1;
		delta = sum * fdsi;
		resetall();
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
