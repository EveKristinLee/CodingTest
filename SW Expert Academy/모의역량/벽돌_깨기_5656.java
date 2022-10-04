package solving.solve_1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽돌_깨기_5656 {

	static int N;
	static int W;
	static int H;
	static int[][] map;
	static int[][] copyMap;
	static int[] order;
	static Queue<Integer[]> q;
	static int minRes;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=H || y>=W) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			copyMap = new int[H][W];
			minRes = Integer.MAX_VALUE;
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			order = new int[N];
			perm(0);
		
			sb.append("#"+t+" "+minRes+"\n");
		}
		System.out.println(sb);
	}
	
	public static void perm(int cnt) {
		if(cnt == N) {
			//순열 완성
			copy();
			int rest = play();
			minRes = Math.min(minRes, rest);
			return;
		}
		
		for(int i=0; i<W; i++) {
			order[cnt] = i;
			perm(cnt+1);
		}
	}

	private static int play() {
		int res = 0;
		q = new ArrayDeque<>();
		for(int i=0; i<N; i++) { //구슬을 N번 던지기
			for(int j=0; j<H; j++) {
				if(copyMap[j][order[i]] != 0) {
					q.offer(new Integer[] {j, order[i]});
					break;
				}
			}
			destroy();
		}
		
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(copyMap[i][j] > 0) {
					res++;
				}
			}
		}
		return res;
	}

	private static void destroy() {
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			int dist = copyMap[x][y];
			copyMap[x][y] = 0;
			
			for(int i=0; i<4; i++) {
				for(int j=1; j<dist; j++) {
					int nx = x + (dx[i] * j);
					int ny = y + (dy[i] * j);
					if(isInside(nx, ny)) {
						if(copyMap[nx][ny] > 0) {
							q.offer(new Integer[] {nx, ny});
						}
					}
				}
			}
		}
		
		//밑으로 붙이기
		for(int i=H-2; i>=0; i--) {
			for(int j=0; j<W; j++) {
				if(copyMap[i+1][j] ==0) {
					for(int k=i; k>=0; k--) {
						if(copyMap[k][j] != 0) {
							copyMap[i+1][j] = copyMap[k][j];
							copyMap[k][j] = 0;
							break;
						}
					}
				}
			}
		}
	}

	private static void copy() {
		for(int i=0; i<H; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
		}
	}
}
