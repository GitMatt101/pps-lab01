package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private static final int PIN = 1;
    private static final int WRONG_PIN = 2;
    private static final int MAX_UNLOCK_ATTEMPTS = 3;
    private SmartDoorLock lock;

    @BeforeEach
    public void init() {
        this.lock = new SmartDoorLockImpl(PIN, MAX_UNLOCK_ATTEMPTS);
    }

    @Test
    public void testInitiallyLocked() {
        assertTrue(this.lock.isLocked());
    }

    @Test
    public void testInitiallyNotBlocked() {
        assertFalse(this.lock.isBlocked());
    }

    @Test
    public void testLock() {
        this.lock.lock();
        assertTrue(this.lock.isLocked());
    }

    @Test
    public void testLockAfterUnlock() {
        this.lock.unlock(PIN);
        this.lock.lock();
        assertTrue(this.lock.isLocked());
    }

    @Test
    public void testUnlock() {
        this.lock.unlock(PIN);
        assertFalse(this.lock.isLocked());
    }

    @Test
    public void testUnlockWithWrongPin() {
        this.lock.unlock(WRONG_PIN);
        assertTrue(this.lock.isLocked());
    }

    @Test
    public void testUnlockWithNewPin() {
        final int newPin = 3;
        this.lock.setPin(newPin);
        this.lock.unlock(newPin);
        assertFalse(this.lock.isLocked());
    }

    @Test
    public void testUnlockWithOldPin() {
        final int newPin = 3;
        this.lock.setPin(newPin);
        this.lock.unlock(PIN);
        assertTrue(this.lock.isLocked());
    }

    @Test
    public void testMaxAttempts() {
        assertEquals(MAX_UNLOCK_ATTEMPTS, this.lock.getMaxAttempts());
    }

    @Test
    public void testFailedAttemptsAfterFailedUnlock() {
        this.lock.unlock(WRONG_PIN);
        assertEquals(1, this.lock.getFailedAttempts());
    }

    @Test
    public void testFailedAttemptsAfterSuccessfulUnlock() {
        this.lock.unlock(PIN);
        assertEquals(0, this.lock.getFailedAttempts());
    }

    @Test
    public void testFailedUnlockWhileAlreadyUnlocked() {
        this.lock.unlock(PIN);
        this.lock.unlock(WRONG_PIN);
        assertFalse(this.lock.isLocked());
        assertEquals(0, this.lock.getFailedAttempts());
    }

    @Test
    public void testBlock() {
        this.block();
        assertTrue(this.lock.isBlocked());
        assertTrue(this.lock.isLocked());
    }

    private void block() {
        for (int i = 0; i < MAX_UNLOCK_ATTEMPTS; i++)
            this.lock.unlock(WRONG_PIN);
    }

    @Test
    public void testUnlockWhileBlocked() {
        this.block();
        this.lock.unlock(PIN);
        assertTrue(this.lock.isBlocked());
        assertTrue(this.lock.isLocked());
    }

    @Test
    public void testReset() {
        this.block();
        this.lock.reset();
        assertTrue(this.lock.isLocked());
        assertEquals(0, this.lock.getFailedAttempts());
        assertFalse(this.lock.isBlocked());
    }
}
