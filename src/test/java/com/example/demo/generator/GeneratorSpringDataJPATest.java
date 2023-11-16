package com.example.demo.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import com.example.demo.generator.model.Item;

import com.example.demo.generator.configuration.SpringDataConfiguration;
import com.example.demo.generator.repositories.ItemRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class GeneratorSpringDataJPATest {

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

    }
}
