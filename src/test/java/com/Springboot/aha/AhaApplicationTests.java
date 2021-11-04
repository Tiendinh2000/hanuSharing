package com.Springboot.aha;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AhaApplicationTests {
    Cal cal = new Cal();

    @Test
    void contextLoads() {
        //given
        int a = 10;
        int b = 20;
        //when
        int expected = 31;
        int result = cal.add(a, b);

        // then
        assertThat(result).isEqualTo(expected);
    }

    class Cal {
        int add(int a, int b) {
            return a + b;
        }

        int minus(int a, int b) {
            return a - b;
        }
    }

}
