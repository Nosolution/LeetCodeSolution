/**
 * 640. Solve the Equation
 * Medium
 * <p>
 * Solve a given equation and return the value of 'x' in the form of a string "x=#value". The equation contains only '+', '-' operation, the variable 'x' and its coefficient. You should return "No solution" if there is no solution for the equation, or "Infinite solutions" if there are infinite solutions for the equation.
 * If there is exactly one solution for the equation, we ensure that the value of 'x' is an integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: equation = "x+5-3+x=6+x-2"
 * Output: "x=2"
 * Example 2:
 * <p>
 * Input: equation = "x=x"
 * Output: "Infinite solutions"
 * Example 3:
 * <p>
 * Input: equation = "2x=x"
 * Output: "x=0"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= equation.length <= 1000
 * equation has exactly one '='.
 * equation consists of integers with an absolute value in the range [0, 100] without any leading zeros, and the variable 'x'.
 */
public class _648SolveTheEquation {

    public String solveEquation(String equation) {
        String res;
        int equIdx = equation.indexOf('=');
        int[] left = parseExpression(equation.substring(0, equIdx));
        int[] right = parseExpression(equation.substring(equIdx + 1));
        left[0] -= right[0];
        right[1] -= left[1];
        if (left[0] == 0) {
            if (right[1] != 0) {
                res = "No solution";
            } else {
                res = "Infinite solutions";
            }
        } else {
            int rv = right[1] / left[0];
            res = String.format("x=%d", rv);
        }

        return res;
    }

    private int[] parseExpression(String s) {
        int[] res = new int[2];
        boolean foundX = false;
        int v = 0, co = 1;
        char c;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            c = ch[i];
            if (c - '0' < 0) {
                res[foundX ? 0 : 1] += v * co;
                co = c == '-' ? -1 : 1;
                v = 0;
                foundX = false;
            } else if (c == 'x') {
                foundX = true;
                if (i == 0 || ch[i - 1] - '0' < 0) {
                    v = 1;
                }
            } else {
                v = v * 10 + c - '0';
            }
        }
        res[foundX ? 0 : 1] += v * co;


        return res;
    }
}
