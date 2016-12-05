package br.ufmg.dcc.nanocomp.ctl.test;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

import br.ufmg.dcc.nanocomp.ctl.model.File;
import br.ufmg.dcc.nanocomp.ctl.parser.ParserFactory;

public class ParserTest {
	
	private static class NoWriter extends Writer {

		@Override
		public void write(char[] cbuf, int off, int len) {
			
		}

		@Override
		public void flush() {
			
		}

		@Override
		public void close() {
			
		}
		
	};
	
	public ParserTest() {
		
	}
	
	@Test
	public void test() throws IOException {
		for(String v : Arrays.asList("/and.ctl","/fg.ctl","/fg[1].ctl","/or_gate_si.ctl","/or.ctl","/xor_gate_si.ctl","/xor_rununtil.ctl","/xor.ctl")){
			System.out.println(v);
			try (Scanner in = new Scanner(ParserTest.class.getResourceAsStream(v));
					Writer out = new NoWriter()) {
				File s = ParserFactory.getInstance().getParser().parse(in.useDelimiter("\\Z").next());
				s.write(out);
			}			
		};
	}

}
