package solving.solve_0915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이닝_17281 {

	static int N; //이닝 수
	static int[][] score;
	static int res = Integer.MIN_VALUE;
	static int[] turn;
	static boolean[] visit;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		score = new int[N][10];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<10; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		turn = new int[10];
		visit = new boolean[10];
		
		turn[4] = 1;
		visit[4] = true;
		
		setOrder(2);
		sb.append(res + " \n");
		System.out.println(sb);
	}


	private static void setOrder(int cnt) {
		if(cnt == 10) {
			play();
			return;
		}
		
		for(int i=1; i<10; i++) {
			if(visit[i]) {
				continue;
			}
			visit[i] = true;
			turn[i] = cnt;
			setOrder(cnt+1);
			visit[i] = false;
		}
	}

	private static void play() {
		int hitter = 1;
		int maxSum = 0;
		
		for(int i=0; i<N; i++) {
			boolean[] base = new boolean[4];
			int out = 0;
			boolean end = false;
			
			while(true) { //아웃이 세개가 안될경우 이닝이 끝나지 않는다.
				for(int j=hitter; j<10; j++) {
					int hit = score[i][turn[j]];

					switch(hit) {
					case 0: //아웃
						out++;
						break;
					case 1: //안타
						for(int h=3; h>=1; h--) {
							if(base[h]) {
								if(h == 3) {
									maxSum++;
									base[h] = false;
								}
								else {
									base[h] = false;
									base[h+1] = true;
								}
 							}
						}
						base[1] = true; //안타를 친 타자
						break;
					case 2: //2루타
						for(int h=3; h>=1; h--) {
							if(base[h]) {
								if(h == 3 || h == 2) {
									maxSum++;
									base[h] = false;
								}
								else {
									base[h] = false;
									base[h+2] = true;
								}
 							}
						}
						base[2] = true;
						break;
					case 3: //3루타
						for(int h=3; h>=1; h--) {
							if(base[h]) {//선수가 서있기만 하면 점수 획득
								maxSum++;
								base[h] = false;
 							}
						}
						base[3] = true;
						break;
					case 4: //홈런
						for(int h=3; h>=1; h--) {
							if(base[h]) {//선수가 서있기만 하면 점수 획득
								maxSum++;
								base[h] = false;
 							}
						}
						maxSum++; //타자도 홈으로
						break;
					}
					
					if(out == 3) { //out이 세개면 이닝 끝남
						hitter = j+1;
						if(hitter == 10) {
							hitter = 1;
						}
						end = true;
						break;
					}
				}
				if(end) {
					//while문 탈출
					break;
				}
				
				//out없이 for문 탈출하면 순서를 다시 1번부터
				hitter = 1;
			}
		}
		res = Math.max(res, maxSum);
	}

}
