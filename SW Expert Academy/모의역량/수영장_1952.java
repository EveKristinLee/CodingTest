package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수영장_1952 {

	static int[] cost;
	static int[] plan;
	static int minCost;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			cost = new int[4];
			plan = new int[12];
			minCost = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			calcMoney(0, 0);
			sb.append("#"+t+" " + minCost).append("\n");
		}
		System.out.println(sb);
	}

	private static void calcMoney(int month, int sum) {
		if(month >= 12) { //일년이 끝났을 경우
			minCost = Math.min(minCost, sum);
			return;
		}
		if(plan[month] == 0) { //이번 달 이용횟수가 0이면 다음달로 이동
			calcMoney(month+1, sum);
		}
		
		calcMoney(month+1, sum + (cost[0] * plan[month]));
		calcMoney(month+1, sum + cost[1]);
		calcMoney(month+3, sum + cost[2]);
		calcMoney(month+12, sum + cost[3]);
	}

}
