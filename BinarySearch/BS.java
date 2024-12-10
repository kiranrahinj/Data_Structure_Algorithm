import java.util.*;
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
    //Kth Missing number
    class Solution4{
        public static int findMissing(int arr[],int k){
            int start=0, end=arr.length-1;
            int missing=0;
            while(start<=end){
                int mid=start+(end-start)/2;
                missing=arr[mid]-(mid+1);

                if(missing<k){
                    start=mid+1;
                }
                else end=mid-1;
            }
           return arr[end]+(k-missing); // return end+1+k; // return start+k;
        }
    }
    //Median of two sorted Array
    class Solution5{
        public static double medianOfArrays1(int a[],int b[]){
            int n=a.length+b.length;
            int arr[]=new int[n];
            int i=0,j=0,idx=0;
           while(i<a.length && j<b.length){
                arr[idx++]=a[i]<b[j] ? a[i++]:b[j++];
           }
           while(i<a.length){
                arr[idx++]=a[i++];
           }
           while(j<b.length){
                arr[idx++]=b[j++];
           }
           double ans=n%2==0 ? (arr[n/2]+arr[n/2-1])/2.0 :arr[n/2];
            return ans;
        }
        public static double medianOfArraysWithoutExtraSpace(int a[],int b[]){
            int n=a.length+b.length;
            int i=0,j=0;
            int c1=n/2,cnt=0;
            int c2=c1-1,d1=-1,d2=-1;

            while(i<a.length && j<b.length){
                if(d1!=-1 && d2!=-1)break;
            
                if(a[i]<b[j]){
                    if(cnt==c1)d1=a[i];
                    if(cnt==c2)d2=a[i];
                    i++;
                    cnt++;
                }
                else{
                    if(cnt==c1)d1=b[j];
                    if(cnt==c2)d2=b[j];
                    j++;
                    cnt++;
                }
                
            }
            while(i<a.length){
                if(d1!=-1 && d2!=-1)break;
                if(cnt==c1)d1=a[i];
                if(cnt==c2)d2=a[i];
                cnt++;i++;
           }
            while(j<b.length){
                if(d1!=-1 && d2!=-1)break;
                if(cnt==c1)d1=b[j];
                if(cnt==c2)d2=b[j];
                cnt++;
                j++;
            }

            if(n%2==1)return d1;
            return (d1+d2)/2.0;
        }       
        public static double medianUsingHeaps(int arr[]){
            PriorityQueue<Integer>left=new PriorityQueue<>((a,b)->b-a);
            PriorityQueue<Integer>right=new PriorityQueue<>((a,b)->a-b);

            for(int k:arr){
                if(left.size()==0  || k<=left.peek()){
                    left.add(k);
                }
                else{
                    right.add(k);
                }

                int ls=left.size();
                int rs=right.size();
                // we are maintainig left size is greter that right -> ls=rs+1
                if(ls-rs==2){//ls is more than rs
                    right.add(left.poll());
                }
                else if(rs>ls){
                    left.add(right.poll());
                }
            }
            if(arr.length%2==0)return ((left.peek()*1.0)+(right.peek()*1.0))/2.0;
            return left.peek()*1.0;
        }
        //this is the main pattern is made  
        public static double medianUsingBS(int a[],int []b){
            int n1=a.length,n2=b.length;
            if(n1>n2)return medianUsingBS(b,a);
            int left=(n1+n2+1)/2;
           

            int start=0,end=n1;
            while(start<=end){
                int mid1=(start+end)/2;
                int mid2=left-mid1;
                int l1=Integer.MIN_VALUE,l2=Integer.MIN_VALUE;
                int r1=Integer.MAX_VALUE,r2=Integer.MAX_VALUE;

                if(mid1<n1)r1=a[mid1];
                if(mid2<n2)r2=b[mid2];
                if(mid1-1>=0)l1=a[mid1-1];
                if(mid2-1>=0)l2=b[mid2-1];

                if(l1<=r2 && l2<=r1){
                    if((n1+n2)%2==0){
                        return (Math.max(l1, l2)+Math.min(r1,r2))/2.0;
                    }
                    return Math.max(l1, l2);
                }
                else if(l1>r2)end=mid1-1;
                else if(l2>r1)start=mid1+1;
            }

            return 0;
        }
    }
    //Gas Station Problem
    //totally diff Algo
    class Solution6{
        static class Pair{
             double diff;
            int idx;
            public Pair(double diff,int idx){
                this.diff=diff;
                this.idx=idx;
            }
        }
        public static double bruteForce(int arr[],int k){
            int n=arr.length;
            int howMany[]=new int[n-1];

            for(int i=0;i<k;i++){
                double maxSections=-1;int max_index=-1;
                for(int j=0;j<n-1;j++){
                    int diff=arr[j+1]-arr[j];
                    double sections=diff/((howMany[j]+1)*1.0);

                    if(maxSections<sections){
                        maxSections=sections;
                        max_index=j;
                    }
                }
                howMany[max_index]++;
            }
            double ans=-1;
            for(int j=0;j<n-1;j++){
                int diff=arr[j+1]-arr[j];
                double sections=diff/((howMany[j]+1)*1.0);
                ans=Math.max(ans,sections);
            }

            return ans;
        }
        public static double usingPQ(int arr[],int k){
            PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->Double.compare(b.diff,a.diff));
            int n=arr.length;
            int howMany[]=new int[n-1];
            for(int i=0;i<n-1;i++){
                pq.add(new Pair(arr[i+1]-arr[i],i));
            }
            for(int i=0;i<k;i++){
                Pair temp=pq.poll();
                int idx=temp.idx;
                howMany[idx]++;

                double diff=arr[idx+1]-arr[idx];
                double sections=diff/((howMany[idx]+1)*1.0);
                pq.add(new Pair(sections, idx));
            }
            return pq.peek().diff;
        }
        
        public static int calculate(int arr[],double mid){
            int cnt=0;
            for(int i=1;i<arr.length;i++){
                int numberBetween=(int) ((arr[i]-arr[i-1])/mid);
                if((arr[i]-arr[i-1])/mid ==numberBetween+mid){
                    numberBetween--;
                }
                cnt+=numberBetween;
            }
            return cnt;
        }

        public static double usingBs(int arr[],int k){
            int n=arr.length;
            double start=0,end=0;
            for(int j=0;j<n-1;j++){
                int diff=arr[j+1]-arr[j];
                end=Math.max(end,diff);
            }

            double diff=(double)1e-6;
            while(end-start>diff){
                double mid=start+(end-start)/2.0;
                int ans=calculate(arr,mid);
                if(ans>k){
                    start=mid;
                }
                else end=mid;
            }
            return end;
        }
    }

    //2D Questions
    //matrix peak element-> Actual intution is apply BS on column then find max_ele on mid column. Then check their right and left val of that max_index ele
    class Solution7 {
        private int helper(int arr[][],int n,int m, int mid){
            int max_val=-1,idx=-1;
    
            for(int i=0;i<n;i++){
                if(max_val<arr[i][mid]){
                    max_val=arr[i][mid];
                    idx=i;
                }
            }
            return idx;
        }
        public int[] findPeakGrid(int[][] arr) {
            int n=arr.length,m=arr[0].length;
            int start=0,end=m-1;
    
            while(start<=end){
                int mid=start+(end-start)/2;
                int maxIndex=helper(arr,n,m,mid);
                int left=mid-1>=0?arr[maxIndex][mid-1]:-1;
                int right=mid+1<m?arr[maxIndex][mid+1]:-1;
    
                if(arr[maxIndex][mid]>left && arr[maxIndex][mid]>right)return new int[]{maxIndex,mid};
                else if(arr[maxIndex][mid]<right)start=mid+1;
                else end=mid-1;
            }
            return new int[]{-1,-1};
        }
    }
}

