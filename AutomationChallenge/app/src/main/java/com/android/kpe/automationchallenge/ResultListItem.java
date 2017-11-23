package com.android.kpe.automationchallenge;

import java.util.ArrayList;
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
        this.one = getRandom(11, 12);
        this.two = getRandom(24, 25);
        this.three = getRandom(36, 37);
        this.four = getRandom(49, 50);
    }

    private String getRandom(int min, int max) {
        return String.valueOf(RandomHelper.between(min, max));
    }

    public static ResultListItem create() {
        return new ResultListItem();
    }

    public static ArrayList<ResultListItem> getRandomList(int count) {
        if(count < 1) {
            throw new IllegalArgumentException("count must be > 0");
        }

        ArrayList<ResultListItem> list = new ArrayList<ResultListItem>();
        for(int index=0; index<count; index++) {
            list.add(create());
        }

        return list;
    }

    public boolean isEqual(ResultListItem item) {
        if(item == null) {
            throw new NullPointerException();
        }
        return item.one.equals(one) && item.two.equals(two) && item.three.equals(three) && item.four.equals(four);
    }
}
