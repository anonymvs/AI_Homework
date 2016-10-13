import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class NNSolutionOne {
	public static void main(String[] args) throws IOException {
		ArrayList<Double> input = new ArrayList<Double>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputstr = br.readLine();
		System.out.println(inputstr);
		String[] inputstrarray = inputstr.split(",");
		for (String str : inputstrarray) {
			input.add(Double.parseDouble(str));
		}
		init_weights(input);
		
	}
	
	public static double init_weights(ArrayList<Double> input) {
		Random rand = new Random();
		for(int i = 0; i < input.size(); i++) {
			if(i < input.size()-1) {
				for(double j = 0; j < input.get(i+1); j++) {
					for(double k = 0; k < input.get(i); k++) {
						double output = rand.nextGaussian() * 0.1;
						System.out.print(Double.toString(output) + ",");
					}
					System.out.print("0.0");
					System.out.println();
				}
			}
		}
		return 0;
	}
}
