class Grendless {
	
	def formatter
	
	def Grendless(out) {
		formatter = new TestResultFormatter(out: out)
	}
	
	def run(file) {
		def files = new Filter().filter(file)
		def result = new Runner().run(*files)
		formatter.format(result)
	}
	
	static void main(args) {
		def runner = new Grendless(System.out)

		for(;;) {
			sleep 2000
			runner.run(new File(args[0]))
		}
	}
}

