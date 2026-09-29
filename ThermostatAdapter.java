public class ThermostatAdapter implements SmartDevice {

    private final LegacyThermostat thermostat;

    public ThermostatAdapter(LegacyThermostat thermostat) {
        if (thermostat == null) {
            throw new IllegalArgumentException("LegacyThermostat instance cannot be null.");
        }
        this.thermostat = thermostat;
    }

    @Override
    public void turnOn() {
        String currentState = getNormalizedDial();

        if ("IDLE".equals(currentState)) {
            thermostat.rotateDial("LOW");
        }
    }

    @Override
    public void turnOff() {
        thermostat.rotateDial("IDLE");
    }

    @Override
    public boolean isOn() {
        String state = getNormalizedDial();

        if (state == null) {
            return false;
        }

        switch (state) {
            case "LOW":
            case "MEDIUM":
            case "MAX":
                return true;
            case "IDLE":
            default:
                return false;
        }
    }

    @Override
    public int getPowerPercent() {
        String state = getNormalizedDial();

        if (state == null) {
            return -1;
        }

        switch (state) {
            case "IDLE":
                return 0;
            case "LOW":
                return 33;
            case "MEDIUM":
                return 66;
            case "MAX":
                return 100;
            default:
                return -1;
        }
    }

    private String getNormalizedDial() {
        String dial = thermostat.checkDial();

        if (dial == null) {
            return null;
        }

        return dial.trim().toUpperCase();
    }
}
