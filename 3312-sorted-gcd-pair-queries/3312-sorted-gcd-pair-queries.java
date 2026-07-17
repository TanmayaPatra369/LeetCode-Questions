class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        int maxE = 0;
        for (int num : nums) {
            maxE = Math.max(maxE, num);
        }

        long[] freq = new long[maxE + 1];
        for (int num : nums) {
            freq[num]++;
        }

        long[] exact = new long[maxE + 1];

        for (int gcd = maxE; gcd >= 1; gcd--) {
            long cnt = 0;

            for (int times = gcd; times <= maxE; times += gcd) {
                cnt += freq[times];
            }

            exact[gcd] = cnt * (cnt - 1) / 2;

            for (int times = 2 * gcd; times <= maxE; times += gcd) {
                exact[gcd] -= exact[times];
            }
        }

        long[] pref = new long[maxE + 1];
        for (int gcd = 1; gcd <= maxE; gcd++) {
            pref[gcd] = pref[gcd - 1] + exact[gcd];
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];

            int left = 1, right = maxE;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (pref[mid] > q) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            result[i] = left;
        }

        return result;
    }
}