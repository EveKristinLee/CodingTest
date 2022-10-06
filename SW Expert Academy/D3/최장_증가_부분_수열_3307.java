package solving.solve_1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 최장_증가_부분_수열_3307 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
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
			sb.append("#"+t+" "+size+"\n");
		}
		System.out.println(sb);
	}
}
