package l1j.server.server.model.skill.executor;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.skill.L1SkillId;

public class L1Insight extends L1BuffSkillExecutorImpl {

	@Override
	public void addEffect(L1Character user, L1Character target, int durationSeconds) {
		if (target instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) target;
			pc.addStr(calcSTR(user.getLevel()));
			pc.addCon(calcCON(user.getLevel()));
			pc.addDex(calcDEX(user.getLevel()));
			pc.addWis(calcWIS(user.getLevel()));
			pc.addInt(calcINT(user.getLevel()));
		}
	}

	@Override
	public void addEffect(int userLevel ,L1Character target ,int durationSeconds)
	{
		if (target instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) target;
			pc.addStr(calcSTR(userLevel));
			pc.addCon(calcCON(userLevel));
			pc.addDex(calcDEX(userLevel));
			pc.addWis(calcWIS(userLevel));
			pc.addInt(calcINT(userLevel));
		}
	}

	@Override
	public void removeEffect(L1Character target) {
		if (target instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) target;
			pc.addStr(-calcSTR(target.getSkillEffectLevel(L1SkillId.INSIGHT)));
			pc.addCon(-calcCON(target.getSkillEffectLevel(L1SkillId.INSIGHT)));
			pc.addDex(-calcDEX(target.getSkillEffectLevel(L1SkillId.INSIGHT)));
			pc.addWis(-calcWIS(target.getSkillEffectLevel(L1SkillId.INSIGHT)));
			pc.addInt(-calcINT(target.getSkillEffectLevel(L1SkillId.INSIGHT)));
		}
	}

	public int calcSTR(int userLevel)
	{
		return (userLevel >= 80) ? 2 : 1;
	}

	public int calcDEX(int userLevel)
	{
		return (userLevel >= 80) ? 2 : 1;
	}

	public int calcCON(int userLevel)
	{
		return (userLevel >= 90) ? 2 : 1;
	}

	public int calcWIS(int userLevel)
	{
		return (userLevel >= 90) ? 2 : 1;
	}
	public int calcINT(int userLevel)
	{
		return 1;
	}
}
