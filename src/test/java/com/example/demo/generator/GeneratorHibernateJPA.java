package com.example.demo.generator;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.example.demo.generator.model.Bid;
import com.example.demo.generator.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Lo unico que necesite es crear el persistence.xml y ya está!
 * 
 * Hay otra forma programatica que podemos verla en spilca, generando una clase CustomPersistenceUnitInfo.java
 * Eso reemplazaria el XML
 * 
 * En una aplicación podremos formar el factory en un @Bean y luego usar el entityManager en cada clase específica 
 * 
 * Acá NO estamos usando Spring Data, es Hibernate con JPA puro
 * 
 * NO usamos el repository que nos provee de métodos, eso se usa en Spring Data
 * 
 * Por eso tenemos mucho más boiler plate code, tendremos quizás más funcionalidades, pero más código
 * 
 * No usamos los métodos, tenemos que iniciar transacciones, escribir queries, todo
 * 
 * @author peter
 *
 */
public class GeneratorHibernateJPA {

	@Test
    public void storeLoadItem() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ch05.generator");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Item item = new Item();
            item.setName("Some Item");
            item.setAuctionEnd(Helper.tomorrow());

            em.persist(item);

            em.getTransaction().commit();
            em.getTransaction().begin();

            List<Item> items =
                    em.createQuery("select i from Item i", Item.class).getResultList();
            //SELECT * from ITEM

            em.getTransaction().commit();

            assertAll(
                    () -> assertEquals(1, items.size()),
                    () -> assertEquals("Some Item", items.get(0).getName())
            );
            
            BigDecimal amount = BigDecimal.valueOf(88.16);
            
            // armamos el bid y se asocia al item
            Bid bid = new Bid(amount,item);
            
            em.getTransaction().begin();
            em.persist(bid);
            em.getTransaction().commit();

        } finally {
            em.close();
            emf.close();
        }
    }
}
