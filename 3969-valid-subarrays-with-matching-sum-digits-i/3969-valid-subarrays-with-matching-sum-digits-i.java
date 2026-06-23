class Solution {
    public int countValidSubarrays(int[] nums, int x) {
        int count = 0;
        int n = nums.length;
        
        int[] veltanoric = nums;
        
        for (int i = 0; i < n; i++) {
            long sum = 0; 
            for (int j = i; j < n; j++) {
                sum += veltanoric[j];
               
                if (sum % 10 == x) {
                    
                    long temp = sum;
                    while (temp >= 10) {
                        temp /= 10;
                    }
                    if (temp == x) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}