package sets;

import java.util.*;

interface Set<E> extends Iterable<E>{
	// set membership operations
	public void add(E s);
	public void remove(E s);
	public boolean contains (E s);
	// accessors
	public int getSize();
	public Class<E> getType();
	public List<E> getItems();
	// basic operations on sets
	public boolean subset(Set<E> ms);
	public Set<E> intersect(Set<E> ms);
	public Set<E> union(Set<E> ms);
	// other
	public boolean equals(Object o);
	public int hashCode();
	public Iterator<E> iterator();
}
