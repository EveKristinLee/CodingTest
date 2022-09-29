package solving.solve_0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리_1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] color = new int[n+1][3];
		int[][] cost = new int[n+1][3];
		
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			color[i][0] = Integer.parseInt(st.nextToken());
			color[i][1] = Integer.parseInt(st.nextToken());
			color[i][2] = Integer.parseInt(st.nextToken());
		}
		cost[1][0] = color[1][0];
		cost[1][1] = color[1][1];
		cost[1][2] = color[1][2];
		for(int i=2; i<=n; i++) {
			cost[i][0] = Math.min(cost[i-1][1], cost[i-1][2]) + color[i][0];
			cost[i][1] = Math.min(cost[i-1][0], cost[i-1][2]) + color[i][1];
			cost[i][2] = Math.min(cost[i-1][0], cost[i-1][1]) + color[i][2];
		}
		System.out.println(Math.min(cost[n][0], Math.min(cost[n][1], cost[n][2])));
	}

}
