package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA 모의
public class 벽돌_깨기_5656_2 {
	
	static int N;
	static int W;
	static int H;
	static int[][] map;
	static int[][] copy;
	static int res;
	static int[] sel;
	
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
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = Integer.MAX_VALUE;
			sel = new int[N];
			perm(0);
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void perm(int cnt) {
		if(cnt == N) {
			copy = copyMap();
			int rest = go();
			res = Math.min(rest, res);
			return;
		}
		for(int i=0; i<W; i++) {
			sel[cnt] = i;
			perm(cnt+1);
		}
	}

	private static int[][] copyMap() {
		int[][] copy = new int[H][W];
		for(int i=0; i<H; i++) {
			System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
		}
		return copy;
	}

	private static int go() {
		//벽돌 깨뜨리기
		for(int i=0; i<N; i++) {
			for(int j=0; j<H; j++) {
				if(copy[j][sel[i]] != 0) {
					bfs(j, sel[i]);
					break;
				}
			}
		}
		
		//남은 벽돌 개수 세기
		int rest = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(copy[i][j] >0) {
					rest++;
				}
			}
		}
		return rest;
	}

	private static void bfs(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[H][W];
		q.offer(new Integer[] {x, y});
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			int cnt = copy[x][y];
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<cnt; j++) {
					int nx = x + (dx[i] * j);
					int ny = y + (dy[i] * j);
					if(isInside(nx, ny)) {
						if(!visit[nx][ny] && copy[nx][ny] > 0) {
							visit[nx][ny] = true;
							q.offer(new Integer[] {nx, ny});
						}
					}
				}
			}
		}
		
		//gravity
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(visit[i][j]) {
					copy[i][j] = 0;
				}
			}
		}
		
		for(int j=0; j<W; j++) {
			for(int i=H-1; i>=0; i--) {
				if(copy[i][j] == 0) {
					for(int k=i; k>=0; k--) {
						if(copy[k][j] != 0) {
							copy[i][j] = copy[k][j];
							copy[k][j] = 0;
							break;
						}
					}
				}
			}
		}
	}
}
