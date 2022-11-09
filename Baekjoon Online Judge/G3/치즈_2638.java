package solving.solve_1109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ G3
public class 치즈_2638 {
	
	static int N;
	static int M;
	static int[][] map;
	static int res;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=M) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = 0;
		while(isRest()) {
			res++;
			bfs();
		}
		System.out.println(res);
	}

	private static void bfs() {
		Queue<Integer[]> q = new ArrayDeque<>();
		int[][] visit = new int[N][M];
		q.offer(new Integer[] {0, 0});
		visit[0][0] = 1;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(map[nx][ny] == 0 && visit[nx][ny] == 0) {
						visit[nx][ny] = 1;
						q.offer(new Integer[] {nx, ny});
					}
					else if(map[nx][ny] == 1) {
						visit[nx][ny]++;
					}
				}
			}
		}
		
		//치즈 녹이기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visit[i][j] >= 2) {
					map[i][j] = 0;
				}
			}
		}
	}

	//true면 치즈가 남아있음, false면 다 녹음
	private static boolean isRest() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}
}
