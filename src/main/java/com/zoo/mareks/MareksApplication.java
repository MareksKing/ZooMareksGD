package com.zoo.mareks;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import com.zoo.mareks.models.Animal;
// import com.zoo.mareks.models.AnimalFoodType;
// import com.zoo.mareks.models.Enclosure;
// import com.zoo.mareks.models.Ticket;
// import com.zoo.mareks.models.TicketType;
// import com.zoo.mareks.models.Visitor;
// import com.zoo.mareks.models.Zoo;
// import com.zoo.mareks.repo.IAnimalRepo;
// import com.zoo.mareks.repo.IEnclosureRepo;
// import com.zoo.mareks.repo.ITicketRepo;
// import com.zoo.mareks.repo.IVisitorRepo;
// import com.zoo.mareks.repo.IZooRepo;

@SpringBootApplication
public class MareksApplication {

	public static void main(String[] args) {
		SpringApplication.run(MareksApplication.class, args);
	}

	// @Bean //visur kur si annotacija, tiks izsaukts automatiski
	// public CommandLineRunner cmdLineRunner(IAnimalRepo animalRepo, IEnclosureRepo enclosureRepo, ITicketRepo ticketRepo, IVisitorRepo visitorRepo, IZooRepo zooRepo) {
		
	// 	return new CommandLineRunner() {
			
	// 		@Override
	// 		public void run(String... args) throws Exception {
				
	// 			Zoo zoo = new Zoo("Jauns zoo darzs", "Riga, Latvia", "29154130");
	// 			Enclosure enclosure = new Enclosure(zoo);
	// 			Animal lauva = new Animal("Lauva", AnimalFood.Gala, enclosure);
	// 			// zooRepo.save(zoo);
	// 			// enclosureRepo.save(enclosure);
	// 			// animalRepo.save(lauva);

	// 			zoo.addEnclosure(enclosure);
	// 			enclosure.addAnimal(lauva);
	// 			zoo.addAnimal(lauva);
	// 			lauva.addZoo(zoo);
	// 			lauva.addEnclosure(enclosure);

	// 			// animalRepo.deleteAll();
	// 			// enclosureRepo.deleteAll();
	// 			// zooRepo.deleteAll();
				
	// 			Visitor visitor = new Visitor("Mareks", "Robalds");
	// 			Ticket ticket = new Ticket(TicketType.ADULT);
	// 			visitor.addTicket(ticket);
	// 			ticket.addVisitor(visitor);
				
				
	// 			zooRepo.save(zoo);
	// 			enclosureRepo.save(enclosure);
	// 			animalRepo.save(lauva);
	// 			visitorRepo.save(visitor);
	// 			ticketRepo.save(ticket);
				
		// 	}
		// };	
	// }
}
