package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class Class implements CtlObject {
	
	private String name;
	
	private Map<String,Variable> properties;
	
	public Class() {
		
	}

	public Class(String name, Map<String, Variable> properties) {
		super();
		this.name = name;
		this.properties = properties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Variable> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Variable> properties) {
		this.properties = properties;
	}
	
	@Override
	public void write(Appendable appendable) throws IOException {
		appendable.append("(make ").append(name);
		if(properties!=null&&!properties.isEmpty()) {
			for(Entry<String, Variable> var : properties.entrySet()) {
				appendable.append(" (").append(var.getKey()).append(" ");
				var.getValue().write(appendable);
				appendable.append(")");
			}
		}
		appendable.append(")");
	}

}
