package l1j.server.server;

import java.util.HashMap;
import java.util.Map;

public class Attribute
{
	private enum _Attribute {NONE(0,"無") ,EARTH(1,"土") ,FIRE(2,"火") ,
						WATER(4,"水") ,WIND(8,"風") ,RAY(16,"光");

		private final int type;
		private final String value;

		private _Attribute(int type ,String value)
    	{
			this.type = type;
			this.value = value;
    	}

		public int getType()
		{
			return type;
		}

		public String getValue()
		{
			return value;
		}
	}

	public static String getAttribute(int type)
	{
		for(_Attribute att : _Attribute.values())
		{
			if(att.getType() == type)
			{
				return att.getValue();
			}
		}

		return "No match type(" + type + ")";
	}
}
