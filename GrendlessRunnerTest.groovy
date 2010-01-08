import org.junit.*

class GrendlessRunnerTest {
	
	def out = new StringWriter()
	
	def singleTest = """import org.junit.*
class SingleTest {
	@Test void success() {}
}"""
	
	@Test void runsSingleTest() {
		def runner = new GrendlessRunner(out)
		runner.run(singleTest)
		assert out.toString().contains('Tests: 1')
	}
	
}