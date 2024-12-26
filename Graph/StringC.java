import java.util.*;
class StringC{
    public static int minTimeToReach(int[][] arr) {
        int row=arr.length,col=arr[0].length;
        Queue<int[]>pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        pq.add(new int[]{0,0,0});
        int dist[][]=new int[row][col];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                dist[i][j]=Integer.MAX_VALUE;
            }
        }
        dist[0][0]=0;
        int dr[]={-1,1,0,0};
        int dc[]={0,0,-1,1};

        while(pq.size()>0){
            int k[]=pq.poll();
            int dst=k[0],r=k[1],c=k[2];

            for(int i=0;i<4;i++){
                int nr=r+dr[i];
                int nc=c+dc[i];

                if(nr>=0 &&nr<row && nc>=0 &&nc<col ){
                    int nt=1+ Math.max(dst,arr[nr][nc]);
                    if(nt<dist[nr][nc]){
                        dist[nr][nc]=nt;
                        pq.add(new int[]{nt,nr,nc});
                    }
                }
            }
        }
        return dist[row-1][col-1];
    }
    public static void main(String angs[]){
        int arr[][]={{0,4},{4,4}};
        System.out.print(minTimeToReach(arr));
    }
}