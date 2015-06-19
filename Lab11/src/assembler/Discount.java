package assembler;

public class Discount extends Decorator {
	private int discount; // discount as a percentage

	public Discount(Part p, int discount) {
		super(p);
		this.discount = discount;
	}

	@Override
	public int getPrice() {
		return p.getPrice() * discount / 100;
	}

	@Override
	public void addPart(Part p) {
		this.p.addPart(p);		
	}

	@Override
	public void removePart(Part p) {
		this.p.removePart(p);
	}

	@Override
	public Part getChild(int index) {
		return this.p.getChild(index);
	}
}
