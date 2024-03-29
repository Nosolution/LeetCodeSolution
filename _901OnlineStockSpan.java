import java.util.ArrayList;
import java.util.List;

/**
 * 901. Online Stock Span
 * Medium
 * <p>
 * <p>
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
 * <p>
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to today's price.
 * <p>
 * For example, if the price of a stock over the next 7 days were [100,80,60,70,60,75,85], then the stock spans would be [1,1,1,2,1,4,6].
 * Implement the StockSpanner class:
 * <p>
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * Output
 * [null, 1, 1, 1, 2, 1, 4, 6]
 * <p>
 * Explanation
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // return 1
 * stockSpanner.next(80);  // return 1
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(70);  // return 2
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
 * stockSpanner.next(85);  // return 6
 */
public class _901OnlineStockSpan {

    static class StockSpanner {
        List<Integer> source;
        List<Integer> spans;

        public StockSpanner() {
            source = new ArrayList<>();
            spans = new ArrayList<>();

        }

        public int next(int price) {
            int i = source.size() - 1;
            int ct = 1;
            while (i >= 0 && source.get(i) <= price) {
                ct += spans.get(i);
                i -= spans.get(i);
            }
            source.add(price);
            spans.add(ct);
            return ct;
        }
    }


}