package com.assembble.gc;

import com.assembble.gc.dto.ErmInfoEventDto;
import com.assembble.gc.mapper.SensorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class GcApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SensorMapper sensorMapper;

	@Test
	public void testDatabaseConnection() {
		try {
			// Execute a simple query to check the database connection
			int result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

			// Assert that the result is 1
			assertEquals(1, result);
		} catch (Exception e) {
			// If an exception occurs, fail the test with an error message
			fail("Database connection test failed: " + e.getMessage());
		}
	}

	@Test
	public void testErmInfoEventSize() {
		// Execute the SQL query
		List<ErmInfoEventDto> ermInfoEventList = sensorMapper.getErmList();

		// Verify the size is 15
		int expectedSize = 15;
		assertEquals(expectedSize, ermInfoEventList.size(), "Size should be 15");
	}




}
