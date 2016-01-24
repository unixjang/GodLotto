package org.hyochan.godlotto.utils.java;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hyochan on 2016-01-21.
 */
public class NumGenerator
{

    private static NumGenerator numGenerator;
    private final int MAX_NUM = 45;
    private final int SIZE = 6;
    private int numArray[];

    public NumGenerator()
    {
        numArray = new int[SIZE];
    }

    public static NumGenerator getInstance()
    {
        if(numGenerator == null)
            numGenerator = new NumGenerator();
        return numGenerator;
    }

    public int[] generate()
    {
        Random random = new Random();
        for(int i = 0; i < SIZE; i++)
        {
            int num;
            for(num = random.nextInt(MAX_NUM) + 1; checkDuplicateNum(num, i); num = random.nextInt(MAX_NUM) + 1);
            numArray[i] = num;
        }

        Arrays.sort(numArray);
        return numArray;
    }

    private boolean checkDuplicateNum(int num, int size)
    {
        for(int i = 0; i < size; i++)
            if(numArray[i] == num)
                return true;

        return false;
    }
}
