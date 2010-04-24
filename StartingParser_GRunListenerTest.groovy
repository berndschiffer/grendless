import org.junit.*

class StartingParser_GRunListenerTest {
	
	def screen = new Screen(scrollLines: 2, out: new StringWriter())
	
	@Test void scrollsTextUpWhenTestRunStarted() {
		screen.startAfresh()
		assert screen.toString() == "\n\n"
	}
}