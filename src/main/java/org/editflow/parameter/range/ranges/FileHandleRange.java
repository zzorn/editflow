package org.editflow.parameter.range.ranges;

import com.badlogic.gdx.files.FileHandle;
import org.demoflow.parameter.range.RangeBase;
import org.flowutils.random.RandomSequence;

/**
 * Range for LibGdx internal file handles.
 *
 */
public final class FileHandleRange extends RangeBase<FileHandle> {

    public static final FileHandleRange FULL = new FileHandleRange();

    public FileHandleRange() {
        super(FileHandle.class);
    }

    @Override protected FileHandle createRandomValue(RandomSequence randomSequence) {
        // No random values supported
        return null;
    }

    @Override
    protected FileHandle doMutateValue(FileHandle value, float mutationAmount, RandomSequence randomSequence) {
        // Mutation not supported
        return value;
    }

    @Override protected FileHandle createCopy(FileHandle source) {
        // The file handles are immutable.
        return source;
    }

    @Override protected FileHandle createDefaultValue() {
        // No handle by default
        return null;
    }

    @Override public FileHandle clampToRange(FileHandle originalValue) {
        // No filtering currently
        return originalValue;
    }


    @Override public FileHandle interpolate(double t, FileHandle a, FileHandle b, FileHandle out) {
        return t < 0.5 ? a : b;
    }
}
