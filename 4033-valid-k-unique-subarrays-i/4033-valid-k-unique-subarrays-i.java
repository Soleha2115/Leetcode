class Solution {

    static class Query {
        int l, r, index;

        Query(int l, int r, int index) {
            this.l = l;
            this.r = r;
            this.index = index;
        }
    }

    public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {

        int n = nums.length;
        int q = queries.length;

        Query[] qs = new Query[q];

        for (int i = 0; i < q; i++) {
            qs[i] = new Query(queries[i][0], queries[i][1], i);
        }

        int blockSize = (int) Math.sqrt(n);

        Arrays.sort(qs, (a, b) -> {
            int blockA = a.l / blockSize;
            int blockB = b.l / blockSize;

            if (blockA != blockB) {
                return Integer.compare(blockA, blockB);
            }

            if ((blockA & 1) == 0) {
                return Integer.compare(a.r, b.r);
            } else {
                return Integer.compare(b.r, a.r);
            }
        });

        boolean[] ans = new boolean[q];

        int[] freq = new int[100001];

        int left = 0;
        int right = -1;

        int distinct = 0;
        int odd = 0;

        for (Query query : qs) {

            int L = query.l;
            int R = query.r;

            while (left > L) {
                left--;

                int x = nums[left];

                if (freq[x] == 0) {
                    distinct++;
                }

                if ((freq[x] & 1) == 0) {
                    odd++;
                } else {
                    odd--;
                }

                freq[x]++;
            }

            while (right < R) {
                right++;

                int x = nums[right];

                if (freq[x] == 0) {
                    distinct++;
                }

                if ((freq[x] & 1) == 0) {
                    odd++;
                } else {
                    odd--;
                }

                freq[x]++;
            }

            while (left < L) {

                int x = nums[left];

                freq[x]--;

                if (freq[x] == 0) {
                    distinct--;
                }

                if ((freq[x] & 1) == 0) {
                    odd--;
                } else {
                    odd++;
                }

                left++;
            }

            while (right > R) {

                int x = nums[right];

                freq[x]--;

                if ((freq[x] & 1) == 0) {
                    odd--;
                } else {
                    odd++;
                }

                if (freq[x] == 0) {
                    distinct--;
                }

                right--;
            }

            ans[query.index] = (distinct == k && odd == 0);
        }

        return ans;
    }
}