package algo_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_123_더하기_2_12101 {

	static int n;
	static int k;
	static ArrayList<String> arr;
	static int[] num = {1, 2, 3}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		
		perm(0, "");
		if(arr.size() >= k) {
			System.out.println(arr.get(k-1));			
		}
		else {
			System.out.println("-1");
		}
	}

	private static void perm(int sum, String s) {
		if(sum == n) {
			s = s.substring(0, s.length()-1);
			arr.add(s);
			return;
		}
		if(sum > n) {
			return;
		}
		for(int i=0; i<3; i++) {
			perm(sum+num[i], s+num[i]+"+");
		}
	}

}
