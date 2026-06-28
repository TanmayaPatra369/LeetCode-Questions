class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> count = new HashMap<>();
        for (int num : nums){
            count.merge((long) num , 1, Integer::sum);
        }
        int count1 = count.getOrDefault(1L,0);
        int ans = (count1 & 1) == 1 ? count1 : count1 - 1;
        count.remove(1L);
        for (long num : count.keySet()){
            int res = 0;
            long x = num;

            while (count.containsKey(x) && count.get(x) > 1){
                res += 2;
                x *= x;
            }
            ans = Math.max(ans, res + (count.containsKey(x) ? 1 : -1));
        }
        return ans;
    }
}