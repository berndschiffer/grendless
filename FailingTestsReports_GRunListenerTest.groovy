import org.junit.*
import org.junit.runner.notification.Failure
import org.junit.runner.Description

class FailingTestsReports_GRunListenerTest {
	
	def screen = new Screen(out: new StringWriter())
	def listener = new GRunListener(screen: screen)
	
	@Before void addFailureToListener() {
		def description = Description.createTestDescription(this.getClass(), 'name')
		def failure = new Failure(description, throwException())
		listener.testFailure(failure)
	}
	
	def throwException() {
		try { createExceptionWithUnknownSources() }
		catch(Exception exception) { return exception }
	}
	
	def createExceptionWithUnknownSources() {
		Eval.me("throw Exception('message')")
	}
	
	@Test void showsLocationOfFailure() {
		assert screen.toString().contains('Failure in FailingTestsReports_GRunListenerTest.name')
	}
	
	@Test void showsExceptionMessage() {
		assert screen.toString().contains('message')		
	}
	
	@Test void showsStackTrace() {
		assert screen.toString().contains('\tat ')				
	}
	
	@Test void filtersOutAllJavaTraceFromStack() {
		assert !screen.toString().contains('.java:')				
	}
	
	@Test void filtersOutAllUnknownSourcesFromStack() {
		assert !screen.toString().contains('Unknown Source')				
	}
}