package me.wickersty.mortuusterra.objects;

public class SupplyDropContentsObject {
	
	public Integer itemId;
	public Integer itemQuantity;
	public Double itemChance;

	public SupplyDropContentsObject(Integer itemId, Integer itemQuantity, double itemChance) {
		
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
		this.itemChance = itemChance;
		
	}
	
}
