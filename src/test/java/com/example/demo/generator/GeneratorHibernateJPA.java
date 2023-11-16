package com.example.demo.generator;

import java.util.List;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.example.demo.generator.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Lo unico que necesite es crear el presistence.xml y ya está!
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

        } finally {
            em.close();
            emf.close();
        }
    }
}
