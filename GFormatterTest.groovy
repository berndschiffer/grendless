import org.junit.*

class GFormatterTest {
	
	def formatter = new GFormatter()
	
	def format2Result = [
		'Tests: 1': [wasSuccessful: {}, runCount: 1],
		'(OK)': [wasSuccessful: { true }, runCount: 1],
		'Failures: 1': [wasSuccessful: { false }, failureCount: 1],
		'Ignored: 1': [wasSuccessful: {}, ignoreCount: 1],
	]
	
	@Test void countsTests() {
		format2Result.each{ format, result -> 
			formatter.out = new StringWriter()
			assertFormattedResult(format, result)
	 	}
	}
	
	def assertFormattedResult(expected, result) {
		formatter.format(result)
		assert formatter.out.toString().contains(expected)
	}
}