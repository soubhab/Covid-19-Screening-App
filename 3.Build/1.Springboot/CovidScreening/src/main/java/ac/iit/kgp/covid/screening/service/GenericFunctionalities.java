package ac.iit.kgp.covid.screening.service;

import java.util.List;

public interface GenericFunctionalities<T, U>
{
	public U get(String id);
	public List<U> getAll();
	public U update(T t);
	public U save(T t);
	public U execute(T t);
	public boolean validate(T t);
	public U delete(T t);
}