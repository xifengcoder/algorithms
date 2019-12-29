package com.algorithms.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xifeng.yang on 2019/12/29
 */
public class KnapSack {
    public int knapSack(int[] v, int[] w, int n, int W, Map<String, Integer> lookup) {
        if (W < 0) {
            return Integer.MIN_VALUE;
        }

        if (n < 0 || W == 0) {
            return 0;
        }

        String key = n + "|" + W;

        if (!lookup.containsKey(key)) {
            int include = v[n] + knapSack(v, w, n - 1, W - w[n], lookup);
            int exclude = knapSack(v, w, n - 1, W, lookup);
            lookup.put(key, Integer.max(include, exclude));
        }

        return lookup.get(key);
    }

    public int knapSack(int[] v, int[] w, int W) {
        int[][] T = new int[v.length + 1][W + 1];

        for (int i = 1; i <= v.length; i++) {
            for (int j = 0; j <= W; j++) {
                if (w[i - 1] > j) {
                    T[i][j] = T[i - 1][j];
                } else {
                    T[i][j] = Integer.max(T[i - 1][j],
                            T[i - 1][j - w[i - 1]] + v[i - 1]);
                }
            }
        }

        return T[v.length][W];
    }

    public static void main(String[] args) {
        int[] v = {20, 5, 10, 40, 15, 25};
        int[] w = {1, 2, 3, 8, 7, 4};

        Map<String, Integer> lookup = new HashMap<>();
        int W = 10;
        int result = 0;
        KnapSack knapSack = new KnapSack();
        result = knapSack.knapSack(v, w, W);
        result = knapSack.knapSack(v, w, v.length - 1, W, lookup);
        System.out.println("result = " + result);
    }
}
