import org.junit.*

class GrendlessTest {
	
	def out = new StringWriter()
	def runner = new Grendless(out)
	
	def singleTest = """import org.junit.*
class SingleTest {
	@Test void success() {}
}"""
	
	@Test void runsSingleTest() {
		runner.run(singleTest)
		assert out.toString().contains('Tests: 1')
	}
	
	@Test void runsSeveralTests() {
		runner.run(singleTest, singleTest)
		assert out.toString().contains('Tests: 2')
	}	
}