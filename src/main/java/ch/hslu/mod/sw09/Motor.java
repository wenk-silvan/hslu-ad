package ch.hslu.mod.sw09;

public class Motor {
    private int motorNumber;
    private Distributor distributor;
    private FuelPump fuelPump;
    private int speed;
    private Car car;

    public Motor() {
        this.fuelPump = new FuelPump();
        this.distributor = new Distributor();
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setNumber(int number) {
        motorNumber = number;
    }

    public void start() {}
}
