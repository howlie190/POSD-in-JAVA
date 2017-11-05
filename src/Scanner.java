
public class Scanner {
	private SymbolTable _symbolTable = new SymbolTable();
	private String _buffer;
	private int _position;
	private int _tokenValue;
	private void processToken(String s, int tokenTyoe) {
		Integer val = -1;
		if(_symbolTable.symbolExist(s, val))
			_tokenValue = val;
		else {
			_symbolTable.getSymbolTable().add(new pair(s, tokenTyoe));
			_tokenValue = _symbolTable.getSymbolTable().size() - 1;
		}
	}
	public Scanner() {
		_buffer = "";
		_position = 0;
		_tokenValue = _symbolTable.NONE;
	}
	public void setInput(String in) { _buffer = in; }
	int tokenValue() { return _tokenValue; }
	int position() { return _position; }
	void backPosition() { _position--; }
	char currentChar() { return _buffer.charAt(_position); }
	int skipLeadingWhiteSpace() {
		for(; (_buffer.charAt(_position) == ' ' || _buffer.charAt(_position) == '\t') && _position < _buffer.length(); ++_position);
		return position();
	}
	String extractAtom() {
		int posBegin = position();
		
	}
}
