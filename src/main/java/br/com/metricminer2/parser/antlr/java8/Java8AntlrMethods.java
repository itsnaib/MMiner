

package br.com.metricminer2.parser.antlr.java8;

import java.util.ArrayList;
import java.util.List;

import br.com.metricminer2.parser.antlr.java8.Java8Parser.ConstructorDeclarationContext;
import br.com.metricminer2.parser.antlr.java8.Java8Parser.FormalParameterContext;
import br.com.metricminer2.parser.antlr.java8.Java8Parser.FormalParameterListContext;
import br.com.metricminer2.parser.antlr.java8.Java8Parser.LastFormalParameterContext;
import br.com.metricminer2.parser.antlr.java8.Java8Parser.MethodDeclarationContext;

public class Java8AntlrMethods {

	public static String fullMethodName(String name, FormalParameterListContext parameters) {
		boolean empty = parameters == null || parameters.lastFormalParameter() == null;
		if(empty) return name + "/0";

		
		List<String> allParams = new ArrayList<String>();
		
		if(parameters.formalParameters()!=null) {
			
			for(FormalParameterContext f : parameters.formalParameters().formalParameter()) {
				if(f!=null) {
					allParams.add(f.unannType().getText());
				}
			}
		}
		
		if(parameters.lastFormalParameter()!=null) {
			LastFormalParameterContext p = parameters.lastFormalParameter();
			if(p!=null && p.formalParameter() !=null) {
				String f = p.formalParameter().unannType().getText();
				if(p.ELLIPSIS()!=null && !p.ELLIPSIS().getText().isEmpty()) f+="...";
				allParams.add(f);
			}
		}
		
		String fullName = name + "/" + allParams.size() + typesIn(allParams);
		
		return fullName;
	}
	
	public static boolean hasNoParameters(FormalParameterListContext parameters) {
		boolean noLastParameter = parameters == null || parameters.lastFormalParameter() == null;
		boolean noParams = (parameters == null || parameters.formalParameters() == null || parameters.formalParameters().formalParameter() == null);
		
		return noLastParameter && noParams;
	}
	
	public static List<FormalParameterContext> params(FormalParameterListContext parameters) {
		boolean noLastParameter = parameters == null || parameters.lastFormalParameter() == null;
		boolean noParams = (parameters == null || parameters.formalParameters() == null || parameters.formalParameters().formalParameter() == null);
		
		List<FormalParameterContext> all = new ArrayList<FormalParameterContext>();
		if(!noParams) {
			all.addAll(parameters.formalParameters().formalParameter());
		}
		if(!noLastParameter) all.add(parameters.lastFormalParameter().formalParameter());
		
		return all;
	}

	private static String typesIn(List<String> parameters) {
		StringBuilder types = new StringBuilder();
		types.append("[");
		for(String p : parameters) {
			types.append(p + ",");
		}
		
		return types.substring(0, types.length() - 1) + "]";
	}

	public static String fullMethodName(MethodDeclarationContext ctx) {
		return fullMethodName(ctx.methodHeader().methodDeclarator().Identifier().getText(), ctx.methodHeader().methodDeclarator().formalParameterList());		
	}

	public static String fullMethodName(ConstructorDeclarationContext ctx) {
		return fullMethodName(ctx.constructorDeclarator().simpleTypeName().getText(), ctx.constructorDeclarator().formalParameterList());
	}
}
