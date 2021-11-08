
### Logging Guide

`java.util.logging` is used for logging in this application. The `LoggerUtil` class inside the `logger` package is used to set up loggers inside each class.

#### Steps for using and setting up Logger:
1. For each class that requires a logger, a `static final` `Logger` needs to be created using the `Logger.getLogger(String name)` method.
   * the `name` of the Logger should be set to the name of the class. (you could use `{Class}.class.getName()` to get the exact name of the class).
2. In the constructor of the class, call `setupLogger(Logger)` to attach the `FileHandler` into the created logger. The `FileHandler` is set at `Level.FINE`.
3. Resulting log will be written in the `programLog.Log` file inside the log folder.