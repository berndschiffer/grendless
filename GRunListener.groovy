import org.junit.runner.notification.*
import org.junit.runner.*

class GRunListener extends RunListener {
	
	def screen
	
	def parserRunStarted = { screen.startAfresh() }
	
	void testFailure(Failure failure) {
		screen.printNewLine()
		def (testName, testClass) = failure.description.displayName.split('[\\(\\)]')
		screen << "Failure in ${testClass}.${testName}\n"
		screen << "${failure.message}\n"
		printStackTrace(failure)
	}
	
	void testUnparsable(Failure failure) {
		testFailure(failure)
	}
	
	private printStackTrace(failure) {
		failure.exception.stackTrace.each{
			if(!it.fileName || it.fileName.endsWith('.java')) return
			screen << "\tat $it\n"
		}
	}
	
	void testStarted(Description description) {
		screen << '.'
	}
		
	void testRunFinished(Result result) {
		screen.printNewLine()
		screen << "Tests: ${result.runCount}"
		screen << (result.wasSuccessful() ? ' (OK)' : " , Failures: ${result.failureCount}")
		if(!result.runCount) screen << ": no tests found :("
		if(result.ignoreCount) screen << ", Ignored: ${result.ignoreCount}"
	}
}