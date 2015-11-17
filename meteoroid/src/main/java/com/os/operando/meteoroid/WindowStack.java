package com.os.operando.meteoroid;

import android.support.annotation.Nullable;
import android.view.Window;

import java.lang.ref.WeakReference;
import java.util.Stack;

public class WindowStack {

    private static Stack<WeakReference<Window>> stack = new Stack<>();

    public static void push(Window window) {
        stack.push(new WeakReference<>(window));
    }

    public static void pop() {
        stack.pop();
    }

    @Nullable
    public static Window peek() {
        return stack.peek().get();
    }

    public static void clear() {
        stack.clear();
    }
}
