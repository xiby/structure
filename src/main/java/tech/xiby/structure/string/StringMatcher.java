package tech.xiby.structure.string;

/**
 * 字符串匹配工具
 *
 * @author xiby
 */
public class StringMatcher {

    /**
     * next数组
     */
    private int[] next;

    /**
     * 模板串
     */
    private String pattern;

    public StringMatcher(String pattern) {
        this.pattern = pattern;
        this.next = new int[pattern.length()];
        this.next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }

    /**
     * 进行匹配，匹配成功，返回首次匹配的位置信息
     *
     * @param sequence
     * @return
     */
    public int match(String sequence) {
        int i = 0;
        int j = 0;
        while (i < sequence.length()) {
            if (sequence.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    return i - pattern.length() + 1;
                }
                ++i;
                ++j;
            } else {
                if (next[j] == -1) {
                    ++i;
                } else {
                    j = next[j];
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StringMatcher matcher = new StringMatcher("abaabcac");
        System.out.println(matcher.match("abcinaooidabaabcac"));
        System.out.println(matcher.match("abaabcaabaabcac"));
    }
}
