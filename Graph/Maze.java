import java.util.*;
class Maze{
    public static void main(String args[]){
        int arr[][]={
            {1,1,1,1},
            {1,1,0,1},
            {1,1,0,1},
            {1,1,1,0}
        };
        int start[]={0,0};
        int dest[]={3,2};
        if(Arrays.equals(start,dest))System.out.print(0);
        
        int dist[][]=new int[4][4];

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                dist[i][j]=(int)1e9;
            }
        }

        Queue<int[]>q=new PriorityQueue<>((a,b)-> a[0]-b[0]);
        q.add(new int[]{0,start[0],start[1]});
        dist[start[0]][start[1]]=0;

        int dr[]={0,0,-1,1};
        int dc[]={1,-1,0,0};

        while(!q.isEmpty()){
            var k=q.poll();
            int dst=k[0],r=k[1],c=k[2];

            for(int i=0;i<4;i++){
                int nr=dr[i]+r;
                int nc=dc[i]+c;

                if(nr>=0&&nr<4&&nc>=0&&nc<4 && arr[nr][nc]==1 && dst+1<dist[nr][nc]){
                    dist[nr][nc]=dst+1;
                    parent[nr][nc]=-1;
                    if(nr==dest[0] && nc==dest[1]){
                        System.out.print(dst+1);
                        break;
                    }
                    q.add(new int[]{dst+1,nr,nc});
                }

            }
        }
        System.out.println();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(parent[i][j]+" ");
            }
             System.out.println();
        }
    }
}