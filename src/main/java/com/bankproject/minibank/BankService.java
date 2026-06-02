package com.bankproject.minibank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
@Service
public class BankService {

    private final KontoRepository repository;

    public BankService(KontoRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public void wykonajPrzelew(String nrNadawcy, String nrOdbiorcy, BigDecimal kwota) {
        Konto nadawca = repository.findById(nrNadawcy)
                .orElseThrow(() -> new IllegalArgumentException("Nie odnaleziono konta nadawcy."));

        Konto odbiorca = repository.findById(nrOdbiorcy)
                .orElseThrow(() -> new IllegalArgumentException("Nie odnaleziono konta odbiorcy."));
        if (nadawca.getNrKonta().equals(odbiorca.getNrKonta())) {
            throw new IllegalArgumentException("Numer konta odbiorcy i nadawcy muszą się różnić");
        }


        if (kwota.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Kwota przelewu musi być większa od 0.");
        }


        if (nadawca.getStanKonta().compareTo(kwota) < 0) {
            throw new IllegalStateException("Brak wystarczającej kwoty na koncie");
        }
        nadawca.wyplac(kwota);
        odbiorca.wplac(kwota);

    }
}