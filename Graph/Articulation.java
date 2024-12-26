import java.util.*;
 class Articulation{
    private static int timer=0;
    private static void dfs(int src,int parent,ArrayList<ArrayList<Integer>>graph,boolean vis[],int tin[],int low[],int mark[]){
        vis[src]=true;
        tin[src]=low[src]=timer;
        timer++;
        int child=0;
        for(var nbr:graph.get(src)){
            if(parent==nbr)continue;

            if(!vis[nbr]){
                dfs(nbr,src,graph,vis,tin,low,mark);
                low[src]=Math.min(low[src],low[nbr]);

                if(low[nbr]>=tin[src] && parent!=-1){
                    mark[src]=1;
                }
                child++;
            }
            else{
                low[src]=Math.min(low[src],tin[nbr]);
            }
        }
        if(child>1 && parent==-1){
            mark[src]=1;
        }
    }
    public static void main(String[] args){
        int arr[][]={
            {0,1},{1,2},{2,3},{3,4},{4,1},{1,5},{5,6},{6,7},{7,5}
        };
        int n=8;
        boolean vis[]=new boolean[n];
        int tin[]=new int[n];
        int low[]=new int[n];
        int mark[]=new int[n];

        ArrayList<ArrayList<Integer>>graph=new ArrayList<>();

        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }

        for(var k:arr){
            graph.get(k[0]).add(k[1]);
            graph.get(k[1]).add(k[0]);
        }
        dfs(0,-1,graph,vis,tin,low,mark);

        ArrayList<Integer>ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(mark[i]==1){
                ans.add(i);
            }
        }
        System.out.println(ans);
    }
}