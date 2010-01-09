class Parser {

	def parse(... tests) {
		tests.collect{ 
				new GroovyClassLoader().parseClass(it)
		}
	}
}