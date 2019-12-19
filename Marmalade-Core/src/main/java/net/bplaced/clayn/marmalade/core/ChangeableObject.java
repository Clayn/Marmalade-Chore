package net.bplaced.clayn.marmalade.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T>
 * @since 0.1
 */
public class ChangeableObject<T>
{

    private final List<Consumer> listeners = new ArrayList<>();

    public void addListener(Consumer<? super T> listener)
    {
        listeners.add(listener);
    }

    public void removeListener(Consumer<? super T> listener)
    {
        listeners.remove(listener);
    }

    public final void changed()
    {
        for (Consumer listener : listeners)
        {
            listener.accept(this);
        }
    }
}
