package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 한빈이와_Spot_Mart_9229 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());			
			int[] weight = new int[n];
			int maxWeight = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			int sum = 0;
			for(int i=0; i<n-1; i++) {
				for(int j=i+1; j<n; j++) {
					sum = weight[i] + weight[j];
					if(sum <= m) {
						maxWeight = Math.max(maxWeight, sum);
					}
				}
			}
			if(maxWeight == Integer.MIN_VALUE) {
				sb.append("#"+t+" -1").append("\n");				
			}
			else {
				sb.append("#"+t+" "+maxWeight).append("\n");
			}
		}
		System.out.println(sb);
	}	
}
