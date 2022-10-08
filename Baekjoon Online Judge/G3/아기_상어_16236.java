package solving.solve_0824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 구현
public class 아기_상어_16236 {
	
	public static class Fish implements Comparable<Fish> {
		int x;
		int y;
		int dist;
		public Fish(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public int compareTo(Fish o) {
			int d = dist - o.dist;
			if(d == 0) {
				d = x - o.x;
				if(d == 0) {
					d = y - o.y;
				}
			}
			return d;
		}
	}

	static int n; //크기
	static int[][] map;
	static boolean[][] visit;
	static int nowx, nowy;
	static int nowSize = 2;
	static int nowEat = 0;
	static int nowDist = 0;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=n) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int [n][n];
		visit = new boolean[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					nowx = i;
					nowy = j;
				}
			}
		}
		while(eat(nowx, nowy)) {
			if(nowEat == nowSize) {
				nowSize++;
				nowEat = 0;
			}
			for(int i=0; i<n; i++) {
				Arrays.fill(visit[i], false);
			}
		}
		System.out.println(nowDist);
	}
	
	private static boolean eat(int x, int y) {
		ArrayList<Fish> fish = new ArrayList<>();
		Queue<Fish> q = new LinkedList<>();
		q.offer(new Fish(x, y, 0));
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			Fish top = q.poll();
			x = top.x;
			y = top.y;
			int d = top.dist;
			
			if(d != 0) { //상어가 아닌 물고기 중
				if(map[x][y] < nowSize && map[x][y] > 0) {
					fish.add(top);
				}
			}
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(isInside(nx, ny)) {
					if(!visit[nx][ny]  &&  map[nx][ny] <= nowSize) {
						q.offer(new Fish(nx, ny, d+1));
						visit[nx][ny] = true;
					}
				}
			}
		}
		if(!fish.isEmpty()) {
			Collections.sort(fish);
			nowDist += fish.get(0).dist;
			nowEat++;
			map[nowx][nowy] = 0;
			nowx = fish.get(0).x;
			nowy = fish.get(0).y;
			map[nowx][nowy] = 9;
			return true;
		}
		return false;
	}

}
