package ch.hslu.mod.sw09;

public class Car {
    public String brand;
    private Extension extension;
    private String sign;
    private Motor motor;

    public Car(Motor motor) {
        this.motor = motor;
    }

    public Car(String brand, String sign) {
        this.brand = brand;
        this.sign = sign;
    }

    public Extension getExtension() {
        return this.extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public boolean start() {
        this.motor.start();
        return true;
    }

    @Override
    public String toString() {
        return "Car[Brand=" + this.brand + ", Kennzeichen = " + this.sign + "]";
    }
}
