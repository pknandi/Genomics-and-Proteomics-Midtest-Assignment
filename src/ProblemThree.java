/*This is an implementation of finding the first occurrence of a string s' in Large string T
such that the hamming distance between another small string s and s' is less or equal than k
Input: Two Strings, T and s and a integer k
Output: First occurrence where given condition is true*/


import java.util.Scanner;

public class ProblemThree {
    private static String T,s,s1;
    private static int k;

    public static void TakeInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Give the long string");
        T = sc.nextLine();
        System.out.println("Give the short string");
        s = sc.nextLine();
        System.out.println("Give the value of k");
        k = sc.nextInt();
    }
    public static int HamDis(String s, String s1){
        int l1 = s.length();
        int l2 = s1.length();
        int cnt=0;
        for(int i = 0; i<l1; i++){
            if(s.charAt(i)!=s1.charAt(i)){
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args){
        TakeInput();
        int szt = T.length();
        int szs = s.length();
        for(int i = 0;i<szt-szs;i++){
            String s1 = T.substring(i,szs+i);
            int dis = HamDis(s,s1);
            if(dis<=k){
                System.out.println("First occurrence is in "+ (i+1) +"th place");
                System.out.println("The string is "+s1);
                return;
            }
        }
        System.out.println("Occurrence not found");
    }
}
