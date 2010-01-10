import org.junit.*

class StartingParser_GRunListenerTest {
	
	def out = new StringWriter()
	def listener = new GRunListener(scrollLines: 2, out: out)
	
	@Test void scrollsTextUpWhenTestRunStarted() {
		listener.parserRunStarted()
		assert out.toString() == "\n\n"
	}
}