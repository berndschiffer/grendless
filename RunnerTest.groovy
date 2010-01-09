import org.junit.*

class RunnerTest {
	
	def runner = new Runner()
	
	static class SingleTest {
		@Test void success() {}
	}
	
	@Test void runsSingleTest() {
		assertSuccessfulRunCount 1, runner.run(SingleTest)
	}
	
	@Test void runsSeveralTests() {
		assertSuccessfulRunCount 2, runner.run(SingleTest, SingleTest)
	}
	
	def assertSuccessfulRunCount(runCount, result) {
		assert runCount == result.runCount
		assert result.wasSuccessful()
	}
}