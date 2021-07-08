package com.springProject;

import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private BookingService bookingService;

//    @Autowired
//    private CustomUserDetailsService userService;

    @GetMapping("")
    public String viewWelcomePage() {
        return "index";
    }

    @GetMapping("/home")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_success.html";
    }

    @GetMapping("/home/booking_flight/create")
    public String viewUserAccountForm(Model model){
        model.addAttribute("booking", new Booking());
        return "booking.html";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("booking") Booking booking) {
        bookingRepo.save(booking);
        return "redirect:/home/booking_flight/success";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_booking");
        Booking booking = bookingService.get(id);
        mav.addObject("booking", booking);
        return mav;
    }

    @RequestMapping(value = "/edit/{id}/edit_success", method = RequestMethod.POST)
    public String editSuccess(@PathVariable(name = "id") int id, Booking booking, Model model) {
        System.out.println(booking);
        model.addAttribute("confirmationNumber", bookingService.update(id, booking));
        return "edit_success.html";
    }

    @PostMapping("/home/booking_flight/success")
    public String processBooking(Booking booking, @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Long userId = userDetails.getUserId();
        booking.setUserId(userId);
        int length = 6;
        String code = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // 36 letter.
        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomCode.append(code.charAt(random.nextInt(code.length())));
        }
        booking.setConfirmationNumber(randomCode.toString());
        bookingService.save(booking);
        model.addAttribute("flight", booking);
        return "booking_success.html";
    }

    @GetMapping("/home/booking_flight/thank_you")
    public String thankYouPage() {
        return "booking_thankyou.html";
    }

    @GetMapping("/home/booked_flights")
    public String reviewBookedFlights(Booking booking, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
//        List<Booking> getUsersByUserId(@PathVariable Long id);
        Long userId = userDetails.getUserId();
        List<Booking> bookedList = bookingRepo.findByUserId(userId);
        model.addAttribute("bookedList", bookedList);
        return "booked_flights.html";
    }

    @GetMapping("/home/check-in")
    public String checkIn(Booking booking, Model model) {
        return "check_in.html";
    }
}