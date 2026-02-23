package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private boolean locked = true;
    private int pin;
    private final int maxAttempts;
    private int failedAttempts = 0;

    public SmartDoorLockImpl(final int pin, final int maxAttempts) {
        this.pin = pin;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (!this.locked || this.isBlocked())
            return;
        if (this.checkPin(pin)) {
            this.locked = false;
            this.failedAttempts = 0;
        } else
            this.failedAttempts++;
    }

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isBlocked() {
        return this.failedAttempts >= this.maxAttempts;
    }

    @Override
    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {
        this.failedAttempts = 0;
        this.locked = true;
    }

    private boolean checkPin(int pin) {
        return this.pin == pin;
    }
}
