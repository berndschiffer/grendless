class Filter {
	
	def filter(file) {
		if(file.file) return [file]
		filterDir(file)
	}
	
	def filterDir(dir) {
		def files = []
		dir.eachFileRecurse{
			if(!it.name.contains('Test.groovy') && !it.name.contains('Tests.groovy')) return
			files << it
		}
		files
	}
}