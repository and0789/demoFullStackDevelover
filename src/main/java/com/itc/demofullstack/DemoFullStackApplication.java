package com.itc.demofullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class DemoFullStackApplication {

    private static List<Costumer> costumers;

    static {
        costumers = new ArrayList<>();
        Costumer andre = new Costumer(
                1,
                "Andre Septian",
                "andre@gmail.com",
                21
        );
        costumers.add(andre);

        Costumer fatimah = new Costumer(
                2,
                "Fatimah Azzahra",
                "fatimah@gmail.com",
                20
        );
        costumers.add(fatimah);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoFullStackApplication.class, args);
    }

    /*
    same as @GetMapping
    @RequestMapping(
            path = "api/v1/costumers",
            method = RequestMethod.GET
            )
     */
    @GetMapping("api/v1/costumers")
    public List<Costumer> getCostumers() {
        return costumers;
    }

    @GetMapping("api/v1/costumers/{costumerId}")
    public Costumer getCostumers(@PathVariable("costumerId") Integer costumerId) {
        Costumer costumer = costumers.stream()
                .filter(c -> c.id.equals(costumerId))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("costumer with id [%s] not found".formatted(costumerId))
                );
        return costumer;
    }

    static class Costumer {
        private Integer id;
        private String name;
        private String email;
        private Integer age;

        public Costumer() {
        }

        public Costumer(Integer id, String name, String email, Integer age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Costumer costumer = (Costumer) o;
            return Objects.equals(id, costumer.id) && Objects.equals(name, costumer.name) && Objects.equals(email, costumer.email) && Objects.equals(age, costumer.age);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, email, age);
        }

        @Override
        public String toString() {
            return "Costumer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
