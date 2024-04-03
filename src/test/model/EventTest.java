// based on https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Event e2;
	private Date d;
	private ListOfDishLog listOfDishLog;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Sensor open at door");
		d = Calendar.getInstance().getTime();
		listOfDishLog = new ListOfDishLog();
		e2 = new Event("Sensor close at door");
	}
	
	@Test
	public void testEvent() {
		assertEquals("Sensor open at door", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
	}


	@Test
	public void testEqualsAndHashCode() {
		assertFalse(e.equals(listOfDishLog));
		assertFalse(e.equals(null));
		assertFalse(e.hashCode() == e2.hashCode());
	}
}
