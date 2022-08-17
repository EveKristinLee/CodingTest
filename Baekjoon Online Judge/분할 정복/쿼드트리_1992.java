package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 쿼드트리_1992 {
	
	static int n;
	static char[][] map;
	static String res; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		res = "";
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		makeQuad(0, 0, n);
		System.out.println(res);
	}

	private static void makeQuad(int x, int y, int N) {
		//압축 가능 여부 확인
		boolean flag = true;
		char tmp = map[x][y];
		for(int i=x; i<x+N; i++) {
			for(int j=y; j<y+N; j++) {
				if(tmp != map[i][j]) {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			res += map[x][y];
			return;
		}
		else {
			res += "(";
			//왼쪽 위
			makeQuad(x, y, N/2);
			//오른쪽 위
			makeQuad(x, y+N/2, N/2);
			//왼쪽 아래
			makeQuad(x+N/2, y, N/2);
			//오른쪽 아래
			makeQuad(x+N/2, y+N/2, N/2);
			res += ")";
		}
	}

}
