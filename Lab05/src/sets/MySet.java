package sets;
import java.util.*;

public class MySet<E> implements Set<E>{
	private ArrayList<E> items;
	private final Class<E> type;
	
	public MySet(Class<E> type){
		this.items = new ArrayList<E>();
		this.type = type;
	}
	
	@Override
	public int getSize() {
		return items.size();
	}
	
	@Override
	public Class<E> getType() {
		return this.type;
	}
	
	@Override
	public Iterator<E> iterator() {
		return items.iterator();
	}
	
	@Override
	public void add(E s) {
		if (!items.contains(s)) {
			items.add(s);
		}
	}
	
	@Override
	public List<E> getItems() {
		return this.items;
	}
	
	@Override
	public boolean subset(Set<E> ms) {
		Iterator<E> iter = ms.iterator();
		while (iter.hasNext()) {
			E elem = iter.next();
			if (!(this.contains(elem))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Set<E> intersect(Set<E> ms) {
		Iterator<E> iter = ms.iterator();
		Set<E> intSet = new MySet<E>(this.type);
		while (iter.hasNext()) {
			E elem = iter.next();
			if (this.contains(elem)) {
				intSet.add(elem);
			}
		}
		return intSet;
	}

	@Override
	public Set<E> union(Set<E> ms) {
		Iterator<E> iter1 = this.iterator();
		Iterator<E> iter2 = ms.iterator();
		Set<E> unSet = new MySet<E>(this.type);
		while (iter1.hasNext()) {
			E elem1 = iter1.next();
			unSet.add(elem1);
		}
		while (iter2.hasNext()) {
			E elem2 = iter2.next();
			unSet.add(elem2);
		}
		return unSet;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;	// Check if identical
		if (o == null) return false; // Check if null
		
		if (!(o instanceof MySet<?>)) return false; // Check if same class
		
		MySet<?> other = (MySet<?>) o; // Check that it has the same generic type
		if (!other.getType().equals(this.getType())) return false;

		@SuppressWarnings("unchecked")
		MySet<E> o2 = (MySet<E>) other;
		for (@SuppressWarnings("unused") E e: o2.getItems()); // causes a ClassCastException since compiler can't check
		if (other.getSize() != this.getSize()) return false; // check that sets are the same size
		
		Iterator<E> iter = this.iterator(); // check that all the elements in this set are in the other
		while (iter.hasNext()) {
			E elem = iter.next();
			if (!o2.contains(elem)) return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return items.hashCode();
	}

	@Override
	public void remove(E s) {
		Iterator<E> iter = this.iterator();
		E elemToRemove = null;
		while (iter.hasNext()) {
			E elem = iter.next();
			if (elem.equals(s)) {
				elemToRemove = elem;
			}
		}
		
		if (elemToRemove != null) {
			this.items.remove(elemToRemove);
		}
	}

	@Override
	public boolean contains(E elem2) {
		Iterator<E> iter = this.iterator();
		while (iter.hasNext()) {
			E elem = iter.next();
			if (elem.equals(elem2)) {
				return true;
			}
		}
		return false;
	}
}
