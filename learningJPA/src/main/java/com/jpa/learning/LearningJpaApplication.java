package com.jpa.learning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jpa.learning.dao.UserRepository;
import com.jpa.learning.entities.User;

@SpringBootApplication
public class LearningJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LearningJpaApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		
		User user = new User();
		user.setName("Ahmad");
		user.setCity("Kabul");
		user.setStatus("Java Developer");
		
//		Single user
//		User user1 = userRepository.save(user);
		
		User user1 = new User();
		user1.setName("Ahmad");
		user1.setCity("Kabul");
		user1.setStatus("Java Developer");
		
		ArrayList<User> users = new ArrayList<User>();
		// List<User> users = List.of(user,user1); //for Higher versions of java
		users.add(user);
		users.add(user1);
		
		Iterable<User> result = userRepository.saveAll(users);
		
		result.forEach(userdata -> {
			System.out.println(userdata);
		});
		
		//Update And Get 
		Optional<User> optional = userRepository.findById(5);
		User userToUpdate = optional.get();
		
		userToUpdate.setName("Not Ahmad Abid");
		
		User userUpdated = userRepository.save(userToUpdate);
		
		System.out.println(userUpdated);
		
		//Getting All
		Iterable<User> itr = userRepository.findAll();
		Iterator<User> iterator = itr.iterator();
		
		while(iterator.hasNext())
		{
			User userGot = iterator.next();
			System.out.println(userGot);
		}
		
		// Or you got iterate with new way
//		itr.forEach(new Consumer<User>() {
//			public void accept(User t) {
//				System.out.println(t);
//			}
//		});
//		itr.forEach(userGt -> {System.out.println(userGt);});
		
		//Delete
		userRepository.deleteById(1);
		
		//Derived Query methods / Custom Method in spring data JPA
		//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject
		
		List<User> userAhmad = userRepository.findByNameAndStatus("Ahmad", "Java Developer");
		userAhmad.forEach(e -> {System.out.println(e);});
		
		//findByNameStartingWith(String prefix)
		//findByNameEndingWith(String suffix)
		//findByNameContaining(String Words)
		
		//findByNameLike(String likePattern)
		
		//findByAgeLessThan(int age)
		//findByAgeGreaterThanEqual(int age)
		//findByAgeIn(Collection<Integer> ages)
		//findByNameOrderByName(String name)
		
		 List<User> userJPQL = userRepository.getUserByName("Ahmad");
		 userJPQL.forEach(e -> {System.out.println(e);});
		 System.out.println("---------------------------------------------------------------------");
		 
		 List<User> userSQL = userRepository.getUsers();
		 userSQL.forEach(e -> {System.out.println(e);});
		 
	}

}
