/**
 * 1641. Count Sorted Vowel Strings
 * Medium
 * <p>
 * <p>
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 * Example 3:
 * <p>
 * Input: n = 33
 * Output: 66045
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 50
 */
public class _1641CountSortedVowelStrings {

    /**
     * a[n] = a[n-1], e[n] = e[n-1]+a[n-1]...
     * cumul[0] = a[n], cumul[2] = a[n]+e[n]...
     */
    public int countVowelStrings(int n) {
        int[] cumul = new int[]{1, 2, 3, 4, 5};
        for (int i = 1; i < n; i++) {
            cumul[1] = cumul[1] + cumul[0];
            cumul[2] = cumul[2] + cumul[1];
            cumul[3] = cumul[3] + cumul[2];
            cumul[4] = cumul[4] + cumul[3];
        }

        return cumul[4];
    }

//    n = 2, a = 1, e = 2, i = 3, o = 4, u = 5, sum = 15
//    n = 3, a = 1, e = 3, i = 6, o = 10, u = 15, sum = 35
//    n = 4, a = 1, e = 4, i = 10, o = 20, u = 35, sum = 70
//    n = 5, a = 1, e = 5, i = 15, o = 35, u = 70, sum = 126
//    n = 6, a = 1, e = 6, i = 21, o = 56, u = 126, sum = 210
//    n = 7, a = 1, e = 7, i = 28, o = 84, u = 210, sum = 330
//    n = 8, a = 1, e = 8, i = 36, o = 120, u = 330, sum = 495
//    n = 9, a = 1, e = 9, i = 45, o = 165, u = 495, sum = 715
//    n = 10, a = 1, e = 10, i = 55, o = 220, u = 715, sum = 1001
//    n = 11, a = 1, e = 11, i = 66, o = 286, u = 1001, sum = 1365
//    n = 12, a = 1, e = 12, i = 78, o = 364, u = 1365, sum = 1820
//    n = 13, a = 1, e = 13, i = 91, o = 455, u = 1820, sum = 2380
//    n = 14, a = 1, e = 14, i = 105, o = 560, u = 2380, sum = 3060
//    n = 15, a = 1, e = 15, i = 120, o = 680, u = 3060, sum = 3876
//    n = 16, a = 1, e = 16, i = 136, o = 816, u = 3876, sum = 4845
//    n = 17, a = 1, e = 17, i = 153, o = 969, u = 4845, sum = 5985
//    n = 18, a = 1, e = 18, i = 171, o = 1140, u = 5985, sum = 7315
//    n = 19, a = 1, e = 19, i = 190, o = 1330, u = 7315, sum = 8855
//    n = 20, a = 1, e = 20, i = 210, o = 1540, u = 8855, sum = 10626
//    n = 21, a = 1, e = 21, i = 231, o = 1771, u = 10626, sum = 12650
//    n = 22, a = 1, e = 22, i = 253, o = 2024, u = 12650, sum = 14950
//    n = 23, a = 1, e = 23, i = 276, o = 2300, u = 14950, sum = 17550
//    n = 24, a = 1, e = 24, i = 300, o = 2600, u = 17550, sum = 20475
//    n = 25, a = 1, e = 25, i = 325, o = 2925, u = 20475, sum = 23751
//    n = 26, a = 1, e = 26, i = 351, o = 3276, u = 23751, sum = 27405
//    n = 27, a = 1, e = 27, i = 378, o = 3654, u = 27405, sum = 31465
//    n = 28, a = 1, e = 28, i = 406, o = 4060, u = 31465, sum = 35960
//    n = 29, a = 1, e = 29, i = 435, o = 4495, u = 35960, sum = 40920
//    n = 30, a = 1, e = 30, i = 465, o = 4960, u = 40920, sum = 46376
//    n = 31, a = 1, e = 31, i = 496, o = 5456, u = 46376, sum = 52360
//    n = 32, a = 1, e = 32, i = 528, o = 5984, u = 52360, sum = 58905
//    n = 33, a = 1, e = 33, i = 561, o = 6545, u = 58905, sum = 66045
//    n = 34, a = 1, e = 34, i = 595, o = 7140, u = 66045, sum = 73815
//    n = 35, a = 1, e = 35, i = 630, o = 7770, u = 73815, sum = 82251
//    n = 36, a = 1, e = 36, i = 666, o = 8436, u = 82251, sum = 91390
//    n = 37, a = 1, e = 37, i = 703, o = 9139, u = 91390, sum = 101270
//    n = 38, a = 1, e = 38, i = 741, o = 9880, u = 101270, sum = 111930
//    n = 39, a = 1, e = 39, i = 780, o = 10660, u = 111930, sum = 123410
//    n = 40, a = 1, e = 40, i = 820, o = 11480, u = 123410, sum = 135751
//    n = 41, a = 1, e = 41, i = 861, o = 12341, u = 135751, sum = 148995
//    n = 42, a = 1, e = 42, i = 903, o = 13244, u = 148995, sum = 163185
//    n = 43, a = 1, e = 43, i = 946, o = 14190, u = 163185, sum = 178365
//    n = 44, a = 1, e = 44, i = 990, o = 15180, u = 178365, sum = 194580
//    n = 45, a = 1, e = 45, i = 1035, o = 16215, u = 194580, sum = 211876
//    n = 46, a = 1, e = 46, i = 1081, o = 17296, u = 211876, sum = 230300
//    n = 47, a = 1, e = 47, i = 1128, o = 18424, u = 230300, sum = 249900
//    n = 48, a = 1, e = 48, i = 1176, o = 19600, u = 249900, sum = 270725
//    n = 49, a = 1, e = 49, i = 1225, o = 20825, u = 270725, sum = 292825
//    n = 50, a = 1, e = 50, i = 1275, o = 22100, u = 292825, sum = 316251


}
