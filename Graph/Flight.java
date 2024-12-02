//q-> to reach at dest with min stops
import java.util.*;
class Flight{
    public static void main(String[] args) throws Exception {
        int arr[][]={
            {0,1,100},
            {1,2,100},
            {1,3,600},
            {2,0,100},
            {2,3,200}
        };
        int stops=3;
        int dest=3;
        ArrayList<ArrayList<Pair>>graph=new ArrayList<>();
        int dist[]=new int[4];
        for(int i=0;i<4;i++){
            graph.add(new ArrayList<>());
            dist[i]=(int)1e9;
        }

        for(int k[]:arr){
            int u=k[0],v=k[1],wt=k[2];
            graph.get(u).add(new Pair(v,wt));
        }
        //stop, src ,dist
        Queue<int[]>q=new LinkedList<>();
        dist[0]=0;
        q.add(new int[]{0,0,0});

        while(q.size()>0){
            int k[]=q.poll();

            int stop=k[0],src=k[1],dst=k[2];

            if(stop>=stops)continue;

            for(Pair k1:graph.get(src)){
                int nbr=k1.u;
                int wt=k1.wt;

                if(dst+wt<dist[nbr]){
                    dist[nbr]=dst+wt;
                    q.add(new int[]{stop+1,nbr,dist[nbr]});
                }
            }
        }
        
        System.out.print(dist[dest]);
    }  
}
class Pair{
    int u;
    int wt;

    public Pair(int u,int wt){
        this.u=u;
        this.wt=wt;
    }
}