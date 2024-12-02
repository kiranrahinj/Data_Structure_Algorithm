import java.util.*;
class Path{

    static ArrayList<ArrayList<Integer>>graph=new ArrayList<>();
    

    private static void dfs(int src,boolean vis[],ArrayList<Integer>al,ArrayList<ArrayList<Integer>>ans,String s){
        
        if(src==4){
            // ans.add(new ArrayList<>(al));
            System.out.println(s);
            return;
        }
        vis[src]=true;

        for(var nbr:graph.get(src)){
            if(!vis[nbr]){
                // al.add(nbr);
                dfs(nbr,vis,al,ans,s+nbr);
                // al.remove(al.size()-1);
            }
        }
        vis[src]=false;
    }
    public static void main(String[] args){
        int arr[][]={
            {4,3,1},{3,2,4},{3},{4}
        };

        ArrayList<ArrayList<Integer>>ans=new ArrayList<>();

        for(int i=0;i<5;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                graph.get(i).add(arr[i][j]);
            }

        }

        boolean vis[]=new boolean[5];
        ArrayList<Integer>al=new ArrayList<>();
        al.add(0);
        String s="0";
        dfs(0,vis,al,ans,s);

        // System.out.println(ans);
    }
}