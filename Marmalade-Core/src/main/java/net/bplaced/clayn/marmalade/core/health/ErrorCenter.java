/*
 * The MIT License
 *
 * Copyright 2019 Clayn <clayn_osmato@gmx.de>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.bplaced.clayn.marmalade.core.health;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.bplaced.clayn.marmalade.core.util.Clearable;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class ErrorCenter implements Clearable
{

    private final Map<Long, Throwable> errors = new HashMap<>();
    private final List<ErrorListener> listeners=new ArrayList<>();
    ErrorCenter()
    {
    }
    
    public void addListener(ErrorListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(ErrorListener listener) {
        listeners.remove(listener);
    }
    
    public void report(Throwable t,Function<? super Throwable,? extends RuntimeException> thrower) {
        report(t);
        if(thrower!=null) {
            throw thrower.apply(t);
        }
    }
    
    public void report(Throwable t) {
        errors.put(System.currentTimeMillis(), t);
        listeners.forEach((l)->l.onError(t));
    }

    public Map<Long,Throwable> getReportedErrors() {
        return Collections.unmodifiableMap(errors);
    }

    @Override
    public void clear()
    {
        errors.clear();
    }
}
