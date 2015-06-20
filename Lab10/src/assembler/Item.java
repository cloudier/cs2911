package assembler;

public class Item implements Part {
	private int price;
	
	public Item (int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

	@Override
	public void addPart(Part p) {
	}

	@Override
	public void removePart(Part p) {
	}

	@Override
	public Part getChild(int index) {
		return null;
	}
}
