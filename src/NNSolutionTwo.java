import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

		boolean b = true;
		while(b) {

			if(line.isEmpty()) {
				b = false;
			} else {
				line = br.readLine();
				inputstr.add(line.split(","));
			}
		}

			
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
			ArrayList<Double> temp = new ArrayList<>();
			String[] strl = inputstr.get(i+1);
			for(int j = 0; j < strl.length; j++) {
				temp.add(Double.parseDouble(strl[j]));
			}
			neuronList.get(i+inputNeuronCnt).setInput(temp);
		}
		int cnt = 0;
		for(int i = 0; i < inLayerNeuronCount.size(); i++) {
			for(int j = 0; j < inLayerNeuronCount.get(i); j++) {
				if(i == 0) {
					neuronList.get(cnt).setLayerid(i);
					cnt++;
				}
				if(i >= 1) {
					ArrayList<Neuron> prevLayer = new ArrayList<>();
					int prevLayerCnt = inLayerNeuronCount.get(i-1);
					for(int k = 0; k < prevLayerCnt; k++) {
						prevLayer.add(neuronList.get(cnt- prevLayerCnt + k - j));
					}
					neuronList.get(cnt).setPrevList(prevLayer);
					neuronList.get(cnt).setLayerid(i);
					cnt++;
				}
			}
		}

		List<List<Double>> inputlist = new ArrayList<>();
		for(int i = otherNeuronCnt + 2; i < inputstr.size() - 1; i++) {
			List<Double> input = new ArrayList<>();
			for(int j = 0; j < inputstr.get(i).length; j++) {
				input.add(Double.parseDouble(inputstr.get(i)[j]));
			}
			inputlist.add(input);
		}
		/*
		for(int i = 0; i < inputlist.size(); i++) {
			System.out.println(inputlist.get(i));
		}
		*/
		ArrayList<Double> output = new ArrayList<>();
		for(int i = 0; i < inputlist.size(); i++) {
			//System.out.println();
			for(int a = 0; a < inputNeuronCnt; a++) {
				neuronList.get(a).init_input(inputlist.get(i).get(a));
			}
			//neuronList.get(1).init_input(inputlist.get(i).get(1));
			for(int c = 0; c < inLayerNeuronCount.get(inLayerNeuronCount.size()-1); c++) {
				output.add(neuronList.get(neuronList.size()- 1 - c).getOutput());
			}

			for(int j = 0; j < neuronList.size(); j++) {
				neuronList.get(j).reset();
			}
		}
		System.out.println(Integer.parseInt(inputstr.get(otherNeuronCnt + 1)[0]));
		for(int i = 0; i < output.size(); i+= inLayerNeuronCount.get(inLayerNeuronCount.size()-1)) {
			for(int j = 0; j < inLayerNeuronCount.get(inLayerNeuronCount.size()-1); j++) {
				int k = inLayerNeuronCount.get(inLayerNeuronCount.size()-1) - j - 1;
				if(j == inLayerNeuronCount.get(inLayerNeuronCount.size()-1)-1 )
					System.out.print(output.get(i+k));
				else
					System.out.print(output.get(i+k) + ",");
			}
			System.out.println();
		}

		/*
		for(int i = 0; i < inLayerNeuronCount.get(inLayerNeuronCount.size()); i++) {
			neuronList.add(new OutputNeuron());
		}
		*/
		
	}
}
