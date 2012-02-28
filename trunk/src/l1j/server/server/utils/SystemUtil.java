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
 package l1j.server.server.utils;

import java.io.File;

public class SystemUtil {
	/**
	 * システムが利用中のヒープサイズをメガバイト単位で返す。<br>
	 * この値にスタックのサイズは含まれない。
	 *
	 * @return 利用中のヒープサイズ
	 */
    public static long getUsedMemoryMB() {
        return ( Runtime.getRuntime().totalMemory() -
        Runtime.getRuntime().freeMemory()) / 1024L / 1024L;}

    /**
     * 得知作業系統是幾位元 Only for Windows
     *
     * @return x86 or x64
     */
    public static String getOsArchitecture() {
            String x64_System = "C:\\Windows\\SysWOW64", result;
            File dir = new File(x64_System);
            if (dir.exists())
                    result = "x64";
            else
                    result = "x86";
            return result;
    }

    /**
     * 取得目前的作業系統
     *
     * @return Linux or Windows
     */
    public static String gerOs() {
            String Os = "", OsName = System.getProperty("os.name");
            if (OsName.toLowerCase().indexOf("windows") >= 0) {
                    Os = "Windows";
            } else if (OsName.toLowerCase().indexOf("linux") >= 0) {
                    Os = "Linux";
            }
            return Os;
    }
}
