import org.junit.*
import org.junit.runner.notification.*

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
	
	static class FailingTest {
		@Test void failure() { assert false }
	}
	
	@Test void triggersRunListenerForFailingTests() {
		def itIs = [:]
		runner.listener = new RunListener() {
			void testFailure(Failure failure) { itIs.called = true }
		}
		runner.run(FailingTest)
		assert itIs.called
	}
}