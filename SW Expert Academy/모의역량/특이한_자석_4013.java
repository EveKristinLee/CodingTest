package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 모의
public class 특이한_자석_4013 {

	static int K; //회전 시키는 횟수
	static int[][] map;
	static int[][] turn;
	static int res;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			K = Integer.parseInt(br.readLine());
			map = new int[5][8];
			turn = new int[K][2];
			res = 0;
			for(int i=1; i<5; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<2; j++) {
					turn[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0; i<K; i++) {
				visit = new boolean[5];
				visit[turn[i][0]] = true;
				rotate(turn[i][0], turn[i][1]);
			}
			res = getScore();
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void rotate(int cnt, int dir) {
		int tmp = dir;
		if(dir == 1) {
			tmp = -1;
		}else {
			tmp = 1;
		}
		if(cnt-1 >= 1) { //작은쪽
			if(map[cnt][6] != map[cnt-1][2]) {
				if(!visit[cnt-1]) {
					visit[cnt-1] = true;
					rotate(cnt-1, tmp);
				}
			}
		}
		if(cnt+1 <= 4) { //큰쪽
			if(map[cnt][2] != map[cnt+1][6]) {
				if(!visit[cnt+1]) {
					visit[cnt+1] = true;
					rotate(cnt+1, tmp);
				}
			}
		}
		go(cnt, dir);
	}

	private static void go(int idx, int dir) {
		if(dir == 1) { //시계방향
			int last = map[idx][7];
			for(int i=6; i>=0; i--) {
				map[idx][i+1] = map[idx][i];
			}
			map[idx][0] = last;
		}
		else if(dir == -1) { //시계방향
			int first = map[idx][0];
			for(int i=1; i<8; i++) {
				map[idx][i-1] = map[idx][i];
			}
			map[idx][7] = first;
		}
	}

	private static int getScore() {
		int sum = 0;
		if(map[1][0] == 1) sum += 1;
		if(map[2][0] == 1) sum += 2;
		if(map[3][0] == 1) sum += 4;
		if(map[4][0] == 1) sum += 8;
		return sum;
	}
}
