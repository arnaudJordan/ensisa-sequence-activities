package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreatedStructure {
	private List<AnatomicStructure> treatedStructure;
	public TreatedStructure() {
		this.treatedStructure=new ArrayList<AnatomicStructure>();
	}
	
	public int size() {
		return treatedStructure.size();
	}
	public boolean isEmpty() {
		return treatedStructure.isEmpty();
	}
	public boolean contains(Object o) {
		return treatedStructure.contains(o);
	}
	public Iterator<AnatomicStructure> iterator() {
		return treatedStructure.iterator();
	}
	public AnatomicStructure get(int index) {
		return treatedStructure.get(index);
	}
	public void addAnatomicStructure(AnatomicStructure anatomicStructure)
	{
		treatedStructure.add(anatomicStructure);
	}
	
	@Override
	public String toString() {
		return "TreatedStructure [treatedStructure=" + treatedStructure + "]";
	}
}
