package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 일곱_난쟁이_2309 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] height = new int[9];
		int sum = 0;
		for(int i=0; i<9; i++) {
			height[i] = Integer.parseInt(br.readLine());
			sum += height[i];
		}	
		
		for(int i=0; i<8; i++) {
			for(int j=i+1; j<9; j++) {
				if((sum - height[i] - height[j]) == 100) {
					height[i] = 0;
					height[j] = 0;
					Arrays.sort(height);
					for(int k=2; k<9; k++) {
						System.out.println(height[k]);
					}
					return;
				}
			}
		}
	}
}
