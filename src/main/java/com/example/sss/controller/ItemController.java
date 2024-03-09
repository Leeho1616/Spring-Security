package com.example.sss.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sss.entity.Item;
import com.example.sss.form.ItemForm;
import com.example.sss.repository.ItemRepository;

@Controller
public class ItemController {

	private final ItemRepository itemRepository;

	public ItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	// トップページ表示
	@GetMapping("/")
	public String index() {
		return "top";
	}

	// 商品一覧表示
	@GetMapping("/items")
	public String items(Model model) {
		List<Item> itemList = itemRepository.findByOrderById();
		model.addAttribute("items", itemList);

		return "items";
	}

	// [新規登録]
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("itemForm", new ItemForm());
		
		return "create";
	}

	// 登録画面の処理
	// [登録]
	@PostMapping("/create")
	@Transactional
	public String doCreate(
			@Validated @ModelAttribute ItemForm itemform, 
			BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			return "create";
		}
	
		itemRepository.save(itemform.toItemEntity());
		
		return "redirect:/items";
	}

	//	[キャンセル]
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/items";
	}

	// 更新画面表示
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		Item item = itemRepository.findById(id).get();
		model.addAttribute("itemForm", item);

		return "update";
	}

	// 更新画面の処理
	@PostMapping("/update")
	@Transactional
	public String doUpdate(
			@Validated @ModelAttribute ItemForm itemform,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "update";
		}

		itemRepository.save(itemform.toItemEntity());
		
		return "redirect:/items";
	}

	// 削除処理
	@PostMapping("/delete")
	@Transactional
	public String delete(@RequestParam Integer id) {
		itemRepository.deleteById(id);

		return "redirect:/items";
	}

}
