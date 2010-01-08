import org.junit.*

class TestResultFormatterTest {
	
	def out = new StringWriter()
	def formatter = new TestResultFormatter(out: out)
	
	def format2Result = [
		'Tests: 1': [wasSuccessful: {}, runCount: 1],
		'(OK)': [wasSuccessful: { true }, runCount: 1],
		'Failures: 1': [wasSuccessful: { false }, failureCount: 1],
	]
	
	@Test void countsTests() {
		format2Result.each{ format, result -> assertFormattedResult(format, result) }
	}
	
	def assertFormattedResult(expected, result) {
		formatter.format(result)
		assert out.toString().contains(expected)
	}
}