//q-> Number of ways to reach particular destination with min sum
import java.util.*;
class Pair{
    int u;int wt;

    public Pair(int u,int wt){
        this.u=u;
        this.wt=wt;
    }
}
class Number{
    public static void main(String[] args) throws Exception {
		//adjacency matrix
		int arr[][]={
            {1,2,0},
            {2,7,3},
            {1,3,2},
            {3,7,2},
            {3,4,1},
            {4,7,2},
            {1,6,3},
            {6,7,1},
            {1,5,2},
            {5,6,0}
        };

        ArrayList<ArrayList<Pair>>graph=new ArrayList<>();
        int dist[]=new int[7];
        int ways[]=new int[7];

        for(int i=0;i<7;i++){
            graph.add(new ArrayList<>());
            dist[i]=(int)1e9;
            ways[i]=0;
        }

        for(var k:arr){
            int u=k[0],v=k[1],wt=k[2];
            graph.get(u-1).add(new Pair(v-1,wt));
        }
        ways[0]=1;

        Queue<int[]>q=new PriorityQueue<>((a,b)-> a[0]-b[0]);
        q.add(new int[]{0,0});
        dist[0]=0;

        while(q.size()>0){
            int k[]=q.poll();
            int src=k[1];
            int wt=k[0];

            for(var temp:graph.get(src)){
                int nbr=temp.u;
                int wt2=temp.wt;

                if(wt+wt2<dist[nbr]){
                    dist[nbr]=wt+wt2;
                    q.add(new int []{dist[nbr],nbr});
                    ways[nbr]=ways[src];
                }
                else if(wt+wt2==dist[nbr]){
                    ways[nbr]+=ways[src];
                }
            }
        }
        System.out.print(ways[6]);

    }    
}