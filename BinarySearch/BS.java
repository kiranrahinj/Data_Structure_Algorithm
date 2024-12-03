class BinarySearch{
    public static int binarySearch(int arr[],int n,int target){
        int start=0,end=n-1;

        while(start<=end){
            int mid=start+(end-start)/2;
            if(arr[mid]==target)return mid;
            else if(arr[mid]<target)start=mid+1;
            else if(arr[mid]>target)end=mid-1;   
        }
        return -1;
    }

    public static int rec(int arr[],int start,int end,int target){
        if(start>end)return -1;

        int mid=start+(end-start)/2;
        int ans=-1;
        if(arr[mid]==target)return mid;
        else if(arr[mid]<target)ans=rec(arr,mid+1,end,target);
        else ans=rec(arr,start,mid-1,target);
        
        return ans;
    }

    public static int lowerBound(int arr[],int n,int target){
        int ans=n;

        int start=0,end=n-1;

        while(start<=end){
            int mid=start+(end-start)/2;

            if(arr[mid]>=target){
                ans=mid;
                end=mid-1;
            }
            else start=mid+1;
        }
        return ans;
    }

    public static int upperBound(int arr[],int n,int target){
        int ans=n;

        int start=0,end=n-1;

        while(start<=end){
            int mid=start+(end-start)/2;

            if(arr[mid]>target){
                ans=mid;
                end=mid-1;
            }
            else start=mid+1;
        }
        return ans;
    }

    public static int floor(int arr[],int n,int target){
        int start=0,end=n-1;
        int ans=0;
        while(start<=end){
            int mid=start+(end-start)/2;

            if(arr[mid]<=target){
                ans=arr[mid];
                start=mid+1;
            }
            else end=mid-1;
        }
        return ans;
    }

    public static int ceil(int arr[],int n,int target){
        int start=0,end=n-1;
        int ans=0;
        while(start<=end){
            int mid=start+(end-start)/2;

            if(arr[mid]>=target){
                ans=arr[mid];
                end=mid-1;
            }
            else start=mid+1;
        }
        return ans;
    }

    public static int lastOccurance(int arr[],int n,int target){
        int start=0,end=n-1;
        int ans=-1;
        while(start<=end){
            int mid=start+(end-start)/2;

           if(arr[mid]==target){
                ans=mid;
                start=mid+1; //moving to right for last occ
            }
            else if(arr[mid]<target){
                start=mid+1;
            }
            else end=mid-1;
        }
        return ans;
    }

    public static int firstOccurance(int arr[],int n,int target){
        int start=0,end=n-1;
        int ans=-1;
        while(start<=end){
            int mid=start+(end-start)/2;

            if(arr[mid]==target){
                ans=mid;
                end=mid-1; //moving to left for 1st occ.
            }
            else if(arr[mid]<target){
                start=mid+1;
            }
            else end=mid-1;
        }
        return ans;
    }

    public static int rotated1(int arr[],int n,int target){
        int start=0,end=n-1;

        while(start<=end){
            int mid=start+(end-start)/2;
            if(arr[mid]==target)return mid;

            // left side is sorted
            if(arr[start]<=arr[mid]){
                // now move start and end as per BS
                if(arr[start]<=target && target<=arr[mid]){
                    end=mid-1;
                }
                else{
                    start=mid+1;
                }
            }
            // rigth side is sorted
            else{
                if(arr[mid]<=target && target<=arr[end]){
                    start=mid+1;
                }
                else{
                    end=mid-1;
                }
            }
        }
        return -1;
    }

    public static int rotated2(int arr[],int n,int target){
        int start=0,end=n-1;

        while(start<=end){
            int mid=start+(end-start)/2;
            if(arr[mid]==target)return mid;
            
            //add check to get unique
            if(arr[start]==arr[mid] && arr[mid]==arr[end]){
                start++;  //this will move towards to unique.
                end--;
                continue;
            }

            // left side is sorted
            if(arr[start]<=arr[mid]){
                // now move start and end as per BS
                if(arr[start]<=target && target<=arr[mid]){
                    end=mid-1;
                }
                else{
                    start=mid+1;
                }
            }
            // rigth side is sorted
            else{
                if(arr[mid]<=target && target<=arr[end]){
                    start=mid+1;
                }
                else{
                    end=mid-1;
                }
            }
        }
        return -1;
    }

    public static int minSorted(int arr[],int n){
        int ans=Integer.MAX_VALUE;
        int start=0,end=n-1;

        while(start<=end){
            int mid=start+(end-start)/2;
            //little bit modification-> if both array sorted then break with min ans.
            if(arr[start]<=arr[mid] && arr[mid]<=arr[end]){
                ans=Math.min(ans,arr[start]);
                break;
            }
            //checking sorted part
            if(arr[start]<=arr[mid]){
                ans=Math.min(ans,arr[start]);
                start=mid+1;
            }
            else{
                ans=Math.min(ans,arr[mid]);
                end=mid-1;
            }
        }
        return ans;
    }

    public static int singleNumber(int arr[],int n){

        int start=0,end=n-1;
        if(arr[start]!=arr[start+1])return start;
        if(arr[end]!=arr[end-1])return end;

        start++;end--;

        while(start<=end){
            int mid=start+(end-start)/2;

            //single number check
            if(arr[mid-1]!=arr[mid]&& arr[mid+1]!=arr[mid])return arr[mid];

            if(mid%2==0 && arr[mid+1]==arr[mid] || mid%2!=0 && arr[mid-1]==arr[mid]){
                start=mid+1;
            }
            else{
                end=mid-1;
            }
        }

        return -1;
    }
    
    public static int peakElement(int arr[],int n){
        
        int start=0,end=n-1;
        if(arr[start]>arr[start+1])return start;
        if(arr[end]>arr[end-1])return end-1;
        start++;end--;

        while(start<=end){

            int mid=start+(end-start)/2;

            // check for peak element
            if(arr[mid]>arr[mid+1] && arr[mid]>arr[mid-1])return mid;

            if(arr[mid]<arr[mid+1]){
                start=mid+1;
            }
            else end=mid-1;
        }

        return -1;
    }

    public static int sqareRoot(int num){
        int start=1,end=num;
        int ans=0;
        while (start<=end) {
            int mid=start+(end-start)/2;

            if(mid*mid<=num){
                ans=mid;
                start=mid+1;
            }
            else end=mid-1;
        }
        return ans;
    }

    public static int check(int mid,int num,int n){
        long ans=1;

        for(int i=0;i<n;i++){
            ans*=mid;
            if(ans>num)return 2;
        }
        if(ans==num)return 1;
        return 0;
    }
    
    public static int nthRoot(int num,int n){
        int start=1,end=num;

        while (start<=end) {
            
            int mid=start+(end-start)/2;
            //check for the nth 
            int midNum=check(mid,num,n);
            if(midNum==1){
                return mid;
            }
            else if(midNum==0)start=mid+1;
            else end=mid-1;
        }
        return -1;
    }
    //875. Koko Eating Bananas problem 
    class Solution {
        public static int calculate(int arr[],int mid){
            int n=arr.length;
            int hours=0;
            for(int i=0;i<n;i++){
                hours+=Math.ceil((double)arr[i]/(double)mid);
            }
            return hours;
        }
        public int minEatingSpeed(int[] arr, int h) {
            int mx=0,ans=0;
            for(int k:arr)mx=Math.max(k,mx);
            int start=1,end=mx;
    
            while(start<=end){
                int mid=start+(end-start)/2;
    
                int hours=calculate(arr,mid);
                if(hours<=h){
                    ans=mid;
                    end=mid-1;
                }
                else start=mid+1;
            }
            return ans;
        }
    }
    //1482. Minimum Number of Days to Make m Bouquets
    class Solution1 {
        public boolean isPossible(int arr[],int m,int k,int mid){
            int cnt=0,ans=0;
    
            for(int i=0;i<arr.length;i++){
                if(arr[i]<=mid){
                    cnt++;
                }else cnt=0;
    
                if(cnt==k){
                    ans++;
                    cnt=0;
                    if(ans==m)return true;
                }
            }
            return false;
        }
        public int minDays(int[] arr, int m, int k) {
            if(arr.length<m*k)return -1;
            int mx=0,ans=-1;
            for(int k1:arr)mx=Math.max(k1,mx);
            int start=1,end=mx;
    
            while(start<=end){
                int mid=start+(end-start)/2;
    
                if(isPossible(arr,m,k,mid)){
                    ans=mid;
                    end=mid-1;
                }
                else{
                    start=mid+1;
                }
            }
            return ans;
        }
    }
    //1283. Find the Smallest Divisor Given a Threshold
    class Solution2 {
        public static boolean isPossible(int arr[],int mid,int threshold){
            int ans=0;
    
            for(int i=0;i<arr.length;i++){
                ans+=Math.ceilDiv(arr[i],mid);
            }
            return ans<=threshold;
        }
    
        public int smallestDivisor(int[] arr, int threshold) {
            int mx=0,ans=0;
            for(int k:arr)mx=Math.max(mx,k);
    
            int start=1,end=mx;
    
            while(start<=end){
                int mid=start+(end-start)/2;
    
                if(isPossible(arr,mid,threshold)){
                    ans=mid;
                    end=mid-1;
                }
                else start=mid+1;
            }
            return ans;
        }
    }
    //Aggressive Cows
    class Solution_1{
        private static boolean isPossible(int arr[],int mid,int k){
            int cnt=1,last=arr[0];
            
            for(int i=1;i<arr.length;i++){
                if(arr[i]-last>=mid){
                    cnt++;
                    last=arr[i];
                }
            }
            return cnt>=k;
        }
        
        public static int aggressiveCows(int arr[],int c){
            int start=1,end=(int)1e9;
            while(start<=end){
                int mid=start+(end-start)/2;
                
                if(isPossible(arr,mid,c)){
                    start=mid+1;
                }
                else end=mid-1;
            }
            return end;
        }
    }
	//Book Allocation
    class Solution3 {
        public static boolean isPossible(int arr[],int mid,int k){
            int sum=0;
            int cnt=1;
            
            for(int i=0;i<arr.length;i++){
                sum+=arr[i];
                if(sum>mid){
                    cnt++;
                    sum=arr[i];
                }
            }
            return cnt<=k;
        }
    
        public static int findPages(int[] arr, int threshold) {
            int ans=-1;
            int mx=0,sum=0;
            for(int i:arr){
                mx=Math.max(mx,i);
                sum+=i;
            }
            //starting is from max because one book need to hold by one stud.
            //So for that you need to select from max book number to statiesfy 3rd condition
            int start=mx,end=sum;
    
            while(start<=end){
                int mid=start+(end-start)/2;
    
                if(isPossible(arr,mid,threshold)){
                    ans=mid;
                    end=mid-1;
                }
                else start=mid+1;
            }
            return ans;
        }
    }


}

public class BS{
    public static void main(String args[]){
        // int arr[]={1,2,1,3,5,6,4};

        // int n=arr.length;

        // System.out.println(BinarySearch.firstOccurance(arr, n, 80));
        // System.out.println(BinarySearch.peakElement(arr, n));
        System.out.println(BinarySearch.nthRoot(27,4));
    }
}