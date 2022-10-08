package solving.solve_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 모의
public class 벌꿀채취_2115 {
	
	public static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N; //벌통 크키
	static int M; //선택할 수 있는 벌통의 개수
	static int C; //채취할 수 있는 최대 양
	static int[][] map;
	static Pos[] select; //선택한 벌꿀통의 시작 좌표
	static int maxHoney; //벌꿀통에서 벌꿀 선택 시 가장 큰 값
	static int res; 
	static boolean[] selHoney;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1];
			select = new Pos[2];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = Integer.MIN_VALUE;
			combiUse(1, 1, 0);
			
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}


	private static void combiUse(int x, int y, int cnt) {
		if(cnt == 2) { //벌꿀통 선택완료
			//각 벌꿀 통에서 선택
			int nowSum = honey();
			res = Math.max(res, nowSum);
			
			return;
		}
		
		for(int i=y; i<=N; i++) {
			if(i+M -1 <= N) { //현재 위치에서 가로로 선택 가능
				select[cnt] = new Pos(x, i);
				combiUse(x, i+M, cnt+1);
			}
		}
		if(x+1 <= N) {
			combiUse(x+1, 1, cnt);
		}
	}

	private static int honey() {
		int res = 0;
		for(int i=0; i<2; i++) {
			int sum = 0; //선택한 벌꿀통의 벌꿀 양
			int tmpRes = 0;
			int x = select[i].x;
			int y = select[i].y;
			for(int j=0; j<M; j++) {
				sum += map[x][y+j];
				tmpRes += (map[x][y+j] * map[x][y+j]);
			}
			if(sum <= C) {
				res += tmpRes;
			}
			else { //선택한 벌꿀통들에서 다시 선택
				maxHoney = 0; //벌꿀 최대값 초기화
				selHoney = new boolean[M];
				combiHoney(select[i], 0, 0);
				res += maxHoney;
			}
		}
		return res;
	}

	private static void combiHoney(Pos now, int sum, int cnt) {
		if(cnt == M) {
			//벌꿀 선택 완료
			int max = 0;
			for(int i=0; i<M; i++) {
				if(selHoney[i]) {
					max += (map[now.x][now.y+i] * map[now.x][now.y+i]);
				}
			}
			maxHoney = Math.max(maxHoney, max);
			return;
		}
		
		if(sum + map[now.x][now.y + cnt] <= C) {
			selHoney[cnt] = true;
			combiHoney(now, sum + map[now.x][now.y + cnt], cnt+1);
		}
		selHoney[cnt] = false;
		combiHoney(now, sum, cnt+1);
	}
}
