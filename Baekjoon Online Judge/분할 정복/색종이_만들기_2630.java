package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이_만들기_2630 {

	static int cntW;
	static int cntB;
	static int n;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cntW = 0;
		cntB = 0;
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		chk(0, 0, n);
		System.out.println(cntW);
		System.out.println(cntB);
	}

	private static void chk(int x, int y, int N) {
		//같은 색인지 확인
		boolean flag = true;
		int tmp = map[x][y];
		for(int i=x; i<x + N; i++) {
			for(int j=y; j<y + N; j++) {
				if(tmp != map[i][j]) {
					flag = false;
					break;
				}
			}
		}
		if(flag) {
			if(map[x][y] == 0) {
				cntW++;
			}
			else {
				cntB++;
			}
			return;
		}
		else {
			chk(x, y, N/2);
			chk(x+N/2, y, N/2);
			chk(x, y+N/2, N/2);
			chk(x+N/2, y+N/2, N/2);
		}
	}

}
