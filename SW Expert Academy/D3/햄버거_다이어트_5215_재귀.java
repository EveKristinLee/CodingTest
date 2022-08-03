package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 햄버거_다이어트_5215_재귀 {
	private static int N;
	private static boolean[] selected;
	private static int[][] food;
	private static int maxPref = 0;
	private static int kcal;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			N = n;
			selected = new boolean[n];
			kcal = Integer.parseInt(st.nextToken());
			maxPref = 0;
			food = new int[n][2];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				food[i][0] = Integer.parseInt(st.nextToken());
				food[i][1] = Integer.parseInt(st.nextToken());
			}
			subset(0);
			
			sb.append("#"+t+" "+maxPref).append("\n");
		}
		System.out.println(sb);
	}

	private static void subset(int cnt) {
		if(cnt == N) {
			int totalPref = 0;
			int totalKcal = 0;
			for(int i=0; i<N; i++) {
				if(selected[i]) {
					totalPref += food[i][0];
					totalKcal += food[i][1];
					if(totalKcal > kcal) {
						return;
					}
				}
			}
			maxPref = Math.max(maxPref, totalPref);
			return;
		}
		selected[cnt] = true;
		subset(cnt+1);
		
		selected[cnt] = false;
		subset(cnt+1);
	}
}
