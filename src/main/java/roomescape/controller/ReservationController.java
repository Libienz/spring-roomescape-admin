package roomescape.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.controller.dto.ReservationRequestDto;
import roomescape.controller.dto.ReservationResponseDto;
import roomescape.entity.Reservation;

@RequestMapping("/reservations")
@RestController
public class ReservationController {

    private List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(1);

    @GetMapping()
    public ResponseEntity<List<ReservationResponseDto>> readAllReservations() {
        List<ReservationResponseDto> reservationResponses = reservations.stream()
                .map(ReservationResponseDto::from)
                .toList();
        return ResponseEntity.ok().body(reservationResponses);
    }

    @PostMapping()
    public ResponseEntity<ReservationResponseDto> createReservation(
            @RequestBody ReservationRequestDto reservationRequestDto) {
        long id = index.getAndIncrement();
        String name = reservationRequestDto.getName();
        LocalDate date = reservationRequestDto.getDate();
        LocalTime time = reservationRequestDto.getTime();

        Reservation newReservation = new Reservation(id, name, date, time);
        reservations.add(newReservation);
        return ResponseEntity.ok().body(ReservationResponseDto.from(newReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable("id") long id) {
        Reservation findReservation = reservations.stream()
                .filter(reservation -> reservation.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("id에 해당하는 예약을 찾을 수 없습니다: " + id));
        reservations.remove(findReservation);
        return ResponseEntity.ok().build();
    }
}
