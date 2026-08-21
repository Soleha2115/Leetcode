class Solution {

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    // Count different amounts <= x
    long count(long x, int[] coins) {
        long ans = 0;
        int n = coins.length;

        // Inclusion-Exclusion
        for (int mask = 1; mask < (1 << n); mask++) {

            long multiple = 1;
            boolean possible = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    multiple = lcm(multiple, coins[i]);

                    if (multiple > x) {
                        possible = false;
                        break;
                    }
                }
            }

            if (possible) {
                long value = x / multiple;

                if (Integer.bitCount(mask) % 2 == 1)
                    ans += value;
                else
                    ans -= value;
            }
        }

        return ans;
    }

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = (long) coins[0] * k;

        // Find kth smallest using binary search
        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }
}