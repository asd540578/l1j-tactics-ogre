<?php
include("setup.php");

$filename = "onlineusers.html";
$html = "";

if (!file_exists($filename) or filemtime($filename) < time() - 1){

mysql_select_db($database_l1jdb, $l1jdb);
$query_R_clan_data = "SELECT * FROM clan_data";
$R_clan_data = mysql_query($query_R_clan_data, $l1jdb) or die(mysql_error());
$row_R_clan_data = mysql_fetch_assoc($R_clan_data);
$totalRows_R_clan_data = mysql_num_rows($R_clan_data);
if ($totalRows_R_clan_data > 0) {
	do {
		$clan_name[$row_R_clan_data['clan_id']] = $row_R_clan_data['clan_name'];
	} while ($row_R_clan_data = mysql_fetch_assoc($R_clan_data));
}
mysql_free_result($R_clan_data);

mysql_select_db($database_l1jdb, $l1jdb);
$query_R_characters = "SELECT * FROM characters WHERE OnlineStatus = 1 ORDER BY Exp DESC";
$query_R_characters_count = "SELECT COUNT(*) AS COUNT FROM characters WHERE OnlineStatus = 1";
$R_characters = mysql_query($query_R_characters, $l1jdb) or die(mysql_error());
$R_characters_count = mysql_query($query_R_characters_count, $l1jdb) or die(mysql_error());
$row_R_characters = mysql_fetch_assoc($R_characters);
$row_R_characters_count = mysql_fetch_assoc($R_characters_count);
$totalRows_R_characters = mysql_num_rows($R_characters);

if ($totalRows_R_characters > 0){

	$html .= "<table style=\"font-family:Verdana;font-size:14px\">\n";
	$html .= "現在のユーザ数：" . $row_R_characters_count['COUNT'] . "人";
	$i=0;
	do {
		$i++;
		if ($row_R_characters['Lawful'] >= 0){$lawful = "blue";}else{$lawful = "red";}
		$char_name = "<span style=\"color:{$lawful}\"><br />" . $row_R_characters['name'] . "</span><span style=\"font-size:13px\"> (" . $row_R_characters['level'] . ")<br>[" . $row_R_characters['Clanname'] . "]</span>";
		$html .= "<td nowrap><img src=\"{$icon_dir}{$chagfx_small[$row_R_characters['Class']]}\" align=\"absmiddle\"> {$char_name}&nbsp;&nbsp;&nbsp;</td>\n";
		if (($i % $td) == 0){$html .= "</tr><tr>\n</tr><tr>\n";}
	} while ($row_R_characters = mysql_fetch_assoc($R_characters));
	$html .= "</table>\n";
	
	file_put_contents($filename,$html);
	file_put_contents("onlineusers_total.txt",$totalRows_R_characters);//ユーザー数合計をファイルに書き出し
	
	echo $html;

}else{
	echo "オンラインのキャラクターはいません。";
}

mysql_free_result($R_characters);

}else{
	include($filename);
}
?>