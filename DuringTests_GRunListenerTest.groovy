import org.junit.*

class DuringTests_GRunListenerTest {
	
	def out = new StringWriter()
	def listener = new GRunListener(out: out)
	
	@Test void showsADotForAStartingTest() {
		listener.testStarted(null)
		assert '.' == out.toString()
	}
	
	@Test void printsANewLineAfterTestsFinish() {
		listener.testRunFinished(null)
		assert '\n' == out.toString()
	}
}