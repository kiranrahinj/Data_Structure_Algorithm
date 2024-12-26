**Dynamic Programming**
    
    DP is the enhanced Recursion. 
    To learn DP we want basic recursion then we can convert it into Memoisation and Tabulation form.
    # Memoisation -> In basic recursion we just have to add  1 line to enhanced to Memoisation -> if(dp[n]!=-1)return dp[n];
    # Tabulation -> to remove recursion calls
    # Memo -> Tabulation
        Steps-
        1. Find the flow, Write the for loop. Mostly Flow is start from base case 
        2. Replace return with continue;
        3. Remove memo line
        4. Replace recursion calls with DP
**1. Fibonacci Example**
```java
    //In below example this is basic recursion 
    public static int fib(int n){
        if(n==0 || n==1)return n;

        return fib(n-1)+fib(n-2);
    }
    
    public static int Memo(int n,int []dp){
        if(n==0 || n==1)
            return dp[n]=n;

        if(dp[n]!=-1)return dp[n]; // here we are checking if its already calculated then return it.

        return dp[n]=Memo(n-1,dp)+Memo(n-2, dp);
    }

    // Now to optimise we need to remove recursion that is called Tabulation
    public static int Tab(int n,int dp[]){
        dp[0]=0;
        dp[1]=1;

        for(int i=2;i<n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    //Tabular
    public static int Tab2(int N,int []dp){

        for(int n=0;n<=N;n++){  // Step 1
            if(n==0 || n==1){
                dp[n]=n; 
                continue;       // Step 2
            }
            // if(dp[n]!=-1)return dp[n];   // Step 3
    
            dp[n]=dp[n-1]+dp[n-2];      // Step 4
            continue;
        }
        return dp[N];
    }
```
**2. Possible Ways to reach End**
```java
    //Basic Recursion
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

    //Memo
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

    //Tabulation
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
```
**3. Stickler Theif gfg**
```java
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
```   
**4. Min cost to climb stairs**
```java  
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
```
**5. Gold Mine problem gfg based on 2D matrix**
```java
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
    
    public static int rec_mine(int arr[][],int dp[][]){
        int ans=0;
        int n=arr.length;

        for(int i=0;i<n;i++){
            int temp=get_max_memo(arr,i,0,dp);
            ans=Math.max(ans,temp);
        }
        return ans;
    }
    
    //this is slight diff because on all 0 col ans is there
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
```
**6. Min Path sum**
```java
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
```      


**7. Subset Sum (Pattern-I pick/not pick)**
```java 
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
```
**8. Coin change II (This is different)**
```java
    public static int rec_coinChange(int idx,int arr[],int sum){
        if(sum==0){
            return 1;
        }
        if(idx==arr.length){
            return 0;
        }
        
        int pick=0;
        if(sum-arr[idx]>=0){
            pick=rec_coinChange(idx, arr, sum-arr[idx]);  //Not going to next idx because we can take many times
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
                int  not_pick=dp[idx][arr[idx]];//rec_coinChange(idx+1, arr, sum);
        
                dp[idx][sum]=pick+not_pick;
            }
        }
        return dp[0][Sum];
    }
```