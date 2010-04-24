class Screen {
	
	def out
	def afreshLines
	def delayer
	
	def startAfresh() { 
		afreshLines.times {
			out << '\n'
			delayer?.delay()
		}
	}
	
	void printNewLine() { out << '\n' }
	
	def leftShift(content) { out << content }
	
	String toString() { out.toString() }
}