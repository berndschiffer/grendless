import org.junit.runner.notification.*
import org.junit.runner.*

class Parser {
	
	def listener

	def parse(... tests) {
		listener?.parserRunStarted?.call()
		tests.inject([]) { classes, test -> parseTest(test, classes) }
	}
	
	def parseTest(test, classes) {
		try {
			classes << new GroovyClassLoader().parseClass(test) }
		catch(Exception exception) {
			def name = test instanceof String ? 'script' : test.name
			def description =
					Description.createTestDescription(exception.getClass(), name)
			listener?.testUnparsable(new Failure(description, exception))
		}
		classes
	}
}