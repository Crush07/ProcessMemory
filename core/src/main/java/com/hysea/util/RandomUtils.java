package com.hysea.util;

import java.util.*;

/**
 * @author hysea
 */
public class RandomUtils {

    public static List<Integer> randomIndex(Integer arrayLength,
                                            Integer number){
        List<Integer> indexList = new ArrayList<>();
        for(int i = 0;i < arrayLength;i++){
            indexList.add(i);
        }
        return randomIntegers(indexList,number);
    }
    public static List<Integer> randomIndexAndExclude(Integer arrayLength,
                                                      List<Integer> excludeIndex,
                                                      Integer number) {
        List<Integer> indexList = new ArrayList<>();
        for(int i = 0;i < arrayLength;i++){
            indexList.add(i);
        }
        return randomIntegersAndExclude(indexList,excludeIndex,number);
    }

    public static List<Integer> randomIntegers(List<Integer> ols,
                                               Integer number) {
        List<Integer> list = new ArrayList<>();
        //特殊情况 需要0个题目
        if (number == null || number == 0) {
            return list;
        }

        //最大随机数
        int maxsize = ols.size();
        //存放随机数
        Set<Integer> randomList = new HashSet<>();
        //随机数生成器
        Random random = new Random();

        //maxsize - number < 0 直接返回 ols
        if (maxsize - number <= 0) {
            return ols;
        }
        //maxsize - number < number 找maxsize - number个题目id并扔掉
        else if (maxsize - number < number) {
            while (randomList.size() < maxsize - number) {
                int i = random.nextInt(maxsize);
                randomList.add(i);
            }
            for (int i = 0; i < ols.size(); i++) {
                if (!randomList.contains(i)) {
                    list.add(ols.get(i));
                }
            }
            return list;
        }
        //maxsize - number > number 直接找number个随机数
        else {
            while (randomList.size() < number) {
                int i = random.nextInt(maxsize);
                randomList.add(i);
            }
            for (Integer r : randomList) {
                list.add(ols.get(r));
            }
            return list;
        }
    }

    public static List<Integer> randomIntegersAndExclude(List<Integer> ols,
                                                         List<Integer> exclude,
                                                         Integer number) {
        //  排序后 二分查找剔除重复
        Collections.sort(exclude);
        for (int i = 0; i < ols.size(); i++) {
            int max = exclude.size() - 1;
            int min = 0;
            int v;
            while (min < max) {
                v = (max + min) / 2;
                if (ols.get(i) > exclude.get(v)) {
                    min = v + 1;
                } else if (ols.get(i) < exclude.get(v)) {
                    max = v - 1;
                } else {
                    ols.remove(i);
                    i--;
                    break;
                }
            }
        }
        return randomIntegers(ols, number);
    }

}
