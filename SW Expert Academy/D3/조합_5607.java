package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA D3
public class 조합_5607 {

	static int N;
	static int R;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			long res = getFacto(N);
			long a = (getFacto(R) * getFacto(N-R)) % 1234567891;
			res *= pow(a, 1234567891 - 2);
			sb.append("#"+t+" "+res % 1234567891+"\n");
		}
		System.out.println(sb);
	}
	
	private static long pow(long num, int r) {
		if(r == 1) {
			return num;
		}
		if(r == 0) {
			return 1;
		}
		if(r % 2 == 0) { //짝수면
			long tmp = pow(num, r/2);
			return tmp * tmp % 1234567891;
		}
		else {
			long tmp = pow(num, r-1);
			return num * tmp % 1234567891;
		}
	}

	private static long getFacto(int num) {
		long res = 1; 
		for(int i=2; i<=num; i++) {
			res = (res * i) % 1234567891;
		}
		return res % 1234567891;
	}
}
