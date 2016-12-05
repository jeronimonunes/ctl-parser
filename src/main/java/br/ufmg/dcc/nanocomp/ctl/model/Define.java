package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public class Define implements CtlObject {

	private Variable value;
	
	public Define() {
		
	}

	public Define(Variable value) {
		this();
		this.value = value;
	}

	public Variable getValue() {
		return value;
	}

	public void setValue(Variable value) {
		this.value = value;
	}
	
	@Override
	public void write(Appendable appendable) throws IOException {
		appendable.append("(define ").append(value.getName()).append(" ");
		value.writeValue(appendable);
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
