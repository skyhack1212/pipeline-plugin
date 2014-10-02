/*
 * The MIT License
 *
 * Copyright (c) 2013-2014, CloudBees, Inc.
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

package org.jenkinsci.plugins.workflow.actions;

import org.jenkinsci.plugins.workflow.graph.AtomNode;
import hudson.model.Action;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Attached to {@link AtomNode} that caused an error.
 *
 * This has to be Action because it's added after a node is created.
 *
 * @author Kohsuke Kawaguchi
 */
public class ErrorAction implements Action {
    private final Throwable error;

    /** For convenience, unwraps {@link UndeclaredThrowableException} automatically. */
    public ErrorAction(Throwable error) {
        if (error instanceof UndeclaredThrowableException) {
            error = ((UndeclaredThrowableException) error).getCause();
        }
        assert error!=null;
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }

    public String getIconFileName() {
        return null;
    }

    public String getDisplayName() {
        return error.getMessage();
    }

    public String getUrlName() {
        return null;
    }
}
