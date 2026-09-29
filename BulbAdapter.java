public class BulbAdapter implements SmartDevice {

    private static final int K = 2;
    private final LegacyBulb bulb;

    public BulbAdapter(LegacyBulb bulb) {
        if (bulb == null) {
            throw new IllegalArgumentException("LegacyBulb instance cannot be null.");
        }
        this.bulb = bulb;
    }

    @Override
    public void turnOn() {
        bulb.setBrightness(255);
    }

    @Override
    public void turnOff() {
        bulb.setBrightness(0);
    }

    @Override
    public boolean isOn() {
        return bulb.hasPower() && bulb.readBrightness() > 0;
    }

    @Override
    public int getPowerPercent() {
        if (!bulb.hasPower()) {
            return 0;
        }

        int rawBrightness = bulb.readBrightness();

        if (rawBrightness == 0) {
            return 0;
        }

        int basePercent = (int) Math.floor((rawBrightness * 100.0) / 255.0);
        return Math.min(basePercent + K, 100);
    }
}
