package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 치즈_2636 {
	
	static int N; //세로
	static int M; //가로
	static int[][] map;
	static boolean[][] visit;
	static Set<Integer[]> edge;
	static int rest = 0;
	
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
		
		int day = 0;
		while(true) {
			visit = new boolean[N][M];
			edge = new HashSet<Integer[]>();
			bfs(0, 0);
			for (Integer[] e : edge) {
			}
			if(edge.size() > 0) {
				day++;
				rest = calcRest();
				melt();
			}else {
				break;
			}
		}
		System.out.println(day);
		System.out.println(rest);
	}

	private static int calcRest() {
		int sum = 0;
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<M-1; j++) {
				if(map[i][j] == 1) {
					sum++;
				}
			}
		}
		return sum;
	}

	private static void melt() {
		for (Integer[] now : edge) {
			map[now[0]][now[1]] = 0;
		}
	}

	private static void bfs(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<Integer[]>();
		q.offer(new Integer[] {x, y});
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny) && !visit[nx][ny]) {
					if(map[nx][ny] == 0) {
						visit[nx][ny] = true;
						q.offer(new Integer[] {nx, ny});
					}
					if(map[nx][ny] == 1) {
						visit[nx][ny] = true;
						edge.add(new Integer[] {nx, ny});
					}
				}
			}
		}
	}
}
