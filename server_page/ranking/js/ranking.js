// JavaScript Document
function ranking(script,sw){
	query = [];
	query['sw'] = sw;
	sendRequest(ranking_loaded,query,'POST','/ranking/'+script+'.php',true,true);
}
function ranking_loaded(oj){
	document.getElementById('ranking_view').innerHTML=oj.responseText;
}
ranking('Exp','ALL');