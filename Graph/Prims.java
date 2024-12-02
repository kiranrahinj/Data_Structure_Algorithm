import java.util.*;

class Pair{
    int u;
    int wt;

    public Pair(int u,int wt){
        this.u=u;
        this.wt=wt;
    }
}

class Prims{
    public static void main(String args[]){
        int arr[][]={
            {0,1,100},
            {1,2,10},
            {1,3,600},
            {2,0,100},
            {2,3,100}
        };
        ArrayList<ArrayList<Pair>>graph=new ArrayList<>();
        for(int i=0;i<4;i++){
            graph.add(new ArrayList<>());
        }

        for(int k[]:arr){
            int u=k[0],v=k[1],wt=k[2];
            graph.get(u).add(new Pair(v,wt));
            graph.get(v).add(new Pair(u,wt));
        }

        Queue<int[]>pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        boolean vis[]=new boolean[4];
        pq.add(new int[]{0,0});

        int ans=0;
        while(pq.size()>0){
            int k[]=pq.poll();
            int wt=k[0];
            int src=k[1];

            if(vis[src])continue;
            ans+=wt;
            vis[src]=true;

            for(var nbr:graph.get(src)){
                if(!vis[nbr.u]){
                    pq.add(new int[]{nbr.wt,nbr.u});
                }
            }
        }
        System.out.print(ans);
    }
}