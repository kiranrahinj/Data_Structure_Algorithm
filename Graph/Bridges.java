import java.util.*;
class Bridges{
    static int ans=0;
    static int timer=0;
    private static void dfs(int src,int parent,ArrayList<ArrayList<Integer>>graph,boolean vis[],int t[],int low[]){
        vis[src]=true;
        t[src]=low[src]=timer;
        timer++;

        for(var nbr:graph.get(src)){
            if(nbr==parent)continue;
            if(!vis[nbr]){
                dfs(nbr,src,graph,vis,t,low);
                low[src]=Math.min(low[src],low[nbr]);

                if(low[nbr]>t[src]){
                    ans++;
                }
            }
            else{
                low[src]=Math.min(low[src],low[nbr]);
            }
        }
    }
    public static void main(String[]args){
        int a[][]={
            {0,1},{1,2},{2,3},{3,4},{1,4},{4,5},{5,6},{6,7},{7,8},{8,9},{6,9}
        };

        boolean vis[]=new boolean[10];
        int t[]=new int[10];
        int low[]=new int[10];

        ArrayList<ArrayList<Integer>>graph=new ArrayList<>();

        for(int i=0;i<10;i++){
            graph.add(new ArrayList<>());
        }

        for(var k:a){
            graph.get(k[0]).add(k[1]);
            graph.get(k[1]).add(k[0]);
        }

        dfs(0,-1,graph,vis,t,low);
        System.out.println(ans);
    }
}