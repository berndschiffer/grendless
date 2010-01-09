import org.junit.*
import org.junit.runner.notification.Failure
import org.junit.runner.Description

class FailingTestsReports_GRunListenerTest {
	
	def out = new StringWriter()
	def listener = new GRunListener(out: out)
	
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
		assert out.toString().contains('Failure in FailingTestsReports_GRunListenerTest.name')
	}
	
	@Test void showsExceptionMessage() {
		assert out.toString().contains('message')		
	}
	
	@Test void showsStackTrace() {
		assert out.toString().contains('\tat ')				
	}
	
	@Test void filtersOutAllJavaTraceFromStack() {
		assert !out.toString().contains('.java:')				
	}
	
	@Test void filtersOutAllUnknownSourcesFromStack() {
		assert !out.toString().contains('Unknown Source')				
	}
	
	//@Test void foo() { assert false }
}