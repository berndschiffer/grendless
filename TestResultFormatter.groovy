class TestResultFormatter {
	
	def out
	
	def format(result) {
		out.print "Tests: ${result.runCount}"
		if(result.wasSuccessful()) out.print ' (OK)'
		else out.println " , Failures: ${result.failureCount}"
		out << '\n'
	}
}