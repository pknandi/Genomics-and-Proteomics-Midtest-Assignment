import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DeBruijnAlgo {
    public static int n=0,totalEdge=0,k,u,v;
    public static String kMer,leftKMer,rightKMer;
    public static ArrayList<String> spectrum = new ArrayList<>();
    public static HashSet<String> smallMerSet = new HashSet<>();
    public static ArrayList<String> smallMerList;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void BuildGraph(ArrayList<ArrayList<Integer>> G,int n){
        G.clear();
        for(int j = 0; j<n+5; j++){
            G.add(new ArrayList<>());
        }
        for(int j = 0; j<n; j++) {
            kMer = spectrum.get(j);
            String[] ans = getTwoMer(kMer,k);
            leftKMer = ans[0];
            rightKMer = ans[1];
            u = smallMerList.indexOf(leftKMer);
            v = smallMerList.indexOf(rightKMer);
            G.get(u).add(v);
        }
    }

    public static void FindEuler(Integer v, ArrayList<ArrayList<Integer>> G, List<Integer> path){
        path.add(v);
        int sze = G.get(v).size();
        //System.out.println(v+"---->"+smallMerList.get(v)+"---->"+sze+"---->"+path.size());
        if(sze==0){
            if(path.size()-1==totalEdge){
                String ans = smallMerList.get(path.get(0));
                for(int i=1;i<path.size();i++){
                    String cur = smallMerList.get(path.get(i));
                    ans += cur.charAt(1);
                }
                System.out.println("The Superstring is: "+ans);
            }
        }
        for(int i = 0;i<G.get(v).size();i++){
            Integer cur = G.get(v).get(i);
            //System.out.println(cur+"-------");
            G.get(v).remove(cur);
            FindEuler(cur,G,path);
        }
    }

    public static void printGraph(ArrayList<ArrayList<Integer>> graph){
        for (int i = 0; i < graph.size(); i++) {
            System.out.println("\nAdjacency list of vertex" + i);
            for (int j = 0; j < graph.get(i).size(); j++) {
                System.out.print(" -> "+graph.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static String[] getTwoMer(String kMer, int k){
        String[] ans = new String[2];
        String leftKMer = Character.toString(kMer.charAt(0));

        for (int j = 1; j < k - 1; j++) {
            leftKMer += kMer.charAt(j);
        }
        ans[0] = leftKMer;
        String rightKMer = Character.toString(kMer.charAt(1));
        for (int j = 2; j < k; j++) {
            rightKMer += kMer.charAt(j);
        }
        ans[1] = rightKMer;
        smallMerSet.add(leftKMer);
        smallMerSet.add(rightKMer);

        return ans;
    }

    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Give the number of k-mer in the spectrum:");
        n = sc.nextInt();
        System.out.println("Give the value of k");
        k=sc.nextInt();

        totalEdge=n;
        sc.nextLine();
        System.out.println("Give the "+ n +" k-mers");
        for(int i = 0; i<n; i++){
            spectrum.add(sc.nextLine());
        }

        for(int i = 0; i<n; i++) {
            kMer = spectrum.get(i);
            getTwoMer(kMer,k);
        }

        int setSize = smallMerSet.size();
        smallMerList = new ArrayList<>(smallMerSet);

        for(int i = 0; i<setSize; i++){
            List<Integer> path = new ArrayList<>();
            BuildGraph(graph,n);
            FindEuler(i,graph,path);
        }
    }
}
