package com.android.kpe.automationchallenge;

import java.util.Random;

/**
 * Created by LocalUser on 12/10/2017.
 */

public class ResultListItem {
    public String one;
    public String two;
    public String three;
    public String four;

    private ResultListItem() {
        this.one = getRandom(1, 24);
        this.two = getRandom(25, 49);
        this.three = getRandom(50, 74);
        this.four = getRandom(75, 99);
    }

    private String getRandom(int min, int max) {
        return String.valueOf(new Random().nextInt(max + 1 - min) + min);
    }

    public static ResultListItem create() {
        return new ResultListItem();
    }
}
