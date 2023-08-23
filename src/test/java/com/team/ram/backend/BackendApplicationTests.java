package com.team.ram.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BackendApplicationTests {
    @Test
    public void main() {
        BackendApplication.main(new String[] {});
        assertTrue(true);
    }
}
