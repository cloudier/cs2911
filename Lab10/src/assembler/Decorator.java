package assembler;

abstract class Decorator implements Part {
	protected Part p;
	
	public Decorator (Part p) {
		this.p = p;
	}
}
