-- 统计指定用户论文个数
DROP PROCEDURE IF EXISTS proc_patent_stat; 

DELIMITER $$ 
CREATE PROCEDURE proc_patent_stat(usercode_var varchar(150))
BEGIN
DECLARE count int(11) DEFAULT 0; 
IF TRIM(usercode_var) != '' THEN
		SELECT    COUNT(*) INTO  count FROM t_zl where firstInvectorcode = usercode_var or secondInvectorcode = usercode_var  or thirdInvectorcode = usercode_var or fourthInvectorcode = usercode_var or fifthInvectorcode = usercode_var or otherInvectorcode like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET patent_all = count where usercode = usercode_var;
END IF;
END $$ 
DELIMITER ;

-- 统计其他作者论文个数
DROP PROCEDURE IF EXISTS proc_patent_othercode; 

DELIMITER $$ 
CREATE PROCEDURE proc_patent_othercode(usercode_var TEXT)
BEGIN
	DECLARE remainder TEXT;
	DECLARE delimiter VARCHAR(10) DEFAULT ',';
	DECLARE cur_position INT DEFAULT 1 ;
	DECLARE cur_string VARCHAR(1000);
	DECLARE delimiter_length TINYINT UNSIGNED;

	-- IF TRIM(usercode_var) != '' THEN
			SET remainder = usercode_var;
			SET delimiter_length = CHAR_LENGTH(delimiter);

      WHILE CHAR_LENGTH(remainder) > 0 AND cur_position > 0 DO
        SET cur_position = INSTR(remainder, delimiter);

				IF cur_position = 0 THEN
					SET cur_string = remainder;
				ELSE
					SET cur_string = LEFT(remainder, cur_position - 1);
        END IF;

				IF TRIM(cur_string) != '' THEN
						CALL proc_patent_stat(cur_string);
				END IF;

				SET remainder = SUBSTRING(remainder, cur_position + delimiter_length);
     END WHILE;
	-- END IF;
END $$ 
DELIMITER ;

DROP TRIGGER IF EXISTS tri_patent_insert;

DELIMITER $$ 
CREATE TRIGGER tri_patent_insert AFTER INSERT ON t_zl FOR EACH ROW
BEGIN
			CALL proc_patent_stat(NEW.firstInvectorcode);
			CALL proc_patent_stat(NEW.secondInvectorcode);
			CALL proc_patent_stat(NEW.thirdInvectorcode);
			CALL proc_patent_stat(NEW.fourthInvectorcode);
			CALL proc_patent_stat(NEW.fifthInvectorcode);
			CALL proc_patent_othercode(NEW.otherInvectorcode);
END $$ 
DELIMITER ;

DROP TRIGGER  IF EXISTS tri_patent_update;
DELIMITER $$ 
CREATE TRIGGER tri_patent_update AFTER UPDATE ON t_zl FOR EACH ROW
BEGIN
			IF OLD.firstInvectorcode != NEW.firstInvectorcode THEN
				CALL proc_patent_stat(OLD.firstInvectorcode);
				CALL proc_patent_stat(NEW.firstInvectorcode);
			END IF;

			IF OLD.secondInvectorcode != NEW.secondInvectorcode THEN
				CALL proc_patent_stat(OLD.secondInvectorcode);
				CALL proc_patent_stat(NEW.secondInvectorcode);
			END IF;

			IF OLD.thirdInvectorcode != NEW.thirdInvectorcode THEN
				CALL proc_patent_stat(OLD.thirdInvectorcode);
				CALL proc_patent_stat(NEW.thirdInvectorcode);
			END IF;

			IF OLD.fourthInvectorcode != NEW.fourthInvectorcode THEN
				CALL proc_patent_stat(OLD.fourthInvectorcode);
				CALL proc_patent_stat(NEW.fourthInvectorcode);
			END IF;

			IF OLD.fifthInvectorcode != NEW.fifthInvectorcode THEN
				 CALL proc_patent_stat(OLD.fifthInvectorcode);
				 CALL proc_patent_stat(NEW.fifthInvectorcode);
			END IF;

			IF OLD.otherInvectorcode != NEW.otherInvectorcode THEN
				CALL proc_patent_othercode(OLD.otherInvectorcode);
				CALL proc_patent_othercode(NEW.otherInvectorcode);
			END IF;
END $$ 
DELIMITER ;

DROP TRIGGER IF EXISTS tri_patent_delete;

DELIMITER $$ 
CREATE TRIGGER tri_patent_delete AFTER DELETE ON t_zl FOR EACH ROW
BEGIN
			CALL proc_patent_stat(OLD.firstInvectorcode);
			CALL proc_patent_stat(OLD.secondInvectorcode);
			CALL proc_patent_stat(OLD.thirdInvectorcode);
			CALL proc_patent_stat(OLD.fourthInvectorcode);
			CALL proc_patent_stat(OLD.fifthInvectorcode);
			CALL proc_patent_othercode(OLD.otherInvectorcode);
END $$ 
DELIMITER ;