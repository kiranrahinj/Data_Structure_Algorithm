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

public class Demo_Disjoint{

    public static void main(String args[]){
        Disjoint ds=new Disjoint(6);

        ds.unionByRank(0,1);
        ds.unionByRank(1,2);
        ds.unionByRank(2,3);

        if(ds.parent[0]==ds.parent[3]){
            System.out.println("same");
        }
        else{
            System.out.println("Not_same");
        }

        ds.unionByRank(3,4);
        ds.unionByRank(4,5);

        if(ds.parent[0]==ds.parent[5]){
            System.out.println("same");
        }
        else{
            System.out.println("Not_same");
        }
    }
}