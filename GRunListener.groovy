import org.junit.runner.notification.*
import org.junit.runner.*

class GRunListener extends RunListener {
	
	def out
	
	void testFailure(Failure failure) {
		def (testName, testClass) = failure.description.displayName.split('[\\(\\)]')
		out << "Failure in ${testClass}.${testName}\n"
		out << "${failure.message}\n"
		failure.exception.stackTrace.each{
			if(!it.fileName) return
			if(it.fileName.endsWith('.java')) return
			out << "\tat $it\n"
		}
	}
	
	void testStarted(Description description) {
		out << '.'
	}
	
	void testRunFinished(Result result) {
		out << '\n'
		out << "Tests: ${result.runCount}"
		out << (result.wasSuccessful() ? ' (OK)' : " , Failures: ${result.failureCount}")
		if(result.ignoreCount) out << ", Ignored: ${result.ignoreCount}"
		out << '\n'
	}
}