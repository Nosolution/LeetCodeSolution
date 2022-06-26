import java.util.stream.Stream;

/**
 * 468. Validate IP Address
 * Medium
 * <p>
 * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 * <p>
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example,
 * "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid IPv4 addresses.
 * <p>
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 * <p>
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses,
 * while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: queryIP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 * <p>
 * Example 2:
 * <p>
 * Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 * <p>
 * Example 3:
 * <p>
 * Input: queryIP = "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * queryIP consists only of English letters, digits and the characters '.' and ':'.
 */
public class _468ValidateIPAddress {


    public String validIPAddress(String queryIP) {
        if (queryIP.contains(".")) {
            String[] words = queryIP.split("\\.");
            return !queryIP.startsWith(".") && !queryIP.endsWith(".") && words.length == 4 && Stream.of(words).allMatch(_468ValidateIPAddress::isIPV4Word) ? "IPv4" : "Neither";
        } else if (queryIP.contains(":")) {
            String[] words = queryIP.split(":");
            return !queryIP.startsWith(":") && !queryIP.endsWith(":") && words.length == 8 && Stream.of(words).allMatch(_468ValidateIPAddress::isIPV6Word) ? "IPv6" : "Neither";
        }
        return "Neither";
    }


    private static boolean isIPV4Word(String word) {
        try {
            int val = Integer.parseInt(word);
            return val <= 255 && !word.matches("0[0-9]+");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isIPV6Word(String word) {
        return 1 <= word.length() && word.length() <= 4 && word.matches("[0-9a-fA-F]+");
    }


}