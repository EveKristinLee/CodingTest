package algo_0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 차이를_최대로_10819 {
	
	static int n;
	static int[] num;
	static int total = 0;
	static boolean[] visit;
	static int[] make;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];
		make = new int[n];		
		visit = new boolean[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		perm(0);
		System.out.println(total);
	}
	
	public static void perm(int cnt) {
		if(cnt == n) {
			int tmp = calc(make);
			if(tmp > total) {
				total = tmp;
			}
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visit[i]) {
				visit[i] = true;
				make[cnt] = num[i];
				perm(cnt+1);
				visit[i] = false;
			}
		}
	}
	
	public static int calc(int[] num) {
		int sum = 0;
		for(int i=0; i<n-1; i++) {
			sum += Math.abs(num[i] - num[i+1]);
		}
		return sum;
	}
}
