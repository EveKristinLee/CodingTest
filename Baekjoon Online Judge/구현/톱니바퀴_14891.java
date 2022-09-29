package solving.solve_0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 톱니바퀴_14891 {

	//s극 1, n극 0
	//1 : 시계방향
	//-1 : 반시계방향
	static int[][] gear;
	
	static int k;
	static int[][] turn;
	static int[] d; //돌리는 방향 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[5][8];
		for(int i=1; i<=4; i++) {
			String s = br.readLine();
			for(int j=0; j<s.length(); j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}
		k = Integer.parseInt(br.readLine());
		turn = new int[k][2];
		for(int i=0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				turn[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//돌리기
		for(int i=0; i<k; i++) {
			d = new int[5];
			d[turn[i][0]] = turn[i][1];
			chk(turn[i][0]);
			turn();
		}
		
		int sum = 0;
		if(gear[1][0] == 1) sum += 1;
		if(gear[2][0] == 1) sum += 2;
		if(gear[3][0] == 1) sum += 4;
		if(gear[4][0] == 1) sum += 8;
		System.out.println(sum);
	}

	private static void turn() {
		for(int i=1; i<=4; i++) {
			if(d[i] == 1) {
				int tmp = gear[i][7];
				for(int j=7; j>=1; j--) {
					gear[i][j] = gear[i][j-1];
				}
				gear[i][0] = tmp;
			}
			else if(d[i] == -1) {
				int tmp = gear[i][0];
				for(int j=0; j<7; j++) {
					gear[i][j] = gear[i][j+1];
				}
				gear[i][7] = tmp;
			}
		}
	}

	private static void chk(int num) {
		//왼쪽 검사
		if(num != 1) {
			for(int i=num-1; i>=1; i--) {
				if(gear[i+1][6] != gear[i][2]) {
					d[i] = -d[i+1];
				}
				else {
					break; //안돌면 멈춤
				}
			}
		}
		
		//오른쪽 검사
		if(num != 4) {
			for(int i=num+1; i<=4; i++) {
				if(gear[i-1][2] != gear[i][6]) {
					d[i] = -d[i-1];
				}
				else {
					break;
				}
			}
		}
	}
}
