package com.example.demo.generator.configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.generator.repositories.ItemRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ConfigForJPA {

	@Autowired
    private ItemRepository itemRepository;
	
	/*
	@BeforeAll
	void beforeAll() {
		itemRepository.deleteAll();
	}
	*/
	
	/*
	@AfterAll
    void afterAll() {
		itemRepository.deleteAll();  // general methods, we don't implement them
    }
    */
}
