package org.pluto.main;

import java.util.ArrayList;
import java.util.List;

public abstract class WinMain {
    private static final List<WinMain> mains = new ArrayList<>();
    private static Throwable err;

    public static void registerEntrypoint(WinMain main) {
        mains.add(main);
    }

    private Window window;

    public abstract void wmain(String[] args, Window window) throws Throwable;

    public static void main(String[] args) throws Throwable {
        mains.stream()
                .peek(m -> m.window = new Window())
                .forEach(wm -> {
                    try {
                        wm.wmain(args, wm.window);
                    } catch (Throwable e) {
                        err = e;
                    }
                });
        if (err != null) throw err;
    }
}
