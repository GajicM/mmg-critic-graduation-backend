package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.repositories.ItemRepository;
import raf.diplomski.mmgcritic.services.ItemService;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
private final ItemRepository itemRepository;


}
