package com.android.kpe.automationchallenge;

import java.util.Random;

/**
 * Created by LocalUser on 19/10/2017.
 */

public class RandomHelper {
    private RandomHelper() {}

    private static Random mRandom = new Random();

    public static int between(int min, int max) {
        return mRandom.nextInt(max + 1 - min) + min;
    }

    public static int max(int max) {
        return mRandom.nextInt(max + 1);
    }

    public static Boolean getRandomBoolean() {
        return between(1, 2) == 1;
    }

    public static String getRandomString(String ...args) {
        if(args == null || args.length == 0) {
            throw new NullPointerException();
        }
        int max = args.length - 1;
        int index = between(0, max);
        return args[index];
    }

}
