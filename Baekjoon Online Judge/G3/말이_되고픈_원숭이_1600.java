package solving.solve_1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말이_되고픈_원숭이_1600 {

	static int K;
	static int W;
	static int H;
	static int[][] map;
	static boolean[][][] visit;
	static int res;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int[] dx2 = {-2, -2, -1, 1, 2, 2, 1, -1};
	static int[] dy2 = {-1, 1, 2, 2, 1, -1, -2, -2};
	
	public static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=H || y>=W) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visit = new boolean[H][W][K+1];
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = bfs(0, 0);
		
		System.out.println(res);
	}

	private static int bfs(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<Integer[]>();
		q.offer(new Integer[] {x, y, 0, K});
		visit[x][y][K] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			int curDist = top[2];
			int curK = top[3];
			
			if(x == H-1 && y == W-1) {
				return curDist;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(!visit[nx][ny][curK] && map[nx][ny] == 0) {
						visit[nx][ny][curK] = true;
						q.offer(new Integer[] {nx, ny, curDist+1, curK});
					}
				}
			}
			
			if(curK > 0) {
				for(int i=0; i<8; i++) {
					int nx = x + dx2[i];
					int ny = y + dy2[i];
					if(isInside(nx, ny)) {
						if(!visit[nx][ny][curK-1] && map[nx][ny] == 0) {
							visit[nx][ny][curK-1] = true;
							q.offer(new Integer[] {nx, ny, curDist+1, curK-1});
						}
					}
				}
			}
		}
		return -1;
	}
}
