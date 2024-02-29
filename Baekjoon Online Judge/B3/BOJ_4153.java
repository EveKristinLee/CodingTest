//BOJ B3 4153 직각삼각형

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_4153 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[3];
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<3; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            if(nums[0] == 0) {
                break;
            }
            Arrays.sort(nums);

            if((nums[0] * nums[0]) + (nums[1] * nums[1]) == (nums[2] * nums[2])) {
                System.out.println("right");
            }
            else{
                System.out.println("wrong");
            }
        }
    }
}
