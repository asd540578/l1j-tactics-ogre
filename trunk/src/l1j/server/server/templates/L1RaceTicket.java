/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
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

public class L1RaceTicket {

	private int _itemobjid;
	private int _round;
	private double _allotment_percentage;
	private int victory;
	private int _runner_num; 
	public int get_itemobjid() {
		return _itemobjid;
	}

	public void set_itemobjid(int i) {
		_itemobjid = i;
	}

	public void set_allotment_percentage(double allotment_percentage) {
		this._allotment_percentage = allotment_percentage;
	}

	public double get_allotment_percentage() {
		return _allotment_percentage;
	}

	public void set_round(int _round) {
		this._round = _round;
	}

	public int get_round() {
		return _round;
	}

	public void set_victory(int victory) {
		this.victory = victory;
	}

	public int get_victory() {
		return victory;
	}

	public void set_runner_num(int _runner_num) {
		this._runner_num = _runner_num;
	}

	public int get_runner_num() {
		return _runner_num;
	}

}