package raf.diplomski.mmgcritic.controllers;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raf.diplomski.mmgcritic.services.ItemService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/items")
public class ItemController {
    private final ItemService itemService;
}
