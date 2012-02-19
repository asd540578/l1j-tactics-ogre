package l1j.server.server.utils;

import java.util.logging.Logger;

import l1j.server.server.datatables.SkillEffectTable;

public class KeyValuePair<T ,U>{

	public T first;
	public U second;

	public KeyValuePair()
	{

	}

	public KeyValuePair(T first ,U second)
	{
		this.first = first;
		this.second = second;
	}

	private static Logger _log = Logger.getLogger(SkillEffectTable.class
			.getName());

	@Override
	public boolean equals(Object  obj)
	{
		if(obj instanceof KeyValuePair)
		{
			@SuppressWarnings("unchecked")
			KeyValuePair pair = (KeyValuePair)obj;
			return first.equals(pair.first) && second.equals(pair.first);
		}
		return false;
	}
}
