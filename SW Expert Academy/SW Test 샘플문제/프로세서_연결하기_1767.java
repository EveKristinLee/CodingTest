package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//SWEA SW Test 샘플문제
public class 프로세서_연결하기_1767 {

	private static class Core {
		int x;
		int y;
		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] map;
	static List<Core> core;
	static int res; //가장 짧은 전선의 길이
	static int maxCnt; //가장 많이 연결한 개수
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return false;
		}
		return true;
	}
	
	private static boolean isEdge(int x, int y) {
		if(x==0 || x==N-1 || y==0 || y==N-1) { //가장 자리
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			core = new ArrayList<>();
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						if(!isEdge(i, j)) {
							core.add(new Core(i, j));
						}
					}
				}
			}
			res = Integer.MAX_VALUE;
			maxCnt = Integer.MIN_VALUE;
			dfs(0, 0, 0);
			
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int idx, int len, int cnt) {
		if(idx == core.size()) { //종료 조건
			if(maxCnt < cnt) { //개수가 더 많으면
				maxCnt = cnt;
				res = len;
			}
			if(maxCnt == cnt) {
				if(res > len) {
					res = len;
				}
			}
			return;
		}
		
		int x = core.get(idx).x;
		int y = core.get(idx).y;
		
		for(int i=0; i<4; i++) {
			int count = 0;
			int nx = x;
			int ny = y;
			int originX = x;
			int originY = y;
			
			while(true) {
				nx += dx[i];
				ny += dy[i];
				
				if(!isInside(nx, ny)) { //벽 만나기
					break;
				}
				
				if(map[nx][ny] == 1) { //전선 만남
					count = 0;
					break;
				}
				count++;
			}
			
			
			if(count == 0) { //연결 못함
				dfs(idx+1, len, cnt);
			}
			else {
				//전선그리기
				for(int j=0; j<count; j++) {
					originX += dx[i];
					originY += dy[i];
					map[originX][originY] = 1;
				}
				
				dfs(idx+1, len+count, cnt+1);
				
				//되돌리기
				originX = x;
				originY = y;
				for(int j=0; j<count; j++) {
					originX += dx[i];
					originY += dy[i];
					map[originX][originY] = 0;
				}
			}
		}
	}

}
