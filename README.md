# lab1soft

**Student ID:** 112 (K = 2)

## Files
- `BulbAdapter.java` — wraps `LegacyBulb`, converts 0–255 raw brightness to a
  0–100 power percent using `floor(raw*100/255) + K`, capped at 100. Reports
  off/0% if `hasPower()` is false (severed filament).
- `ThermostatAdapter.java` — wraps `LegacyThermostat`, translates dial states
  (`IDLE`/`LOW`/`MEDIUM`/`MAX`) to on/off and 0/33/66/100%. Any unrecognized
  or null dial state returns `false` / `-1` instead of throwing.
- `Main.java` — wires both adapters into `ModernHub`, runs `activateAll`,
  `calculateAveragePowerUsage`, `emergencyShutdown`, and a fault-injection
  audit (broken filament, `"STUCK"` dial).

## Build & Run
Place these three files alongside the provided `SmartDevice.java`,
`LegacyBulb.java`, `LegacyThermostat.java`, `ModernHub.java`, then:

```
mkdir -p bin
javac -d bin *.java
java -cp bin Main
```
