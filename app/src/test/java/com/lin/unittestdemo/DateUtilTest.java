package com.lin.unittestdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by lin on 2018/3/21.
 * 执行顺序：@BeforeClass –> @Before –> @Test –> @After –> @AfterClass
 */

public class DateUtilTest {
    private String time = "2017-10-15 16:00:12";
    private long timeStamp = 1508054412000L;
    private Date mDate;

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
    public void stampToDateTest() {
        assertEquals(time, DateUtil.stampToDate(timeStamp));
    }
    @Test
    public void DateTostampTest() throws ParseException {
        assertEquals(timeStamp, DateUtil.dateToStamp(time));
    }

    /**
     * assertThat(T actual, Matcher<? super T> matcher);
      assertThat(String reason, T actual, Matcher<? super T> matcher);
      其中reason为断言失败时的输出信息，actual为断言的值，matcher为断言的匹配器。
     * @throws ParseException
     */
    @Test
    public void assertThatTest() throws ParseException {
        assertThat(timeStamp, equalTo(DateUtil.dateToStamp(time)));
//        assertThat("sss",1508054312000L, equalTo(DateUtil.dateToStamp(time)));
    }
}
