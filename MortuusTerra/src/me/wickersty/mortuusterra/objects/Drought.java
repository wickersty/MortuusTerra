package me.wickersty.mortuusterra.objects;

import java.util.Random;

import me.wickersty.mortuusterra.MortuusTerra;

public class Drought {
	
	private final MortuusTerra instance;
	
	public Boolean droughtActive;
	public Integer daysSinceDrought;
	public Integer droughtLength;


	public Drought(MortuusTerra instance, Boolean droughtActive) {
		
		this.instance = instance;
		
		this.droughtActive = droughtActive;
		this.daysSinceDrought = 0;

		Random randomInt = new Random();
		this.droughtLength = randomInt.nextInt(instance.getConfigManager().droughtsMaxLength) + 1;

		if (droughtActive == true) {
		
			instance.getWorldManager().announceBeginningOfDrought();
			
		}

	}
	
	public Boolean getDroughtActive() {
		
		return droughtActive;
		
	}
	
	public Integer getDaysSinceDrought() {
		
		return daysSinceDrought;
		
	}
	
	public Integer getDroughtLength() {
		
		return droughtLength;
		
	}
	
	public void incrementDroughtLength() {
		
		daysSinceDrought++;
		
		if (daysSinceDrought == droughtLength) {
			
			endDrought();
			
		}
		
	}
	
	public void endDrought() {
		
		this.droughtActive = false;

		this.daysSinceDrought = 0;
		this.droughtLength = 0;
		
		instance.getWorldManager().announceEndOfDrought();
		
	}

}
