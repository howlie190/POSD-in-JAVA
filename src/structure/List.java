package structure;

import java.util.ArrayList;

import basic.Term;
import basic.Variable;

public class List implements Term {
	private ArrayList<Term>_elements;
	public List() { _elements = new ArrayList<>(); }
	public List(ArrayList<Term>elements) { _elements = elements; }
	public List getList() { return this; }
	public String symbol() {
		if(_elements.isEmpty())
			return "[]";
		String _result = "[";
		for(int i = 0; i < _elements.size() - 1; i++)
			_result += (_elements.get(i).symbol() + ", ");
		return _result += (_elements.get(_elements.size() - 1).symbol() + "]");
	}
	public String value() {
		if(_elements.isEmpty())
			return "[]";
		String _result = "[";
		for(int i = 0; i < _elements.size() - 1; i++)
			_result += (_elements.get(i).value() + ", ");
		return _result += (_elements.get(_elements.size() - 1).value() + "]");
	}
	public boolean match(Term term) {
		List pList = term.getList();
		Variable pVariable = term.getVariable();
		if(pList != null) {
			boolean _result = true;
			if(_elements.size() != pList._elements.size())
				_result = false;
			else {
				for(int i = 0; i < _elements.size(); i++) {
					if(!(_elements.get(i).match(pList._elements.get(i)))) {
						_result = false;
						break;
					}
				}
			}
			return _result;
		}
		if(pVariable != null)
			return pVariable.match(this);
		return false;
	}
}
