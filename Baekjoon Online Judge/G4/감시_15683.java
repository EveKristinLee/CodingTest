package solving.solve_0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 감시_15683 {

	public static class CCTV {
		int x;
		int y;
		int type;
		int dir; 
		
		public CCTV(int x, int y, int type, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
			this.dir = dir;
		}
	}
	
	static int n;
	static int m;
	static int[][] map;
	static int[] dir;
	static ArrayList<CCTV> cctv;
	static boolean[] visit;
	static int minCnt = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cctv = new ArrayList<>();
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					cctv.add(new CCTV(i, j, map[i][j], 0));
				}
			}
		}
		dir = new int[cctv.size()];
		visit = new boolean[cctv.size()];
		
		setDir(0);
		System.out.println(minCnt);
	}

	private static void setDir(int cnt) {
		if(cnt == cctv.size()) {
			//각 cctv의 방향에 따라 map 채우기
			int[][] copy = copyMap();
			for(int i=0; i<cctv.size(); i++) {
				int nx = cctv.get(i).x;
				int ny = cctv.get(i).y;
				int nType = cctv.get(i).type;
				int nDir = dir[i];
				//0 : 오, 1 : 아래, 2 : 위, 3 : 왼
				if(nType == 1) {
					go(nx, ny, copy, nDir);
				}
				else if(nType == 2) {
					if(nDir == 0) {
						go(nx, ny, copy, 0); //오른쪽
						go(nx, ny, copy, 3); //왼쪽
					}
					else if(nDir == 1) {
						go(nx, ny, copy, 1); //아래
						go(nx, ny, copy, 2); //위
					}
				}
				else if(nType == 3) {
					if(nDir == 0) {
						go(nx, ny, copy, 2); //위
						go(nx, ny, copy, 0); //오
					}
					else if(nDir == 1) {
						go(nx, ny, copy, 0); //오
						go(nx, ny, copy, 1); //아래
					}
					else if(nDir == 2) {
						go(nx, ny, copy, 3); //왼
						go(nx, ny, copy, 1); //아래
					}
					else if(nDir == 3) {
						go(nx, ny, copy, 2); //위
						go(nx, ny, copy, 3); //왼
					}
				}
				else if(nType == 4) {
					if(nDir == 0) {
						//ㅗ
						//오아위왼 -> 0, 2, 3
						go(nx, ny, copy, 3); //왼
						go(nx, ny, copy, 2); //위
						go(nx, ny, copy, 0); //오
					}
					else if(nDir == 1) {
						//ㅏ -> 0, 1, 2
						go(nx, ny, copy, 2); //위
						go(nx, ny, copy, 0); //오
						go(nx, ny, copy, 1); //아래
					}
					else if(nDir == 2) {
						//ㅜ -> 0, 1, 3
						go(nx, ny, copy, 3); //왼
						go(nx, ny, copy, 0); //오
						go(nx, ny, copy, 1); //아래
					}
					else if(nDir == 3) {
						//ㅓ
						//1, 2, 3
						go(nx, ny, copy, 2); //위
						go(nx, ny, copy, 1); //아래
						go(nx, ny, copy, 3); //왼
					}
				}
				else if(nType == 5) {
					go(nx, ny, copy, 0); //오
					go(nx, ny, copy, 1); //아래
					go(nx, ny, copy, 2); //위
					go(nx, ny, copy, 3); //왼
				}
			}
			
			int nowCnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(copy[i][j] == 0) {
						nowCnt++;
					}
				}
			}
//			System.out.println(nowCnt);
			minCnt = Math.min(minCnt, nowCnt);
			return;
		}
		
		CCTV now = cctv.get(cnt);
		switch(now.type) {
		case 1: case 3: case 4:
			for(int i=0; i<4; i++) {
				dir[cnt] = i;
				setDir(cnt+1);
			}
			break;
			
		case 2:
			for(int i=0; i<2; i++) {
				dir[cnt] = i;
				setDir(cnt+1);
			}
			break;
			
		case 5:
			dir[cnt] = 0;
			setDir(cnt+1);
			break;
		}
	}

	private static int[][] copyMap() {
		int[][] tmp = new int[n][m];
		for(int i=0; i<map.length; i++) {
			System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
		}
		return tmp;
	}
	
	private static void go(int x, int y, int[][] map, int dir) { //방향에 따라 바라보기
		if(dir == 0) { //오른쪽 바라보기
			for(int i=y; i<m; i++) {
				if(map[x][i] == 6) {
					break;
				}
				else if(map[x][i] == 0){
					map[x][i] = -1;					
				}
			}
		}
		else if(dir == 1) { //아래
			for(int i=x; i<n; i++) {
				if(map[i][y] == 6) {
					break;
				}
				else if(map[i][y] == 0){
					map[i][y] = -1;					
				}
			}
		}
		else if(dir == 2) { //위
			for(int i=x; i>=0; i--) {
				if(map[i][y] == 6) {
					break;
				}
				else if(map[i][y] == 0){
					map[i][y] = -1;					
				}
			}
		}
		else if(dir == 3) {//왼
			for(int i=y; i>=0; i--) {
				if(map[x][i] == 6) {
					break;
				}
				else if(map[x][i] == 0){
					map[x][i] = -1;					
				}
			}
		}
	}

}
