package com.david.leetcode;

/**
 * dp
 * int[i][j] 相当于分别存两个字符串的子串
 * w1[i]==w2[j]:
 * 当前字符相同，当前位置不会增加距离 dp[i][j]=dp[i-1][j-1]
 * w1[i]!=w2[j]:
 * 当前字符不同经过一次变换(替换 删除/增加) 距离+1
 * dp[i][j]=min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])+1
 * <p>
 * dp[i-1][i-1] 为替换
 * dp[i-1][j] dp[i][j-1] 为i/j 删除/增加 因为w1删除相当于w2增加
 * <p>
 * \ '' h o r s e
 * '' 0 1 2 3 4 5
 * r  1
 * o  2
 * s  3
 */
public class MinDistance_72 {
    public static void main(String[] args) {
        System.out.println(new Solution().minDistance("ros", "horse"));
    }

    static class Solution {
        public int minDistance(String word1, String word2) {
            int l1 = word1.length(), l2 = word2.length();
            int[][] dp = new int[l1 + 1][l2 + 1];
            //初始化第一列  hourse -->0  编辑距离5
            for (int i = 1; i <= l1; i++) dp[i][0] = dp[i - 1][0] + 1;
            //初始化第一行 ros -->0 编辑距离3
            for (int j = 1; j <= l2; j++) dp[0][j] = dp[0][j - 1] + 1;

            for (int i = 1; i <= l1; i++) {
                for (int j = 1; j <= l2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                    else dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
            return dp[l1][l2];
        }
    }
}
