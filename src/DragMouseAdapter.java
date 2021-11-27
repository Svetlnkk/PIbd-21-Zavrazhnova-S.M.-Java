import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class DragMouseAdapter extends MouseAdapter {
    public void mousePressed(MouseEvent event) {
        var c = (JComponent) event.getSource();
        var handler = c.getTransferHandler();
        handler.exportAsDrag(c, event, TransferHandler.COPY);
    }
}
