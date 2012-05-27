package l1j.server.server.model.skill.executor;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.skill.L1SkillId;

public class L1HorrorOfDeath extends L1BuffSkillExecutorImpl {

	@Override
	public void addEffect(L1Character user, L1Character target, int durationSeconds) {
		target.addStr(calcSTR(user.getLevel()));
		target.addDex(calcDEX(user.getLevel()));
		target.addInt(calcINT(user.getLevel()));
	}

	@Override
	public void addEffect(int userLevel, L1Character target, int durationSeconds) {
		target.addStr(calcSTR(userLevel));
		target.addDex(calcDEX(userLevel));
		target.addInt(calcINT(userLevel));
	}

	@Override
	public void removeEffect(L1Character target) {
		target.addStr(-calcSTR(target.getSkillEffectLevel(L1SkillId.HORROR_OF_DEATH)));
		target.addDex(-calcDEX(target.getSkillEffectLevel(L1SkillId.HORROR_OF_DEATH)));
		target.addInt(-calcINT(target.getSkillEffectLevel(L1SkillId.HORROR_OF_DEATH)));
	}

	private int calcSTR(int userLevel)
	{
		return -((userLevel - 70) / 4 + 2);
	}

	private int calcDEX(int userLevel)
	{
		return -((userLevel - 70) / 4 + 2);
	}

	private int calcINT(int userLevel)
	{
		return -((userLevel - 70) / 4);
	}
}
