package org.ricky.core.common.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.ricky.core.common.domain.marker.Identified;
import org.ricky.core.common.validation.id.shortuuid.ShortUUID;

import static org.ricky.core.common.constants.MyConstants.MAX_URL_LENGTH;


/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/9
 * @className UploadedFile
 * @desc 上传文件
 */
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadedFile implements Identified {

    /**
     * id，用于前端loop时作为key
     */
    @NotBlank
    @ShortUUID
    private final String id;

    /**
     * 文件名称
     */
    @Size(max = 200)
    private String name;

    /**
     * 文件类型
     */
    @NotBlank
    @Size(max = 500)
    private final String type;

    /**
     * 文件url
     */
    @NotBlank
    @Size(max = MAX_URL_LENGTH)
    private final String fileUrl;

    /**
     * 阿里云的文件key
     */
    @Size(max = 500)
    private final String ossKey;

    /**
     * 文件大小
     */
    @Min(0)
    private final int size;

    @Override
    public String getIdentifier() {
        return id;
    }

}
