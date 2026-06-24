class Solution {
    private static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (m <= 1) return 0;
        if (n == 1) return m;

        // Total dimensions of our state transition matrix: 2 * m
        int size = 2 * m;
        long[][] T = new long[size][size];

        // Populate the transition matrix using explicitly spaced operators
        for (int k = 0; k < m; k++) {
            for (int j = 0; j < m; j++) {
                if (j > k) {
                    // Transitioning UP: previous step must have been DOWN (state k + m)
                    T[j][k + m] = 1;
                } else if (j < k) {
                    // Transitioning DOWN: previous step must have been UP (state k)
                    T[j + m][k] = 1;
                }
            }
        }

        // Raise the transition matrix to the power of (n - 2)
        long[][] Tn = matrixPower(T, n - 2, size);

        // Build base state vectors for length n = 2
        long[] base = new long[size];
        for (int k = 0; k < m; k++) {
            for (int j = 0; j < m; j++) {
                if (j > k) {
                    base[j]++;
                } else if (j < k) {
                    base[j + m]++;
                }
            }
        }

        // Multiply the exponentiated matrix by the base counts
        long totalWays = 0;
        for (int i = 0; i < size; i++) {
            long ways = 0;
            for (int j = 0; j < size; j++) {
                ways = (ways + Tn[i][j] * base[j]) % MOD;
            }
            totalWays = (totalWays + ways) % MOD;
        }

        return (int) totalWays;
    }

    // Binary matrix exponentiation helper
    private long[][] matrixPower(long[][] matrix, int exp, int size) {
        long[][] result = new long[size][size];
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        long[][] base = matrix;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, base, size);
            }
            base = multiply(base, base, size);
            exp >>= 1;
        }
        return result;
    }

    // Safe matrix multiplication logic under modulo bounds
    private long[][] multiply(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}