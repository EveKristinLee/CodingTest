package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//SWEA 모의
public class 핀볼_게임_5650 {
	
	static int N;
	static int[][] map;
	static List<Integer[]>[] worm;
	static int res;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; //상하좌우
	
	private static boolean isEdge(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			worm = new ArrayList[11];
			for(int i=6; i<=10; i++) {
				worm[i] = new ArrayList<>();
			}
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 6) {
						worm[map[i][j]].add(new Integer[] {i, j});
					}
				}
			}
			res = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == 0) {
						for(int d=0; d<4; d++) {
							int cnt = go(i, j, d);
							res = Math.max(cnt, res);
						}
					}
				}
			}
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static int go(int x, int y, int dir) {
		int cnt = 0;
		int sx = x;
		int sy = y;
		
		while(true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(isEdge(nx, ny)) { //벽에 부딫힘, 방향만 바꾸고 현위치
				cnt++;
				dir = turnDir(dir);
			}
			
			else if(map[nx][ny] == -1 || (sx == nx && sy == ny)) { //블랙홀이거나 처음 위치면 게임 끝
				break;
			}
			
			else if(map[nx][ny] >= 1 && map[nx][ny] <= 5) { //블럭만남
				cnt++;
				if(map[nx][ny] == 1) { 
					if(dir == 1) {
						dir = 3;
					}
					else if(dir == 2) {
						dir = 0;
					}
					else {
						dir = turnDir(dir);
					}
				}
				else if(map[nx][ny] == 2) {
					if(dir == 0) {
						dir = 3;
					}
					else if(dir == 2) {
						dir = 1;
					}
					else {
						dir = turnDir(dir);
					}
				}
				else if(map[nx][ny] == 3) {
					if(dir == 0) {
						dir = 2;
					}
					else if(dir == 3) {
						dir = 1;
					}
					else {
						dir = turnDir(dir);
					}
				}
				else if(map[nx][ny] == 4) {
					if(dir == 1) {
						dir = 2;
					}
					else if(dir == 3) {
						dir = 0;
					}
					else {
						dir = turnDir(dir);
					}				
				}
				else if(map[nx][ny] == 5) {
					dir = turnDir(dir);
				}
				x = nx;
				y = ny;
			}
			
			else if(map[nx][ny] >= 6) { //웜홀만남
				for(int i=0; i<2; i++) {
					if(worm[map[nx][ny]].get(i)[0] != nx || worm[map[nx][ny]].get(i)[1] != ny) {
						int tmpx = nx;
						int tmpy = ny;
						if(i == 0) {
							nx = worm[map[tmpx][tmpy]].get(0)[0];
							ny = worm[map[tmpx][tmpy]].get(0)[1];
						}else {
							nx = worm[map[tmpx][tmpy]].get(1)[0];
							ny = worm[map[tmpx][tmpy]].get(1)[1];
						}
						break;
					}
				}
			}
			x = nx;
			y = ny;
		}
		return cnt;
	}

	private static int turnDir(int dir) {
		if(dir == 0) {
			return 1;
		} else if(dir == 1) {
			return 0;
		} else if(dir == 2) {
			return 3;
		}else if(dir == 3) {
			return 2;
		}
		return -1;
	}
}
