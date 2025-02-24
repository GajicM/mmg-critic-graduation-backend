package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.services.ItemService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/limit/{title}")
    public ResponseEntity<?> searchAllByTitleContainingWithLimit(@PathVariable String title){
        try{
            return ResponseEntity.ok(itemService.searchAllByTitleContaining(title,true));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/no-limit/{title}")
    public ResponseEntity<?> searchAllByTitleContainingNoLimit(@PathVariable String title){
        try{
            return ResponseEntity.ok(itemService.searchAllByTitleContaining(title,false));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }
}
