

package br.com.metricminer2.parser.antlr.java8;

import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import br.com.metricminer2.parser.antlr.java8.Java8Parser.CompilationUnitContext;
 
public class Java8AntLRVisitor {

	public void visit(Java8Listener visitor, InputStream is) {
		try {
			CharStream input = new ANTLRInputStream(is);
			Java8Lexer lex = new Java8Lexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lex);
			Java8Parser parser = new Java8Parser(tokens);
			parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
			parser.removeErrorListeners();
			parser.setErrorHandler(new BailErrorStrategy());
			try {
				CompilationUnitContext compilationUnitContext = parser
						.compilationUnit();
				new ParseTreeWalker().walk(visitor, compilationUnitContext);
			} catch (RuntimeException ex) {

					tokens.reset(); // rewind input stream
					
					// back to standard listeners/handlers
					parser.addErrorListener(ConsoleErrorListener.INSTANCE);
					parser.setErrorHandler(new DefaultErrorStrategy());
					parser.getInterpreter()
							.setPredictionMode(PredictionMode.LL);
					CompilationUnitContext compilationUnitContext = parser
							.compilationUnit();
					new ParseTreeWalker().walk(visitor, compilationUnitContext);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}