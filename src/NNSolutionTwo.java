import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NNSolutionTwo {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String[]> inputstr = new ArrayList<String[]>();
		String line;
			int n = 0;
			
			line = br.readLine();
			inputstr.add(n, line.split(","));
			int read = 0;
			for(int i = 1; i < inputstr.size(); i++) {
				read += Integer.parseInt(inputstr.get(0)[i]);
			}
			n++;
			while(n <= read){
				line = br.readLine();
				inputstr.add(n, line.split(","));
				n++;
			}
			String linee = br.readLine();
			int inputcount = Integer.parseInt(linee) + n;
			while(n < inputcount) {
				line = br.readLine();
				inputstr.add(n, line.split(","));
				n++;
			}
			
			
			/*
			while((line = br.readLine()) != null) {
				inputstr.add(n, line.split(","));
				n++;
			}
			*/
			
		//amount of layers
		//int layercnt = inputstr.get(0).length;
		//amount of neurons in layers
			
		ArrayList<Integer> inLayerNeuronCount = new ArrayList<Integer>();
		for(String str : inputstr.get(0)) {
			inLayerNeuronCount.add(Integer.parseInt(str));
		}
		//amount of inputNeurons
		int inputNeuronCnt = inLayerNeuronCount.get(0);
		//amount of any other Neurons
		int otherNeuronCnt = 0;
		for (int i = 1; i < inLayerNeuronCount.size(); i++) {
			otherNeuronCnt += inLayerNeuronCount.get(i);
		}
		int neuronCount = inputNeuronCnt + otherNeuronCnt;
		
		ArrayList<Neuron> neuronList = new ArrayList<Neuron>();
		for(int i = 0; i < inputNeuronCnt; i++) {
			neuronList.add(new InputNeuron());
		}
		
		for(int i = 1; i < inLayerNeuronCount.size(); i++) {
			for(int j = 0; j < inLayerNeuronCount.get(i); j++) {
				if(i == inLayerNeuronCount.size()-1)
					neuronList.add(new OutputNeuron());
				else
					neuronList.add(new HiddenNeuron());
			}
		}
		
		for(int i = 0; i < neuronList.size() - inputNeuronCnt; i++) {
			ArrayList<Double> temp = new ArrayList<Double>();
			System.out.println(inputstr.size());
			String[] strl = inputstr.get(i+1);
			for(int j = 0; j < strl.length; j++) {
				temp.add(Double.parseDouble(strl[j]));
			}
			neuronList.get(i+2).setInput(temp);
		}
		/*
		for(int i = 0; i < inLayerNeuronCount.get(inLayerNeuronCount.size()); i++) {
			neuronList.add(new OutputNeuron());
		}
		*/
		
	}
}
