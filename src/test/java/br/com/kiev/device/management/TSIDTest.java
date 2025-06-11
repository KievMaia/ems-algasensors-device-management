package br.com.kiev.device.management;

import br.com.kiev.device.management.common.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class TSIDTest {
    @Test
    void shouldGenerateTSID() {
        var tsid = IdGenerator.generateTSID();
        Assertions.assertThat(tsid.getInstant())
                .isCloseTo(Instant.now(), Assertions.within(1, ChronoUnit.MINUTES));
    }
}
