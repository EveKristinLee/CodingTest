package solving.solve_1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 모의
public class 보호_필름_2112 {
	
	static int D; //두께
	static int W; //가로 크기
	static int K; //합격기준
	static int[][] map; //A:0, B:1
	static int res;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if(K==1) { //안전 기준이 1이면 바로 안전
				sb.append("#"+t+" "+0+"\n");
				continue;
			}
			res = Integer.MAX_VALUE;
			dfs(0, 0);
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}


	private static void dfs(int idx, int cnt) {
		if(isSafe()) { //안전
			res = Math.min(res, cnt);
			return;
		}
		if(cnt > res) {
			return;
		}
		
		if(idx == D) {
			return;
		}
		
		//현재 열 저장
		int[] tmp = new int[W];
		for(int i=0; i<W; i++) {
			tmp[i] = map[idx][i];
		}
		
		//현재열 그대로
		dfs(idx+1, cnt);
		//현재열 A로
		for(int i=0; i<W; i++) {
			map[idx][i] = 0;
		}
		dfs(idx+1, cnt+1);
		//현재열 B로
		for(int i=0; i<W; i++) {
			map[idx][i] = 1;
		}
		dfs(idx+1, cnt+1);
		
		
		//돌아오면 원상복귀
		for(int i=0; i<W; i++) {
			map[idx][i] = tmp[i];
		}
	}


	private static boolean isSafe() {
		for(int j=0; j<W; j++) {
			int cnt =1;
			boolean safe = false;
			for(int i=1; i<D; i++) {
				if(map[i-1][j] == map[i][j]) {
					cnt++;
					if(cnt == K) {
						safe = true;
						break;
					}
				}
				else {
					cnt=1;
				}
			}
			if(!safe) {
				return false;
			}
		}
		return true;
	}
}
