package org.example.structure.business.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculPrixTtcDefaultTest {
    /**
     * Method under test: {@link CalculPrixTtcDefault#apply(double, int)}
     */
    @Test
    void apply() {
        assertThat((new CalculPrixTtcDefault()).apply(5.0d, 0)).isEqualTo(5.0d);
        assertThat((new CalculPrixTtcDefault()).apply(5.0d, 100)).isEqualTo(10.0d);
        assertThat((new CalculPrixTtcDefault()).apply(5.0d, 75)).isEqualTo(8.75d);
        assertThat((new CalculPrixTtcDefault()).apply(5.0d, 50)).isEqualTo(7.50d);
        assertThat((new CalculPrixTtcDefault()).apply(5.0d, 25)).isEqualTo(6.25d);
        assertThat((new CalculPrixTtcDefault()).apply(10.5d, 15)).isEqualTo(12.075d);
        assertThat((new CalculPrixTtcDefault()).apply(0.0d, 1)).isEqualTo(0.0d);
        assertThat((new CalculPrixTtcDefault()).apply(-0.5d, 1)).isEqualTo(0.0d);
    }
}

