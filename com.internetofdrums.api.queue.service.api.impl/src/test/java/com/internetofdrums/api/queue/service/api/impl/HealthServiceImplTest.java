package com.internetofdrums.api.queue.service.api.impl;

import org.junit.Test;

public class HealthServiceImplTest {

    @Test
    public void thatGetHealthWorksCorrectly() {
        new HealthServiceImpl().getHealth();
    }
}
