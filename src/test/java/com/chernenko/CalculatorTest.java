package com.chernenko;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anton on 16.02.2017.
 */
@RunWith(Parameterized.class)
public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {7.2, -12.33, -5.13, 19.53, -88.776, -0.5839416058394161},
                {5.0, 0, 5.0, 5.0, 0.0, Double.POSITIVE_INFINITY},
                {88.1, 2.7, 90.8, 85.4, 237.87, 32.62962962962963}
        });
    }

    private double x, y, sum, sub, mul, div;

    public CalculatorTest(double x, double y, double sum, double sub, double mul, double div) {
        this.x = x;
        this.y = y;
        this.sum = sum;
        this.sub = sub;
        this.mul = mul;
        this.div = div;
    }

    @Test
    public void addition() throws Exception {
        assertEquals(sum, calculator.addition(x, y), 0.01);
    }

    @Test
    public void subtraction() throws Exception {
        assertEquals(sub, calculator.subtraction(x, y), 0.01);
    }

    @Test
    public void multiplication() throws Exception {
        assertEquals(mul, calculator.multiplication(x, y), 0.01);
    }

    @Test
    public void division() throws Exception {
        assertEquals(div, calculator.division(x, y), 0.01);
    }

    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new CalculatorListener());
        core.run(CalculatorTest.class);
    }
}

class CalculatorListener extends RunListener {

    @Override
    public void testStarted(Description desc) {
        System.out.println("Started: " + desc.getDisplayName());
    }

    @Override
    public void testFinished(Description desc) {
        System.out.println("Finished: " + desc.getDisplayName());
    }

    @Override
    public void testFailure(Failure fail) {
        System.out.println("Failed: " + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
    }
}
