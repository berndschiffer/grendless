import org.junit.*

class ScreenTest {
	
	def screen = new Screen(afreshLines: 2, out: new StringWriter())
	
	@Test void scrollsTextUp() {
		screen.startAfresh()
		assert screen.toString() == "\n\n"
	}
	
	@Test void delaysScrolling() {
		def delays = 0
		screen.delayer = [delay: { delays++ }]
		screen.startAfresh()
		assert 2 == delays
	}
}