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

    /**
     * 代码清单15-73
     */
    @Test
    public void helloWorld1() {
        ExpressionParser parser = new SpelExpressionParser();
        //连接字符串，并且通过#end赋值变量
        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        Assert.assertEquals("Hello World!", expression.getValue(context));
    }

    /**
     * 代码清单15-75
     * 演示字面量表达式的double和null类型
     */
    @Test
    public void spelGrammarTest() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("2.5");
        System.out.println(expression.getValue(Double.class));
        expression = parser.parseExpression("null");
        Object obj = expression.getValue();
        //对象为null
        System.out.println(obj == null);
        expression = parser.parseExpression("null");
        String str = expression.getValue(String.class);
        //字段串的值为null
        System.out.println(str);
    }

    /**
     * 代码清单15-76
     * 演示算术运算表达式的除和求余（MOD）
     */
    @Test
    public void spelArithmeticTest() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("5.0/3.0");
        System.out.println(expression.getValue(Double.class));
        expression = parser.parseExpression("7 MOD 4");
        System.out.println(expression.getValue(Integer.class));
    }


    /**
     * 代码清单15-77
     * 演示关系表达式大于等于和区间
     */
    @Test
    public void spelRelationalTest() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("4>=5");
        System.out.println(expression.getValue(Boolean.class));
        expression = parser.parseExpression("1 between {1, 2}");
        System.out.println(expression.getValue(Boolean.class));
    }

}