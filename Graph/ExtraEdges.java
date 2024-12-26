import java.util.*;

class Disjoint{
    int rank[];
    int parent[];
    public Disjoint(){};

    public Disjoint(int n){
        rank=new int[n];
        parent=new int[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }

    public int findUParent(int node){
        if(node==parent[node])return node;
        return parent[node]=findUParent(parent[node]);
    }

    public void unionByRank(int u,int v){
        int parent_u=findUParent(u);
        int parent_v=findUParent(v);

        if(parent_u==parent_v)return;

        if(rank[parent_u]<rank[parent_v]){
            parent[parent_u]=parent_v;
        }
        else if(rank[parent_u]>rank[parent_v]){
            parent[parent_v]=parent_u;
        }
        else{
            parent[parent_v]=parent_u;
            rank[parent_u]++;
        }
    }
}
class ExtraEdges{
    public static void main(String args[]){
        int arr[][]={
            {0,1,100},
            {1,2,10},
            {1,3,600},
            {2,0,100},
            {2,3,100}
        };
        int cnt=0;

        Disjoint ds=new Disjoint(4);
        for(var k:arr){
            int u=k[0];
            int v=k[1];

            if(ds.findUParent(u)==ds.findUParent(v)){
                cnt++;
            }
            else{
                ds.unionByRank(u,v);
            }
        }

        int comp=0;
        for(int i=0;i<4;i++){
            if(ds.parent[i]==i){
                comp++;
            }
        }
        System.out.println(comp+" "+cnt);
    }
}