public class InputHandler {
    private final UserPlane user;
    private final Runnable fireAction;

    public InputHandler(UserPlane user, Runnable fireAction) {
        this.user = user;
        this.fireAction = fireAction;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case UP -> user.moveUp();
            case DOWN -> user.moveDown();
            case SPACE -> fireAction.run();
        }
    }

    public void handleKeyRelease(KeyEvent e) {
        if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
            user.stop();
        }
    }
} 