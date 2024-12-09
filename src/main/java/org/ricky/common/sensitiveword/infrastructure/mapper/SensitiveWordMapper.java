package org.ricky.common.sensitiveword.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ricky.common.sensitiveword.domain.SensitiveWord;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/21
 * @className SensitiveWordMapper
 * @desc
 */
@Mapper
public interface SensitiveWordMapper {

    @Select("select `word` from my_sensitive_word.sensitive_word")
    List<SensitiveWord> listAllSensitiveWord();

}
