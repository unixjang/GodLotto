package org.hyochan.godlotto.data;

import java.util.Arrays;

/**
 * Created by hyochan on 2016-01-24.
 */
public class RollingData {

    private int numbers[];

    public RollingData(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public String toString(){
        return Arrays.toString(numbers);
    }
}
