class Runner {

	def core = new org.junit.runner.JUnitCore()
	def listener
	
	void setListener(listener) {
		core.addListener(listener)
	}
	
	def run(... testClasses) {
		def result = core.run(*testClasses)
	}
}