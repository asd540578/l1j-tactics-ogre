<?php
include("setup.php");

if (isset($_POST['sw'])){
	$sw = (get_magic_quotes_gpc()) ? $_POST['sw'] : addslashes($_POST['sw']);
}else{
	exit;
}

$filename = "OE_{$sw}.html";

if (!file_exists($filename) or filemtime($filename) < time() - $renewal_time){

mysql_select_db($database_l1jdb, $l1jdb);

$query_R_characters = "SELECT * FROM characters";
$R_characters = mysql_query($query_R_characters, $l1jdb) or die(mysql_error());
$row_R_characters = mysql_fetch_assoc($R_characters);
$totalRows_R_characters = mysql_num_rows($R_characters);
if ($totalRows_R_characters){
	do {
		if ($row_R_characters['Lawful'] >= 0){$lawful = "blue";}else{$lawful = "red";}
		$char_name[$row_R_characters['id']] = "<span style=\"color:{$lawful}\">" . $row_R_characters['name'] . "</span><span style=\"font-size:10px\">(" . $row_R_characters['level'] . ")</span>";
	} while ($row_R_characters = mysql_fetch_assoc($R_characters));
}

switch ($sw){
	case "s_weapon":
		$query_R_items = "SELECT * FROM inventory_items LEFT JOIN weapon ON inventory_items.item_id = weapon.item_id LEFT JOIN item_rank on item_rank.item_id = weapon.item_id 
							WHERE location = 0 and NOT weapon.item_id IS NULL
							and		item_rank.rank in ('S') ORDER BY enchant_level DESC LIMIT 0,{$oe_num}";break;
	case "s_armor":
		$query_R_items = "SELECT * FROM inventory_items LEFT JOIN armor ON inventory_items.item_id = armor.item_id  LEFT JOIN item_rank on item_rank.item_id = armor.item_id 
							WHERE location = 0 and NOT armor.item_id IS NULL
							and		item_rank.rank in ('S')  ORDER BY enchant_level DESC LIMIT 0,{$oe_num}";break;
	case "a_weapon":
		$query_R_items = "SELECT * FROM inventory_items LEFT JOIN weapon ON inventory_items.item_id = weapon.item_id LEFT JOIN item_rank on item_rank.item_id = weapon.item_id 
							WHERE location = 0 and NOT weapon.item_id IS NULL
							and		item_rank.rank in ('A') ORDER BY enchant_level DESC LIMIT 0,{$oe_num}";break;
	case "a_armor":
		$query_R_items = "SELECT * FROM inventory_items LEFT JOIN armor ON inventory_items.item_id = armor.item_id  LEFT JOIN item_rank on item_rank.item_id = armor.item_id 
							WHERE location = 0 and NOT armor.item_id IS NULL
							and		item_rank.rank in ('A')  ORDER BY enchant_level DESC LIMIT 0,{$oe_num}";break;
	case "b_weapon":
		$query_R_items = "SELECT * FROM inventory_items LEFT JOIN weapon ON inventory_items.item_id = weapon.item_id LEFT JOIN item_rank on item_rank.item_id = weapon.item_id 
							WHERE location = 0 and NOT weapon.item_id IS NULL
							and		item_rank.rank in ('B') ORDER BY enchant_level DESC LIMIT 0,{$oe_num}";break;
	case "b_armor":
		$query_R_items = "SELECT * FROM inventory_items LEFT JOIN armor ON inventory_items.item_id = armor.item_id  LEFT JOIN item_rank on item_rank.item_id = armor.item_id 
							WHERE location = 0 and NOT armor.item_id IS NULL
							and		item_rank.rank in ('B')  ORDER BY enchant_level DESC LIMIT 0,{$oe_num}";break;
	default : exit;
}

$R_items = mysql_query($query_R_items, $l1jdb) or die(mysql_error());
$row_R_items = mysql_fetch_assoc($R_items);
$totalRows_R_items = mysql_num_rows($R_items);

$rank = 0;
$rank_check = "";

$html = "<table width=\"700\"><tr><td>\n";
$html .= "<table><tr>\n";

if ($totalRows_R_items > 0){
	do {
		$rank++;
		$item_name = "+" . $row_R_items['enchant_level'] . " " . $row_R_items['name'];
		if ($rank_check == $row_R_items['enchant_level']){
			$html .= "<td rowspan=\"2\" align=\"right\"><strong><big style=\"font-size:20px\">{$rank2}</big></strong></td>";
		}else{
			$rank2=$rank;$html .= "<td align=\"right\"><strong><big style=\"font-size:20px\">{$rank2}</big></strong></td>";$rank_check=$row_R_items['enchant_level'];
		}
		$html .= "<td><img src=\"/ranking/images/inv_gfx/{$row_R_items['invgfx']}.png\"
		 align=\"left\" hspace=\"5\"><strong><span style=\"font-size:13px\">
		 {$item_name}</span></strong><br />
		 {$char_name[$row_R_items['owner_id']]}</td>";
		
		if (($rank % ($oe_num / 2)) == 0){
			$html .= "</tr>\n";
			$html .= "</table>\n";
			$html .= "</td><td>\n";
			$html .= "<table><tr>\n";
			$html .= "</tr><tr>\n";
		}else{
			$html .= "</tr><tr>\n</tr><tr>\n";
		}

	} while ($row_R_items = mysql_fetch_assoc($R_items));
}

$html .= "</table>\n";
$html .= "</td></tr></table>\n";

file_put_contents($filename,$html);

echo $html;

mysql_free_result($R_items);

}else{
	include($filename);
}
?>
