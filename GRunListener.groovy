import org.junit.runner.notification.*
import org.junit.runner.*

class GRunListener extends RunListener {
	
	def out
	def scrollLines
	
	def parserRunStarted = {
		scrollLines.times { out << '\n' }
	}
	
	void testFailure(Failure failure) {
		printNewLine()
		def (testName, testClass) = failure.description.displayName.split('[\\(\\)]')
		out << "Failure in ${testClass}.${testName}\n"
		out << "${failure.message}\n"
		printStackTrace(failure)
	}
	
	void testUnparsable(Failure failure) {
		testFailure(failure)
	}
	
	private printStackTrace(failure) {
		failure.exception.stackTrace.each{
			if(!it.fileName || it.fileName.endsWith('.java')) return
			out << "\tat $it\n"
		}
	}
	
	void testStarted(Description description) {
		out << '.'
	}
	
	void printNewLine() {
		out << '\n'
	}
	
	void testRunFinished(Result result) {
		printNewLine()
		out << "Tests: ${result.runCount}"
		out << (result.wasSuccessful() ? ' (OK)' : " , Failures: ${result.failureCount}")
		if(!result.runCount) out << ": no tests found :("
		if(result.ignoreCount) out << ", Ignored: ${result.ignoreCount}"
	}
}