package org.ricky.common.sensitiveword.infrastructure;

import lombok.RequiredArgsConstructor;
import org.ricky.common.sensitiveword.domain.SensitiveWord;
import org.ricky.common.sensitiveword.domain.SensitiveWordRepository;
import org.ricky.common.sensitiveword.infrastructure.mapper.SensitiveWordMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/21
 * @className MySqlSensitiveWordRepository
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class MySqlSensitiveWordRepository implements SensitiveWordRepository {

    private final SensitiveWordMapper sensitiveWordMapper;

    @Override
    @Cacheable(value = "SENSITIVE_WORD")
    public List<String> listAllSensitiveWord() {
        List<SensitiveWord> sensitiveWords = sensitiveWordMapper.listAllSensitiveWord();
        return sensitiveWords.stream()
                .map(SensitiveWord::getWord)
                .toList();
    }
}
