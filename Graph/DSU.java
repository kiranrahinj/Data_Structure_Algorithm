import java.util.*;
class DisjointSet{
    int rank[],parent[];
    
    public DisjointSet(int n){
        rank=new int[n];
        parent=new int[n];
        
        for(int i=0;i<n;i++)parent[i]=i;
    }
    
    int findParent(int node){
        if(node==parent[node])return node;
        return parent[node]=findParent(parent[node]);
    }
    
    void unionByRank(int u,int v){
        int parent_u=findParent(u);
        int parent_v=findParent(v);
        
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


class Solution {

    public static List<List<String>> mergeDetails(List<List<String>> arr) {
        int n=arr.size();
        
        DisjointSet ds=new DisjointSet(n);
        
        Map<String,Integer>hm=new HashMap<>();
        
        for(int i=0;i<n;i++){
            for(int j=1;j<arr.get(i).size();j++){
                String word=arr.get(i).get(j);
                if(hm.containsKey(word)==false){
                    hm.put(word,i);
                }
                else{
                    ds.unionByRank(i,hm.get(word));
                }
            }
        }
        
        List<String> mails[]=new ArrayList[n];
        
        for(int i=0;i<n;i++)mails[i]=new ArrayList<>();
        
        for(var k:hm.keySet()){
            int node=ds.findParent(hm.get(k));
            mails[node].add(k);
        }
        
        List<List<String>>ans=new ArrayList<>();
        
        for(int i=0;i<n;i++){
            if(mails[i].size()==0)continue;
            Collections.sort(mails[i]);
            List<String>temp=new ArrayList<>();
            temp.add(arr.get(i).get(0));
            for(var k:mails[i]){
                temp.add(k);
            }
            ans.add(new ArrayList<>(temp));
        }
        return ans;
    }
}

public class DSU{
    public static void main(String args[]){
        String arr[][]={
            {"John","johnsmith@mail.com","john_newyork@mail.com"},
            {"John","johnsmith@mail.com","john00@mail.com"},
            {"Mary","mary@mail.com"},
            {"John","johnnybravo@mail.com"}
        };
        List<List<String>>al=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            ArrayList<String> temp=new ArrayList<>();
            for(int j=0;j<arr[i].length;j++){
                temp.add(arr[i][j]);
            }
            al.add(new ArrayList<>(temp));
        }
        System.out.println(Solution.mergeDetails(al));
    }
}