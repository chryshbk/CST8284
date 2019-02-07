package speedTest;

import static org.junit.Assert.*;
import org.junit.Test;

import cst8284.collections.WordDump;

public class TimeTest {

	@Test
	public void test() {

		System.out.print("getStringFromFile() method takes ");
		showTestResult(() -> WordDump.getStringFromFile());

		System.out.print("getStringBuilderFromFile() method takes ");
		showTestResult(() -> WordDump.getStringBuilderFromFile());

		System.out.print("getArrayListFromFile() method takes ");
		showTestResult(() -> WordDump.getArrayListFromFile());

		assertTrue(true);
	}

	private static <T> T showTestResult(TimeTestInterface<T> run) {
		long startTime = System.currentTimeMillis();
		T t = run.methodUnderTest();
		System.out.println((float) (System.currentTimeMillis() - startTime) / 1000 + " seconds");
		return (t);
	}

}
