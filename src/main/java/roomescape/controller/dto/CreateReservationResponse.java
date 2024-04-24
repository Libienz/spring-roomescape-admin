package roomescape.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.entity.Reservation;

public class CreateReservationResponse {
    private final Long id;
    private final String name;
    private final LocalDate date;
    private final LocalTime time;

    public CreateReservationResponse(long id, String name, LocalDate date, LocalTime time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static CreateReservationResponse from(Reservation reservation) {
        return new CreateReservationResponse(
                reservation.getId(),
                reservation.getName(),
                reservation.getStartDate(),
                reservation.getStartTime());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}