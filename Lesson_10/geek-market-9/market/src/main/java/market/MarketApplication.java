package market;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients
@EntityScan({"contract.entities", "market.entities"})

public class MarketApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MarketApplication.class, args);

	}
}