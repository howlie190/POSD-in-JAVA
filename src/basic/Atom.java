package basic;

public class Atom implements Term {
	private String _symbol;
	public Atom(String symbol) { _symbol = symbol; }
	public String symbol() { return _symbol; }
	public boolean match(Term term) {
		Variable pVariable = term.getVariable();
		if(pVariable != null)
			return pVariable.match(this);
		return value() == term.value();
	}
}
