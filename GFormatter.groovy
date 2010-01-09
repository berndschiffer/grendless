class TestResultFormatter {
	
	def out
	
	def format(result) {
		out << "Tests: ${result.runCount}"
		out << (result.wasSuccessful() ? ' (OK)' : " , Failures: ${result.failureCount}")
		if(result.ignoreCount) out << ", Ignored: ${result.ignoreCount}"
		out << '\n'
	}
}