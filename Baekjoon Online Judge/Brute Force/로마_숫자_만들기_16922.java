package algo_0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 로마_숫자_만들기_16922 {

	static int[] num = {1, 5, 10, 50};
	static boolean[] sum;
	static int n;
	static int maxCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sum = new boolean[50*20+1];
		maxCnt = 0;
		
		perm(0, 0, 0);
		System.out.println(maxCnt);
	}

	private static void perm(int cnt, int total, int idx) {
		if(cnt == n) {
			if(!sum[total]) {
				sum[total] = true;
				maxCnt++;
			}
			return;
		}
		
		for(int i=idx; i<4; i++) {
			perm(cnt+1, total+num[i], i);
		}
	}
}
