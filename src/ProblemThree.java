import java.util.Scanner;

public class ProblemThree {
    private static String T,s,s1;
    private static int k;
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Give the long string");
        T = sc.nextLine();
        System.out.println("Give the short string");
        s = sc.nextLine();
        System.out.println("Give the value of k");
        k = sc.nextInt();
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
