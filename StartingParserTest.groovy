import org.junit.*

class StartingParserTest {
	
	def parser = new Parser()

	@Test void foo() {
		def listenerIsCalled = false
		parser.listener = [parserRunStarted: { listenerIsCalled = true }, 
											 testUnparsable: { failure -> }]
		parser.parse('-not parsable-')
		assert listenerIsCalled
	}
}