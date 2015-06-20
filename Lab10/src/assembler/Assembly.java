package assembler;
import java.util.*;

public class Assembly implements Part {
	private int price;
	private ArrayList<Part> children;
	
	public Assembly (int price) {
		this.price = price;
		this.children = new ArrayList<Part>();
	}
	
	public int getPrice() {
		int sumPrices = this.price;
		for (Part p: children) {
			sumPrices += p.getPrice();
		}
		return sumPrices;
	}
	
	@Override
	public void addPart(Part p) {
		children.add(p);
	}
	
	@Override
	public void removePart(Part p) {
		children.remove(p);
	}
	
	@Override
	public Part getChild(int index) {
		return children.get(index);
	}
}
