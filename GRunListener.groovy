import org.junit.runner.notification.*

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
}