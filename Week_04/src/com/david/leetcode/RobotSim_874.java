package com.david.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RobotSim_874 {
    public static void main(String[] args) {
        System.out.println(new Solution().robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{{2, 4}}));
    }

    /**
     * 压缩写法 使用Stream 变慢
     */
    static class Solution1 {
        public int robotSim(int[] commands, int[][] obstacles) {
            int[][] ds = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//1.定义4个方向
            Set<String> obs = Arrays.stream(obstacles).map(a -> a[0] + " " + a[1]).collect(Collectors.toSet());//2.在、障碍物编码存入set
            int x = 0, y = 0, d = 0, res = 0;//定义坐标 方向
            for (int c : commands) { //3.遍历命令
                if (c == -1) d = (d + 1) % 4;
                else if (c == -2) d = (d + 3) % 4;
                else while (c-- > 0 && !obs.contains((x + ds[d][0]) + " " + (y + ds[d][1]))) {
                        x += ds[d][0];
                        y += ds[d][1];
                        res = Math.max(res, x * x + y * y);
                    }
            }
            return res;
        }
    }

    /**
     * 一般写法
     */
    static class Solution {
        public int robotSim(int[] commands, int[][] obstacles) {
            //1.定义4个方向
            int[][] direcs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            //2.在、障碍物编码存入set
            HashSet<String> obs = new HashSet<>();
            for (int[] obstacle : obstacles) {
                obs.add(obstacle[0] + " " + obstacle[1]);
            }
            int x = 0, y = 0, direc = 0, result = 0;//声明当前坐标，方向，结果
            //3.遍历命令
            for (int command : commands) {
                if (command == -1) {//右转
                    direc = (direc + 1) % 4;
                } else if (command == -2) {//左转
                    direc = (direc + 3) % 4;
                } else {
                    while (command-- > 0 && !obs.contains((x + direcs[direc][0]) + " " + (y + direcs[direc][1]))) {//前进一步不是障碍就前进 否则退出
                        x += direcs[direc][0];
                        y += direcs[direc][1];
                        result = Math.max(result, x * x + y * y);
                    }
                }
            }
            return result;
        }
    }
}
