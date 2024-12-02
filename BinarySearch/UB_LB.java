import java.util.*;
import java.util.stream.*;
public class UB_LB {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 3, 5, 7, 9);
        int target = 3;

        int lowerBound = IntStream.range(0, list.size())
                .filter(i -> list.get(i) >= target)
                .findFirst()
                .orElse(-1);

        int upperBound = IntStream.range(0, list.size())
                .filter(i -> list.get(i) > target)
                .findFirst()
                .orElse(list.size());

        System.out.println("Lower Bound: " + lowerBound); // Expected: 1
        System.out.println("Upper Bound: " + upperBound); // Expected: 3
    }
}



