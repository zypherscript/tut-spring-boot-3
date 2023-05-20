package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
class CustomerController {

  private final CustomerRepository customerRepository;

  @ResponseBody
  @GetMapping("/test/customers")
  Iterable<Customer> customers() {
    return customerRepository.findAll();
  }

//  @GetMapping("/test/vthread")
//  void testVthread() {
//    var start = Instant.now();
//
//    final AtomicInteger atomicInteger = new AtomicInteger();
//    Runnable runnable = () -> {
//      try {
//        Thread.sleep(Duration.ofSeconds(1));
//      } catch (Exception ignored) {
//      }
//      log.info("Work Done - " + atomicInteger.incrementAndGet());
//    };
//
//    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
//      for (int i = 0; i < 10_000; i++) {
//        executor.submit(runnable);
//      }
//    }
//
//    var finish = Instant.now();
//    long timeElapsed = Duration.between(start, finish).toMillis();
//    System.out.println("Total elapsed time : " + timeElapsed);
//  }

  @QueryMapping
    //graphql
  Collection<Customer> customersByName(@Argument String name) {
    return customerRepository.findByName(name);
  }

  @SneakyThrows
  @GetMapping("/test/{str}")
  ResponseEntity<Void> test(@PathVariable String str) {
    TimeUnit.SECONDS.sleep(2);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}