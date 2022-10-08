package solving.solve_0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class 좌표_압축_18870 {

	static int N;
	static Integer[] num;
	static Integer[] idx;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		num = new Integer[N];
		idx = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Set<Integer> set = new HashSet<Integer>(Arrays.asList(num));
		ArrayList<Integer> arr = new ArrayList<>(set);
		Collections.sort(arr);
		
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<arr.size(); i++) {
			map.put(arr.get(i), i);
		}
		for(int i=0; i<num.length; i++) {
			sb.append(map.get(num[i]) + " ");
		}
		System.out.println(sb);
	}

}
