package homework;
//BOJ B2 1076 저항

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1076 {
    static Map<String, Integer> value;
    static Map<String, Integer> product;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String v1 = br.readLine();
        String v2 = br.readLine();
        String p1 = br.readLine();

        value = new HashMap<>();
        product = new HashMap<>();
        value.put("black", 0); value.put("brown", 1); value.put("red", 2); value.put("orange", 3);
        value.put("yellow", 4); value.put("green", 5); value.put("blue", 6); value.put("violet", 7);
        value.put("grey", 8); value.put("white", 9);

        product.put("black", 1); product.put("brown", 10); product.put("red", 100); product.put("orange", 1_000);
        product.put("yellow", 10_000); product.put("green", 100_000); product.put("blue", 1_000_000); product.put("violet", 10_000_000);
        product.put("grey", 100_000_000); product.put("white", 1_000_000_000);

        long res = 0;
        res += (value.get(v1) * 10);
        res += value.get(v2);
        res *= product.get(p1);

        System.out.println(res);
    }
}
