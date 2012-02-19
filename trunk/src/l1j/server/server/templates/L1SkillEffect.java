/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.server.server.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

import l1j.server.server.utils.L1QueryUtil.EntityFactory;

/* t.s 2012/01/22 add start */
public class L1SkillEffect
{

	private static final long serialVersionUID = 1L;

	public L1SkillEffect()
	{
	}

	private Integer _id; // itemかskillのid
	private Integer _type; // 0:item 1:skill
	private String _pattern; // エフェクトパターン
	private Integer _line_radius; // 効果距離（何セルまでエフェクトを出すか）
	private Integer _line_interval;
	private Integer _count; // エフェクトの発生回数（一部パターンで使用）
	private Integer _sleep_time; // エフェクトを表示する間隔(1/1000秒)
	private Integer _effect_1; // 重ねるエフェクト（必須）
	private Integer _effect_2; // 重ねるエフェクト
	private Integer _effect_3; // 重ねるエフェクト
	private Integer _effect_4; // 重ねるエフェクト

	public Integer getId()
	{
		return _id;
	}

	public void setId(Integer _id)
	{
		this._id = _id;
	}

	public Integer getType()
	{
		return _type;
	}

	public void setType(Integer _type)
	{
		this._type = _type;
	}

	public String getPattern()
	{
		return _pattern;
	}

	public void setPattern(String _pattern)
	{
		this._pattern = _pattern;
	}

	public Integer getLineRadius()
	{
		return _line_radius;
	}

	public void setLineRadius(Integer _line_radius)
	{
		this._line_radius = _line_radius;
	}

	public Integer getLineInterval()
	{
		return this._line_interval;
	}

	public void setLineInterval(Integer _line_interval)
	{
		this._line_interval = _line_interval;
	}

	public Integer getSleepTime()
	{
		return _sleep_time;
	}

	public void setSleepTime(Integer _sleep_time)
	{
		this._sleep_time = _sleep_time;
	}

	public Integer getCount()
	{
		return _count;
	}

	public void setCount(Integer _count)
	{
		this._count = _count;
	}

	public Integer getEffect_1()
	{
		return _effect_1;
	}

	public void setEffect_1(Integer _effect_1)
	{
		this._effect_1 = _effect_1;
	}

	public Integer getEffect_2()
	{
		return _effect_2;
	}

	public void setEffect_2(Integer _effect_2)
	{
		this._effect_2 = _effect_2;
	}

	public Integer getEffect_3()
	{
		return _effect_3;
	}

	public void setEffect_3(Integer _effect_3)
	{
		this._effect_3 = _effect_3;
	}

	public Integer getEffect_4()
	{
		return _effect_4;
	}

	public void setEffect_4(Integer _effect_4)
	{
		this._effect_4 = _effect_4;
	}

	public static class Factory implements EntityFactory<L1SkillEffect>
	{
		public L1SkillEffect fromResultSet(ResultSet rs) throws SQLException
		{
			L1SkillEffect effect = new L1SkillEffect();

			effect.setId(rs.getInt("id"));
			effect.setType(rs.getInt("type"));
			effect.setPattern(rs.getString("pattern"));
			effect.setLineRadius(rs.getInt("line_radius"));
			effect.setLineInterval(rs.getInt("line_interval"));
			effect.setSleepTime(rs.getInt("sleep_time"));
			effect.setCount(rs.getInt("count"));
			effect.setEffect_1(rs.getInt("effect_1") == 0 ? null : rs
					.getInt("effect_1"));
			effect.setEffect_2(rs.getInt("effect_2") == 0 ? null : rs
					.getInt("effect_2"));
			effect.setEffect_3(rs.getInt("effect_3") == 0 ? null : rs
					.getInt("effect_3"));
			effect.setEffect_4(rs.getInt("effect_4") == 0 ? null : rs
					.getInt("effect_4"));

			return effect;
		}
	}
}
/* t.s 2012/01/22 add end */