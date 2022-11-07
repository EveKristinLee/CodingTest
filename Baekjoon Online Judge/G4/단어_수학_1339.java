package solving.solve_1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//BOJ G4
public class 단어_수학_1339 {

	static int N;
	static int[] alpha;
	static String[] s;
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		s = new String[N];
		alpha = new int[27];
		res = 0;
		
		for(int i=0; i<N; i++) {
			s[i] = br.readLine();
		}
		
		for(int i=0; i<N; i++) {
			int tmp = (int)Math.pow(10, s[i].length()-1);
			for(int j=0; j<s[i].length(); j++) {
				alpha[s[i].charAt(j) - 'A'] += tmp;
				tmp /= 10;
			}
		}
		
		Arrays.sort(alpha);
		int idx = 9;
		for(int i=alpha.length-1; i>=0; i--) {
			if(alpha[i] ==0) {
				break;
			}
			res += (idx * alpha[i]);
			idx--;
		}
		
		System.out.println(res);
	}
}
