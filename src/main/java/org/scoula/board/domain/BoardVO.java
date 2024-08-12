package org.scoula.board.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // <setting name="mapUnderscoreToCamelCase" value="true"/>

    // mapUnderscoreToCamelCase ->  스네이크 케이스 == 카멜 케이스

    // reg_date -> regDate
    // update_date -> updateDate


}
