import org.junit.*
import org.junit.runner.*

class GRunListenerTest {
	
	def screen = new Screen(out: new StringWriter())
	def listener = new GRunListener(screen: screen)
	
	@Test void showsADotForAStartingTest() {
		listener.testStarted(null)
		assert '.' == screen.toString()
	}

	def format2Result = [
		'Tests: 1': [wasSuccessful: {}, getRunCount: { 1 }],
		'(OK)': [wasSuccessful: { true }, getRunCount: { 1 }],
		'Failures: 1': [wasSuccessful: { false }, getFailureCount: { 1 }],
		'Ignored: 1': [wasSuccessful: {}, getIgnoreCount: { 1 }],
		'no tests found': [wasSuccessful: {}, getRunCount: { 0 }],
	]
	
	@Test void countsTests() {
		format2Result.each{ format, result -> 
			screen.out = new StringWriter()
			assertFormattedResult(format, result)
	 	}
	}
	
	def assertFormattedResult(expected, result) {
		listener.testRunFinished(result as Result)
		assert listener.screen.toString().contains(expected)
	}
	
}