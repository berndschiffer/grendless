class Collector {
	
	def collect(file) {
		if(file.file) return [file]
		collectFiles(file)
	}
	
	def collectFiles(dir) {
		def files = []
		dir.eachFileRecurse{ files << it }
		files
	}
}