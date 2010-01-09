import org.junit.*

class RunnerTest {
	
	def runner = new Runner()
	
	def singleTest = """import org.junit.*
class SingleTest {
	@Test void success() {}
}"""
	
	@Test void runsSingleTest() {
		assertSuccessfulRunCount 1, runner.run(singleTest)
	}
	
	def assertSuccessfulRunCount(runCount, result) {
		assert runCount == result.runCount
		assert result.wasSuccessful()
	}
	
	@Test void runsSeveralTests() {
		assertSuccessfulRunCount 2, runner.run(singleTest, singleTest)
	}	
}