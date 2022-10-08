package solving.solve_0830;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 별_찍기_10_2447 {

	static int N;
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = ' ';
			}
		}
		dfs(N, 0, 0);
		
		for(int i=0; i<N; i++) {
			bw.write(map[i]);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

	private static void dfs(int n, int x, int y) {
		if(n == 1) {
			map[x][y] = '*';
			return;
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i==1 && j==1) {
					continue;
				}
				dfs(n/3, x+i*(n/3), y+j*(n/3));
			}
		}
	}

}
