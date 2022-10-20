package com.cg.fitnesstracker.app.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.fitnesstracker.app.exceptions.ActivityException;
import com.cg.fitnesstracker.app.model.Activity;
import com.cg.fitnesstracker.app.model.Cardio;
import com.cg.fitnesstracker.app.model.Customer;
import com.cg.fitnesstracker.app.model.enums.CardioType;
import com.cg.fitnesstracker.app.repository.ActivityRepository;
import com.cg.fitnesstracker.app.repository.CardioRepository;
import com.cg.fitnesstracker.app.repository.CustomerRepository;
import com.cg.fitnesstracker.app.service.ActivityService;

@Component
public class ActivityServiceImpl implements ActivityService {

	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ActivityRepository activityRepo;
	@Autowired
	private CardioRepository cardioRepo;
	
	@Override
	public Activity getActivityById(int activityId)
	{
		Optional<Activity> activity= activityRepo.findById(activityId);
		if(!activity.isPresent())
		{
			throw new ActivityException("No such activity found", 404);
		}
		return activity.get();
	}
	
	@Override
	public int getCaloriesBurned(String userName, int activityId)
	{
		Cardio cardio=cardioRepo.findById(activityId).get();
		Customer cust=customerRepository.findByUsername(userName);
		
		float weightfact= (float) (cust.getWeight()*0.02);
		
		float calories=0;
		if(cardio.getCardioType().equals(CardioType.RUNNING))
		{
			calories = cardio.getTimeInMinutes()*14*weightfact;
		}
		else if(cardio.getCardioType().equals(CardioType.JOGGING))
		{
			calories = cardio.getTimeInMinutes()*9*weightfact;
		}
		else if(cardio.getCardioType().equals(CardioType.CYCLING))
		{
			calories = cardio.getTimeInMinutes()*10*weightfact;
		}
		else if(cardio.getCardioType().equals(CardioType.SWIMING))
		{
			calories = cardio.getTimeInMinutes()*9*weightfact;
		}
		else if(cardio.getCardioType().equals(CardioType.WALKING))
		{
			calories = cardio.getTimeInMinutes()*6*weightfact;
		}
		else
		{
			throw new ActivityException("Invalid Cardio", 400);
		}
		System.out.println("=======================");
		System.out.println(calories);
		return (int) calories;
	}
	
	@Override
	public Activity addCardioActivityService(String username, Activity cardioActivity) {

		Customer customer = customerRepository.findByUsername(username);
		
		if(customer!=null)
		{
			cardioActivity.setCustomer(customer);
			
			List<Activity> activityList=customer.getActivities();
			activityList.add(cardioActivity);
			activityRepo.saveAll(activityList);
			customer.setActivities(activityList);
		}
		return  cardioActivity;
	}

	@Override
	public Activity addWorkoutActivityService(String username, Activity workoutActivity) {
		Customer customer = customerRepository.findByUsername(username);

		if(customer!=null)
		{
			workoutActivity.setCustomer(customer);
			
			workoutActivity.setCustomer(customer);
			List<Activity> activityList=customer.getActivities();
			activityList.add(workoutActivity);
			activityRepo.saveAll(activityList);
		}
		return  workoutActivity;
	}

	@Override
	@Transactional
	public Activity deleteActivity(String userName, int activityId) {
		Activity activity=activityRepo.findById(activityId).get();
//		Activity cardioActivity=cardioRepo.findById(activityId).get();
		if(activity!=null)
		{
			activityRepo.deleteByActivityId(activityId);
		}
		return activity;
	}
	
	@Override
	public List<Activity> getActivity(String userName) {
		Customer customer=customerRepository.findByUsername(userName);

		List<Activity> activityList=customer.getActivities();
		
		return activityList;
	}

//	@Override
//	@Transactional
//	public List<Cardio> getCardioActivity(CardioType cardioType) {
//		List<Cardio> cardio=activityRepo.getCardioActivity(cardioType);
//		return cardio;
//	}

	
//	@Override
//	public Workout deleteWorkoutActivityService(String userName, WorkoutType workoutType) {
//		Customer customer=customerRepository.findByUserName(userName);
//
//		Workout workout=workoutRepo.findByWorkoutType(workoutType);
//		if(workout!=null && customer!=null)
//		{
//			List<Activity> activityList=customer.getActivities();
//			activityList.remove(workout);
//			activityRepo.saveAll(activityList);
//		}
//		return workout;
//	}

//	@Override
//	public Cardio updateCardioActivityService(int userId, Cardio cardioActivity) {
//		cardioService.updateExistingCardio(userId, cardioActivity);
//		return cardioActivity;
//	}
//
//	@Override
//	public Workout updateWorkoutActivityService(int userId, Workout workoutActivity) {
//		workoutService.updateExistingWorkout(userId, workoutActivity);
//		return workoutActivity;
//	}

}
