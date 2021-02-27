import javax.swing.*;
import java.util.Scanner;

public class ProblemOne {
    private static String alignmentA,alignmentB;
    private static int lenA,lenB,match,mismatch, gapPenalty,score=0;

    private static int[][] scoreMatrix= new int[100][100];

    public static void TakeInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter sequence A:");
        alignmentA = sc.nextLine();
        System.out.println("Enter sequence B:");
        alignmentB = sc.nextLine();
        System.out.println("Enter match premium:");
        match = sc.nextInt();
        System.out.println("Enter mismatch penalty:");
        mismatch = sc.nextInt();
        System.out.println("Enter indel/gap penalty");
        gapPenalty = sc.nextInt();
    }
    private static void ValueInitialize() {
        alignmentA = "TACGGGTAT";
        alignmentB = "GGACGTACG";
        System.out.println("Sequence A: TACGGGTAT");
        System.out.println("Sequence B: GGACGTACG");
        lenA = alignmentA.length();
        lenB = alignmentB.length();

        match = +5;mismatch = -5;gapPenalty = -5;
        System.out.println("Match Premium: +5");
        System.out.println("Mismatch & Gap penalty: -5");
    }

    public static int Similarity(char a, char b){
        if(a==b) return match;
        else return mismatch;
    }

    public static void BuildScoreMatrix(String sequenceA,String sequenceB){
        int align,delete,insert;
        int len1 = sequenceA.length();
        int len2 = sequenceB.length();
        for (int i=0; i<=len1; i++){
            scoreMatrix[i][0] = gapPenalty * i;
        }
        for(int j=0; j<=len2; j++){
            scoreMatrix[0][j] = gapPenalty * j;
        }
        for(int i=1; i<=len1; i++){
            for (int j=1; j<=len2; j++){
                align = scoreMatrix[i-1][j-1] + Similarity(sequenceA.charAt(i-1),sequenceB.charAt(j-1));
                delete = scoreMatrix[i-1][j] + gapPenalty;
                insert = scoreMatrix[i][j-1] + gapPenalty;
                scoreMatrix[i][j] = Math.max(align,Math.max(delete,insert));
            }
        }
    }


    public static void PrintScoreMatrix(String sequenceA, String sequenceB){
        int len1 = sequenceA.length();
        int len2 = sequenceB.length();
        System.out.print("\t"+"\t");
        for(int j=0; j<len2; j++){
            System.out.print(sequenceB.charAt(j)+"\t");
        }
        System.out.println();
        for(int i=0; i<=len1; i++){
            if(i==0) {
                System.out.print("\t");
            }
            if(i>0){
                System.out.print(sequenceA.charAt(i-1)+"\t");
            }
            for(int j=0; j<=len2; j++){
                System.out.print(scoreMatrix[i][j]+"\t");
            }
            System.out.println();
        }
    }


    public static void GlobalAlignment(String sequenceA, String sequenceB){
        String ansAlignA="";
        String ansAlignB="";
        int i = sequenceA.length();
        int j = sequenceB.length();
        while(i>0 || j>0){
            if(i>0 && j>0 && scoreMatrix[i][j]==scoreMatrix[i-1][j-1]+Similarity(sequenceA.charAt(i-1),sequenceB.charAt(j-1))){
                ansAlignA += sequenceA.charAt(i-1);
                ansAlignB += sequenceB.charAt(j-1);
                i--;
                j--;
            }
            else if(i>0 && scoreMatrix[i][j]==scoreMatrix[i-1][j]+gapPenalty){
                ansAlignA += sequenceA.charAt(i-1);
                ansAlignB += "_";
                i--;
            }
            else{
                ansAlignA += "_";
                ansAlignB += sequenceB.charAt(j-1);
                j--;
            }
        }

        int len1 = ansAlignA.length();
        int len2 = ansAlignB.length();
        System.out.print("Sequence A ----> ");
        for(int k=len1-1; k>=0; k--){
            System.out.print(ansAlignA.charAt(k));
        }
        System.out.println();
        System.out.print("Sequence B ----> ");
        for(int k=len2-1; k>=0; k--){
            System.out.print(ansAlignB.charAt(k));
        }
        System.out.println();
        for(int k=len1-1;k>=0;k--){
            score += Similarity(ansAlignA.charAt(k),ansAlignB.charAt(k));
        }
        System.out.println("Score value of the global alignment is: "+score);
    }

    public static void main(String[] args){
        //TakeInput();
        ValueInitialize();

        BuildScoreMatrix(alignmentA,alignmentB);
        System.out.println("Here is the table of global alignment");
        PrintScoreMatrix(alignmentA,alignmentB);

        System.out.println("Global Alignment for the sequences are:");
        GlobalAlignment(alignmentA,alignmentB);
    }
}
