package com.uifuture.spring.core.bean.spel;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 对应章节15.8  SpEL表达式
 *
 * @author chenhx
 * @version SpELTest.java, v 0.1 2019-05-08 23:56 chenhx
 */
public class SpELTest {

    @Test
    public void helloWorld1() {
        ExpressionParser parser = new SpelExpressionParser();
        //连接字符串，并且通过#end赋值变量
        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        Assert.assertEquals("Hello World!", expression.getValue(context));
    }

}