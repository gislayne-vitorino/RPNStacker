package rpnstacker;

/**
 * @author Henrique Rebelo
 */
public enum TokenType {

	// Literals.
	NUM,

	// Single-character tokens for operations.
	MINUS, PLUS, SLASH, STAR,
	
	EOF

}