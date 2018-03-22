package com.lin.unittestdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Created by lin on 2018/3/21.
 * 参数化测试
 */
@RunWith(Parameterized.class)
public class DateUtilTest2 {
    private String time ;
    private long timeStamp = 1508054412000L;
    private Date mDate;

    public DateUtilTest2(String time){
        this.time = time;
    }

    @Parameterized.Parameters
    public static Collection primeNum() {
        return Arrays.asList(new String[]{
                "2017-10-15",
                "2017-10-15 16:00:12",
                "2017年10月15日 16时00分12秒"
        });
    }

    @Before
    public void setUp() {
        System.out.println("text begin");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void tearDown() {
        System.out.println("text ended");
    }


    @Test
        public void DateTostampTest() throws ParseException {
        System.out.println(time);
        DateUtil.dateToStamp(time);
    }
}
