package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 치킨_배달_15686 {

	static int n;
	static int m;
	static int[][] map;
	static ArrayList<Integer[]> chicken;
	static ArrayList<Integer[]> home;
	static int[] comb;
	static int minDist = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		comb = new int[m];
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					chicken.add(new Integer[] {i, j});
				}
				if(map[i][j] == 1) {
					home.add(new Integer[] {i, j});
				}
			}
		}
		combi(0, 0);
		System.out.println(minDist);
	}

	private static void combi(int cnt, int start) {
		if(cnt == m) {
			int dist = calcDist();
			minDist = Math.min(minDist, dist);
			return;
		}
	
		for(int i=start; i<chicken.size(); i++) {
			comb[cnt] = i;
			combi(cnt+1, i+1);
		}
	}

	private static int calcDist() {
		int total = 0;
		for(int i=0; i<home.size(); i++) {
			int dist = Integer.MAX_VALUE;
			for(int j=0; j<m; j++) {
				dist = Math.min(dist, Math.abs(home.get(i)[0] - chicken.get(comb[j])[0]) + Math.abs(home.get(i)[1] - chicken.get(comb[j])[1]));
			}
			total += dist;
		}
		return total;
	}
}
