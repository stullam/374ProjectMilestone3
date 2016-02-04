package problem.client;

import java.util.Enumeration;
import java.util.Iterator;

public class AdapterToArrayList implements Enumeration<String>{
	private Iterator<String> adapterList;
	
	public AdapterToArrayList(Iterator<String> iterator){
		this.adapterList = iterator;
		
	}
	@Override
	public boolean hasMoreElements() {
		// TODO Auto-generated method stub
		return this.adapterList.hasNext();
	}

	@Override
	public String nextElement() {
		// TODO Auto-generated method stub
		if(hasMoreElements()) {
			return this.adapterList.next();
		}
		else {
			return null;
		}
	}
}
