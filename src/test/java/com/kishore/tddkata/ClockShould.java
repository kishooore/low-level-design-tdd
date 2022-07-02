package com.kishore.tddkata;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class ClockShould {

    @Test
    void return_today_date_as_dd_MM_yyyy_format() {
        Clock clock = new TestableClock();
        String today = clock.todayAsString();
        assertThat(today).isEqualTo("24/05/2015");
    }

    private static class TestableClock extends Clock {
        @Override
        protected LocalDate today() {
            return LocalDate.of(2015, 05, 24);
        }
    }
}
