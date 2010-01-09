import org.junit.*
import org.junit.runner.notification.*

class ParserWithUnparsablesTest {
	
	def parser = new Parser()
	def failure
	def classes
	
	@Before void addListenerAndParsesUnparsableClass() {
		parser.listener = [testUnparsable: { input -> failure = input }]
		classes = parser.parse('-not parsable-')
	}
	
	@Test void returnsFailures() {
		assert Failure == failure.getClass()
	}
	
	@Test void refusesUnparsableClasses() {
		assert [] == classes
	}
	
	@Test void returnsExceptionClass() {
		assert failure.exception instanceof Exception
	}
}