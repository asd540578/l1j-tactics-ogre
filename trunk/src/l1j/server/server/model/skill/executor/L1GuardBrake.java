package l1j.server.server.model.skill.executor;

import l1j.server.server.model.L1Character;

public class L1GuardBrake extends L1BuffSkillExecutorImpl {

	private int AC = 0;

	@Override
	public void addEffect(L1Character user, L1Character target, int durationSeconds) {
		this.user = user;
		this.target = target;
		this.AC = ((user.getLevel() - 70) / 4) * 2 + 20;
		target.addAc(AC);
	}

	@Override
	public void removeEffect(L1Character target) {
		target.addAc(-AC);
	}
}
