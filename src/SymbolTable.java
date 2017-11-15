import java.util.ArrayList;
class Val {
	private int val;
	public Val(int val) { this.val = val; }
	public int getVal() { return val; }
	public void setVal(int val) { this.val = val; }
}

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
	public boolean symbolExist(String s, Val val) {
		boolean found = false;
		int count;
		for(count = 0; count < _symbolTable.size(); count++)
			if(s.equals(_symbolTable.get(count).first()))
				found = true;
		val.setVal(count - 1);
		return found;
	}
	public ArrayList<pair> getSymbolTable() { return _symbolTable; }
}
