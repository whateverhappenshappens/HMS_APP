package com.yashaswi.Hospital.Management;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Dummy {
    static String nameOk(String first,String second){
        int len1=first.length();
        int len2=second.length();
        List<String> str1=new ArrayList<>();
        List<String> str2=new ArrayList<>();

        for(int i=0;i<first.length();i++){
            for(int j=i+1;j<first.length();j++){
                str1.add(first.substring(i,j));
            }
        }
        for(int i=0;i<second.length();i++){
            for(int j=i+1;j<second.length();j++){
                str2.add(first.substring(i,j));
            }
        }
        for(String s:str1){
           for(String s2:str2){
               if(s.equals(s2)){
                   return "YES";
               }
           }
        }
        return "NO";
    }
    public static long goodArray(List<List<Integer>> arr) {
        int n = arr.size();
        int b = arr.get(0).get(1); // Cost b for an operation
        int[] h = new int[n];
        int[] cost = new int[n];

        for (int i = 0; i < n; i++) {
            h[i] = arr.get(i).get(0); // Height
            cost[i] = arr.get(i).get(1); // Operation cost
        }

        long[][] dp = new long[n][2];
        dp[0][0] = 0;
        dp[0][1] = b;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + Math.abs(h[i - 1] - 1));
            dp[i][1] = Math.max(dp[i - 1][0] + cost[i], dp[i - 1][1] + Math.abs(h[i - 1] - h[i]) + cost[i]);
        }

        long result = Math.max(dp[n - 1][0], dp[n - 1][1] + Math.abs(h[n - 1] - 1));
        return result;
    }

    public static void main(String[] args) {

        List<List<Integer>> arr = new ArrayList<>();
        arr.add(List.of(2, 3));
        arr.add(List.of(2, 10));
        arr.add(List.of(2, 6));
        System.out.println(goodArray(arr));
}
}






