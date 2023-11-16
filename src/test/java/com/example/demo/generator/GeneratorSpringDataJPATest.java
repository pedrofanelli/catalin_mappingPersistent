package com.example.demo.generator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.demo.generator.model.Item;
import com.example.demo.generator.configuration.ConfigForJPA;
import com.example.demo.generator.repositories.ItemRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


public class GeneratorSpringDataJPATest extends ConfigForJPA {

	@Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadItem() {

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(Helper.tomorrow());

        itemRepository.save(item);

        List<Item> items = (List<Item>) itemRepository.findAll();

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals("Some Item", items.get(0).getName())
        );
        
        Item item2 = new Item();
        item2.setName("Some Item 2");
        item2.setAuctionEnd(Helper.tomorrow());
        
        itemRepository.save(item2);

    }
}
