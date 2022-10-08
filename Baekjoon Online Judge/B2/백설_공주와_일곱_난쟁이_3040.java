package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백설_공주와_일곱_난쟁이_3040 {

	private static int[] select;
	private static int[] height;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		height = new int[9];
		select = new int[7];
		for(int i=0; i<9; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}	
		combi(0, 0, 0);
	}

	private static void combi(int cnt, int start, int sum) {
		if(cnt == 7) {
			if(sum == 100) {
				for(int i=0; i<select.length; i++) {
					System.out.println(select[i]);
				}
			}
			return;
		}
		if(sum > 100) {
			return;
		}
		for(int i=start; i<9; i++) {
			select[cnt] = height[i];
			combi(cnt+1, i+1, sum + height[i]);
		}
	}
}
