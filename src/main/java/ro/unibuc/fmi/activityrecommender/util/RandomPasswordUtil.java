package ro.unibuc.fmi.activityrecommender.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPasswordUtil {

    public static String generate() {
        return RandomStringUtils.randomAscii(10);
    }

}
