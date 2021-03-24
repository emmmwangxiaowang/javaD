package javaD.CommandTool;

/**
 * 自定义异常
 */
public class exception {
    /**
     * 指令不存在
     */
    static class CommandNotExitException extends Exception {
        public CommandNotExitException(String message) {
            super(message);
        }

    }

    static class CommandArgumentException extends Exception {
        public CommandArgumentException(String message) {
            super(message);
        }


    }
}
