package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class List extends ArrayList<Variable> implements CtlObject {

	private static final long serialVersionUID = 1L;
	
	public List(Collection<Variable> c) {
		super(c);
	}

	@Override
	public void write(Appendable appendable) throws IOException {
		appendable.append("(list");
		if(!isEmpty()) {
			for(Variable v : this) {
				appendable.append("\n\t");
				v.write(appendable);
			}
		}
		appendable.append(")");
	}
	
	@Override
	public String toString() {
		try {
			StringBuilder appendable = new StringBuilder();
			write(appendable);
			return appendable.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
