-- 统计指定用户论文个数
DROP PROCEDURE IF EXISTS proc_writting_stat; 

DELIMITER $$ 
CREATE PROCEDURE proc_writting_stat(usercode_var varchar(150))
BEGIN
DECLARE count int(11) DEFAULT 0; 
IF TRIM(usercode_var) != '' THEN
		SELECT    COUNT(*) INTO  count FROM t_writing where first_editor_code = usercode_var or second_editor_code = usercode_var  or third_editor_code = usercode_var or first_author_code = usercode_var or second_author_code = usercode_var  or third_author_code = usercode_var or fourth_author_code = usercode_var or fifth_author_code = usercode_var or other_author_code like concat('%',usercode_var,'%') or other_editor_code like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET writting_all = count where usercode = usercode_var;
END IF;
END $$ 
DELIMITER ;

-- 统计其他作者论文个数
DROP PROCEDURE IF EXISTS proc_writting_othercode; 

DELIMITER $$ 
CREATE PROCEDURE proc_writting_othercode(usercode_var TEXT)
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
						CALL proc_writting_stat(cur_string);
				END IF;

				SET remainder = SUBSTRING(remainder, cur_position + delimiter_length);
     END WHILE;
	-- END IF;
END $$ 
DELIMITER ;

DROP TRIGGER IF EXISTS tri_writting_insert;

DELIMITER $$ 
CREATE TRIGGER tri_writting_insert AFTER INSERT ON t_writing FOR EACH ROW
BEGIN
			CALL proc_writting_stat(NEW.first_editor_code);
			CALL proc_writting_stat(NEW.second_editor_code);
			CALL proc_writting_stat(NEW.third_editor_code);
			CALL proc_writting_othercode(NEW.other_editor_code);

			CALL proc_writting_stat(NEW.first_author_code);
			CALL proc_writting_stat(NEW.second_author_code);
			CALL proc_writting_stat(NEW.third_author_code);
			CALL proc_writting_stat(NEW.fourth_author_code);
			CALL proc_writting_stat(NEW.fifth_author_code);
			CALL proc_writting_othercode(NEW.other_author_code);
END $$ 
DELIMITER ;

DROP TRIGGER  IF EXISTS tri_writting_update;
DELIMITER $$ 
CREATE TRIGGER tri_writting_update AFTER UPDATE ON t_writing FOR EACH ROW
BEGIN
IF OLD.first_editor_code != NEW.first_editor_code THEN
				CALL proc_writting_stat(OLD.first_editor_code);
				CALL proc_writting_stat(NEW.first_editor_code);
			END IF;

			IF OLD.second_editor_code != NEW.second_editor_code THEN
				CALL proc_writting_stat(OLD.second_editor_code);
				CALL proc_writting_stat(NEW.second_editor_code);
			END IF;

			IF OLD.third_editor_code != NEW.third_editor_code THEN
				CALL proc_writting_stat(OLD.third_editor_code);
				CALL proc_writting_stat(NEW.third_editor_code);
			END IF;

			IF OLD.other_editor_code != NEW.other_editor_code THEN
				CALL proc_writting_othercode(OLD.other_editor_code);
				CALL proc_writting_othercode(NEW.other_editor_code);
			END IF;


			IF OLD.first_author_code != NEW.first_author_code THEN
				CALL proc_writting_stat(OLD.first_author_code);
				CALL proc_writting_stat(NEW.first_author_code);
			END IF;

			IF OLD.second_author_code != NEW.second_author_code THEN
				CALL proc_writting_stat(OLD.second_author_code);
				CALL proc_writting_stat(NEW.second_author_code);
			END IF;

			IF OLD.third_author_code != NEW.third_author_code THEN
				CALL proc_writting_stat(OLD.third_author_code);
				CALL proc_writting_stat(NEW.third_author_code);
			END IF;

			IF OLD.fourth_author_code != NEW.fourth_author_code THEN
				CALL proc_writting_stat(OLD.fourth_author_code);
				CALL proc_writting_stat(NEW.fourth_author_code);
			END IF;

			IF OLD.fifth_author_code != NEW.fifth_author_code THEN
				 CALL proc_writting_stat(OLD.fifth_author_code);
				 CALL proc_writting_stat(NEW.fifth_author_code);
			END IF;

			IF OLD.other_author_code != NEW.other_author_code THEN
				CALL proc_writting_othercode(OLD.other_author_code);
				CALL proc_writting_othercode(NEW.other_author_code);
			END IF;
END $$ 
DELIMITER ;

DROP TRIGGER IF EXISTS tri_writting_delete;

DELIMITER $$ 
CREATE TRIGGER tri_writting_delete AFTER DELETE ON t_writing FOR EACH ROW
BEGIN
			CALL proc_writting_stat(OLD.first_editor_code);
			CALL proc_writting_stat(OLD.second_editor_code);
			CALL proc_writting_stat(OLD.third_editor_code);
			CALL proc_writting_othercode(OLD.other_editor_code);

			CALL proc_writting_stat(OLD.first_author_code);
			CALL proc_writting_stat(OLD.second_author_code);
			CALL proc_writting_stat(OLD.third_author_code);
			CALL proc_writting_stat(OLD.fourth_author_code);
			CALL proc_writting_stat(OLD.fifth_author_code);
			CALL proc_writting_othercode(OLD.other_author_code);
END $$ 
DELIMITER ;