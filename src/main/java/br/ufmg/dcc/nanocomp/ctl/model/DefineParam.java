package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public class DefineParam implements CtlObject {

	private Variable value;
	
	public DefineParam() {
		
	}

	public DefineParam(Variable value) {
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
		appendable.append("(define-param ").append(value.getName()).append(" ");
		Object value = this.value.getValue();
		if(value instanceof CtlObject) {
			((CtlObject) value).write(appendable);
		} else {
			appendable.append(value.toString());
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
