public final class Electronic extends Product implements Switchable {

    private boolean isOn = false;
    public Electronic(String name, double prize) {
        super(name, prize);
    }

    @Override
    public String info() {
        return "Electronic";
    }

    @Override
    public void turn_on() {
        isOn = true;
    }

    @Override
    public void turn_off() {
        isOn = false;
    }
}