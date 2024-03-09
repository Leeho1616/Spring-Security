package com.example.sss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sss.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByOrderById();
}
