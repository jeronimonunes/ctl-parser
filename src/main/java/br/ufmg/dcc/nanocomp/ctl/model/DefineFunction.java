package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public class DefineFunction implements CtlObject {

	private Function name;
	
	private Function body;
	
	public DefineFunction() {
		
	}

	public DefineFunction(Function name, Function body) {
		this();
		this.name = name;
		this.body = body;
	}
	
	public Function getName() {
		return name;
	}
	
	public void setName(Function name) {
		this.name = name;
	}
	
	public Function getBody() {
		return body;
	}
	
	public void setBody(Function body) {
		this.body = body;
	}

	@Override
	public void write(Appendable appendable) throws IOException {
		appendable.append("(define ");
		getName().writeDefinition(appendable);
		appendable.append(" ");
		getBody().writeDefinition(appendable);
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
