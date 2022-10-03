package solving.solve_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원판_돌리기_17822 {
	
	static int N;
	static int M;
	static int T;
	static int[][] plate;
	static int[][] turn;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		plate = new int[N+1][M];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				plate[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		turn = new int[T][3];
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				turn[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<T; i++) {
			//원판 돌리기
			doTurn(turn[i][0], turn[i][0], turn[i][1], turn[i][2], 2);
			//인접한 수 삭제 확인
			if(!delete()) {
				//인접한 수 없을 경우 숫자 바꿈
				calcNum();
			}
		}
		
		int res = 0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				res += plate[i][j];
			}
		}
		System.out.println(res);
	}

	private static void calcNum() {
		int sum = 0;
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				if(plate[i][j] != 0) {
					sum += plate[i][j];
					cnt++;
				}
			}
		}
		double avg = (double)sum / (double)cnt;
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				if(plate[i][j] != 0) {
					if(plate[i][j] > avg) {
						plate[i][j]--;
					}
					else if(plate[i][j] < avg){
						plate[i][j]++;
					}
				}
			}
		}
	}

	private static boolean delete() {
		boolean[][] chk = new boolean[N+1][M];
		boolean flag = false;
		//양 옆 확인
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				int tmp = plate[i][j];
				if(tmp != 0) {
					if(j == 0) {
						if(tmp == plate[i][M-1]) {
							chk[i][j] = true;
							chk[i][M-1] = true;
						}
						if(tmp == plate[i][j+1]) {
							chk[i][j] = true;
							chk[i][j+1] = true;
						}
					}
					else if(j == M-1) {
						if(tmp == plate[i][j-1]) {
							chk[i][j] = true;
							chk[i][j-1] = true;
						}
						if(tmp == plate[i][0]) {
							chk[i][j] = true;
							chk[i][0] = true;
						}
					}
					else {
						if(tmp == plate[i][j-1]) {
							chk[i][j] = true;
							chk[i][j-1] = true;
						}
						if(tmp == plate[i][j+1]) {
							chk[i][j] = true;
							chk[i][j+1] = true;
						}
					}
				}
			}  
		}
		
		//위 아래 확인
		for(int j=0; j<M; j++) {
			for(int i=1; i<=N; i++) {
				int tmp = plate[i][j];
				if(tmp != 0) {
					if(i == 1) {
						if(tmp == plate[i+1][j]) {
							chk[i][j] = true;
							chk[i+1][j] = true;
						}
					}
					else if(i == N) {
						if(tmp == plate[i-1][j]) {
							chk[i][j] = true;
							chk[i-1][j] = true;
						}
					}
					else {
						if(tmp == plate[i+1][j]) {
							chk[i][j] = true;
							chk[i+1][j] = true;
						}
						if(tmp == plate[i-1][j]) {
							chk[i][j] = true;
							chk[i-1][j] = true;
						}
					}
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				if(chk[i][j]) {
					plate[i][j] = 0;
					flag = true;
 				}
			}
		}
		return flag;
	}

	private static void doTurn(int start, int num, int dir, int k, int mul) {
		for(int i=0; i<k; i++) {
			if(dir == 0) { //시계방향
				int tmp = plate[num][M-1];
				for(int j=M-1; j>=1; j--) {
					plate[num][j] = plate[num][j-1];
				}
				plate[num][0] = tmp;
			}
			else if(dir == 1) { //반시계방향
				int tmp = plate[num][0];
				for(int j=0; j<M-1; j++) {
					plate[num][j] = plate[num][j+1];
				}
				plate[num][M-1] = tmp;
			}
		}
		if(start * mul <= N) {
			doTurn(start, start*mul, dir, k, mul+1);
		}
	} 
}
