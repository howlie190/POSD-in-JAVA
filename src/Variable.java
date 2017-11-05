
public class Variable extends Term {
	private Term _ref = null;
	public Variable(String symbol) { super(symbol); }
	public Variable getVariable() { return this; }
	public String value() {
		if(_ref == null)
			return super.value();
		else
			return _ref.value();
	}
	public boolean match(Term term) {
		if(value().equals(term.value()))
			return true;
		if(_ref == null) {
			_ref = term;
			return true;
		}
		return _ref.match(term);
	}
}
