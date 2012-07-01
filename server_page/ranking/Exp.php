<?php
include("setup.php");

if (isset($_POST['sw'])){
	$class = (get_magic_quotes_gpc()) ? $_POST['sw'] : addslashes($_POST['sw']);
}else{
	$class = "ALL";
}

$filename = "Exp_{$class}.html";

if (!file_exists($filename) or filemtime($filename) < time() - $renewal_time){
		
mysql_select_db($database_l1jdb, $l1jdb);
switch ($class){
	case "ALL":
		$query_R_characters = "SELECT * FROM characters ORDER BY Exp DESC LIMIT 0,50";break;
	case "Royal":
		$query_R_characters = "SELECT * FROM characters WHERE Class = 0 or Class = 1 ORDER BY Exp DESC LIMIT 0,{$exp_num}";break;
	case "Knight":
		$query_R_characters = "SELECT * FROM characters WHERE Class = 61 or Class = 48 ORDER BY Exp DESC LIMIT 0,{$exp_num}";break;
	case "Wizard":
		$query_R_characters = "SELECT * FROM characters WHERE Class = 734 or Class = 1186 ORDER BY Exp DESC LIMIT 0,{$exp_num}";break;
	case "Elf":
		$query_R_characters = "SELECT * FROM characters WHERE Class = 138 or Class = 37 ORDER BY Exp DESC LIMIT 0,{$exp_num}";break;
	case "DarkElf":
		$query_R_characters = "SELECT * FROM characters WHERE Class = 2786 or Class = 2796 ORDER BY Exp DESC LIMIT 0,{$exp_num}";break;
	case "DragonKnight":
		$query_R_characters = "SELECT * FROM characters WHERE Class = 6658 or Class = 6661 ORDER BY Exp DESC LIMIT 0,{$exp_num}";break;
	case "Illusionist":
		$query_R_characters = "SELECT * FROM characters WHERE Class = 6671 or Class = 6650 ORDER BY Exp DESC LIMIT 0,{$exp_num}";break;
	default : exit;
}

$R_characters = mysql_query($query_R_characters, $l1jdb) or die(mysql_error());
$row_R_characters = mysql_fetch_assoc($R_characters);
$totalRows_R_characters = mysql_num_rows($R_characters);

$rank = 0;
$rank_check = "";

$html = "<table width=\"750\" style=\"font-family:Verdana;font-size:14px\"><tr>\n";

if ($totalRows_R_characters > 0){
	do {
		$rank++;
		if ($row_R_characters['Lawful'] >= 0){$lawful = "blue";}else{$lawful = "red";}
		$char_name = "<span style=\"color:{$lawful}\">" . $row_R_characters['name'] . "</span><span style=\"font-size:13px\">(" . $row_R_characters['level'] . ")<br />[" . $row_R_characters['Clanname'] ."]</span>";
		if ($rank < 11){
			if ($rank_check == $row_R_characters['Exp']){
				$html .= "<td align=\"right\" valign=\"top\"><strong><big>{$rank2}</big></strong></td>";
			}else{
				$rank2=$rank;$html .= "<td align=\"right\" valign=\"top\"><strong><big style=\"font-size:20px\">{$rank2}</big></strong></td>";$rank_check=$row_R_characters['Exp'];
			}
			$html .= "<td width=\"18%\"><img src=\"/ranking/images/icon/big/{$chagfx_big[$row_R_characters['Class']]}\" align=\"absmiddle\"><div align=\"left\"><strong><big style=\"font-size:13px\">{$char_name}</big></strong></div></td>";
		}else{
			if ($rank_check == $row_R_characters['Exp']){
				$html .= "<td align=\"right\">{$rank2}</td>";
			}else{
				$rank2=$rank;$html .= "<td align=\"right\">{$rank2}</td>";$rank_check=$row_R_characters['Exp'];
			}
			$html .= "<td><img src=\"/ranking/images/icon/small/{$chagfx_small[$row_R_characters['Class']]}\" align=\"absmiddle\"><br> {$char_name}</td>";
		}
		
		if (($rank % 5) == 0){$html .= "</tr><tr>\n</tr><tr>\n";} 

	} while ($row_R_characters = mysql_fetch_assoc($R_characters));
}

$html .= "</table>\n";

file_put_contents($filename,$html);

echo $html;

mysql_free_result($R_characters);

}else{
	include($filename);
}
?>
