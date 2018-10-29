
public interface List {
	
	public void add(Object obj) throws Exception;
	public void add(int pos, Object obj) throws Exception;
	public Object get(int pos) throws Exception;
	public Object remove(int pos) throws Exception;
	public int size();

}
