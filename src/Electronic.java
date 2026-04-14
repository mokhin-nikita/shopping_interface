public final class Electronic extends Product {

    public Electronic(String name, double prize) {
        super(name, prize);
    }

    @Override
    public String info() {
        return "Electronic";
    }
}