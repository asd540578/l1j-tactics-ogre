package l1j.server.server.model.skill.executor;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.skill.L1SkillId;

public class L1GuardBrake extends L1BuffSkillExecutorImpl {

	@Override
	public void addEffect(L1Character user, L1Character target, int durationSeconds) {
		target.addAc(calcAC(user.getLevel()));
	}

	@Override
	public void addEffect(int userLevel, L1Character target, int durationSeconds) {
		target.addAc(calcAC(userLevel));
	}

	@Override
	public void removeEffect(L1Character target) {
		int userLevel = target.getSkillEffectLevel(L1SkillId.GUARD_BRAKE);
		target.addAc(-1 * calcAC(userLevel));
	}


	private int calcAC(int userLevel)
	{
		return (userLevel > 70)? ((userLevel - 70) / 4) * 3 + 20 : 20;
	}
}
