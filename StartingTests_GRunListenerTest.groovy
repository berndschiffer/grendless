import org.junit.*

class StartingTests_GRunListenerTest {
	
	def out = new StringWriter()
	def listener = new GRunListener(scrollLines: 2, out: out)
	
	@Test void scrollsTextUpWhenTestRunStarted() {
		listener.testRunStarted(null)
		assert out.toString() == "\n\n"
	}
}