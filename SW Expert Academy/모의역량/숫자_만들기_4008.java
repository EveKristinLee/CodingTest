package solving.solve_A형보충;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자_만들기_4008 {

	static int res;
	static int N;
	static int[] op; // +, -, *, /
	static int[] num;
	static int[] opList; //0:+, 1:-, 2:*, 3:/
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			op = new int[4];
			num = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			opList = new int[N-1];
			int idx = 0;
			for(int i=0; i<4; i++) {
				while(op[i] > 0) {
					opList[idx] = i;
					idx++;
					op[i]--;
				}
			}
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			Arrays.sort(opList);
			do {
				int calcRes = calc(num, opList);
				min = Math.min(min, calcRes);
				max = Math.max(max, calcRes);
			}while(np(opList));
			
			res = max - min;
			
			sb.append("#"+t+" " + res+"\n");
		}
		System.out.println(sb);
	}

	private static int calc(int[] num, int[] opList) {
		int sum = num[0];
		for(int i=0; i<N-1; i++) {
			switch (opList[i]) {
			case 0:
				sum += num[i+1];
				break;
			case 1:
				sum -= num[i+1];
				break;
			case 2:
				sum *= num[i+1];
				break;
			case 3:
				sum /= num[i+1];
				break;
			default:
				break;
			}
		}
		return sum;
	}

	private static boolean np(int[] num) {
		int i = N-2;
		while(i>0 && num[i-1] >= num[i]) --i;
		if(i == 0) return false;
		int j = N-2;
		while(num[i-1]>= num[j]) --j;
		swap(i-1, j, num);
		int k = N-2;
		while(i<k) {
			swap(i++, k--, num);
		}
		return true;
	}

	private static void swap(int i, int j, int[] num) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}
}
