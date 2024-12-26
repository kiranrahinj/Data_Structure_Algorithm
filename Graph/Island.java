class Disjoint{
    int rank[],parent[],size[];

    public Disjoint(int n){
        rank=new int[n];
        parent=new int[n];
        size=new int[n];
        
        for(int i=0;i<n;i++){
            parent[i]=i;
            size[i]=1;
        }
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

    void unionBySize(int u,int v){
        int parent_u=findParent(u);
        int parent_v=findParent(v);

        if(parent_u==parent_v)return;

        if(size[parent_u]<size[parent_v]){
            parent[parent_u]=parent_v;
            size[parent_v]+=size[parent_u];
        }
        else{
            parent[parent_v]=parent_u;
            size[parent_u]+=size[parent_v];
        }
    }
}

public class Island{
    static int arr[][]={{0,0},{0,1},{0,3},{1,0},{1,1},{1,2},{1,3},{2,2},{3,1},{3,2}};

    public static void main(String [] args){
        int size=4;
        int n=arr.length;

        Disjoint ds=new Disjoint(size*size);
        boolean vis[][]=new boolean[n][n];
        int cnt=0;
        int ans[]=new int[n];
        int dr[]={0,0,-1,1};
        int dc[]={1,-1,0,0};
        for(int i=0;i<n;i++){
            int row=arr[i][0],col=arr[i][1];

            if(vis[row][col]){
                ans[i]=cnt;
                continue;
            }
            cnt++;
            vis[row][col]=true;
            
            for(int j=0;j<4;j++){
                int nr=row+dr[j];
                int nc=col+dc[j];

                if(nr>=0 && nr<size && nc>=0 && nc<size && vis[nr][nc]){
                    int a=nr*size+nc,b=row*size+col;
                    if(ds.findParent(a)!=ds.findParent(b)){
                        ds.unionByRank(a, b);
                        cnt--;
                    }
                }
            }
            ans[i]=cnt;
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(ans[i]);
        }

        

        // Disjoint ds=new Disjoint(size*size);
        // boolean vis[][]=new boolean[size][size];

        // int cnt=0;
        // int ans[]=new int[n];

        // for(int i=0;i<n;i++){
        //     int row=arr[i][0],col=arr[i][1];

        //     if(vis[row][col]){
        //         ans[i]=cnt;
        //         continue;
        //     }
        //     vis[row][col]=true;
        //     cnt++;

        //     int dr[]={0,0,-1,1};
        //     int dc[]={1,-1,0,0};
            
        //     for(int j=0;j<4;j++){
        //         int nr=row+dr[j];
        //         int nc=col+dc[j];

        //         if(nr>=0 && nr<size && nc>=0 && nc<size ){
        //             if(vis[nr][nc]==true){
        //                 int parent_row=row*size+col;
        //                 int parent_newRow=nr*size+nc;
        //                 if(ds.findParent(parent_row)!=ds.findParent(parent_newRow)){
        //                     cnt--;
        //                     ds.unionByRank(parent_newRow,parent_row);
        //                 }
        //             }
        //         }
        //     }
        //     ans[i]=cnt;
        // }
        // for(int i=0;i<arr.length;i++){
        //     System.out.println(ans[i]);
        // }
    }
}