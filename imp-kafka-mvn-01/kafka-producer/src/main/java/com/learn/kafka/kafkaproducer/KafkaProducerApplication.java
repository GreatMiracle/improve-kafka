package com.learn.kafka.kafkaproducer;

import com.learn.kafka.kafkacore.entity.FoodOrder;
import com.learn.kafka.kafkacore.entity.Image;
import com.learn.kafka.kafkacore.entity.SimpleNumber;
import com.learn.kafka.kafkaproducer.producer.FoodOrderProducer;
import com.learn.kafka.kafkaproducer.producer.HelloKafkaProducer;
import com.learn.kafka.kafkaproducer.producer.ImageProducer;
import com.learn.kafka.kafkaproducer.producer.SimpleNumberProducer;
import com.learn.kafka.kafkaproducer.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

//	private final HelloKafkaProducer helloKafkaProducer;
//	private final FoodOrderProducer foodOrderProducer;
//	private final SimpleNumberProducer simpleNumberProducer;
	private final ImageService imageService;
	private final ImageProducer imageProducer;

	@Override
	public void run(String... args) throws Exception {
//		helloKafkaProducer.sendHello("kien");

//		var chickenOrder = new FoodOrder(3, "Chicken");
//		var fishOrder = new FoodOrder(10, "Fish");
//		var pizzaOrder = new FoodOrder(5, "Pizza");

//		foodOrderProducer.send(chickenOrder);
//		foodOrderProducer.send(fishOrder);
//		foodOrderProducer.send(pizzaOrder);

//		for (int i = 100; i < 103; i++) {
//			var simpleNumber = new SimpleNumber(i);
//			simpleNumberProducer.send(simpleNumber);
//		}


		var img1 = imageService.generateImage("jpg");
		var img2 = imageService.generateImage("png");
		var img3 = imageService.generateImage("svg");
		var img4 = imageService.generateImage("gif");
		var img5 = imageService.generateImage("bmp");
		var img6 = imageService.generateImage("tiff");


		imageProducer.send(img1, 0);
		imageProducer.send(img2, 0);
		imageProducer.send(img3, 0);
		imageProducer.send(img4, 1);
		imageProducer.send(img5, 1);
		imageProducer.send(img6, 1);



	}
}
