package com.example.demo.generator.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.generator.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
