package ch.hslu.mod.sw09;

public class PersonCarTest {
    public static void main(String[] args) {
        Person person = new Person("Hans Muster", 23);
        System.out.println(person);

        System.out.println("\naddCar()'s:");
        Car auto1 = new Car("Marke1", "Kennzeichen1");
        System.out.println("pers.addCar(auto1) = " + person.addCar(auto1));
        Car auto2 = new Car("Marke2", "Kennzeichen2");
        System.out.println("pers.addCar(auto2) = " + person.addCar(auto2));
        System.out.println(person);

        System.out.println("\naddCar()'s:");
        Car auto3 = new Car("Marke3", "Kennzeichen3");
        System.out.println("pers.addCar(auto3) = " + person.addCar(auto3));
        Car auto4 = new Car("Marke4", "Kennzeichen4");
        System.out.println("pers.addCar(auto4) = " + person.addCar(auto4));
        Car auto5 = new Car("Marke5", "Kennzeichen5");
        System.out.println("pers.addCar(auto5) = " + person.addCar(auto5));
        Car auto6 = new Car("Marke6", "Kennzeichen6");
        System.out.println("pers.addCar(auto6) = " + person.addCar(auto6));
        System.out.println(person);


        System.out.println("\nRemove car()'s:");
        System.out.println("pers.removeCar(auto2) = " + person.removeCar(auto2));
        System.out.println("pers.removeCar(auto4) = " + person.removeCar(auto4));
        System.out.println("pers.removeCar(auto4) = " + person.removeCar(auto4));
        System.out.println(person);
    }
}

/* Session-Log:
Person{name=Hans Muster, age=23, cars=[]}

addCar()'s:
person.addCar(auto1) = true
person.addCar(auto2) = true
Person{name=Hans Muster, age=23, cars=[Car{type=Marke1, sign=Kennzeichen1}, Car{type=Marke2, sign=Kennzeichen2}]}

addCar()'s:
person.addCar(auto3) = true
person.addCar(auto4) = true
person.addCar(auto5) = true
person.addCar(auto6) = false
Person{name=Hans Muster, age=23, cars=[Car{type=Marke1, sign=Kennzeichen1}, Car{type=Marke2, sign=Kennzeichen2}, Car{type=Marke3, sign=Kennzeichen3}, Car{type=Marke4, sign=Kennzeichen4}, Car{type=Marke5, sign=Kennzeichen5}]}

Remove car()'s:
person.removeCar(auto2) = true
person.removeCar(auto4) = true
person.removeCar(auto4) = false
Person{name=Hans Muster, age=23, cars=[Car{type=Marke1, sign=Kennzeichen1}, Car{type=Marke3, sign=Kennzeichen3}, Car{type=Marke5, sign=Kennzeichen5}]}
*/
