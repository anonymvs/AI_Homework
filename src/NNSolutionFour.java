import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hege on 2016.10.17..
 */
public class NNSolutionFour {
    public static void main(String[] args) throws IOException {
        ArrayList<String[]> inputStringArray;
        inputStringArray = readingInputFromConsole();

        ArrayList<Double> teachingParameters = new ArrayList<>();
        teachingParameters = processTeachingParameters(inputStringArray);

        ArrayList<Integer> architecture;
        architecture = processArchitectureParameters(inputStringArray);

        int otherNeuronCnt = processNotInputNeuronCount(architecture);

        ArrayList<Neuron> neurons;
        neurons = generateNeuronListFromInput(inputStringArray, architecture);

        setWeightBiasInNeurons(inputStringArray, neurons, architecture);
        setLinkstoPrevperNextLayer(neurons, architecture);

        List<List<Double>> inputTemplates;
        inputTemplates = processInputTemplatesFromInput(inputStringArray, otherNeuronCnt);

        changeNeuronAttributesByDelta(inputTemplates, neurons, architecture, teachingParameters);

    }


    //reading the input parameters
    public static ArrayList<String[]> readingInputFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String[]> inputstr = new ArrayList<String[]>();
        String line;
        int n = 0;

        line = br.readLine();
        inputstr.add(n, line.split(","));

