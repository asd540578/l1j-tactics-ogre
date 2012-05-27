package l1j.server.server.model.skill.executor;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.skill.L1SkillId;

public class L1IllusionOgre extends L1BuffSkillExecutorImpl {

	@Override
	public void addEffect(L1Character user, L1Character target,
			int durationSeconds) {
		target.addDmgup(calcDMGUP(user.getLevel()));
		target.addHitup(calcHITUP(user.getLevel()));
		target.addBowDmgup(calcDMGUP(user.getLevel()));
		target.addBowHitup(calcHITUP(user.getLevel()));
	}

	@Override
	public void addEffect(int userLevel, L1Character target,
			int durationSeconds) {
		target.addDmgup(calcDMGUP(userLevel));
		target.addHitup(calcHITUP(userLevel));
		target.addBowDmgup(calcDMGUP(userLevel));
		target.addBowHitup(calcHITUP(userLevel));
	}

	@Override
	public void removeEffect(L1Character target) {
		target.addDmgup(-calcDMGUP(target.getSkillEffectLevel(L1SkillId.ILLUSION_OGRE)));
		target.addHitup(-calcHITUP(target.getSkillEffectLevel(L1SkillId.ILLUSION_OGRE)));
		target.addBowDmgup(-calcDMGUP(target.getSkillEffectLevel(L1SkillId.ILLUSION_OGRE)));
		target.addBowHitup(-calcHITUP(target.getSkillEffectLevel(L1SkillId.ILLUSION_OGRE)));
	}

	private int calcDMGUP(int userLevel)
	{
		return (userLevel > 70)? ((userLevel - 70) / 3 + 3) : 3;
	}

	private int calcHITUP(int userLevel)
	{
		return (userLevel > 70)? ((userLevel - 70) / 3 + 1) : 1;
	}

}
