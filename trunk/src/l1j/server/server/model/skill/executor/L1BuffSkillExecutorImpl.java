package l1j.server.server.model.skill.executor;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.templates.L1CharacterBuff;

public abstract class L1BuffSkillExecutorImpl extends L1SkillExecutorImpl
		implements L1BuffSkillExecutor {

	// リストア時にバッファの術者レベルを指定して呼び出し特殊な処理を行う。
	// 通常のスキルは、ここで記述している通りの通常実装している処理を呼び出す。
	@Override
	public void addEffect(int userLevel ,L1Character target ,int durationSeconds)
	{
		addEffect(null ,target ,durationSeconds);
	}

	// バッファの術者レベルを指定してスキルエフェクトを呼び出すように変更
	@Override
	public void restoreEffect(L1PcInstance target, L1CharacterBuff buff) {
		addEffect(buff.getUserLevel(), target, buff.getRemainingTime());
	}

	@Override
	public L1CharacterBuff getCharacterBuff(L1PcInstance pc) {
		return null;
	}
}
