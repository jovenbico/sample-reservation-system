package com.bicjo.resys.service.reservation;

import com.bicjo.resys.domain.TrainingSession;
import com.bicjo.resys.domain.exception.ReservationException;

public interface ReservationService {

	TrainingSession createTrainingSession(int capacity);

	void reserve(long userId, long trainingSessionId, int totalSets)
			throws ReservationException;

	void cancel(long reservationId);

}
