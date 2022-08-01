package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 부분수열의_합_14225 {
	
	static int n; 
	static Set<Integer> chk = new HashSet<>();
	static int[] nums;
	
	static void makeSum(int idx, int sum) {
		if(idx == n) {
			chk.add(sum);
		}
		else {
			makeSum(idx+1, sum+nums[idx]);
			makeSum(idx+1, sum);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		makeSum(0, 0);
		
		int res = 1;
		while(true) {
			if(!chk.contains(res)) {
				break;
			}
			res++;
		}
		System.out.println(res);
	}

}
