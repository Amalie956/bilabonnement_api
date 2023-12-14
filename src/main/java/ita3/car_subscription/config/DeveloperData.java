package ita3.car_subscription.config;

import ita3.car_subscription.entity.Car;
import ita3.car_subscription.entity.Customer;
import ita3.car_subscription.entity.DamageReport;
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

    public DeveloperData(ISubscriptionRepository subscriptionRepository, ICarRepository carRepository, ICustomerRepository customerRepository, IDamageReportRepository damageReportRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.damageReportRepository = damageReportRepository;
    }

    @Override
       public void run(ApplicationArguments args) throws Exception {
        //Opretter objekter til databasen
        //Bil objekter
        Car car1 = new Car("VW Golf VI", "Manuel","Benzin", 18.21,true,98172,150000);
        Car car2 = new Car("Seat Ibiza", "Manuel","Benzin", 21.87,false,01210161,135000);
        Car car3 = new Car("Tesla", "Automatgear","El", 32.91,true,8271922,349000);
        Car car4 = new Car("BMW ID3", "Automatgear","Hybrid", 27.21,false,7281226,397000);
        Car car5 = new Car("BMW ID4", "Automatgear","Hybrid", 28.28,false,7725173,450000);
        Car car6 = new Car("Porche Tayan", "Automatgear","Diesel", 18.87,true,8263662,220000);
        Car car7 = new Car("Range Rover", "Automatgear","Benzin", 17.11,true,9826512,250000);
        Car car8 = new Car("Seat Cupra", "Manuel","Benzin", 16.87,false,1127252,175000);
        Car car9 = new Car("Kia", "Manuel","Benzin", 13,true,1127252,175000);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
        carRepository.save(car7);
        carRepository.save(car8);
        carRepository.save(car9);

        //Kunde objekter
        Customer customer1 = new Customer("Lars", "Andersen","290107851", 818231717);
        Customer customer2 = new Customer("Hanne", "Munk","240780771", 981737113);
        Customer customer3 = new Customer("Thomas", "Jensen","3110944562", 71663616);
        Customer customer4 = new Customer("Henrik", "Hansen","2711991771", 13616371);
        Customer customer5 = new Customer("Mikkel", "Madsen","0707028712", 73717316);
        Customer customer6 = new Customer("Per", "Larsen","12120121", 63616383);
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);
        customerRepository.save(customer6);

        //Datoer for abonnement
        Subscription subscription1 = new Subscription(LocalDate.of(2022, 12, 25), LocalDate.of(2023, 3, 25),36.000,customer1,car1);
        Subscription subscription2 = new Subscription(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 11, 6),18.000,customer2,car2);
        subscriptionRepository.save(subscription1);
        subscriptionRepository.save(subscription2);

        //Skaderapport objekt
        DamageReport damageReport1 = new DamageReport("Dæk fladt", "Dæk", 1, 500, subscription1);
        damageReportRepository.save(damageReport1);
    }

}
