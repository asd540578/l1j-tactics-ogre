<?php
//データベース接続設定
$hostname_l1jdb = "localhost";
$database_l1jdb = "l1jdb";
$username_l1jdb = "root";
$password_l1jdb = "orekiba848";
$l1jdb = mysql_pconnect($hostname_l1jdb, $username_l1jdb, $password_l1jdb) or trigger_error(mysql_error(),E_USER_ERROR); 
mysql_query("SET NAMES sjis")or die("can not SET NAMES sjis");

//ページを更新する間隔（秒）
$renewal_time = 60;

//キャラ画像があるフォルダへのパス
$icon_dir = "/ranking/images/icon/small/";

//キャラ画像小
$chagfx_small[0] = "m_pri_mini.png";//プリ男
$chagfx_small[1] = "f_pri_mini.png";//プリ女
$chagfx_small[61] = "m_kni_mini.png";//ナイト男
$chagfx_small[48] = "f_kni_mini.png";//ナイト女
$chagfx_small[734] = "m_wiz_mini.png";//ウィズ男
$chagfx_small[1186] = "f_wiz_mini.png";//ウィズ女
$chagfx_small[138] = "m_elf_mini.png";//エルフ男
$chagfx_small[37] = "f_elf_mini.png";//エルフ女
$chagfx_small[2786] = "m_de_mini.png";//DE男
$chagfx_small[2796] = "f_de_mini.png";//DE女
$chagfx_small[6658] = "m_dk_mini.png";//DRK男
$chagfx_small[6661] = "f_dk_mini.png";//DRK女
$chagfx_small[6671] = "m_ill_mini.png";//ILL男
$chagfx_small[6650] = "f_ill_mini.png";//ILL女

//キャラクターの表示列数
$td = "4";
?>