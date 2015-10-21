package com.bicjo.resys.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bicjo.resys.domain.exception.ReservationException;

@Entity(name = "training_sessions")
public class TrainingSession implements Domain {

	private static final long serialVersionUID = -4475708079342850468L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "total_capacity")
	private int totalCapacity;

	@OneToMany(mappedBy = "trainingSession")
	private List<Reservation> reservations = new ArrayList<>();

	public TrainingSession() {
		this(0);
	}

	public TrainingSession(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation reserveSpace(long userId, int numberOfPeople)
			throws ReservationException {

		if (seatsAvailable(numberOfPeople)) {
			User requestor = new User(userId);
			Reservation newReservation = new Reservation(requestor,
					numberOfPeople);
			reservations.add(newReservation);
			return newReservation;
		} else {
			throw new ReservationException("No available space");
		}

	}

	private boolean seatsAvailable(int numberOfPeople) {

		int reservedCount = reservedSeats();

		return (reservedCount + numberOfPeople) <= totalCapacity;
	}

	private int reservedSeats() {
		int reservedCount = 0;
		for (Reservation each : reservations) {
			reservedCount += each.occupiedSeats();
		}
		return reservedCount;
	}

	public int availableSeats() {
		return totalCapacity - reservedSeats();
	}

}
