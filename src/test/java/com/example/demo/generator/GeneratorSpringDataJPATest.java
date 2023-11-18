package com.example.demo.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.example.demo.generator.model.Item;
import com.example.demo.generator.model.Bid;
import com.example.demo.generator.configuration.ConfigForJPA;
import com.example.demo.generator.repositories.BidRepository;
import com.example.demo.generator.repositories.ItemRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.generator.configuration.SpringDataConfiguration;

/**
 * Implementamos Spring Data!
 * 
 * Podemos usar ConfingForJPA que usará Springboot, y levantará la data de la base del archivo application.properties
 * Esa fue la metodología para el proyecto anterior catalin_SpringDataJPA1
 * 
 * O podemos hacerlo con SpringDataConfiguration que setea con beans la base desde la tipica configuracion de Spring,
 * evitando usar el archivo application.properties
 * 
 * Acá usamos el repository que nos provee de métodos!
 * 
 * Mucho menos código que usando Hibernate/JPA puro, pero menos rendimiento
 * 
 * @author peter
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
//public class GeneratorSpringDataJPATest extends ConfigForJPA {
public class GeneratorSpringDataJPATest {
	
	@Autowired
    private ItemRepository itemRepository;
	
	@Autowired
	private BidRepository bidRepository;

    @Test
    void storeLoadItem() {

        Item item = new Item();
        item.setName("COCAINA #1");
        item.setAuctionEnd(Helper.tomorrow());

        itemRepository.save(item);

        List<Item> items = (List<Item>) itemRepository.findAll();

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals("COCAINA #1", items.get(0).getName())
        );
        
        Item item2 = new Item();
        item2.setName("Some Item #2");
        item2.setAuctionEnd(Helper.tomorrow());
        
        itemRepository.save(item2);
        
        BigDecimal amount = BigDecimal.valueOf(88.16);
        
        // armamos el bid y se asocia al item
        Bid bid = new Bid(amount,item);
        
        bidRepository.save(bid);
        
        Set<Bid> listaDeBids = item.getBids();
        System.out.println(listaDeBids.size());
        listaDeBids.forEach(bi->System.out.println(bi.getAmount()));
        
    }
}
