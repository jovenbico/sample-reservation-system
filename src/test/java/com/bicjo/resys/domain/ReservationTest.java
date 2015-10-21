package com.bicjo.resys.domain;

import org.junit.Assert;
import org.junit.Test;

import com.bicjo.resys.domain.exception.ReservationException;

public class ReservationTest {

	@Test
	public void createReservation() {

		TrainingSession trainingSession = new TrainingSession(5);

		try {

			long userId = 1;
			trainingSession.reserveSpace(userId, 3);

		} catch (ReservationException e) {
			Assert.fail();
		}

	}

	@Test
	public void createReservationNoAvailableSpace() {

		TrainingSession trainingSession = new TrainingSession(5);

		try {

			long userId = 1;

			trainingSession.reserveSpace(userId, 3);
			trainingSession.reserveSpace(userId, 3);

			Assert.fail();
		} catch (ReservationException e) {
			Assert.assertEquals(e.getMessage(), "No available space");
		}

	}

	@Test
	public void cancelReservation() {

		TrainingSession trainingSession = new TrainingSession(5);

		try {

			long userId = 1;

			Reservation reservation = trainingSession.reserveSpace(userId, 3);
			reservation.cancel();

			Assert.assertEquals(reservation.isCancelled(), true);
			Assert.assertEquals(reservation.occupiedSeats(), 0);
			Assert.assertEquals(trainingSession.availableSeats(), 5);

		} catch (ReservationException e) {
			Assert.fail();
		}

	}

}
