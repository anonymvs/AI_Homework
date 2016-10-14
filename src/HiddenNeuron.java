
public class HiddenNeuron extends Neuron{
	
	public HiddenNeuron() {
		System.out.println("HiddenNeuron");
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
			System.out.println("HIDDEN NEURON result in layer number: " + layerid + "result: " + output + "||| Prev layercnt: " + this.prevLayer.size());
			return output;
		} else {
			output = 0;
			System.out.println("HIDDEN NEURON result in layer number: " + layerid + "--> aktivált fos" + "||| Prev layercnt: " + this.prevLayer.size());
			return output;
		}
	}

	@Override
	public void init_input(double input) {
		return;
	}
}
