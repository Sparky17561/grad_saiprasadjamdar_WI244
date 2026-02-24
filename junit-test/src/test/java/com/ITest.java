package com;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class ITest {

    // ✅Test void method execution (interaction testing)
    @Test
    void testVoidMethodCalled() {

        // Create mock of interface
        I mockObj = mock(I.class);

        // Call method
        mockObj.abc();

        // Verify method called once
        verify(mockObj, times(1)).abc();
    }

    // ✅Verify method called N times
    @Test
    void testMethodCalledNTimes() {

        I mockObj = mock(I.class);

        mockObj.abc();
        mockObj.abc();
        mockObj.abc();

        // Pass if called exactly 3 times
        verify(mockObj, times(3)).abc();
    }

    //Fail if not called required number of times
    @Test
    void testMethodCallFailure() {

        I mockObj = mock(I.class);

        mockObj.abc();
        mockObj.abc();

        // This will FAIL if expecting 3 times
        verify(mockObj, times(3)).abc();
    }
}