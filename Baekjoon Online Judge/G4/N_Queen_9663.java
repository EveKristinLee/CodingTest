package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class N_Queen_9663 {
	
	public static int n;
	public static int[] map;
	public static int cnt = 0;
	
	public static boolean chk(int x) {
		for(int i=0; i<x; i++) {
			if(map[i] == map[x]) {
				return false;
			}
			if(Math.abs(map[x] - map[i]) == x - i) {
				return false;
			}
		}
		return true;
	}
	
	public static void go(int idx) {
		if(idx == n) {
			cnt++;
			return;
		}
		for(int i=0; i<n; i++) {
			map[idx] = i;
			if(chk(idx)) {
				go(idx+1);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n];
		go(0);
		System.out.println(cnt);	
	}

}
