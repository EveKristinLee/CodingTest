package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

//SWEA 모의
public class 보물상자_비밀번호_5658 {
	
	static int N; //숫자의 개수
	static int K; //크기 순서
	static String in;
	static Set<String> s;
	static String[] nums;
	static List<Integer> li;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			in = br.readLine();
			
			nums = new String[4];
			
			s = new HashSet<>();
			String tmp = in;
			while(true) {
				nums = getNums(tmp, N/4);
				boolean isStop = true;
				for(int i=0; i<4; i++) {
					if(!s.contains(nums[i])) {
						isStop = false;
						s.add(nums[i]);
					}
				}
				if(isStop) {
					break;
				}
				tmp = rotate(tmp);
			}
			
			li = new ArrayList<>();
			for (String string : s) {
				li.add(Integer.parseInt(string, 16));
			}
			Collections.sort(li, Collections.reverseOrder());
			sb.append("#"+t+" "+li.get(K-1)+"\n");
		}
		System.out.println(sb);
	}

	private static String[] getNums(String s, int len) {
		String[] res = new String[4];
		int idx = 0;
		for(int i=0; i<s.length(); i+=len) {
			String tmp = "";
			for(int j=i; j<i+len; j++) {
				tmp += s.charAt(j);
			}
			res[idx++] = tmp;
		}
		return res;
	}

	private static String rotate(String s) {
		String tmp = "";
		for(int i=1; i<s.length(); i++) {
			tmp += s.charAt(i);
		}
		tmp += s.charAt(0);
		return tmp;
	}
}
