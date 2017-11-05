import java.util.ArrayList;

class pair {
	private String first;
	private int second;
	public pair(String first, int second) {
		this.first = first;
		this.second = second;
	}
	public String first() { return first; }
	public int second() { return second; }
}

public class SymbolTable {
	private ArrayList<pair>_symbolTable = new ArrayList<>();
	public int NONE = -1;
	public int EOS = '\0';
	public int NUMBER = 256;
	public int ATOM = 257;
	public int ATOMSC = 258;
	public int VAR = 259;
	public boolean isSpecialCh(char c) {
		return c == '+'
			|| c == '-'
		    || c == '*'
		    || c == '/'
		    || c == '<'
		    || c == '>'
		    || c == '.'
		    || c == '&'
		    || c == '\\'
		    || c == '~'
		    || c == '^'
		    || c == '$'
		    || c == '#'
		    || c == '@'
		    || c == '?'
		    || c == ':';
	}
	public boolean symbolExist(String s, Integer val) {
		boolean found = false;
		int count;
		for(count = 0; count < _symbolTable.size(); count++)
			if(s.equals(_symbolTable.get(count).first()))
				found = true;
		val = count;
		return found;
	}
	public ArrayList<pair> getSymbolTable() { return _symbolTable; }
}
