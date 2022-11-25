package vistas;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public interface IVista {
    void setActionListener(ActionListener actionListener);

    void setWindowListener(WindowListener windowListener);
}
