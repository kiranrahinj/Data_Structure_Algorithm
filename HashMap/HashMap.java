package HashMap;

public class HashMap {
    //Longest Substring without Repeating Character
    public static int longestUniqueString(String s){
        int n=s.length();
        int ans=0;
        int freq[]=new int[123];
        int end=0,start=0;
        while(end<n){
            char ch=s.charAt(end++);
            freq[ch]++;

            while(start<end && freq[ch]>=2){
                char ch2=s.charAt(start);
                freq[ch2]--;
                start++;
            }
           ans=Math.max(ans,end-start);
        }
        return ans;
    }
    //Count of Substring without Repeating Character
    public static int countLongestUniqueString(String s){
        int n=s.length();
        int ans=0;
        int freq[]=new int[123];
        int end=0,start=0;
        while(end<n){
            char ch=s.charAt(end++);
            freq[ch]++;

            while(start<end && freq[ch]>=2){
                char ch2=s.charAt(start);
                freq[ch2]--;
                start++;
            }
            ans+=end-start;
        }
        return ans;
    }

    public static void main(String[] args){
        String s="abbbacbcdbadbdbacdeb";
        System.out.println(longestUniqueString(s));
        System.out.println(countLongestUniqueString(s));
    }
}
