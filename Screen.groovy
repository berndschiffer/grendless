class Screen {
	
	def out
	def scrollLines
	
	def startAfresh() { scrollLines.times { out << '\n' } }
	
	void printNewLine() { out << '\n' }
	
	def leftShift(content) { out << content }
	
	String toString() { out.toString() }
}