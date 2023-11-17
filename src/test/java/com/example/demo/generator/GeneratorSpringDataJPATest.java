package com.example.demo.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import com.example.demo.generator.model.Item;
import com.example.demo.generator.configuration.ConfigForJPA;
import com.example.demo.generator.repositories.ItemRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.generator.configuration.SpringDataConfiguration;

/**
 * Implementamos Spring Data!
 * 
 * Podemos usar ConfingForJPA que usará Springboot, y levantará la data de la base del archivo application.properties
 * 
 * O podemos hacerlo con SpringDataConfiguration que setea con beans la base desde la tipica configuracion de Spring
 * 
 * @author peter
 *
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class GeneratorSpringDataJPATest extends ConfigForJPA {
//public class GeneratorSpringDataJPATest {
	
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
