package com.example.sss.form;

import com.example.sss.entity.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {

	private Integer id;

	@NotBlank(message = "商品名を入力してください")
	private String name;

	@PositiveOrZero(message = "価格は0以上にしてください")
	private String price;

	@PositiveOrZero(message = "在庫数は0以上にしてください")
	private String stockQuantity;

	//	Itemエンティティへ変換するヘルパーメソッド
	public Item toItemEntity() {
		return new Item(
				id, 
				name, 
				Integer.parseInt(price), 
				Integer.parseInt(stockQuantity));
	}

	//	Itemエンティティから変換するヘルパーメソッド
	public ItemForm fromItemEntity(Item item) {
		return new ItemForm(
					item.getId(), 
					item.getName(), 
					item.getPrice().toString(), 
					item.getStockQuantity().toString());
	}
}
