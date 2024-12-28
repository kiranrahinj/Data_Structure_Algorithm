package DP;
import java.util.*;
class DP {
    //Fibonacci
    public static int fib(int n){
        if(n==0 || n==1)return n;

        return fib(n-1)+fib(n-2);
    }
    public static int Memo(int n,int []dp){
        if(n==0 || n==1)return dp[n]=n;
        if(dp[n]!=-1)return dp[n];

        return dp[n]=Memo(n-1,dp)+Memo(n-2, dp);
    }
    public static int Tab(int n,int dp[]){
        dp[0]=0;
        dp[1]=1;

        for(int i=2;i<n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static int Tab2(int N,int []dp){
        for(int n=0;n<=N;n++){
            if(n==0 || n==1){
                dp[n]=n; 
                continue; 
            }
            // if(dp[n]!=-1)return dp[n]; 
    
            dp[n]=dp[n-1]+dp[n-2];
            continue;
        }
        return dp[N];
    }

    // Possible ways to reach at end
    public static int Ways(int idx,int arr[]){
        if(idx==arr.length){
            return 1;
        }
        int ans=0;
        for(int jump=1;jump<=arr[idx];jump++){
            if(idx+jump<=arr.length){
                ans+=Ways(idx+jump, arr);
            }
        }
        return ans;
    }

    public static int Ways_Memo(int idx,int arr[],int dp[]){
        if(idx==arr.length){
            return dp[idx]=1;
        }

        if(dp[idx]!=0)return dp[idx];

        int ans=0;
        for(int jump=1;jump<=arr[idx];jump++){
            if(idx+jump<=arr.length){
                ans+=Ways(idx+jump, arr);
            }
        }
        return dp[idx]=ans;
    }

    public static int Ways_Tab(int arr[],int dp[]){
        
        for(int idx=arr.length;idx>=0;idx--){

            if(idx==arr.length){
                dp[idx]=1;
                continue;
            }
            // if(dp[idx]!=0)return dp[idx];

            int ans=0;
            for(int jump=1;jump<=arr[idx];jump++){
                if(idx+jump<=arr.length){
                    ans+=dp[idx+jump];
                }
            }
            dp[idx]=ans;
            continue;
        }
        return dp[0];
    }

    //Stickler Theif
    public static int rec(int idx, int arr[]){
        if(idx==arr.length)return 0;
        
        int rob=arr[idx];
        int not_rob=0;

        if(idx+2<arr.length){
            rob+=rec(idx+2,arr);
        }
        not_rob=rec(idx+1, arr);

        return Math.max(rob,not_rob);
    }

    public static int Memo(int idx, int arr[],int dp[]){
        if(idx==arr.length)return 0;

        if(dp[idx]!=0)return dp[idx];
        
        int rob=arr[idx];
        int not_rob=0;

        if(idx+2<arr.length){
            rob+=rec(idx+2,arr);
        }
        not_rob=rec(idx+1, arr);

        return dp[idx]=Math.max(rob,not_rob);
    }

    public static int Tabulation(int idx,int arr[],int dp[]){

        for(idx=arr.length;idx>=0;idx--){
            if(idx==arr.length){
                dp[idx]=0;
                continue;
            }

            int rob=arr[idx];
            int not_rob=0;

            if(idx+2<=arr.length){
                rob+=dp[idx+2];
            }
            not_rob=dp[idx+1];

            dp[idx]=Math.max(rob,not_rob);
        }

        return dp[0];
    }
    
    //Min cost to climb stairs
    public static int rec_climb(int idx,int arr[]){

        if(idx==arr.length){
            return 0;
        }

        int oneStep=rec_climb(idx+1, arr);
        int twoStep=Integer.MAX_VALUE;

        if(idx+2<=arr.length){
            twoStep=rec_climb(idx+2, arr);
        }
        return arr[idx]+Math.min(oneStep,twoStep);
    }

    public static int memo_climb(int idx,int arr[],int memo[]){
        if(idx==arr.length){
            return 0;
        }
        if(memo[idx]!=0)return memo[idx];

        int oneStep=rec_climb(idx+1, arr);
        int twoStep=Integer.MAX_VALUE;

        if(idx+2<=arr.length){
            twoStep=rec_climb(idx+2, arr);
        }
        return memo[idx]=arr[idx]+Math.min(oneStep,twoStep);
    }
    public static int tab_climb(int idx,int arr[],int dp[]){
       
        for(idx=arr.length;idx>=0;idx--){
            if(idx==arr.length){
                dp[idx]= 0;
                continue;
            }
    
            int oneStep=dp[idx+1];
            int twoStep=Integer.MAX_VALUE;
    
            if(idx+2<=arr.length){
                twoStep=dp[idx+2];
            }
            dp[idx]=arr[idx]+Math.min(oneStep,twoStep);
        }
        return dp[0];
    }
    //this is like the fib code bcz these are depend on there p+1 and p+2 val
    public static int simple(int arr[]){
        int n=arr.length;
        int idxp1=arr[n-1];
        int idxp2=0;

        for(int idx=n-2;idx>=0;idx--){
            int ansIdx=arr[idx]+Math.min(idxp1,idxp2);

            idxp2=idxp1;
            idxp1=ansIdx;
        }

        return Math.min(idxp1,idxp2);
    }

    //Gold mine problem
    private static int get_max(int arr[][],int i,int j){
        if(j==arr[0].length-1){
            return arr[i][j];
        }    
        int up=0;
        int same=0;
        int down=0;

        if(i-1>=0){
            up=get_max(arr, i-1, j+1);
        }
        same=get_max(arr, i, j+1);

        if(i+1<arr.length){
            down=get_max(arr, i+1, j+1);
        }
        return arr[i][j]+Math.max(up,Math.max(same,down));        
    }
    private static int get_max_memo(int arr[][],int i,int j,int dp[][]){
        if(j==arr[0].length-1){
            return dp[i][j]=arr[i][j];
        }  
        if(dp[i][j]!=0)return dp[i][j];  
        int up=0;
        int same=0;
        int down=0;

        if(i-1>=0){
            up=get_max(arr, i-1, j+1);
        }
        same=get_max(arr, i, j+1);

        if(i+1<arr.length){
            down=get_max(arr, i+1, j+1);
        }
        return dp[i][j]=arr[i][j]+Math.max(up,Math.max(same,down));        
    }
    public static int tab_mines(int arr[][],int dp[][]){
        for(int j=arr[0].length-1;j>=0;j--){
            for(int i=0;i<arr.length;i++){
                if(j==arr[0].length-1){
                    dp[i][j]=arr[i][j];
                    continue;
                }   
                int up=0;
                int same=0;
                int down=0;
        
                if(i-1>=0){
                    up=dp[i-1][j+1];//get_max(arr, i-1, j+1);
                }
                same=dp[i][j+1];//get_max(arr, i, j+1);
        
                if(i+1<arr.length){
                    down=dp[i+1][j+1];//get_max(arr, i+1, j+1);
                }
                dp[i][j]=arr[i][j]+Math.max(up,Math.max(same,down));
            }
        }
        int ans=0;
        for(int i=0;i<arr.length;i++){
            ans=Math.max(dp[i][0],ans);
        }
        return ans;
    }
    public static int rec_mine(int arr[][],int dp[][]){
        int ans=0;
        int n=arr.length;

        for(int i=0;i<n;i++){
            int temp=get_max_memo(arr,i,0,dp);
            ans=Math.max(ans,temp);
        }
        return ans;
    }
    
    //minimum path sum
    public static int rec_min(int i,int j,int arr[][]){ 
        if(i==arr.length-1 && j==arr[0].length-1){
            return arr[i][j];
        }
        int right=Integer.MAX_VALUE;
        int down=right;

        if(i+1<arr.length){
            down=rec_min(i+1,j,arr);
        }
        if(j+1<arr[0].length){
            right=rec_min(i,j+1,arr);
        }
        return arr[i][j]+Math.min(right,down);
    }
    public static int memo_min(int i,int j,int arr[][],int dp[][]){
        if(i==arr.length-1 && j==arr[0].length-1){
            return dp[i][j]=arr[i][j];
        }
        if(dp[i][j]!=0)return dp[i][j];

        int right=Integer.MAX_VALUE;
        int down=right;

        if(i+1<arr.length){
            down=rec_min(i+1,j,arr);
        }
        if(j+1<arr[0].length){
            right=rec_min(i,j+1,arr);
        }
        return dp[i][j]=(arr[i][j]+Math.min(right,down));
    }
    public static int tab_min(int i,int j,int arr[][],int dp[][]){
        for(i=arr.length-1;i>=0;i--){
            for(j=arr[0].length-1;j>=0;j--){
                if(i==arr.length-1 && j==arr[0].length-1){
                    dp[i][j]=arr[i][j];
                    continue;
                }        
                int right=Integer.MAX_VALUE;
                int down=right;
        
                if(i+1<arr.length){
                    down=dp[i+1][j];//rec_min(i+1,j,arr);
                }
                if(j+1<arr[0].length){
                    right=dp[i][j+1];//rec_min(i,j+1,arr);
                }
                dp[i][j]=(arr[i][j]+Math.min(right,down));
            }
        }
        return dp[0][0];
    }
    // in this variation we have print its path also
    public static int tab_min2(int i,int j,int arr[][],int dp[][]){
        String sdp[][]=new String[arr.length][arr[0].length];
        for(i=arr.length-1;i>=0;i--){
            for(j=arr[0].length-1;j>=0;j--){
                if(i==arr.length-1 && j==arr[0].length-1){
                    dp[i][j]=arr[i][j];
                    sdp[i][j]="";
                    continue;
                }        
                int right=Integer.MAX_VALUE;
                int down=right;
        
                if(i+1<arr.length){
                    down=dp[i+1][j];//rec_min(i+1,j,arr);
                }
                if(j+1<arr[0].length){
                    right=dp[i][j+1];//rec_min(i,j+1,arr);
                }
                int ans=arr[i][j];
                if(right<down){
                    ans+=right;
                    sdp[i][j]="r"+sdp[i][j+1];
                }
                else{
                    ans+=down;
                    sdp[i][j]="d"+sdp[i+1][j];
                }
                // dp[i][j]=(arr[i][j]+Math.min(right,down));
                dp[i][j]=ans;
            }
        }
        System.out.println(sdp[0][0]);
        return dp[0][0];
    }
    
    //Subset sum 
    public static boolean rec_subsetSum(int idx,int arr[],int target){
        if(target==0){
            return true;
        }
        if(idx==arr.length){
            return false;
        }

        boolean pick=false;
 
        if(target-arr[idx]>=0){
            pick=rec_subsetSum(idx+1, arr, target-arr[idx]);
        }
        boolean not_pick=rec_subsetSum(idx+1, arr, target);

        return pick||not_pick;
    }
    //for Memo condition it will fail because we are calculating ans in false and true manner
    //but we are checking if(memo[idx][target]!=fasle)return true; 
    //here is the issue if we have alredy calculated ans is false then it will not work 
    //to avoid this issue we will use int type Memo
    public static int Memo_subsetSum(int idx,int arr[],int target,int Memo[][]){
        if(target==0){
            return Memo[idx][target]=1;
        }
        if(idx==arr.length){
            return Memo[idx][target]=0;
        }
        if(Memo[idx][target]!=-1)return Memo[idx][target];
        int pick=0;

        if(target-arr[idx]>=0){
            pick=Memo_subsetSum(idx+1, arr, target-arr[idx],Memo);
        }
        int not_pick=Memo_subsetSum(idx+1, arr, target,Memo);

        return Memo[idx][target]=pick|not_pick;
    }
    public static int tab_subsetSum(int idx,int arr[],int Target,int Memo[][]){
        for(idx=arr.length;idx>=0;idx--){
            for(int target=0;target<=Target;target++){
                if(target==0){
                    Memo[idx][target]=1;
                    continue;
                }
                if(idx==arr.length){
                    Memo[idx][target]=0;
                    continue;
                }

                int pick=0;
                if(target-arr[idx]>=0){
                    pick=Memo[idx+1][target-arr[idx]];//Memo_subsetSum(idx+1, arr, target-arr[idx],Memo);
                }
                int not_pick=Memo[idx+1][target];//Memo_subsetSum(idx+1, arr, target,Memo);
        
                Memo[idx][target]=pick|not_pick;
            }
        }
       
        return Memo[0][Target];
    }

    public static int rec_coinChange(int idx,int arr[],int sum){
        if(sum==0){
            return 1;
        }
        if(idx==arr.length){
            return 0;
        }
        int pick=0;
        if(sum-arr[idx]>=0){
            pick=rec_coinChange(idx, arr, sum-arr[idx]);
        }
        int  not_pick=rec_coinChange(idx+1, arr, sum);

        return pick+not_pick;
    }

    public static int memo_coinChange(int idx,int arr[],int sum,int dp[]){
        if(sum==0){
            return dp[idx]=1;
        }
        if(idx==arr.length){
            return dp[idx]=0;
        }

        if(dp[idx]!=-1)return dp[idx];

        int pick=0;
        if(sum-arr[idx]>=0){
            pick=rec_coinChange(idx, arr, sum-arr[idx]);
        }
        int  not_pick=rec_coinChange(idx+1, arr, sum);

        return dp[idx]=pick+not_pick;
    }

    public static int tab_coinChange(int idx,int arr[],int Sum,int dp[][]){
        for(idx=arr.length;idx>=0;idx--){
            for(int sum=0;sum<=Sum;sum++){
                if(sum==0){
                   dp[idx][sum]=1;
                   continue;
                }
                if(idx==arr.length){
                   dp[idx][sum]=0;
                   continue;
                }
            
                int pick=0;
                if(sum-arr[idx]>=0){
                    pick=dp[idx][sum-arr[idx]];//rec_coinChange(idx, arr, sum-arr[idx]);
                }
                int not_pick=dp[idx+1][sum];//rec_coinChange(idx+1, arr, sum);
        
                dp[idx][sum]=pick+not_pick;
            }
        }
        return dp[0][Sum];
    }

    //Number of ways to reach end
    public static int rec_ways(int i,int j,int arr[][]){
        if(i==arr.length-1 && j==arr[0].length-1){
            return 1;
        }

        int right=0;
        if(j+1<arr[0].length){
           right+=rec_ways(i,j+1,arr);
        }
        int down=0;
        if(i+1<arr.length){
            down+=rec_ways(i+1,j,arr);
        }
        return right+down;
    }

    //Paint House problem
    public static int rec_PaintHouse(int arr[][]){
        int n=arr.length,m=arr[0].length;
        int dp[][]=new int[n][m];
        for(int h=0;h<arr.length;h++){
            if(h==0){
                dp[0][0]=arr[0][0];
                dp[0][1]=arr[0][1];
                dp[0][2]=arr[0][2];
            }
            else{
                dp[h][0]=arr[h][0]+ Math.min(dp[h-1][1],dp[h-1][2]);
                dp[h][1]=arr[h][1]+ Math.min(dp[h-1][0],dp[h-1][2]);
                dp[h][2]=arr[h][2]+ Math.min(dp[h-1][0],dp[h-1][1]);
            }
        }
        int ans=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            ans=Math.min(ans,dp[n-1][i]);
        }
        return ans;
    }

    // Partition into K subarray.  This is really good problem
    // https://course.acciojob.com/idle?question=5fce6e4c-9dda-4bec-87f1-8e57e27dd386
    static int mod=100007 ;
    public static int rec_kSubarrayWay(int n,int k){
        if(n==0 || k==0 || k>n){
            return 0;
        }
        if(n==k || k==1){
            return 1;
        }
        return ((rec_kSubarrayWay(n-1,k)*k)%mod + rec_kSubarrayWay(n-1,k-1)%mod)%mod;
    }

    public static int memo_kSubarrayWay(int n,int k,int memo[][]){
        if(n==0 || k==0 || k>n){
            return 0;
        }
        if(n==k || k==1){
            return 1;
        }
        if(memo[n][k]!=0)return memo[n][k];
       
        return memo[n][k]=((memo_kSubarrayWay(n-1,k,memo)*k)%mod + memo_kSubarrayWay(n-1,k-1,memo)%mod)%mod;
    }
    public static int tab_kSubarrayWay(int N, int K,int memo[][]) {
        for(int n=0;n<=N;n++){
            for(int k=0;k<=K;k++){
               if(n==0 || k==0 || k>n){
                    memo[n][k]=0;
                    continue;
               }
                if(n==k || k==1){
                    memo[n][k]= 1;
                    continue;
                }
                memo[n][k]=((memo[n-1][k]*k)%mod + memo[n-1][k-1]%mod)%mod;
            }
        }
       return memo[N][K];
    }

    //FRIENDS PAIRING PROBLEM
    public static int rec_friend(int n){
        if(n<=0)return 1;
        // if(n==0)return 1;
        return rec_friend(n-1)+rec_friend(n-2)*(n-1);
    }
   
    //LIS
    public static int rec_LIS(int i,int arr[],int prev){
        if(i==arr.length)return 0;

        int not_pick=rec_LIS(i+1,arr,prev);
        int pick=0;
        if(arr[i]>prev){
            pick=1+rec_LIS(i+1,arr,arr[i]);
        }
        return Math.max(pick,not_pick);
    }
    
    public static int tab_LIS(int arr[]){
        int ans=0;
        int n=arr.length;
        int dp[]=new int[n];

        for(int i=0;i<n;i++){
            dp[i]=1;

            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            ans=Math.max(ans,dp[i]);
        }

        return ans;
    }

    //same pattern of above. In LIS we are checking element from j=0 -> i
    //https://www.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    public int maxSumIS(int arr[]) {
        int n=arr.length;
        int dp[]=new int[n];
        int ans=0;
        
        for(int i=0;i<n;i++){
            dp[i]=arr[i];
            
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    dp[i]=Math.max(dp[i],dp[j]+arr[i]);
                }
            }
            ans=Math.max(dp[i],ans);
        }
        return ans;
    }
    

    
    public static void main(String[] args) {
        // int n=45;
        // // System.out.println(fib(n));
        // int dp[]=new int[n+1];
        // Arrays.fill(dp,-1);
        // System.out.println(Memo(n,dp));
        // System.out.println(Tab(n,dp));
        // System.out.println(Tab2(n, dp));
//---------------------------------------------------------------------------
        // int arr[]={3,3,0,2,2,3};
        // int dp[]=new int[7];

        // System.out.println(Ways_Tab(arr,dp));
        // System.out.println(Ways_Memo(0,arr,dp));
        // System.out.println(Ways(0,arr));
//---------------------------------------------------------------------------
        // int arr[]={5,10,100,10,5};
        // int dp[]=new int[6];
        
        // System.out.println(rec(0, arr));
        // System.out.println(Memo(0, arr, dp));
        // System.out.println(Tabulation(0, arr, dp));
//---------------------------------------------------------------------------
        // int arr[]={1,100,1,1,1,100,1,1,100,1};
        // int memo[]=new int[arr.length+1];
        // System.out.println(simple(arr));
        // System.out.println(rec_climb(0, arr));
        // System.out.println(memo_climb(0, arr, memo));
        // System.out.println(tab_climb(0, arr, memo));
//---------------------------------------------------------------------------
        // int arr[][]={{1,2,1,5},
        //              {2,2,4,1},
        //              {5,0,2,3},
        //              {0,6,1,2}};
        // int n=arr.length,m=arr[0].length;
        // int dp[][]=new int[n][m];
        // // System.out.println(rec_mine(arr,dp));
        // // System.out.println(tab_mines(arr, dp));
        // System.out.println(rec_min(0, 0, arr));
        // System.out.println(memo_min(0,0,arr,dp));
        // System.out.println(tab_min2(0, 0, arr, dp));
//---------------------------------------------------------------------------
        // int arr[]={3,34,4,12,5,2};
        // int target=9;
        // int Memo[][]=new int[arr.length+1][target+1];
        // for(int i=0;i<=arr.length;i++) Arrays.fill(Memo[i],-1);
        // System.out.println(rec_subsetSum(0,arr,target));
        // System.out.println(Memo_subsetSum(0, arr, target, Memo));
        // System.out.println(tab_subsetSum(0, arr, target, Memo));
        // for(var k:Memo){
        //     for(var k2:k){
        //         System.out.print(k2+" ");
        //     }
        //     System.out.println();
        // }
//---------------------------------------------------------------------------
    // int arr[]={2,5,3,6};
    // int sum=10;
    // int dp[][]=new int[arr.length+1][sum+1];
    // // Arrays.fill(dp,-1);
    // System.out.println(rec_coinChange(0,arr,sum));
    // System.out.println(tab_coinChange(0,arr,sum,dp));
//---------------------------------------------------------------------------
        // int arr[][]={{1,2,3},
        //              {4,5,3},
        //              {4,5,3}};
        // System.out.println(rec_ways(0, 0, arr));
//---------------------------------------------------------------------------
        // int arr[][]={{1,2,3},
        //             {4,5,3},
        //             {4,5,3}};
        
        // System.out.println(rec_PaintHouse(arr));
//---------------------------------------------------------------------------
        //paint house 2
        // System.out.println(rec_friend(4));
//---------------------------------------------------------------------------
        // int arr[]={10,9,2,5,3,7,101,18};
        // System.out.println(tab_LIS(arr));
//---------------------------------------------------------------------------

    }
}
