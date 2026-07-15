class Solution {
public int gcdOfOddEvenSums(int n) {
        int se = 0, so = 0;

        for (int i = 1; i <= n; i++) {
            so += 2 * i - 1;
            se += 2 * i;
        }

        for (int i = so; i > 1; i--)
            if (so % i == 0 && se % i == 0)
                return i;

        return 1;
    }
}