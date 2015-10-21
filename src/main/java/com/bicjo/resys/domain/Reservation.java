package com.bicjo.resys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "reservations")
public class Reservation implements Domain {

	private static final long serialVersionUID = 5570999411000873160L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "training_session_id")
	private TrainingSession trainingSession;

	@Column(name = "number_of_people")
	private int numberOfPeople;
	private boolean cancelled;

	public Reservation() {
		this(null, 0);
	}

	public Reservation(User user, int numberOfPeople) {
		this.user = user;
		this.numberOfPeople = numberOfPeople;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public TrainingSession getTrainingSession() {
		return trainingSession;
	}

	public void setTrainingSession(TrainingSession trainingSession) {
		this.trainingSession = trainingSession;
	}

	public void cancel() {
		cancelled = true;
	}

	public int occupiedSeats() {
		return cancelled ? 0 : numberOfPeople;
	}

}
