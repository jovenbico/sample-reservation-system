package com.bicjo.resys.service.reservation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bicjo.resys.core.email.EmailSender;
import com.bicjo.resys.core.repository.Repository;
import com.bicjo.resys.domain.Reservation;
import com.bicjo.resys.domain.TrainingSession;
import com.bicjo.resys.domain.exception.ReservationException;
import com.bicjo.resys.service.reservation.ReservationService;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	@Qualifier(Repository.HIBERNATE)
	private Repository repository;

	@Autowired
	private EmailSender emailSender;

	@Override
	public TrainingSession createTrainingSession(int capacity) {

		TrainingSession trainingSession = new TrainingSession(5);
		repository.insert(trainingSession);

		return trainingSession;

	}

	@Override
	public void reserve(long userId, long trainingSessionId, int totalSets)
			throws ReservationException {

		TrainingSession trainingSession = repository.retrieve(
				TrainingSession.class, trainingSessionId);

		Reservation newReservation = trainingSession.reserveSpace(userId,
				totalSets);
		repository.insert(newReservation);
	}

	@Override
	public void cancel(long reservationId) {

		Reservation reservation = repository.retrieve(Reservation.class,
				reservationId);
		reservation.cancel();
		repository.update(reservation);

	}

}
