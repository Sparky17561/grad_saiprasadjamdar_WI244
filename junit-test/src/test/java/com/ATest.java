package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ATest {

    // ✅ 1️⃣ Test void method execution
    @Test
    void testVoidMethodExecution() {
        I obj = new A();

        obj.abc();

        A impl = (A) obj;
        assertEquals(1, impl.getCounter());
    }

    // ✅ 2️⃣ Test method called N times
    @Test
    void testMethodCalledNTimes() {
        I obj = new A();

        obj.abc();
        obj.abc();
        obj.abc();

        A impl = (A) obj;

        assertEquals(3, impl.getCounter());
    }

    // ✅ 3️⃣ Fail case example
    @Test
    void testMethodCallFailureExample() {
        I obj = new A();

        obj.abc();
        obj.abc();

        A impl = (A) obj;

        // This will fail if expecting 3
        assertEquals(2, impl.getCounter());
    }
}