public class BS{
    public static void main(String args[]){
        // int arr[]={1,2,1,3,5,6,4};

        // int n=arr.length;

        // System.out.println(BinarySearch.firstOccurance(arr, n, 80));
        // System.out.println(BinarySearch.peakElement(arr, n));
        // System.out.println(BinarySearch.nthRoot(27,4));

        // int arr[]={2,5,6,8,9};
        // System.out.println(BinarySearch.Solution4.findMissing(arr, 6));

        // int a[]={2,5,6,8,7};
        // int b[]={1,2,4,5,9};
        // System.out.println(BinarySearch.Solution5.medianOfArrays1(a, b));
        // System.out.println(BinarySearch.Solution5.medianOfArraysWithoutExtraSpace(a, b));
        // System.out.println(BinarySearch.Solution5.medianUsingBS(a, b));

        // int arr[]=new int[10];
        // int idx=0;
        // for(int i=0;i<a.length;i++){
        //     arr[idx++]=a[i];
        //     arr[idx++]=b[i];
        // }
        // System.out.println(BinarySearch.Solution5.medianUsingHeaps(arr));

        int arr[]={1,2,3,4,5,6,7,8,9,10};
        System.out.println(BinarySearch.Solution6.bruteForce(arr, 9));
        System.out.println(BinarySearch.Solution6.usingPQ(arr, 9));
        System.out.println(BinarySearch.Solution6.usingBs(arr, 9));

        int arr1[]={3,6,12,19,33,44,67,72,89,95};
        System.out.println(BinarySearch.Solution6.bruteForce(arr1, 2));
        System.out.println(BinarySearch.Solution6.usingPQ(arr1, 2));
        System.out.println(BinarySearch.Solution6.usingBs(arr1, 2));

    }
}