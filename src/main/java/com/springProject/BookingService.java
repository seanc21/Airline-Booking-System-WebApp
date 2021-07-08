package com.springProject;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository repo;

    public List<Booking> listAll() {
        return repo.findAll();
    }

    public void save(Booking booking) {
        System.out.println(booking.toString());
        repo.save(booking);
    }

    public Booking get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public static String confirmationNumber() {
        int length = 6;
        String code = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // 36 letter.
        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomCode.append(code.charAt(random.nextInt(code.length())));
        }
        return randomCode.toString();
    }

    public List<Booking> getUsersByUserId(Long Id) {
        return repo.findByUserId(Id);
    }

    public String update(long oldId, Booking booking) {
        Optional<Booking> possibleSheet = repo.findById(oldId);
        Booking oldBooking;
        if (possibleSheet.isPresent()) {
            oldBooking = possibleSheet.get();
            oldBooking.setPassFirstName(booking.getPassFirstName());
            oldBooking.setPassLastName(booking.getPassLastName());
            oldBooking.setTravelFrom(booking.getTravelFrom());
            oldBooking.setTravelTo(booking.getTravelTo());
            oldBooking.setDateOfDeparture(booking.getDateOfDeparture());
            oldBooking.setDateOfReturning(booking.getDateOfReturning());

            repo.save(oldBooking);
            return oldBooking.getConfirmationNumber();
        }
        return "";
    }
}