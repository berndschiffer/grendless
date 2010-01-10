class Filter {
	
	def filter(files) {
		files.findAll{ it.name.contains('Test.groovy') || it.name.contains('Tests.groovy') }
	}
}