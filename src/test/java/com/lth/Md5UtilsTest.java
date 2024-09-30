package com.lth;

import com.lth.utils.Md5Util;
import org.junit.jupiter.api.Test;

public class Md5UtilsTest {

    @Test
    public void testMd5(){
        String password = "admin";
        String s = Md5Util.getMD5String(password);

        System.out.println(s);
    }

}
