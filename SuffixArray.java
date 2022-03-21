import java.util.*;

/**
 * MG21320005 黄国钊
 */
public class SuffixArray {

    private final static int SIZE = 256;
    private final static int MSZ = 10000; //桶大小
    private final static int minLen = 5; //最短字符串长度
    private final static int K = 5; //最少重复出现次数


    /**
     * 用后缀数组帮助求解，时间复杂度O(nlogn)，空间复杂度，大概是O(n)吧，MSZ与s长度的关系暂时不清楚，太小会数组越界
     */
    public String solve(String s) {
        int[] sa = getSA(s);
        int[] height = getHeight(s, sa);
        String res = "", tmp;
        //堆中保存height[i]值也就是最长公共前缀最短的i
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(o -> height[o]));

        int i = 1;
        boolean extendWindow;
        int window = K - 1;
        //使用滑动窗口求解
        while (i < s.length()) {
            //找到下一组开始的后缀组
            if (i <= s.length() - window) {
                for (int j = i + window; i < j; i++) {
                    if (height[i] >= minLen) {
                        heap.add(i);
                    } else {
                        //尝试失败，从下一个i开始重新找后缀组
                        i++;
                        heap.clear();
                        break;
                    }
                }
            } else {
                break;
            }

            if (!heap.isEmpty()) {
                //找到后先求当前结果
                tmp = s.substring(sa[heap.peek()], sa[heap.peek()] + height[heap.peek()]);
                if (tmp.length() > res.length()) {
                    res = tmp;
                }

                //如果公共前缀长度不够，从下一个i从头开始找后缀组
                for (; i < s.length() && height[i] >= minLen; i++) {
                    extendWindow = false;
                    if (height[i] >= height[heap.peek()]) {
                        //下一个最大公共前缀大于当前前缀，扩大窗口
                        window++;
                        extendWindow = true;
                    } else {
                        //否则移动窗口
                        heap.remove(i - window + 1);
                    }
                    heap.add(i);
                    tmp = s.substring(sa[heap.peek()], sa[heap.peek()] + height[heap.peek()]);
                    if (tmp.length() > res.length() || extendWindow) {
                        res = tmp;
                    }
                }
            }
            heap.clear();

        }

        return res;
    }


    /**
     * 后缀数组的计算方式，可以当成封装api使用
     * sa[i]代表排名为i的后缀字符串的起始字符下标，按字典序排列
     * 计算时间复杂度O(nlogn)，空间复杂度O(n)
     */
    private int[] getSA(String s) {
        char[] ch = s.toCharArray();
        int n = s.length();
        int m = SIZE;
        int[] sa = new int[n]; //后缀数组,表示排名为i的后缀的起始位置的下标
        int[] x = new int[n]; //字符的ascii码数组
        int[] y = new int[n];
        int[] ysort = new int[n];
        int[] bucket = new int[MSZ];

        Arrays.fill(bucket, 0);
        //为字符计数
        for (int i = 0; i < n; i++) {
            x[i] = ch[i];
            bucket[ch[i]]++;
        }
        //转为累积
        for (int i = 1; i < m; i++) {
            bucket[i] += bucket[i - 1];
        }
        //之后bucket[c]代表c最靠前是多少名，sa[i]代表排名为i的后缀的起始位置下标z
        for (int i = n - 1; i >= 0; i--) {
            sa[--bucket[ch[i]]] = i;
        }

        for (int k = 1, p = 0; p < n; k *= 2, m = p) {
            p = 0;
            //y[i]表示第二关键字排名为i的数，第一关键字的位置
            for (int i = n - k; i < n; i++)
                y[p++] = i;
            //第n-k到第n-1位是没有第二关键字的 所以排名在最前面
            for (int i = 0; i < n; i++) {
                if (sa[i] >= k)
                    y[p++] = sa[i] - k;
            }

            //排名为i的数 在数组中是否在第k位以后
            //如果满足(sa[i]>k) 那么它可以作为别人的第二关键字，就把它的第一关键字的位置添加进y就行了
            //所以i枚举的是第二关键字的排名，第二关键字靠前的先入队
            for (int i = 0; i < n; i++)
                ysort[i] = x[y[i]];


            //初始化bucket
            Arrays.fill(bucket, 0);
            for (int i = 0; i < n; i++) {
                bucket[x[i]]++;
            }
            //第一关键字排名为1~i的数有多少个
            for (int i = 1; i < m; i++)
                bucket[i] += bucket[i - 1];
            for (int i = n - 1; i >= 0; i--) {
                sa[--bucket[x[y[i]]]] = y[i];
                y[i] = 0;
            }

//            Arrays.fill(y, 0);
            System.arraycopy(x, 0, y, 0, n);

            x[sa[0]] = 0;
            p = 1;
            for (int i = 1; i < n; i++)
                x[sa[i]] = compare(y, sa[i], sa[i - 1], k) ? p - 1 : p++;
        }

        return sa;

    }

    private int[] getRank(int[] sa) {
        int len = sa.length;
        int[] rank = new int[len];
        for (int i = 0; i < len; i++)
            rank[sa[i]] = i;
        return rank;
    }

    /**
     * height[i]表示sa[i]与sa[i-1]代表的后缀字符串的最长公共前缀长度
     * 计算时间复杂度O(n)，空间复杂度O(n)
     */
    private int[] getHeight(String s, int[] sa) {
        char[] ch = s.toCharArray();
        int len = sa.length;
        int[] h = new int[len];
        int[] rank = getRank(sa);

        Arrays.fill(h, 0);
        int index = 0;
        for (int i = 0; i < len; i++) {
            if (rank[i] == 0)
                continue;
            for (int j = sa[rank[i] - 1]; inBounds(i + index, j + index, len) && ch[i + index] == ch[j + index]; )
                index++;
            h[rank[i]] = index;
            if (index > 0)
                index--;
        }
        return h;
    }

    private boolean inBounds(int i, int j, int len) {
        return i < len && j < len;
    }

    private boolean compare(int[] y, int a, int b, int l) {
        int[] tmp = new int[y.length + 1];
        System.arraycopy(y, 0, tmp, 0, y.length);
        return tmp[a] == tmp[b] && tmp[a + l] == tmp[b + l];
    }

}
