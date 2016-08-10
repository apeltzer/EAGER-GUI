package IO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReferenceDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton referenceButton;
    private JTextField mtcapture_jtextfield;
    private JTextPane pleaseInputTheNameTextPane;
    private Communicator communicator;
    private JButton runButton;

    public ReferenceDialog(final Communicator communicator, JButton runButton) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        mtcapture_jtextfield.setText(communicator.getFilter_for_mt());
        this.communicator = communicator;
        this.runButton = runButton;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        referenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ReferenceFastAChooser rffqc = new ReferenceFastAChooser(communicator);
                referenceButton.setForeground(Color.green);

                if (checkIfInputWasSelected()) {
                    runButton.setEnabled(true);
                    buttonOK.setEnabled(true);
                } else {
                    runButton.setEnabled(false);
                    buttonCancel.setEnabled(false);
                }
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here
        this.communicator.setFilter_for_mt(this.mtcapture_jtextfield.getText());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private boolean checkIfInputWasSelected() {
        boolean tmp = false;
        if (communicator.getGUI_inputfiles() != null && communicator.getGUI_reference() != null && communicator.getGUI_resultspath() != null) {
            if(communicator.getGUI_inputfiles().size() != 0){
                tmp = true;
            }
        }
        return tmp;
    }
}
