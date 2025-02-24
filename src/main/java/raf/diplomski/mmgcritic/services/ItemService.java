package raf.diplomski.mmgcritic.services;

import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.SearchResultDto;

import java.util.List;

@Service
public interface ItemService {
     List<SearchResultDto> searchAllByTitleContaining(String string,boolean limit);
}
