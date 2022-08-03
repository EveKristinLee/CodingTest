package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 햄버거_다이어트_5215 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int kcal = Integer.parseInt(st.nextToken());
			int maxPref = 0;
			int[][] food = new int[n][2];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				food[i][0] = Integer.parseInt(st.nextToken());
				food[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for(int s = 0, end = 1<<n; s<end; s++) {
				int totalPref = 0;
				int totalKcal = 0;
				for(int i=0; i<n; i++) {
					if((s & 1<<i) != 0) {
						totalPref += food[i][0];
						totalKcal += food[i][1];
					}
				}
				if(totalKcal <= kcal) {
					maxPref = Math.max(maxPref, totalPref);
				}
			}
			sb.append("#"+t+" "+maxPref).append("\n");
		}
		System.out.println(sb);
	}
}
