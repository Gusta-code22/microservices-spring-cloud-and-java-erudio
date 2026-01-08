package github.io.Gusta_code22.repository;

import github.io.Gusta_code22.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    Exchange findByFromAndTo(String from, String to);
}
