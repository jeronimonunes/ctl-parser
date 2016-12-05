package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public class Set implements CtlObject {
	
	private String name;
	
	private Variable value;
	
	public Set() {
		
	}

	public Set(String name, Variable value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Variable getValue() {
		return value;
	}

	public void setValue(Variable value) {
		this.value = value;
	}
	
	@Override
	public void write(Appendable appendable) throws IOException {
		appendable.append("(set! ").append(name).append(" ");
		value.write(appendable);
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
