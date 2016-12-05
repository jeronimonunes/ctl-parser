package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class File implements CtlObject {

	private List<CtlObject> objects;

	public File() {

	}

	public File(List<CtlObject> objects) {
		this.objects = objects;
	}

	public List<CtlObject> getObjects() {
		return this.objects;
	}

	public void setObjects(List<CtlObject> objects) {
		this.objects = objects;
	}

	@Override
	public String toString() {
		try {
			Writer appendable = new StringWriter();
			write(appendable);
			return appendable.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(Appendable appendable) throws IOException {
		for(CtlObject o : objects) {
			o.write(appendable);
			appendable.append("\n\n");
		}
	}

}
