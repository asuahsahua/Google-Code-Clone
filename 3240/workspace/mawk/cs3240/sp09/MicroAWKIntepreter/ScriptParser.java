package cs3240.sp09.MicroAWKIntepreter;
import cs3240.sp09.AbstractSyntaxTree.*;


public class ScriptParser {
	
	private ScriptReader reader;
	
	public ScriptParser(String script){
		reader = new ScriptReader(script);
	}
	
	public ASTNode parse() {
		return program();
	}
	
	public ASTNode program(){
		ASTNode programNode = new ASTNode(ASTNode.NodeType.Program);
		programNode.setLeftChild(statement());
		if(reader.token != (char)-1){
			programNode.setRightChild(program());
		}
		return programNode;
	}

	public ASTNode statement() {
		ASTNode statementNode = new ASTNode(ASTNode.NodeType.Statement);
		switch(reader.token){
			case 'B':
				statementNode.setLeftChild(begin());
				break;
			case 'E':
				statementNode.setLeftChild(end());
				break;
			case '{':
				reader.match('{');
				statementNode.setLeftChild(funcBlock());
				reader.match('}');
				break;
			default:
				statementNode.setLeftChild(regex());
				reader.match('{');
				statementNode.setRightChild(funcBlock());
				reader.match('}');
				break;
		}
		return statementNode;
	}

	public ASTNode regex(){
		ASTNode regexNode = new RegexNode(ASTNode.NodeType.Regex);
		regexNode.setLeftChild(term());
		if(reader.token == '|'){
			reader.match('|');
			regexNode.setRightChild(regex());
		}
		return regexNode;
	}
	
	public ASTNode term(){
		ASTNode termNode = new ASTNode(ASTNode.NodeType.Term);
		termNode.setLeftChild(factor());
		if(reader.token == 'a' || reader.token == 'b' || reader.token == 'c' || reader.token == '('){
			termNode.setRightChild(term());
		}
		return termNode;
	}
	
	public ASTNode factor(){
		ASTNode factorNode = new ASTNode(ASTNode.NodeType.Factor);
		factorNode.setLeftChild(atom());
		if(reader.token == '*' || reader.token == '+' || reader.token == '?'){
			factorNode.setRightChild(metacharacter());
		}
		return factorNode;
	}

	private ASTNode atom() {
		ASTNode atomNode = new ASTNode(ASTNode.NodeType.Atom);
		if(reader.token == '('){
			reader.match('(');
			atomNode.setLeftChild(regex());
			reader.match(')');
		} else {
			atomNode.setLeftChild(character());
		}
		return atomNode;
	}

	public ASTNode metacharacter(){
		ASTNode metacharacterNode = new ASTNode(ASTNode.NodeType.Metacharacter);
		switch(reader.token){
		case '*':
			metacharacterNode.setLeftChild(star());
			break;
		case '+':
			metacharacterNode.setLeftChild(oneOrMore());
			break;
		case '?':
			metacharacterNode.setLeftChild(optional());
			break;
		default:
			// TODO: throw error
			break;
		}
		return metacharacterNode;
	}
	public ASTNode star(){
		ASTNode starNode = new ASTNode(ASTNode.NodeType.Star);
		reader.match('*');
		return starNode;
	}
	public ASTNode oneOrMore(){
		ASTNode oneOrMoreNode = new ASTNode(ASTNode.NodeType.OneOrMore);
		reader.match('+');
		return oneOrMoreNode;
	}
	public ASTNode optional(){
		ASTNode optionalNode = new ASTNode(ASTNode.NodeType.Optional);
		reader.match('?');
		return optionalNode;
	}
	
	public ASTNode begin() {
		ASTNode beginNode = new ASTNode(ASTNode.NodeType.Begin);
		reader.matchString("BEGIN{");
		beginNode.setLeftChild(funcBlock());
		reader.match('}');
		return beginNode;
	}
	
	public ASTNode end() {
		ASTNode endNode = new ASTNode(ASTNode.NodeType.End);
		reader.matchString("END{");
		endNode.setLeftChild(funcBlock());
		reader.match('}');
		return endNode;
	}
	
	public ASTNode funcBlock(){
		ASTNode funcBlockNode = new ASTNode(ASTNode.NodeType.FunctionBlock);
		funcBlockNode.setLeftChild(function());
		if(reader.token == ';'){
			reader.match(';');
			funcBlockNode.setRightChild(funcBlock());
		}
		return funcBlockNode;
	}
	
	public ASTNode function(){
		ASTNode functionNode = new ASTNode(ASTNode.NodeType.Function);
		switch(reader.token){
		case 's':
			functionNode.setLeftChild(substringFunction());
			break;
		case 'i':
			functionNode.setLeftChild(insertFunction());
			break;
		case 'p':
			functionNode.setLeftChild(printFunction());
			break;
		case 'r':
			functionNode.setLeftChild(reFunction());
			break;
		default:
			// TODO: parse error
			break;
		}
		return functionNode;
	}

	public ASTNode substringFunction() {
		ASTNode substringFunctionNode = new ASTNode(ASTNode.NodeType.SubstringFunction);
		reader.matchString("substring(");
		substringFunctionNode.setLeftChild(integer());
		reader.match(',');
		if(reader.token == 'E'){
			reader.matchString("END");
			substringFunctionNode.setRightChild(new ASTNode(ASTNode.NodeType.EndIndex));
		} else {
			substringFunctionNode.setRightChild(integer());
		}
		reader.match(')');
		return substringFunctionNode;
	}
	
