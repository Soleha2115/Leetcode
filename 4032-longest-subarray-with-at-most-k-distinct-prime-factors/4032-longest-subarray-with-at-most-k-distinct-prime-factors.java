class Solution {

    public int longestSubarray(int[] nums, int k) {

        int n = nums.length;

        List<List<Integer>> factors = new ArrayList<>();

        for (int num : nums) {
            factors.add(getPrimeFactors(num));
        }

        int[] freq = new int[100001];

        int distinct = 0;
        int left = 0;
        int answer = 0;

        for (int right = 0; right < n; right++) {

            for (int prime : factors.get(right)) {
                if (freq[prime] == 0) {
                    distinct++;
                }
                freq[prime]++;
            }

            while (distinct > k) {

                for (int prime : factors.get(left)) {
                    freq[prime]--;

                    if (freq[prime] == 0) {
                        distinct--;
                    }
                }

                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }

    private List<Integer> getPrimeFactors(int num) {

        List<Integer> factors = new ArrayList<>();

        for (int p = 2; p * p <= num; p++) {

            if (num % p == 0) {
                factors.add(p);

                while (num % p == 0) {
                    num /= p;
                }
            }
        }

        if (num > 1) {
            factors.add(num);
        }

        return factors;
    }
}