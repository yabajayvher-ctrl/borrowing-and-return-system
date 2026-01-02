package javaapplication15;
import javax.swing.JFrame;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class fullscreen extends  JFrame implements KeyListener {
    
    public fullscreen() {
        // Must be set before making the frame visible or displayable
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this); // Add a key listener to handle exiting

        // Get the local graphics environment and default screen device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        // Check if full-screen exclusive mode is supported
        if (gd.isFullScreenSupported()) {
            // Enter full-screen exclusive mode
            gd.setFullScreenWindow(this);
        } else {
            // Fallback for systems that don't support exclusive mode
            System.err.println("Full-screen exclusive mode not supported. Maximizing instead.");
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
        }
    }

    // Method to exit full-screen on 'ESC' key press
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            gd.setFullScreenWindow(null); // Exit full-screen mode
            dispose(); // Close the window
            System.exit(0);
        }
    }

    // Other required KeyListener methods (not used here)
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        // Ensure Swing components are created and updated on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new fullscreen();
            }
        });
    }
}
