
public class InputNeuron extends Neuron{
	private double start_input;
	
	
	public InputNeuron() {
		this.layerid = 0;
		System.out.println("InputNeuron");
	}
	
	public void setInput(double arg) {
		this.start_input = arg;
	}
}
