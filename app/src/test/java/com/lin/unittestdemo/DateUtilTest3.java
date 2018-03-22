package com.lin.unittestdemo;

import org.junit.Rule;
import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by lin on 2018/3/21.
 * assertThat(T actual, Matcher<? super T> matcher);
   assertThat(String reason, T actual, Matcher<? super T> matcher);
   其中reason为断言失败时的输出信息，actual为断言的值，matcher为断言的匹配器。
 */

public class DateUtilTest3 {
    private String time = "2017-10-15 16:00:12";

    @Rule
    public MyRule mRule = new MyRule();
    @Test
    public void assertThatTest() throws ParseException {
        assertThat("sss",1508054312000L, equalTo(DateUtil.dateToStamp(time)));
    }
    @Test
    public void isPhoneNumTest() throws ParseException {
        assertThat("13822222222", new IsMobilePhoneMatcher());
    }
}
