<?php
//�f�[�^�x�[�X�ڑ��ݒ�
$hostname_l1jdb = "localhost";
$database_l1jdb = "l1jdb";
$username_l1jdb = "root";
$password_l1jdb = "orekiba848";
$l1jdb = mysql_pconnect($hostname_l1jdb, $username_l1jdb, $password_l1jdb) or trigger_error(mysql_error(),E_USER_ERROR); 
mysql_query("SET NAMES sjis")or die("can not SET NAMES sjis");

//�y�[�W���X�V����Ԋu�i�b�j
$renewal_time = 60;

//�L�����摜������t�H���_�ւ̃p�X
$icon_dir = "/ranking/images/icon/small/";

//�L�����摜��
$chagfx_small[0] = "m_pri_mini.png";//�v���j
$chagfx_small[1] = "f_pri_mini.png";//�v����
$chagfx_small[61] = "m_kni_mini.png";//�i�C�g�j
$chagfx_small[48] = "f_kni_mini.png";//�i�C�g��
$chagfx_small[734] = "m_wiz_mini.png";//�E�B�Y�j
$chagfx_small[1186] = "f_wiz_mini.png";//�E�B�Y��
$chagfx_small[138] = "m_elf_mini.png";//�G���t�j
$chagfx_small[37] = "f_elf_mini.png";//�G���t��
$chagfx_small[2786] = "m_de_mini.png";//DE�j
$chagfx_small[2796] = "f_de_mini.png";//DE��
$chagfx_small[6658] = "m_dk_mini.png";//DRK�j
$chagfx_small[6661] = "f_dk_mini.png";//DRK��
$chagfx_small[6671] = "m_ill_mini.png";//ILL�j
$chagfx_small[6650] = "f_ill_mini.png";//ILL��

//�L�����N�^�[�̕\����
$td = "4";
?>