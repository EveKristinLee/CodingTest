package solving.solve_1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ G1
public class 구슬_탈출_2_13460 {
	
	public static class Ball {
		int x;
		int y;
		int cnt;
		
		public Ball(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int N;
	static int M;
	static char[][] map;
	static int res;
	static Ball rball;
	static Ball bball;
	static int mx, my;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'R') {
					rball = new Ball(i, j, 0);
				}
				else if(map[i][j] == 'B') {
					bball = new Ball(i, j, 0);
				}
			}
		}
		
		res = bfs(rball, bball);
		System.out.println(res);
	}

	private static int bfs(Ball rball, Ball bball) {
		Queue<Ball> rq = new ArrayDeque<>();
		Queue<Ball> bq = new ArrayDeque<>();
		boolean[][][][] visit = new boolean[N][M][N][M];
		rq.offer(rball);
		bq.offer(bball);
		visit[rball.x][rball.y][bball.x][bball.y] = true;
		
		while(!rq.isEmpty() && !bq.isEmpty()) {
			
			Ball nrBall = rq.poll();
			Ball nbBall = bq.poll();
			
			//10번 이상 움직이면 안됨
			if(nrBall.cnt > 10) {
				return -1;
			}
			
			//파란공이 구멍에 들어가도 다른 방식으로 빨간공이 먼저 들어갈 수 있다.
			if(map[nbBall.x][nbBall.y] == 'O') {
				continue;
			}
			
			//빨간 구슬이 구멍에 빠지면
			if(map[nrBall.x][nrBall.y] == 'O') {
				return nrBall.cnt;
			}
			
			for(int i=0; i<4; i++) { //4방향
				
				int bx = nbBall.x;
				int by = nbBall.y;
				while(true) {
					bx += dx[i];
					by += dy[i];
					//구멍에 들어가면
					if(map[bx][by] == 'O') {
						break;
					}
					else if(map[bx][by] == '#') { //벽이면
						bx -= dx[i];
						by -= dy[i];
						break;
					}
				}
				
				int rx = nrBall.x;
				int ry = nrBall.y;
				while(true) {
					rx += dx[i];
					ry += dy[i];
					//구멍에 들어가면
					if(map[rx][ry] == 'O') {
						break;
					}
					else if(map[rx][ry] == '#') { //벽이면
						rx -= dx[i];
						ry -= dy[i];
						break;
					}
				}
				
				//두개의 공이 같은 위치이고 구멍에 들어간게 아니라면 공들의 위치 옮겨줘야함 - 움직인 거리가 긴걸 덜 이동
				if(bx == rx && by == ry && map[rx][ry] != 'O') {
					int rDist = Math.abs(nrBall.x - rx) + Math.abs(nrBall.y - ry);
					int bDist = Math.abs(nbBall.x - bx) + Math.abs(nbBall.y - by);
					
					if(rDist > bDist) {
						rx -= dx[i];
						ry -= dy[i];
					}
					else {
						bx -= dx[i];
						by -= dy[i];
					}
				}
				
				if(!visit[rx][ry][bx][by]) {
					visit[rx][ry][bx][by] = true;
					rq.offer(new Ball(rx, ry, nrBall.cnt+1));
					bq.offer(new Ball(bx, by, nbBall.cnt+1));
				}
			}
		}
		return -1;
	}
}
