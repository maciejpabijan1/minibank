package com.bankproject.minibank;
import java.math.BigDecimal;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {

    private final KontoRepository repository;
    private final BankService bankService;

    public BankController(KontoRepository repository, BankService bankService) {
        this.repository = repository;
        this.bankService = bankService;
    }

    @GetMapping("/")
    public String pokazStroneGlowna(HttpSession session, org.springframework.ui.Model model) {

        String nrKontaZalogowanego = (String) session.getAttribute("zalogowanyNrKonta");

        if (nrKontaZalogowanego == null) {
            return "login";
        }

        java.util.Optional<Konto> znalezioneKonto = repository.findById(nrKontaZalogowanego);

        if (znalezioneKonto.isPresent()) {

            Konto zalogowanyUzytkownik = znalezioneKonto.get();

            model.addAttribute("imieUzytkownika", zalogowanyUzytkownik.getImie());
            model.addAttribute("nazwiskoUzytkownika", zalogowanyUzytkownik.getNazwisko());
            model.addAttribute("saldoUzytkownika", zalogowanyUzytkownik.getStanKonta());
            model.addAttribute("nrKontaUzytkownika", zalogowanyUzytkownik.getNrKonta());
        }

        return "przelewy";
    }

    @PostMapping("/zaloguj")
    public String zaloguj(@RequestParam String nrKonta,
                          @RequestParam String haslo,
                          HttpSession session) {

        java.util.Optional<Konto> znalezioneKonto = repository.findById(nrKonta);

        if (znalezioneKonto.isPresent()) {

            Konto kontoUzytkownika = znalezioneKonto.get();

            if (kontoUzytkownika.getHaslo().equals(Szyfrowanie.haszuj(haslo))) {

                session.setAttribute("zalogowanyNrKonta", nrKonta);

                return "redirect:/";

            } else {

                session.setAttribute("kom","Niepoprawne hasło");
                return "redirect:/blad";
            }

        } else {
            session.setAttribute("kom","Konto o takim numerze nie istnieje");
            return "redirect:/blad";
        }
    }

    @PostMapping("/wykonaj-przelew")
    public String obsluzPrzelew(@RequestParam String nrKontaOdbiorcy,
                                @RequestParam BigDecimal kwota,
                                HttpSession session) {

        String nrKontaNadawcy = (String) session.getAttribute("zalogowanyNrKonta");

        if (nrKontaNadawcy == null) {
            return "redirect:/login";
        }

        try {
            bankService.wykonajPrzelew(nrKontaNadawcy, nrKontaOdbiorcy, kwota);

            session.setAttribute("nrKontaOdbiorcy", nrKontaOdbiorcy);
            session.setAttribute("kwota", kwota);
            return "redirect:/podsumowanie";

        } catch (IllegalArgumentException | IllegalStateException e) {
            session.setAttribute("kom", e.getMessage());
            return "redirect:/blad";
        }
    }

    @GetMapping("/podsumowanie")
    public String pokazPodsumowanie(HttpSession session, org.springframework.ui.Model model) {

        String nrKontaZalogowanego = (String) session.getAttribute("zalogowanyNrKonta");

        if (nrKontaZalogowanego == null) {
            return "redirect:/login";
        }

        java.util.Optional<Konto> znalezioneKonto = repository.findById(nrKontaZalogowanego);

        if (znalezioneKonto.isPresent()) {
            Konto zalogowanyUzytkownik = znalezioneKonto.get();

            String nrKontaOdbiorcy = (String) session.getAttribute("nrKontaOdbiorcy");
            if (nrKontaOdbiorcy == null) {
                return "redirect:/";
            }

            java.util.Optional<Konto> znalezionyOdbiorca = repository.findById(nrKontaOdbiorcy);


            if (znalezionyOdbiorca.isPresent()) {
                Konto odbiorca = znalezionyOdbiorca.get();

                model.addAttribute("imieA", odbiorca.getImie());
                model.addAttribute("nazwiskoA", odbiorca.getNazwisko());
                model.addAttribute("nrKontaA", odbiorca.getNrKonta());
                model.addAttribute("kwota", session.getAttribute("kwota"));
            } else {
                return "redirect:/";
            }
        }
        return "podsumowanie";
    }
    @GetMapping("/blad")
    public String pokazBlad(HttpSession session, org.springframework.ui.Model model) {
        String kom=(String) session.getAttribute("kom");
        model.addAttribute("komunikat",kom);
        return "blad";
    }

    @PostMapping("/wyloguj")
    public String obsluzWylogowanie(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}