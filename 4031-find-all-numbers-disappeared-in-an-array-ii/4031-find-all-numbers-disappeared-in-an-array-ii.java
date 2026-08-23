class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        long start = lower;

        for (int num : nums) {
            
            if (num > upper) {
                break;
            }

            if (num < start) {
                continue;
            }

            if (num > start) {
                result.add(Arrays.asList((int) start, num - 1));
            }

            start = (long) num + 1;
        }

        if (start <= upper) {
            result.add(Arrays.asList((int) start, upper));
        }

        return result;
    }
}