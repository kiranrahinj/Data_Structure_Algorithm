// q-> minimum effort to travel from one corner to other

import java.util.*;
class MinEffort{
    public static void main(String[] args) throws Exception {
		//adjacency matrix
		int arr[][]={
            {1,2,2},
            {1,8,2},
            {1,2,1}
        };

        int start[]={0,0};
        int dest[]={2,2};

        int dist[][]=new int[4][4];

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                dist[i][j]=(int)1e9;
            }
        }
        int dr[]={0,0,-1,1};
        int dc[]={1,-1,0,0};

        Queue<int[]>q=new PriorityQueue<>((a,b)-> a[0]-b[0]);
        dist[0][0]=0;
        q.add(new int[]{0,0,0});
        while(q.size()>0){
            int k[]=q.poll();
            int diff=k[0];
            int r=k[1];
            int c=k[2];
            
            if(r==2 && c==2){
                System.out.print(diff);
                break;
            }

            for(int i=0;i<4;i++){
                int nr=dr[i]+r;
                int nc=dc[i]+c;

                if(nr>=0&& nr<3 && nc>=0&&nc<3){
                    int effort=Math.max(Math.abs(arr[nr][nc]-arr[r][c]),diff);

                    if(dist[nr][nc]>effort){
                        dist[nr][nc]=effort;
                        q.add(new int[]{effort,nr,nc});
                    }
                }
            }    
        }
    }
}