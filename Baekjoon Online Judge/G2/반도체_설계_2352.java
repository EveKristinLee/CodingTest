package solving.solve_1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ G2
public class 반도체_설계_2352 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		int[] C = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int size = 0;
		for(int k=0; k<N; k++) {
			int pos = Arrays.binarySearch(C, 0, size, num[k]);
			if(pos >= 0) continue;
			
			int iPos = Math.abs(pos) -1;
			C[iPos] = num[k];
			if(iPos == size) {
				size++;
			}
		}
		
		System.out.println(size);
	}

}
