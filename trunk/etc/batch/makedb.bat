SET MYSQL=mysql
SET USER=root
SET PASSWORD=
%MYSQL%  --user=%USER% --password=%PASSWORD% --default-character-set=utf8 < ..\..\db\makedb.sql
%MYSQL%  --user=%USER% --password=%PASSWORD% --default-character-set=utf8 < ..\..\db\make_tables.sql