	public ASTNode integer() {
		ASTNode integerNode = new ASTNode(ASTNode.NodeType.Integer);
		integerNode.setLeftChild(number());
		if(reader.token >= '0' && reader.token <= '9')
			integerNode.setRightChild(integer());
		return integerNode;
	}
	private ASTNode number() {
		ASTNode numberNode = new ASTNode(ASTNode.NodeType.Number);
		switch(reader.token){
		case '0':
			numberNode.setLeftChild(zero());
			break;
		case '1':
			numberNode.setLeftChild(one());
			break;
		case '2':
			numberNode.setLeftChild(two());
			break;
		case '3':
			numberNode.setLeftChild(three());
			break;
		case '4':
			numberNode.setLeftChild(four());
			break;
		case '5':
			numberNode.setLeftChild(five());
			break;
		case '6':
			numberNode.setLeftChild(six());
			break;
		case '7':
			numberNode.setLeftChild(seven());
			break;
		case '8':
			numberNode.setLeftChild(eight());
			break;
		case '9':
			numberNode.setLeftChild(nine());
			break;
		default:
			// TODO: parse error
			break;
		}
		return numberNode;
	}
	private ASTNode zero() {
		reader.match('0');
		return new ASTNode(ASTNode.NodeType.Zero);
	}
	private ASTNode one() {
		reader.match('1');
		return new ASTNode(ASTNode.NodeType.One);
	}
	private ASTNode two() {
		reader.match('2');
		return new ASTNode(ASTNode.NodeType.Two);
	}
	private ASTNode three() {
		reader.match('3');
		return new ASTNode(ASTNode.NodeType.Three);
	}
	private ASTNode four() {
		reader.match('4');
		return new ASTNode(ASTNode.NodeType.Four);
	}
	private ASTNode five() {
		reader.match('5');
		return new ASTNode(ASTNode.NodeType.Five);
	}
	private ASTNode six() {
		reader.match('6');
		return new ASTNode(ASTNode.NodeType.Six);
	}
	private ASTNode seven() {
		reader.match('7');
		return new ASTNode(ASTNode.NodeType.Seven);
	}
	private ASTNode eight() {
		reader.match('8');
		return new ASTNode(ASTNode.NodeType.Eight);
	}
	private ASTNode nine() {
		reader.match('9');
		return new ASTNode(ASTNode.NodeType.Nine);
	}

	public ASTNode insertFunction() {
		ASTNode insertFunctionNode = new ASTNode(ASTNode.NodeType.InsertFunction);
		reader.matchString("insert(");
		if(reader.token == 'E'){
			reader.matchString("END");
			insertFunctionNode.setLeftChild(new ASTNode(ASTNode.NodeType.EndIndex));
		} else {
			insertFunctionNode.setLeftChild(integer());
		}
		reader.match(',');
		insertFunctionNode.setRightChild(character());
		reader.match(')');
		return insertFunctionNode;
	}
	
	private ASTNode character() {
		ASTNode character = new ASTNode(ASTNode.NodeType.Character);
		switch(reader.token){
		case 'a':
			character.setLeftChild(a());
			break;
		case 'b':
			character.setLeftChild(b());
			break;
		case 'c':
			character.setLeftChild(c());
			break;
		default:
			System.err.println("ERROR: ScriptParser.character() - reader.token = " + reader.token);
			break;
		}
		return character;
	}
	private ASTNode a() {
		reader.match('a');
		return new ASTNode(ASTNode.NodeType.A);
	}
	private ASTNode b() {
		reader.match('b');
		return new ASTNode(ASTNode.NodeType.B);
	}
	private ASTNode c() {
		reader.match('c');
		return new ASTNode(ASTNode.NodeType.C);
	}

	public ASTNode printFunction(){
		ASTNode printFunctionNode = new ASTNode(ASTNode.NodeType.PrintFunction);
		reader.matchString("print(");
		if(reader.token == '"'){
			printFunctionNode.setLeftChild(string());
		} else {
			printFunctionNode.setLeftChild(line());
		}
		reader.match(')');
		return printFunctionNode;
	}
	
	private ASTNode line() {
		reader.matchString("LINE");
		return new ASTNode(ASTNode.NodeType.Line);
	}

	private ASTNode string() {
		ASTNode stringNode = new ASTNode(ASTNode.NodeType.String);
		reader.match('"');
		stringNode.setLeftChild(stringInner());
		reader.match('"');
		return stringNode;
	}

	private ASTNode stringInner() {
		StringInnerNode stringInnerNode = new StringInnerNode(ASTNode.NodeType.StringInner);
//		stringInnerNode.setLeftChild(stringCharacter());
		String str = "";
		while(reader.token != '"'){
			str += reader.token;
			reader.getcharWithWhitespace();
		}
//		if(reader.token != '"'){
//			stringInnerNode.setRightChild(stringInner());
//		}
		stringInnerNode.setValue(str);
		return stringInnerNode;
	}

	public ASTNode reFunction(){
		reader.matchString("re");
		switch(reader.token){
		case 'p':
			return replaceFunction();
		case 'm':
			return removeFunction();
		default:
			// TODO: error
			return null;
		}
	}

	private ASTNode removeFunction() {
		ASTNode removeFunctionNode = new ASTNode(ASTNode.NodeType.RemoveFunction);
		reader.matchString("move(");
		removeFunctionNode.setLeftChild(character());
		reader.match(')');
		return removeFunctionNode;
	}

	private ASTNode replaceFunction() {
		ASTNode replaceFunctionNode = new ASTNode(ASTNode.NodeType.ReplaceFunction);
		reader.matchString("place(");
		replaceFunctionNode.setLeftChild(character());
		reader.match(',');
		replaceFunctionNode.setRightChild(character());
		reader.match(')');
		return replaceFunctionNode;
	}
}