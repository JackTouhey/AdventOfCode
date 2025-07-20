import java.util.HashMap;
import java.util.Map;

public class DayEleven2024 {
    private static final long[] INPUT_STONES = {510613L, 358L, 84L, 40702L, 4373582L, 2L, 0L, 1584L};
    
    public static void main(String[] args) {
        // Initialize stone counts
        Map<Long, Long> stoneCount = new HashMap<>();
        for (long stone : INPUT_STONES) {
            stoneCount.put(stone, stoneCount.getOrDefault(stone, 0L) + 1);
        }
        
        // Apply transformations for 75 iterations
        for (int blink = 1; blink <= 75; blink++) {
            Map<Long, Long> nextStoneCount = new HashMap<>();
            
            for (Map.Entry<Long, Long> entry : stoneCount.entrySet()) {
                long stone = entry.getKey();
                long count = entry.getValue();
                
                if (stone == 0) {
                    // Rule 1: 0 becomes 1
                    nextStoneCount.merge(1L, count, Long::sum);
                } else if (hasEvenDigits(stone)) {
                    // Rule 2: Split even-digit numbers
                    long[] splitStones = splitNumber(stone);
                    nextStoneCount.merge(splitStones[0], count, Long::sum);
                    nextStoneCount.merge(splitStones[1], count, Long::sum);
                } else {
                    // Rule 3: Multiply by 2024
                    nextStoneCount.merge(stone * 2024, count, Long::sum);
                }
            }
            
            stoneCount = nextStoneCount;
            System.out.println("After blink " + blink + ": " + getTotalStones(stoneCount) + " stones");
        }
        
        System.out.println("Final answer: " + getTotalStones(stoneCount));
    }
    
    /**
     * Check if a number has an even number of digits using mathematical operations
     */
    private static boolean hasEvenDigits(long value) {
        int digitCount = (value == 0) ? 1 : (int) Math.log10(value) + 1;
        return digitCount % 2 == 0;
    }
    
    /**
     * Split a number in half mathematically (no string operations)
     */
    private static long[] splitNumber(long value) {
        int digitCount = (value == 0) ? 1 : (int) Math.log10(value) + 1;
        long divisor = 1;
        for (int i = 0; i < digitCount / 2; i++) {
            divisor *= 10;
        }
        
        long leftHalf = value / divisor;
        long rightHalf = value % divisor;
        
        return new long[]{leftHalf, rightHalf};
    }
    
    /**
     * Calculate total number of stones across all values
     */
    private static long getTotalStones(Map<Long, Long> stoneCount) {
        return stoneCount.values().stream().mapToLong(Long::longValue).sum();
    }
}