package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 준환이의_양팔저울_3234 {
	
	static int n;
	static int[] weight;
	static boolean[] visit;
	static int total;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			weight = new int[n];
			visit = new boolean[n];
			total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(weight);
			do {
				chk(0, 0, 0);

			}while(np(weight));
			
			sb.append("#"+t+" "+total).append("\n");
		}
		System.out.println(sb);
	}

	public static void chk(int left, int right, int cnt) {
		if(right > left) {
			return;
		}
		if(cnt == n) {
			total++;
			return;
		}
		if(right <= left) {
			chk(left + weight[cnt], right, cnt+1);
			chk(left, right + weight[cnt], cnt+1);
		}
	}
	
	public static boolean np(int[] numbers) {
		
		int N = numbers.length;
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) --i;

		if(i==0) return false; 
	
		int j= N-1;
		while(numbers[i-1]>=numbers[j]) --j;
	
		swap(numbers, i-1, j);

		int k = N-1;
		while(i<k) swap(numbers, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] numbers,int i,int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}
