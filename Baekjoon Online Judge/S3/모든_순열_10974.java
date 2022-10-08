package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 모든_순열_10974 {

	static int n;
	static Integer[] num;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new Integer[n];
		visit = new boolean[n+1];
		
		perm(0);
	}

	private static void perm(int cnt) {
		if(cnt == n) {
			for (Integer i : num) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=1; i<=n; i++) {
			if(!visit[i]) {
				num[cnt] = i;
				visit[i] = true;
				perm(cnt+1);
				visit[i] = false;
			}
		}
	}
}
