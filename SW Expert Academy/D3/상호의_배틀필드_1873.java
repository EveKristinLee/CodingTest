package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상호의_배틀필드_1873 {
	static int h;
	static int w;
	static char[][] map;
	
	static int dir = 0; //0 : 우, 1 : 하, 2 : 좌, 3 : 상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0}; //우 -> 하 -> 좌 -> 상
	
	static int nowx = 0;
	static int nowy = 0;
	
	private static boolean isInside(int x, int y) {
		return (x>=0) && (y>=0) && (x<h) && (y<w); 
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			nowx = 0;
			nowy = 0; //전차의 현재 위치
			for(int i=0; i<h; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0; j<w; j++) {
					if(map[i][j] == '^') {
						dir = 3;
						nowx = i;
						nowy = j;
					}
					else if(map[i][j] == 'v') {
						dir = 1;
						nowx = i;
						nowy = j;
					}
					else if(map[i][j] == '<') {
						dir = 2;
						nowx = i;
						nowy = j;
					}
					else if(map[i][j] == '>') {
						dir = 0;
						nowx = i;
						nowy = j;
					}
				}
			}
			
			int orderN = Integer.parseInt(br.readLine());
			String order = br.readLine();
			
			for(int i=0; i<order.length(); i++) {
				switch(order.charAt(i)) {
				case 'U':
					U();
					break;
				case 'D':
					D();
					break;
				case 'L':
					L();
					break;
				case 'R':
					R();
					break;
				case 'S':
					S();
					break;
				}
			}
			
			System.out.print("#" + t + " ");
			for(int i=0; i<h; i++) {
				for(int j=0;j<w; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	
	private static void U() {
		dir = 3;
		move(dir);
	}
	private static void D() {
		dir = 1;
		move(dir);
	}
	private static void R() {
		dir = 0;
		move(dir);
	}
	private static void L() {
		dir = 2;
		move(dir);
	}
	
	private static void S() {
		if(dir == 0) { //우
			for(int i = nowy; i<w; i++) {
				if(map[nowx][i] == '#') {
					break;
				}
				if(map[nowx][i] == '*') {
					map[nowx][i] = '.';
					break;
				}
			}
		}
		if(dir == 1) { //하
			for(int i = nowx; i<h; i++) {
				if(map[i][nowy] == '#') {
					break;
				}
				if(map[i][nowy] == '*') {
					map[i][nowy] = '.';
					break;
				}
			}
		}
		if(dir == 2) { //좌
			for(int i = nowy; i>=0; i--) {
				if(map[nowx][i] == '#') {
					break;
				}
				if(map[nowx][i] == '*') {
					map[nowx][i] = '.';
					break;
				}
			}
		}
		if(dir == 3) { //상
			for(int i = nowx; i>=0; i--) {
				if(map[i][nowy] == '#') {
					break;
				}
				if(map[i][nowy] == '*') {
					map[i][nowy] = '.';
					break;
				}
			}
		}
	}
	
	private static void move(int d) {
		int nx = nowx + dx[d];
		int ny = nowy + dy[d];
		if(!isInside(nx, ny) || map[nx][ny] != '.') { //범위 안이 아니거나 이동할 위치가 평지가 아니면 현재 전차방향만 바꿈
			
			if(d == 0) { // 위치 방향에 따라 전차 모양으로 바꾸기
				map[nowx][nowy] = '>'; 
			}
			if(d == 1) {
				map[nowx][nowy] = 'v';
			}
			if(d == 2) {
				map[nowx][nowy] = '<';
			}
			if(d == 3) {
				map[nowx][nowy] = '^';
			} 
		}
		else if(isInside(nx, ny)) {
			if( map[nx][ny] == '.') {
				map[nowx][nowy] = '.'; //원래 있던 위치 평지로
				if(map[nx][ny] == '.') {
					if(d == 0) { // 이동한 위치 방향에 따라 전차 모양으로 바꾸기
						map[nx][ny] = '>'; 
					}
					if(d == 1) {
						map[nx][ny] = 'v';
					}
					if(d == 2) {
						map[nx][ny] = '<';
					}
					if(d == 3) {
						map[nx][ny] = '^';
					}
					nowx = nx;
					nowy = ny;
				}
			}
		}
	}
}
