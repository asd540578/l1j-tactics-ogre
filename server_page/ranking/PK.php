<?php
include("setup.php");

if (isset($_POST['sw'])){
	$class = (get_magic_quotes_gpc()) ? $_POST['sw'] : addslashes($_POST['sw']);
}else{
	$class = "ALL";
}

$filename = "PK_{$class}.html";

if (!file_exists($filename) or filemtime($filename) < time() - $renewal_time){
		
mysql_select_db($database_l1jdb, $l1jdb);
switch ($class){
	case "ALL":
		$query_R_characters = "select a.name , a.class as class, a.level as level,  a.clanname as clanname, a.lawful as lawful, ifnull(b.cnt,0) as kill_cnt ,ifnull(c.cnt,0) as dead_cnt from	characters a
		left join ( select	killer_name as name,count(*) cnt from pk_history group by killer_name) b on a.name = b.name 
		left join ( select	dead_name as name,count(*) cnt from pk_history group by dead_name ) c on a.name = c.name
		group by a.name order by b.cnt desc LIMIT 0,{$pk_num}";break;
	case "Royal":
		$query_R_characters = "select a.name , a.class as class, a.level as level,  a.clanname as clanname, a.lawful as lawful, ifnull(b.cnt,0) as kill_cnt ,ifnull(c.cnt,0) as dead_cnt from	characters a
		left join ( select	killer_name as name,count(*) cnt from pk_history group by killer_name) b on a.name = b.name 
		left join ( select	dead_name as name,count(*) cnt from pk_history group by dead_name ) c on a.name = c.name
		where a.class in (0 ,1) group by a.name order by b.cnt desc LIMIT 0,{$pk_num}";break;
	case "Knight":
		$query_R_characters = "select a.name , a.class as class, a.level as level,  a.clanname as clanname, a.lawful as lawful, ifnull(b.cnt,0) as kill_cnt ,ifnull(c.cnt,0) as dead_cnt from	characters a
		left join ( select	killer_name as name,count(*) cnt from pk_history group by killer_name) b on a.name = b.name 
		left join ( select	dead_name as name,count(*) cnt from pk_history group by dead_name ) c on a.name = c.name
		where a.class in (61 ,48) group by a.name order by b.cnt desc LIMIT 0,{$pk_num}";break;
	case "Wizard":
		$query_R_characters = "select a.name , a.class as class, a.level as level,  a.clanname as clanname, a.lawful as lawful, ifnull(b.cnt,0) as kill_cnt ,ifnull(c.cnt,0) as dead_cnt from	characters a
		left join ( select	killer_name as name,count(*) cnt from pk_history group by killer_name) b on a.name = b.name 
		left join ( select	dead_name as name,count(*) cnt from pk_history group by dead_name ) c on a.name = c.name
		where a.class in (734 ,1186) group by a.name order by b.cnt desc LIMIT 0,{$pk_num}";break;
	case "Elf":
		$query_R_characters = "select a.name , a.class as class, a.level as level,  a.clanname as clanname, a.lawful as lawful, ifnull(b.cnt,0) as kill_cnt ,ifnull(c.cnt,0) as dead_cnt from	characters a
		left join ( select	killer_name as name,count(*) cnt from pk_history group by killer_name) b on a.name = b.name 
		left join ( select	dead_name as name,count(*) cnt from pk_history group by dead_name ) c on a.name = c.name
		where a.class in (138 ,37) group by a.name order by b.cnt desc LIMIT 0,{$pk_num}";break;
	case "DarkElf":
		$query_R_characters = "select a.name , a.class as class, a.level as level,  a.clanname as clanname, a.lawful as lawful, ifnull(b.cnt,0) as kill_cnt ,ifnull(c.cnt,0) as dead_cnt from	characters a
		left join ( select	killer_name as name,count(*) cnt from pk_history group by killer_name) b on a.name = b.name 
		left join ( select	dead_name as name,count(*) cnt from pk_history group by dead_name ) c on a.name = c.name
		where a.class in (2786 ,2796) group by a.name order by b.cnt desc LIMIT 0,{$pk_num}";break;
	case "DragonKnight":
		$query_R_characters = "select a.name , a.class as class, a.level as level,  a.clanname as clanname, a.lawful as lawful, ifnull(b.cnt,0) as kill_cnt ,ifnull(c.cnt,0) as dead_cnt from	characters a
		left join ( select	killer_name as name,count(*) cnt from pk_history group by killer_name) b on a.name = b.name 
		left join ( select	dead_name as name,count(*) cnt from pk_history group by dead_name ) c on a.name = c.name
		where a.class in (6658 ,6661) group by a.name order by b.cnt desc LIMIT 0,{$pk_num}";break;
	case "Illusionist":
		$query_R_characters = "select a.name , a.class as class, a.level as level,  a.clanname as clanname, a.lawful as lawful, ifnull(b.cnt,0) as kill_cnt ,ifnull(c.cnt,0) as dead_cnt from	characters a
		left join ( select	killer_name as name,count(*) cnt from pk_history group by killer_name) b on a.name = b.name 
		left join ( select	dead_name as name,count(*) cnt from pk_history group by dead_name ) c on a.name = c.name
		where a.class in (6671 ,6650) group by a.name order by b.cnt desc LIMIT 0,{$pk_num}";break;
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
		if ($row_R_characters['lawful'] >= 0){$lawful = "blue";}else{$lawful = "red";}
		$char_name = "<span style=\"color:{$lawful}\">" . $row_R_characters['name'] . "</span><span style=\"font-size:13px\">(" . $row_R_characters['level'] . ")<br /> <span style=\"color:red\">K:" . $row_R_characters['kill_cnt'] . " D:" . $row_R_characters['dead_cnt'] . "</span><br />[" . $row_R_characters['clanname'] ."]</span>";
		if ($rank < 11){
			if ($rank_check == $row_R_characters['kill_cnt']){
				$html .= "<td align=\"right\" valign=\"top\"><strong><big>{$rank2}</big></strong></td>";
			}else{
				$rank2=$rank;$html .= "<td align=\"right\" valign=\"top\"><strong><big style=\"font-size:20px\">{$rank2}</big></strong></td>";$rank_check=$row_R_characters['kill_cnt'];
			}
			$html .= "<td width=\"18%\"><img src=\"/ranking/images/icon/big/{$chagfx_big[$row_R_characters['class']]}\" align=\"absmiddle\"><div align=\"left\"><strong><big style=\"font-size:13px\">{$char_name}</big></strong></div></td>";
		}else{
			if ($rank_check == $row_R_characters['kill_cnt']){
				$html .= "<td align=\"right\">{$rank2}</td>";
			}else{
				$rank2=$rank;$html .= "<td align=\"right\">{$rank2}</td>";$rank_check=$row_R_characters['kill_cnt'];
			}
			$html .= "<td><img src=\"/ranking/images/icon/small/{$chagfx_small[$row_R_characters['class']]}\" align=\"absmiddle\"><br> {$char_name}</td>";
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
