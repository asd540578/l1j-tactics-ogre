package l1j.server.server.model;

import java.util.HashMap;
import java.util.Map;

public class Attribute
{
	private enum _Attribute {NONE(0,"無") ,EARTH(1,"地") ,FIRE(2,"火") ,
						WATER(4,"水") ,WIND(8,"風") ,RAY(16,"光") ,DARK(32 ,"闇");

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

		public String getString()
		{
			return value;
		}
	}

	/**
	 * コード値から対応する属性名を取得する
	 * @param value 属性のコード値
	 * @return 火・水・風・土・光・闇か該当無のいずれか
	 */
	public static final String getAttribute(int type)
	{
		for(_Attribute att : _Attribute.values())
		{
			if(att.getType() == type)
			{
				return att.getString();
			}
		}

		return "No match type(" + type + ")";
	}

	/**
	 * 属性名から対応するコード値を取得する
	 * @param value 火・水・風・土・光・闇のいずれか
	 * @return 該当無の場合は0
	 */
	public static final int getAttribute(String value)
	{
		for(_Attribute att : _Attribute.values())
		{
			if(att.getString().equals(value))
			{
				return att.getType();
			}
		}

		return 0;
	}
}
