package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이_2563 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[101][101]; //도화지
		int n = Integer.parseInt(br.readLine()); 
		for(int c=0; c<n; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int i=x; i<x+10; i++) {
				for(int j=y; j<y+10; j++) {
					if(board[i][j] != 1) {
						board[i][j] = 1; 	//도화지 위에 색종이 영역 그리기						
					}
				}
			}
		}
		int size = 0;
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				if(board[i][j] == 1) {
					size++;
				}
			}
		}
		System.out.println(size);
	}
}
