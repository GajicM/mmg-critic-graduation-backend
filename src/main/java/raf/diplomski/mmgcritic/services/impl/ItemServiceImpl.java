package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.SearchResultDto;
import raf.diplomski.mmgcritic.repositories.ItemRepository;
import raf.diplomski.mmgcritic.services.ItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
private final ItemRepository itemRepository;

    public List<SearchResultDto> searchAllByTitleContaining(String string,boolean limit) {
        List<Object[]> results;
        if(limit)
            results = itemRepository.searchAllByTitleWithLimit(string);
        else
            results= itemRepository.searchAllByTitleNoLimit(string);
        List<SearchResultDto> searchResults = new ArrayList<>();

        for (Object[] result : results) {
            String title = (String) result[0];
            String type = (String) result[1];
            Long id = (Long) result[2];
            searchResults.add(new SearchResultDto(title, type, id));
        }

        return searchResults;


    }

}
