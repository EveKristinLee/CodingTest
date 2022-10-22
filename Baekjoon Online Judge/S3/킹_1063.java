package solving.solve_1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ S3
public class 킹_1063 {

	static int kx, ky;
	static int rx, ry;
	static int N;
	static int[] move;
	static int[][] map;
	static String kRes;
	static String rRes;
	
	static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
	static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1}; //R, L, T, B, RB, LB, RT, LT
	
	private static boolean isInside(int x, int y) {
		if(x<=0 || y<=0 || x>8 || y>8) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String king = st.nextToken();
		String rock = st.nextToken();
		N = Integer.parseInt(st.nextToken());
		kx = king.charAt(1) - '0';
		ky = (king.charAt(0) - 'A') + 1; 
		rx = rock.charAt(1) - '0';
		ry = (rock.charAt(0) - 'A') + 1; 
		
		move = new int[N];
		for(int i=0; i<N; i++) {
			move[i] = getDir(br.readLine());
		}
		map = new int[9][9];
		map[kx][ky] = 1; //왕
		map[rx][ry] = -1; //돌
		go(); 
		kRes = getPos(kx, ky);
		rRes = getPos(rx, ry);
		System.out.println(kRes);
		System.out.println(rRes);
	}

	private static String getPos(int x, int y) {
		String t = "";
		t += (char)(y-1 + 'A');
		t += (char)(x + '0');
		return t;
	}

	private static void go() {
		for(int i=0; i<N; i++) {
			int nx = kx + dx[move[i]];
			int ny = ky + dy[move[i]];
			if(isInside(nx, ny)) {
				if(map[nx][ny] != -1) { //돌이 없을때
					map[nx][ny] = 1;
					map[kx][ky] = 0;
					kx = nx;
					ky = ny;
				}
				else if(map[nx][ny] == -1) {
					int mx = rx + dx[move[i]];
					int my = ry + dy[move[i]];
					if(isInside(mx, my)) {
						map[mx][my] = -1;
						map[rx][ry] = 0;
						rx = mx;
						ry = my;
						kx = nx;
						ky = ny;
					}
				}
			}
		}
	}

	private static int getDir(String m) {
		switch (m) {
		case "R":
			return 0;
		case "L":
			return 1;
		case "B":
			return 2;
		case "T":
			return 3;
		case "RT":
			return 4;
		case "LT":
			return 5;
		case "RB":
			return 6;
		case "LB":
			return 7;
		default:
			break;
		}
		return -1;
	}
}
