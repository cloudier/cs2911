package assembler;

public interface Part {
	public int getPrice();
	public void addPart(Part p);
	public void removePart(Part p);
	public Part getChild(int index);
}
