package org.ricky.core.common.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/26
 * @className ListUtils
 * @desc 列表工具
 */
public class ListUtils {

    /**
     * 求两个列表的差集，list1 - list2<br>
     * 时间复杂度：O(n + m)，n为第一个列表的长度，m为第二个列表的长度<br>
     *
     * @param list1 第一个列表
     * @param list2 第二个列表
     * @return 差集
     */
    public static <T> Set<T> diff(List<T> list1, List<T> list2) {
        HashSet<T> result = new HashSet<>(list1);
        result.removeAll(new HashSet<>(list2));
        return result;
    }

}
