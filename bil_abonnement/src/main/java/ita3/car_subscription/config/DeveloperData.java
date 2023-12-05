package ita3.car_subscription.config;

import ita3.car_subscription.entity.Car;
import ita3.car_subscription.entity.Customer;
import ita3.car_subscription.entity.Subscription;
import ita3.car_subscription.repository.ICarRepository;
import ita3.car_subscription.repository.ICustomerRepository;
import ita3.car_subscription.repository.IDamageReportRepository;
import ita3.car_subscription.repository.ISubscriptionRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DeveloperData implements ApplicationRunner {
    private ISubscriptionRepository subscriptionRepository;
    private ICarRepository carRepository;
    private ICustomerRepository customerRepository;
    private IDamageReportRepository damageReportRepository;

    //private static final List<Subscription> SUBSCRIPTIONS = Arrays.asList(
            //new Subscription());


    public DeveloperData(ISubscriptionRepository subscriptionRepository, ICarRepository carRepository, ICustomerRepository customerRepository, IDamageReportRepository damageReportRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.damageReportRepository = damageReportRepository;
    }

    @Override
    //@Transactional
    public void run(ApplicationArguments args) throws Exception {
        Car car1 = new Car("VW Golf VI", "Manuel","Benzin", 18.21,true,98172,150000);
        Car car2 = new Car("Seat Ibiza", "Automatgear","Benzin", 21.87,false,01210161,135000);
        carRepository.save(car1);
        carRepository.save(car2);

        Customer customer1 = new Customer("Lars", "Andersen","290107851", 818231717);
        Customer customer2 = new Customer("Hanne", "Munk","240780771", 981737113);
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Subscription subscription1 = new Subscription(LocalDate.of(2022, 12, 25), LocalDate.of(2023, 3, 25),36.000,customer1,car1);
        Subscription subscription2 = new Subscription(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 11, 6),18.000,customer2,car2);
        subscriptionRepository.save(subscription1);
        subscriptionRepository.save(subscription2);
    }

}
