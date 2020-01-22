package net.bplaced.clayn.marmalade.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T>
 * @since 0.1
 */
public class ChangeableObject<T>
{

    protected transient final List<Consumer> listeners = new ArrayList<>();
    private transient final AtomicBoolean changesLocked = new AtomicBoolean(false);

    public void addListener(Consumer<? super T> listener)
    {
        listeners.add(listener);
    }

    public void removeListener(Consumer<? super T> listener)
    {
        listeners.remove(listener);
    }

    public final void lockChanges()
    {
        changesLocked.set(true);
    }

    public final void unlockChanges(boolean changed)
    {
        changesLocked.set(false);
        if (changed)
        {
            changed();
        }
    }

    public final void unlockChanges()
    {
        unlockChanges(false);
    }

    protected final void changed()
    {
        if (changesLocked.get())
        {
            return;
        }
        for (Consumer listener : listeners)
        {
            listener.accept(this);
        }
    }
}