        boolean b = true;
        while(b) {

            if(line.isEmpty()) {
                b = false;
            } else {
                line = br.readLine();
                inputstr.add(line.split(","));
            }
        }
        return inputstr;
    }
    //Processes the teaching parameters from input
    public static ArrayList<Double> processTeachingParameters(ArrayList<String[]> arg) {
        ArrayList<Double> ret = new ArrayList<>();
        for(int i = 0; i < arg.get(0).length; i++) {
            ret.add(Double.parseDouble(arg.get(0)[i]));
        }
        return ret;
    }
    //Processes the architecture parameters from input
    public static ArrayList<Integer> processArchitectureParameters(ArrayList<String[]> arg) {
        ArrayList<Integer> ret = new ArrayList<>();
        for(String str : arg.get(1)) {
            ret.add(Integer.parseInt(str));
        }
        return ret;
    }
    //Processes the count of the Neurons that are not inputNeurons
    public static int processNotInputNeuronCount(ArrayList<Integer> arg) {
        int ret = 0;
        for (int i = 1; i < arg.size(); i++) {
            ret += arg.get(i);
        }
        return ret;
    }
    //Generates the Neurons for the list from the input
    public static ArrayList<Neuron> generateNeuronListFromInput(ArrayList<String[]> input, ArrayList<Integer> arch) {
        ArrayList<Neuron> ret = new ArrayList<>();
        for(int i = 0; i < arch.get(0); i++) {
            ret.add(new InputNeuron());
        }
        for(int i = 1; i < arch.size(); i++) {
            for(int j = 0; j < arch.get(i); j++) {
                if(i == arch.size()-1)
                    ret.add(new OutputNeuron());
                else
                    ret.add(new HiddenNeuron());
            }
        }
        return ret;
    }
    //Sets up the input argument of the Neurons
    public static void setWeightBiasInNeurons(ArrayList<String[]> input, ArrayList<Neuron> neurons, ArrayList<Integer> arch) {
        for(int i = 0; i < neurons.size() - arch.get(0); i++) {
            ArrayList<Double> temp = new ArrayList<>();
            String[] strl = input.get(i+2);
            for(int j = 0; j < strl.length; j++) {
                temp.add(Double.parseDouble(strl[j]));
            }
            neurons.get(i + arch.get(0)).setInput(temp);
        }
    }
    //Sets up the links between Neurons and the layer surrounding them
    public static void setLinkstoPrevperNextLayer(ArrayList<Neuron> neurons, ArrayList<Integer> arch) {
        int cnt = 0;
        for(int i = 0; i < arch.size(); i++) {
            for(int j = 0; j < arch.get(i); j++) {
                if(i != arch.size()-1) {
                    ArrayList<Neuron> nextLayer = new ArrayList<>();
                    int nextLayerCnt = arch.get(i+1);
                    for(int m = 0; m < nextLayerCnt; m++) {
                        nextLayer.add(neurons.get(cnt + arch.get(i) - j + m));
                    }
                    neurons.get(cnt).setNextList(nextLayer);
                }
                if(i == 0) {
                    neurons.get(cnt).setLayerid(i);
                    cnt++;
                }
                if(i >= 1) {
                    ArrayList<Neuron> prevLayer = new ArrayList<>();
                    int prevLayerCnt = arch.get(i-1);
                    for(int k = 0; k < prevLayerCnt; k++) {
                        prevLayer.add(neurons.get(cnt - prevLayerCnt + k - j));
                    }
                    neurons.get(cnt).setPrevList(prevLayer);
                    neurons.get(cnt).setLayerid(i);
                    neurons.get(cnt).setInLayerNumber(j);
                    cnt++;
                }
            }
        }
    }
    //Processes the list of input templates from the input
    public static List<List<Double>> processInputTemplatesFromInput(ArrayList<String[]> arg, int otherNeuronCount) {
        List<List<Double>> inputlist = new ArrayList<>();
        for(int i = otherNeuronCount + 3; i < arg.size() - 1; i++) {
            List<Double> input = new ArrayList<>();
            for(int j = 0; j < arg.get(i).length; j++) {
                input.add(Double.parseDouble(arg.get(i)[j]));
            }
            inputlist.add(input);
        }
        return inputlist;
    }
    //Changes the attributes of the Neurons
    public static void changeNeuronAttributesByDelta(List<List<Double>> inputTemplates, ArrayList<Neuron> neurons, ArrayList<Integer> arch, ArrayList<Double> teachingParameters) {
        teachingAlgorithm(inputTemplates, neurons, arch, teachingParameters);

    }

    public static void teachingAlgorithm(List<List<Double>> inputTemplates, ArrayList<Neuron> neurons, ArrayList<Integer> arch, ArrayList<Double> teachingParameters) {
        double R = teachingParameters.get(2);
        int St = (int) (R * inputTemplates.size());
        double epoch = teachingParameters.get(0);
        int E = (int) epoch;
        double mu = teachingParameters.get(1);

        for(int e = 0; e < E; e++) {
            for(int i = 0; i < St; i++) {
                for(int a = 0; a < arch.get(0); a++) {
                    neurons.get(a).init_input(inputTemplates.get(i).get(a));
                }
                System.out.println(i + ". Tanítási bemenet: " + inputTemplates.get(i));
                for(int c = 0; c < arch.get(arch.size()-1); c++) {
                    double op = neurons.get(neurons.size()- 1 - c).getOutput();
                    System.out.println("Tanítás előtti kimenet:" + op);
                }
                updateNeuronAttributes(neurons, arch, mu, inputTemplates.get(i).get(inputTemplates.get(i).size()-1));
                //System.out.println("Tanítás utáni kimenet: " + neurons.get(neurons.size()-1).getOutput());
                double valami = (inputTemplates.get(i).get(2) - neurons.get(neurons.size()-1).getOutput()) * mu * 2;
                System.out.println("Hiba*mu*2: " + valami);
                System.out.println("Módosult hálózat:");
                writeOutChangedArchitecture(neurons, arch);
                System.out.println();
                for(int z = 0; z < neurons.size(); z++) {
                    neurons.get(z).reset();
                }
                
            }
            validateNeuronNetworkByTemplates(inputTemplates, neurons, arch);
        }
        //writeOutChangedArchitecture(neurons, arch);
    }

    public static void updateNeuronAttributes(ArrayList<Neuron> neurons, ArrayList<Integer> arch, double mu, double expectedResult) {
        for (int i = arch.get(0); i < neurons.size(); i++) {
            neurons.get(i).updateNeuronAttrib(mu, expectedResult);
        }
    }
    
    public static void validateNeuronNetworkByTemplates(List<List<Double>> inputTemplates, ArrayList<Neuron> neurons, ArrayList<Integer> arch) {
        
    }

    public static void writeOutChangedArchitecture(ArrayList<Neuron> neurons, ArrayList<Integer> arch) {
        for(int i = arch.get(0); i < neurons.size(); i++) {
            for(int j = 0; j < neurons.get(i).input.size(); j++) {
                if(j != neurons.get(i).input.size()-1)
                    System.out.print(neurons.get(i).input.get(j) + ",");
            }
            System.out.print(neurons.get(i).input.get(neurons.get(i).input.size() - 1));
            System.out.println();
        }

    }

}
