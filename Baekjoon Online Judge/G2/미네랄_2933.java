package solving_2.solve_12.solve_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ G2
public class 미네랄_2933 {

	static int R; //세로
	static int C; //가로
	static char[][] map;
	static int N;
	static int[] move;
	static boolean[][] visit;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<=0 || y<=0 || x>R || y>C) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R+1][C+1];
		for(int i=1; i<=R; i++) {
			String s = br.readLine();
			for(int j=1; j<=C; j++) {
				map[i][j] = s.charAt(j-1);
			}
		}
		N = Integer.parseInt(br.readLine());
		move = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			//막대기 던지기
			shoot(i); 
			//클러스터 확인하고 내리기
			down();
		}
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void down() {
		visit = new boolean[R+1][C+1];
		Queue<Integer[]> q = new ArrayDeque<>(); 
		
		//바닥면에 있는 미네랄 큐에 넣기
		for(int i=1; i<=C; i++) {
			if(map[R][i] == 'x') {
				q.offer(new Integer[] {R, i});
				visit[R][i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(!visit[nx][ny] && map[nx][ny] == 'x') {
						visit[nx][ny] = true;
						q.offer(new Integer[] {nx, ny});
					}
				}
			}
		}
		
		//공중에 떠있는 클러스터 저장 후 .으로 바꾸기
		List<Integer[]> cluster = new ArrayList<>();
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(!visit[i][j] && map[i][j] == 'x') {
					cluster.add(new Integer[] {i, j});
					map[i][j] = '.';
				}
			}
		}
		if(cluster.isEmpty()) {
			return;
		}
		
		//클러스터 내리기
		boolean flag = true;
		while(flag) {
			for(int i=0; i<cluster.size(); i++) {
				int x = cluster.get(i)[0]+1;
				int y = cluster.get(i)[1];
				
				if(!isInside(x, y) || map[x][y] =='x') {
					flag = false;
					break;
				}
			}
			
			if(!flag) {
				break;
			}
			
			for(int i=0; i<cluster.size(); i++) {
				cluster.get(i)[0]++;
			}
		}
		for(int i=0; i<cluster.size(); i++) {
			map[cluster.get(i)[0]][cluster.get(i)[1]] = 'x';
		}
	}

	private static void shoot(int idx) {
		int h = R - move[idx]+1;
		if(idx%2 == 0) { //왼=>오
			for(int i=1; i<=C; i++) {
				if(map[h][i] == 'x') {
					map[h][i] = '.';
					break;
				}
			}
		}
		else { //오=>왼
			for(int i=C; i>=1; i--) {
				if(map[h][i] == 'x') {
					map[h][i] = '.';
					break;
				}
			}
		}
	}
}
