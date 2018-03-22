package com.lin.unittestdemo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lin on 2018/3/22.
 * Mockito框架不支持mock匿名类、final类、static方法、private方法。而PowerMock框架解决了这些问题
 * Robolectric：它通过实现一套JVM能运行的Android代码，从而做到脱离Android运行环境进行测试。
 */

public class MockitoRuleTest {
    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();
    @Mock
    Person mPerson;
    /**
     * 其他方法
     * reset(T … mocks)	重置Mock
     * spy(Class<T> classToSpy)	实现调用真实对象的实现
     * inOrder(Object… mocks)	验证执行顺序
     *
     * @InjectMocks注解 自动将模拟对象注入到被测试对象中
     */
    @Spy
    Person mManPerson;

    @Test
    public void testSpyIsNotNull() {
        Assert.assertNotNull(mManPerson);
    }

    @Test
    public void testIsNotNull() {
        Assert.assertNotNull(mPerson);
    }

    /**
     * 打桩方法
     * “打桩”顾名思义就是将我们Mock出的对象进行操作，比如提供模拟的返回值等，给Mock打基础。
     */
    @Test
    public void testPersonReturn() {
        when(mPerson.getName()).thenReturn("lin");
        when(mPerson.getSex()).thenThrow(new NullPointerException("do not what sex it is"));
        System.out.println(mPerson.getName());
        System.out.println(mPerson.getSex());
    }

    /**
     * thenAnswer：对结果进行拦截
     */
    @Test
    public void testthenAnswer() {
        when(mPerson.eat(anyString())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return args[0].toString() + "真好吃";
            }
        });

        System.out.println(mPerson.eat("饺子"));
    }

    /**
     * 前面所说的都是状态测试，但是如果不关心返回结果，
     * 而是关心方法有否被正确的参数调用过，这时候就应该使用验证方法了。
     * 从概念上讲，就是和状态测试所不同的“行为测试”了。
     * <p>
     * 常用的验证方法
     * verify(T mock)
     */
    @Test
    public void testVerify() {
        when(mPerson.getName()).thenReturn("lin");
        //延时1秒验证
        System.out.println(System.currentTimeMillis());
        System.out.println(mPerson.getName());
        verify(mPerson, after(1000)).getName();
        System.out.println(System.currentTimeMillis());

        //至少验证2次
        mPerson.getSex();
        mPerson.getSex();
        verify(mPerson, atLeast(2)).getSex();

        //至多验证2次
//        mPerson.getSex();
        verify(mPerson, atMost(2)).getSex();

        //验证方法在100ms超时前调用了2次
        verify(mPerson, timeout(100).times(4)).getSex();
    }

    /**
     * 常用参数匹配器
     */
    @Test
    public void testPersonAny() {
        when(mPerson.eat(any(String.class))).thenReturn("米饭");
        //或
        //when(mPerson.eat(anyString())).thenReturn("米饭");

        //输出米饭
        System.out.println(mPerson.eat("面条"));

    }

    @Test
    public void testPersonContains() {

        when(mPerson.eat(contains("面"))).thenReturn("面条");
        //输出面条
        System.out.println(mPerson.eat("面"));

    }

    @Test
    public void testPersonArgThat() {

        //自定义输入字符长度为偶数时，输出面条。
        when(mPerson.eat(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return argument.length() % 2 == 0;
            }
        }))).thenReturn("面条");
        //输出面条
        System.out.println(mPerson.eat("1234"));


    }

    /**
     * 验证执行顺序
     */
    @Test
    public void testInOrder() {
        mPerson.setName("lin");
        mPerson.setSex("man");
        InOrder inOrder = inOrder(mPerson);
        //正确
        inOrder.verify(mPerson).setName("lin");
        inOrder.verify(mPerson).setSex("man");

        //错误
//        inOrder.verify(mPerson).setSex("man");
//        inOrder.verify(mPerson).setName("lin");
    }
}
