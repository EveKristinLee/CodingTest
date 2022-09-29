package solving.solve_A형보충;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범_검거_1953 {

	static int N;
	static int M;
	static int R;
	static int C;
	static int L;
	static int[][] map;
	static int res;
	
	static int[] dx = {-1, 1, 0, 0}; //상, 하, 좌, 우
	static int[] dy = {0, -0, -1, 1};
	
	private static boolean isInside(int x, int y) {
		if(x<0||y<0||x>=N||y>=M) {
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
			M = Integer.parseInt(st.nextToken());
			R= Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = 0;
			bfs(R, C);
			
			sb.append("#" + t + " " + res + "\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int x, int y) {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		boolean[][] visit = new boolean[N][M];
		q.offer(new Integer[] {x, y, 0});
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			int cnt = top[2];
			if(cnt >= L) {
				return;
			}
			res++;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny) && !visit[nx][ny]) {
					if(map[nx][ny] != 0) {
						if(isMove(map[x][y], map[nx][ny], i)) {
							visit[nx][ny] = true;
							q.offer(new Integer[] {nx, ny, cnt+1});
						}
					}
				}
			}
		}
	}

	private static boolean isMove(int from, int to, int dir) {
		switch(from) {
		case 1:
			if(dir == 0) {//상
				if(to==1||to==2||to==5||to==6) {
					return true;
				}
			}else if(dir == 1) {//하
				if(to==1||to==2||to==4||to==7) {
					return true;
				}
			}else if(dir == 2) {//좌
				if(to==1||to==3||to==4||to==5) {
					return true;
				}
			}else if(dir == 3) {//우
				if(to==1||to==3||to==6||to==7) {
					return true;
				}
			}
			break;
		case 2:
			if(dir == 0) {//상
				if(to==1||to==2||to==5||to==6) {
					return true;
				}
			}else if(dir == 1) {//하
				if(to==1||to==2||to==4||to==7) {
					return true;
				}
			}
			break;
		case 3:
			if(dir == 2) {//좌
				if(to==1||to==3||to==4||to==5) {
					return true;
				}
			}else if(dir == 3) {//우
				if(to==1||to==3||to==6||to==7) {
					return true;
				}
			}
			break;
		case 4:
			if(dir == 0) {//상
				if(to==1||to==2||to==5||to==6) {
					return true;
				}
			}else if(dir == 3) {//우
				if(to==1||to==3||to==6||to==7) {
					return true;
				}
			}
			break;
		case 5:
			if(dir == 1) {//하
				if(to==1||to==2||to==4||to==7) {
					return true;
				}
			}else if(dir == 3) {//우
				if(to==1||to==3||to==6||to==7) {
					return true;
				}
			}
			break;
		case 6:
			if(dir == 1) {//하
				if(to==1||to==2||to==4||to==7) {
					return true;
				}
			}else if(dir == 2) {//좌
				if(to==1||to==3||to==4||to==5) {
					return true;
				}
			}
			break;
		case 7:
			if(dir == 0) {//상
				if(to==1||to==2||to==5||to==6) {
					return true;
				}
			}else if(dir == 2) {//좌
				if(to==1||to==3||to==4||to==5) {
					return true;
				}
			}
			break;
		}
		return false;
	}

}
