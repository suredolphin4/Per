-- 统计指定用户论文个数
DROP PROCEDURE IF EXISTS proc_plan_stat; 

DELIMITER $$ 
CREATE PROCEDURE proc_plan_stat(usercode_var varchar(150))
BEGIN
DECLARE count int(11) DEFAULT 0; 
IF TRIM(usercode_var) != '' THEN
		SELECT    COUNT(*) INTO  count FROM t_gh where firstauthorcode = usercode_var or secauthorcode = usercode_var  or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET plan_all = count where usercode = usercode_var;
END IF;
END $$ 
DELIMITER ;

-- 统计其他作者论文个数
DROP PROCEDURE IF EXISTS proc_plan_othercode; 

DELIMITER $$ 
CREATE PROCEDURE proc_plan_othercode(usercode_var TEXT)
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
						CALL proc_plan_stat(cur_string);
				END IF;

				SET remainder = SUBSTRING(remainder, cur_position + delimiter_length);
     END WHILE;
	-- END IF;
END $$ 
DELIMITER ;

DROP TRIGGER IF EXISTS tri_plan_insert;

DELIMITER $$ 
CREATE TRIGGER tri_plan_insert AFTER INSERT ON t_gh FOR EACH ROW
BEGIN
			CALL proc_plan_stat(NEW.firstauthorcode);
			CALL proc_plan_stat(NEW.secauthorcode);
			CALL proc_plan_stat(NEW.threeauthorcode);
			CALL proc_plan_stat(NEW.fourauthorcode);
			CALL proc_plan_stat(NEW.fiveauthorcode);
			CALL proc_plan_othercode(NEW.otherauthorcode);
END $$ 
DELIMITER ;

DROP TRIGGER  IF EXISTS tri_plan_update;
DELIMITER $$ 
CREATE TRIGGER tri_plan_update AFTER UPDATE ON t_gh FOR EACH ROW
BEGIN
			IF OLD.firstauthorcode != NEW.firstauthorcode THEN
				CALL proc_plan_stat(OLD.firstauthorcode);
				CALL proc_plan_stat(NEW.firstauthorcode);
			END IF;

			IF OLD.secauthorcode != NEW.secauthorcode THEN
				CALL proc_plan_stat(OLD.secauthorcode);
				CALL proc_plan_stat(NEW.secauthorcode);
			END IF;

			IF OLD.threeauthorcode != NEW.threeauthorcode THEN
				CALL proc_plan_stat(OLD.threeauthorcode);
				CALL proc_plan_stat(NEW.threeauthorcode);
			END IF;

			IF OLD.fourauthorcode != NEW.fourauthorcode THEN
				CALL proc_plan_stat(OLD.fourauthorcode);
				CALL proc_plan_stat(NEW.fourauthorcode);
			END IF;

			IF OLD.fiveauthorcode != NEW.fiveauthorcode THEN
				 CALL proc_plan_stat(OLD.fiveauthorcode);
				 CALL proc_plan_stat(NEW.fiveauthorcode);
			END IF;

			IF OLD.otherauthorcode != NEW.otherauthorcode THEN
				CALL proc_plan_othercode(OLD.otherauthorcode);
				CALL proc_plan_othercode(NEW.otherauthorcode);
			END IF;
END $$ 
DELIMITER ;

DROP TRIGGER IF EXISTS tri_plan_delete;

DELIMITER $$ 
CREATE TRIGGER tri_plan_delete AFTER DELETE ON t_gh FOR EACH ROW
BEGIN
			CALL proc_plan_stat(OLD.firstauthorcode);
			CALL proc_plan_stat(OLD.secauthorcode);
			CALL proc_plan_stat(OLD.threeauthorcode);
			CALL proc_plan_stat(OLD.fourauthorcode);
			CALL proc_plan_stat(OLD.fiveauthorcode);
			CALL proc_plan_othercode(OLD.otherauthorcode);
END $$ 
DELIMITER ;