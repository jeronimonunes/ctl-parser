package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public class SetParam implements CtlObject {
	
	private String name;
	
	private Variable value;
	
	public SetParam() {
		
	}

	public SetParam(String name, Variable value) {
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
		appendable.append("(set-param! ").append(name).append(" ");
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
