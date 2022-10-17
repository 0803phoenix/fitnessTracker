package com.cg.fitnesstracker.app.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.cg.fitnesstracker.app.model.enums.CardioType;
import com.cg.fitnesstracker.app.model.enums.DayOfWeek;

@Entity
public class Cardio extends Activity{
//    @Id
//    private int cardioId;
    private CardioType cardioType;
    private int distance;
    private int timeInMinutes;
    private DayOfWeek dayOfWeek;
    private boolean succesFlag;
    private LocalDate date;
    
    public Cardio() {}

	public LocalDate getDate() {
		return date;
	}

	public void setDate() {
		this.date = LocalDate.now();
	}
    
    public boolean isSuccesFlag() {
		return succesFlag;
	}

	public void setSuccesFlag(boolean succesFlag) {
		this.succesFlag = succesFlag;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

    public CardioType getCardioType() {
        return cardioType;
    }
    public void setCardioType(CardioType cardioType) {
        this.cardioType = cardioType;
    }
    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public int getTimeInMinutes() {
        return timeInMinutes;
    }
    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }
    
    
    
}