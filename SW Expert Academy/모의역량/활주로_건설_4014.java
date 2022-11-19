package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 모의
public class 활주로_건설_4014 {

	static int N; //지도 크기
	static int X; //경사로의 길이
	static int[][] map;
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = 0;
			for(int i=0; i<N; i++) {
				if(rowChk(i)) {
					res++;
				}
				if(colChk(i)) {
					res++;
				}
			}
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static boolean colChk(int idx) {
		boolean[] visit = new boolean[N];
		int bef = map[0][idx];
		for(int i=1; i<N; i++) {
			if(Math.abs(bef - map[i][idx]) >= 2) { //높이차이가 2이상
				return false;
			}
			else if(bef - map[i][idx] == 1) { //내려가는 경우
				int now = map[i][idx];
				for(int j=i; j<i+X; j++) {
					if(j < N) { //isInside
						if(visit[j]) { //경사로가 이미 있으면
							return false;
						}
						if(map[j][idx] != now) { //경사로 놓을 곳의 높이가 같지 않으면
							return false;
						}
						visit[j] = true;
					}
					else { //범위 내부에 없으면
						return false;
					}
				}
			}
			else if(bef - map[i][idx] == -1) { //올라가는 경우
				int now = map[i-1][idx];
				for(int j=i-1; j>=i-X; j--) {
					if(j >= 0) { //isInside
						if(visit[j]) { //경사로가 이미 있으면
							return false;
						}
						if(map[j][idx] != now) { //경사로 놓을 곳의 높이가 같지 않으면
							return false;
						}
						visit[j] = true;
					}
					else { //범위 내부에 없으면
						return false;
					}
				}
			}
			bef = map[i][idx];
		}
		return true;
	}

	private static boolean rowChk(int idx) {
		boolean[] visit = new boolean[N];
		int bef = map[idx][0];
		for(int i=1; i<N; i++) {
			if(Math.abs(bef - map[idx][i]) >= 2) { //높이차이가 2이상
				return false;
			}
			else if(bef - map[idx][i] == 1) { //내려가는 경우
				int now = map[idx][i];
				for(int j=i; j<i+X; j++) {
					if(j < N) { //isInside
						if(visit[j]) { //경사로가 이미 있으면
							return false;
						}
						if(map[idx][j] != now) { //경사로 놓을 곳의 높이가 같지 않으면
							return false;
						}
						visit[j] = true;
					}
					else { //범위 내부에 없으면
						return false;
					}
				}
			}
			else if(bef - map[idx][i] == -1) { //내려가는 경우
				int now = bef;
				for(int j=i-1; j>=i-X; j--) {
					if(j >= 0) { //isInside
						if(visit[j]) { //경사로가 이미 있으면
							return false;
						}
						if(map[idx][j] != now) { //경사로 놓을 곳의 높이가 같지 않으면
							return false;
						}
						visit[j] = true;
					}
					else { //범위 내부에 없으면
						return false;
					}
				}
			}
			bef = map[idx][i];
		}
		return true;
	}

}
