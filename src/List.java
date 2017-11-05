import java.util.ArrayList;
import java.util.Locale.LanguageRange;

public class List extends Term {
	private ArrayList<Term>_elements;
	public List() { _elements = null; }
	public List(ArrayList<Term>elements) { _elements = elements; }
	public List getList() { return this; }
	public String symbol() {
		if(_elements.isEmpty())
			return "[]";
		String result = "[";
		for(int i = 0; i < _elements.size() - 1; i++)
			result += (_elements.get(i).symbol() + ", ");
		return result += (_elements.get(_elements.size() - 1).symbol() + "]");
	}
	public String value() {
		if(_elements.isEmpty())
			return "[]";
		String result = "[";
		for(int i = 0; i < _elements.size() - 1; i++)
			result += (_elements.get(i).value() + ", ");
		return result += (_elements.get(_elements.size() - 1).value() + "]");
	}
	public boolean match(Term term) {
		List pList = term.getList();
		if(pList != null) {
			if(_elements.size() != pList._elements.size())
				return false;
			for(int i = 0; i < _elements.size(); i++)
				if(!_elements.get(i).match(pList._elements.get(i)))
					return false;
			return true;
		}
		return false;
	}
	public Term head() {
		return _elements.get(0);
	}
	public List tail() {
		ArrayList<Term>ret = new ArrayList<>(_elements.subList(1, _elements.size()));
		return new List(ret);
	}
}
