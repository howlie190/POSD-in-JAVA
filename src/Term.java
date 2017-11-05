import java.text.DecimalFormat;

public class Term {
	protected String _symbol;
	protected Term() { _symbol = ""; }
	protected Term(String symbol) { _symbol = symbol; }
	protected Term(double value) {
		DecimalFormat format = new DecimalFormat("0.######");
		_symbol = format.format(value);
	}
	public Variable getVariable() { return null; }
	public List getList() { return null; }
	public String symbol() { return _symbol; }
	public String value() { return symbol(); }
	public boolean match(Term term) {
		Variable pVariable = term.getVariable();
		if(pVariable != null)
			return pVariable.match(this);
		return value().equals(term.value());
	}
}
