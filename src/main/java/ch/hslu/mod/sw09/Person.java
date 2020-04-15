package ch.hslu.mod.sw09;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name = "";
    private int age;
    private List<Car> cars;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.cars = new ArrayList<Car>();
    }

    public String getName() {
        return this.name;
    }

    public String addCar(Car car) {
        this.cars.add(car);
        return car.toString();
    }

    public String removeCar(Car car) {
        this.cars.remove(car);
        return car.toString();
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Person[Name = %s, Cars = %s]", this.name, this.cars.size());
    }
}
