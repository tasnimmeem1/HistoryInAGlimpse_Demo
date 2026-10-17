package com.historyinaglimpse.animation_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"supabase.secret-key=test-only-key", "supabase.url=http://127.0.0.1:9"})
class AnimationBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
