package solving_2.solve_12.solve_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ G2
public class 스타트_택시_19238 {

	private static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int dis;
		public Pos(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		@Override
		public int compareTo(Pos o) {
			if(this.dis == o.dis) {
				if(this.x == o.x) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.dis - o.dis;
		}
	}
	
	static int N, M;
	static int energy;
	static int[][] map;
	static Pos taxi;
	static Pos[] dest;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<=0 || y<=0 || x>N || y>N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		energy = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		dest = new Pos[M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					map[i][j] = -1; //벽 = -1
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		taxi = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			map[x1][y1] = i;
			dest[i] = new Pos(x2, y2, 0);
		}
		
		while(M-- > 0) {
			//승객까지 가기
			int dist = shortest();
			energy -= dist;
			if(dist == -1 || energy <= 0) { //손님을 태웠는데 연로가 0이면 움직일 수 없음
				System.out.println(-1);
				return;
			}
			
			//도착지 가기
			int get = getDist(map[taxi.x][taxi.y]);
			energy -= get;
			if(get == -1 || energy < 0) {
				System.out.println(-1);
				return;
			}
			energy += (get * 2);
		
		}
		System.out.println(energy);
	}

	private static int getDist(int idx) {
		map[taxi.x][taxi.y] = 0; //손님 태운 칸 0으로 만들기
		
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N+1][N+1];
		taxi.dis = 0;
		q.add(taxi);
		visit[taxi.x][taxi.y] = true;
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			if(now.x == dest[idx].x && now.y == dest[idx].y) { //현재 손님의 도착지
				taxi.x = now.x;
				taxi.y = now.y;
				return now.dis;
			}
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(isInside(nx, ny)) {
					if(!visit[nx][ny] && map[nx][ny] >= 0) {
						visit[nx][ny] = true;
						q.offer(new Pos(nx, ny, now.dis + 1));
					}
				}
			}
		}
		return -1;
	}

	private static int shortest() {
		Queue<Pos> q = new ArrayDeque<>();
		Queue<Pos> pq = new PriorityQueue<>();
		boolean[][] visit = new boolean[N+1][N+1];
		taxi.dis = 0;
		q.add(taxi);
		visit[taxi.x][taxi.y] = true;
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			if(map[now.x][now.y] > 0) { //손님
				pq.offer(now);
			}
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(isInside(nx, ny)) {
					if(!visit[nx][ny] && map[nx][ny] >= 0) {
						visit[nx][ny] = true;
						q.offer(new Pos(nx, ny, now.dis+1));
					}
				}
			}
		}
		if(!pq.isEmpty()) { //같은 거리에 손님 있는지 확인
			taxi = pq.poll(); //택시 위치 바꿔주기
			return taxi.dis;
		}
		return -1; //손님이 없으면
 	}

}
