import java.util.*;

class Pair{
	int u;
	int wt;
	
	public Pair(int u,int wt) {
		this.u=u;
		this.wt=wt;
	}
}
public class Demo {
	public static void dfs(int src,ArrayList<ArrayList<Pair>>graph,Stack<Integer>st,boolean vis[]) {
		vis[src]=true;
		
		for(var nbr:graph.get(src)) {
			if(!vis[nbr.u]) {
				dfs(nbr.u,graph,st,vis);
			}
		}
		st.push(src);
	}
	private static void dijkstra(ArrayList<ArrayList<Pair>>graph,int dist[]){
		int parent[]=new int[dist.length];
		// parent[0]=0;
		Queue<int[]>pq=new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
		pq.add(new int[]{0,0});

		while(pq.size()>0){
			var k=pq.poll();
			int dst=k[0];
			int src=k[1];

			for(var nbr:graph.get(src)){
				int n_src=nbr.u;
				int n_dist=nbr.wt;

				if(n_dist+dst<dist[n_src]){
					parent[n_src]=src;
					dist[n_src]=n_dist+dst;
					pq.add(new int[]{dist[n_src],n_src});
				}
			}
		}
		int node=6;
		while(node!=parent[node]){
			System.out.print(node+" ");
			node=parent[node];
		}

	}
	public static void main(String[] args) throws Exception {
		//adjacency list
		int edges[][]= {{0,1,1},{0,5,1},{1,2,2},{2,3,3},{3,6,3},{5,4,2},{5,2,1},{4,3,1}};
		int n=7;
		
		//create graph from that
		ArrayList<ArrayList<Pair>>graph=new ArrayList<>();
		int dist[]=new int[n];
		
		for(int i=0;i<n;i++) {
			graph.add(new ArrayList<>());
			dist[i]=(int)1e9;
		}
		
		for(int k[]:edges) {
			int u=k[0];
			int v=k[1];
			int wt=k[2];
			
			graph.get(u).add(new Pair(v,wt));
			graph.get(v).add(new Pair(u,wt));
		}
		dist[0]=0;


		dijkstra(graph,dist);
		//find shortest distance using topo

		// Stack<Integer>st=new Stack<>();
		// boolean vis[]=new boolean[n];
		
		// for(int i=0;i<n;i++) {
		// 	if(!vis[i]) {
		// 		dfs(i,graph,st,vis);
		// 	}
		// }
		// 
		// while(!st.isEmpty()) {
		// 	int src=st.pop();

		// 	for(var nbr:graph.get(src)) {
		// 		if(dist[nbr.u]>dist[src]+nbr.wt) {
		// 			dist[nbr.u]=dist[src]+nbr.wt;
		// 		}
		// 	}
		// }
		
		// print distance
		int idx=0;
		for(int k:dist) {
			System.out.println(idx+++ " "+k);
		}
	}

}
