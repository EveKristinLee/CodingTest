package solving.solve_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//BOJ G4
public class Puyo_Puyo_11559 {

	static char[][] map;
	static int res;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=12 || y>=6) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		for(int i=0; i<12; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();
		}
		
		res = 0;
		while(true) {
			boolean isStop = true;
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(map[i][j] != '.') {
						boolean flag = bfs(i, j);
						if(flag) {
							isStop = false;
						}
					}
				}
			}
			if(isStop) {
				break;
			}
			//뿌요 밑으로 내리기
			reArrange();
			res++;
		}
		System.out.println(res);
	}

	private static void reArrange() {
		for(int i=0; i<6; i++) {
			for(int j=11; j>=0; j--) {
				if(map[j][i] == '.') {
					for(int k=j-1; k>=0; k--) {
						if(map[k][i] != '.') {
							map[j][i] = map[k][i];
							map[k][i] = '.';
							break;
						}
					}
				}
			}
		}
	}

	private static boolean bfs(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[12][6];
		List<Integer[]> del = new LinkedList<>();
		q.offer(new Integer[] {x, y});
		del.add(new Integer[] {x, y});
		visit[x][y] = true;
		
		//같은 색 뿌요 찾기
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(!visit[nx][ny] && map[nx][ny] == map[x][y]) {
						visit[nx][ny] = true;
						q.offer(new Integer[] {nx, ny});
						del.add(new Integer[] {nx, ny});
					}
				}
			}
		}
		
		if(del.size() >= 4) {
			//터뜨리기
			for(int i=0; i<del.size(); i++) {
				map[del.get(i)[0]][del.get(i)[1]] = '.';
			}
			return true;
		}
		else {
			return false;
		}
	}

}
