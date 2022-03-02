import java.util.*;

/**
 * 380. Insert Delete GetRandom O(1)
 * Medium
 * <p>
 * Implement the RandomizedSet class:
 * <p>
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -2^31 <= val <= 2^31 - 1
 * At most 2 * 10^5 calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 */
public class _380InsertDeleteGetRandomO1 {

    HashMap<Integer, Integer> table;
    Integer[] array;//使用包装类，可以用null指代空白
    int size; //实际大小
    int ptr; //指向空白处的指针

    /**
     * 使用哈希表和数组完成。数组保存数据和用于随机抽取，哈希表用于记录数字O(1)查找。
     */

    public _380InsertDeleteGetRandomO1() {
        table = new HashMap<>();
        array = new Integer[3]; //初始大小不能太大，否则早期的remove会导致连续resize，降低速度
        size = 0;
        ptr = 0;
    }

    public boolean insert(int val) {
        if (!table.containsKey(val)) {
            table.put(val, ptr);
            array[ptr] = val;
            size++;
            //resize参数随便设置的
            if (size >= array.length * 0.8) {
                resize(array.length * 2);
            }
            //找到下一个空白处
            while (array[ptr] != null) {
                ptr = (ptr + 1) % array.length;
            }
            return true;


        }
        return false;
    }


    public boolean remove(int val) {
        if (table.containsKey(val)) {
            ptr = table.remove(val);
            array[ptr] = null;
            size--;
            if (size < (array.length + 1) / 2) {
                resize(array.length * 2 / 3);
            }
            return true;
        }
        return false;
    }

    private void resize(int newSize) {
        //其实不知道这句有没有用，为了防止在1附近总是resize所以加了这一句
        if (newSize > 2) {
            Integer[] newArray = new Integer[newSize];
            for (int i = 0, j = 0; i < array.length; i++) {
                //顺带让新数组变紧凑
                if (array[i] != null) {
                    newArray[j] = array[i];
                    table.put(array[i], j);
                    j++;
                }
            }
            array = newArray;
        }
    }

    public int getRandom() {
        int i;
        //为了不让重复太多次，负载应该比较高
        while (true) {
            i = (int) (Math.random() * array.length);
            if (array[i] != null)
                return array[i];
        }
    }

}




