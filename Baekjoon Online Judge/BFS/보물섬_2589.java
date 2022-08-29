package algo_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보물섬_2589 {

	static int N;
	static int M;
	static char[][] map;
	static int res = Integer.MIN_VALUE;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isInside(int x, int y) {
		if(x<0|| y<0 || x>=N || y>=M) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for(int i=0; i<N; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0;j<M; j++) {
				if(map[i][j] == 'L') {
					res = Math.max(res, bfs(i, j));
				}
			}
		}
		System.out.println(res);
	}

	private static int bfs(int x, int y) {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		boolean[][] visit = new boolean[N][M];
		q.offer(new Integer[] {x, y, 0});
		visit[x][y] = true;
		int maxDist = 0;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			int dist = top[2];
			maxDist = Math.max(dist, maxDist);
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(isInside(nx, ny) && !visit[nx][ny] && map[nx][ny] == 'L') {
					q.offer(new Integer[] {nx, ny, dist+1});
					visit[nx][ny] = true;
				}
			}
		}
		return maxDist;
	}
	

}
