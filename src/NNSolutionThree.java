import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hege on 2016.10.16..
 */
public class NNSolutionThree {
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
        //Saves the number of neurons per layer
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
        //Creates the list of Neurons
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
        //Adds the weights and bias to the neurons
        for(int i = 0; i < neuronList.size() - inputNeuronCnt; i++) {
            ArrayList<Double> temp = new ArrayList<>();
            String[] strl = inputstr.get(i+1);
            for(int j = 0; j < strl.length; j++) {
                temp.add(Double.parseDouble(strl[j]));
            }
            neuronList.get(i+inputNeuronCnt).setInput(temp);
        }
        //Sets up the neurons attribute about the previous layer, and for the next one too
        int cnt = 0;
        for(int i = 0; i < inLayerNeuronCount.size(); i++) {
            for(int j = 0; j < inLayerNeuronCount.get(i); j++) {
                if(i != inLayerNeuronCount.size()-1) {
                    ArrayList<Neuron> nextLayer = new ArrayList<>();
                    int nextLayerCnt = inLayerNeuronCount.get(i+1);
                    for(int m = 0; m < nextLayerCnt; m++) {
                        nextLayer.add(neuronList.get(cnt + inLayerNeuronCount.get(i) - j + m));
                    }
                    neuronList.get(cnt).setNextList(nextLayer);
                }
                if(i == 0) {
                    neuronList.get(cnt).setLayerid(i);
                    cnt++;
                }
                if(i >= 1) {
                    ArrayList<Neuron> prevLayer = new ArrayList<>();
                    int prevLayerCnt = inLayerNeuronCount.get(i-1);
                    for(int k = 0; k < prevLayerCnt; k++) {
                        prevLayer.add(neuronList.get(cnt - prevLayerCnt + k - j));
                    }
                    neuronList.get(cnt).setPrevList(prevLayer);
                    neuronList.get(cnt).setLayerid(i);
                    neuronList.get(cnt).setInLayerNumber(j);
                    cnt++;
                }
            }
        }
        //Stores the inputs
        List<List<Double>> inputlist = new ArrayList<>();
        for(int i = otherNeuronCnt + 2; i < inputstr.size() - 1; i++) {
            List<Double> input = new ArrayList<>();
            for(int j = 0; j < inputstr.get(i).length; j++) {
                input.add(Double.parseDouble(inputstr.get(i)[j]));
            }
            inputlist.add(input);
        }

        for(int i = 0; i < inputlist.size(); i++) {
            //System.out.println();
            for(int a = 0; a < inputNeuronCnt; a++) {
                neuronList.get(a).init_input(inputlist.get(i).get(a));
            }

            for(int c = 0; c < inLayerNeuronCount.get(inLayerNeuronCount.size()-1); c++) {
                neuronList.get(neuronList.size()- 1 - c).getOutput();
            }
        }

        //Derives the stuff I need, and inside generates other stuff I need fot the stuff mentioned before
        for (int i = inputNeuronCnt; i < neuronList.size(); i++) {
            neuronList.get(i).deriveNeuronAttrib();
        }
        //Writing out the architecture
        for(int i = 0; i < inputstr.get(0).length; i++) {
            if (i != inputstr.get(0).length - 1)
                System.out.print(inputstr.get(0)[i] + ",");
        }
        System.out.print(inputstr.get(0)[inputstr.get(0).length - 1]);
        System.out.println();
        //Writing out stuff that this subject needs
        for(int i = inputNeuronCnt; i < neuronList.size(); i++) {
            for(int j = 0; j < neuronList.get(i).input.size(); j++) {
                if(j != neuronList.get(i).input.size()-1)
                    System.out.print(neuronList.get(i).input.get(j) + ",");
            }
            System.out.print(neuronList.get(i).input.get(neuronList.get(i).input.size() - 1));
            System.out.println();
        }




    }
}
