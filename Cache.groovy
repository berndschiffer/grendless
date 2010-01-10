class Cache {
	
	def action
	def cachedFiles
	
	def execute(action) {
		this.action = action
		this
	}
	
	def ifThereAreNew(files) {
		if(!areModified(files)) return
		update(files)
		action()
	}
	
	def update(files) {
		cachedFiles = files.collect{ file ->
			def lastModified = file.lastModified()
			[absolutePath: file.absolutePath, lastModified: { lastModified }]
		}
	}
	
	def areModified(files) {
		cachedFiles?.size() != files.size() || files.any{ file -> isModified(file) }
	}
	
	def isModified(file) {
		def cachedFile = cachedFiles.find { cached -> cached.absolutePath == file.absolutePath}
		!cachedFile || cachedFile?.lastModified() != file.lastModified()
	} 
}