package raf.diplomski.mmgcritic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class SearchResultDto {
    private String title;
    private String type;
    private Long id;
}
