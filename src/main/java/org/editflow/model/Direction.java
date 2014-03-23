package org.editflow.model;

/**
 *
 */
public enum Direction {
    WRITE_ONLY(false, true),
    READ_ONLY(true, false),
    BOTH(true, true);

    private final boolean readable;
    private final boolean writable;

    private Direction(boolean readable, boolean writable) {
        this.readable = readable;
        this.writable = writable;
    }

    public boolean isReadable() {
        return readable;
    }

    public boolean isWritable() {
        return writable;
    }

    /**
     * @param direction true if source, false if target
     * @return true if the specified direction is supported, false if not.
     */
    public boolean supportsDirection(boolean direction) {
        if (direction) return readable;
        else return writable;
    }
}
