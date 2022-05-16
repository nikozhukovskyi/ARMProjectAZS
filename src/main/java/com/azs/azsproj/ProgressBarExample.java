package com.azs.azsproj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.Objects;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class ProgressBarExample extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private JProgressBar progBar;
    private static JButton startBtn;
    //private JPr;

    public void initializeUI(double Liters) {

        progBar = new JProgressBar();
        progBar.setStringPainted(true);


        startBtn = new JButton("Start");
        startBtn.addActionListener(new ClickAction());

        JButton stopBtn = new JButton("Cancel");
        stopBtn.addActionListener(e -> dispose());

        // stopBtn.addActionListener(clickAction);


        double T = 0.85;
        double Sum = Liters * T;
        int ds = (int) Sum;

        timer = new Timer(ds, new UpdateBarListener());

        createLayout(progBar, startBtn, stopBtn);
        setSize(350, 150);
        setTitle("Progress");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createLayout(JComponent... arg) {

        JPanel pane = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]).addComponent(arg[1]).addComponent(arg[2]));

        gl.setVerticalGroup(gl.createParallelGroup(CENTER).addComponent(arg[0]).addComponent(arg[1]).addComponent(arg[2]));

        pack();
    }

    private class UpdateBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int val = progBar.getValue();

            int MAX_VAL = 100;
            if (val >= MAX_VAL) {
                timer.stop();
                startBtn.setText("End");
                return;
            } else if (Objects.equals(startBtn.getText(), "End")) {
                System.out.println("END!!");
            }

            progBar.setValue(++val);
        }


    }

    private class ClickAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (timer.isRunning()) {
                timer.stop();
                startBtn.setText("Start");
                //stopBtn.setText("Cancel");
            } else if (!"End".equals(startBtn.getText())) {
                timer.start();
                startBtn.setText("Stop");
            } else if (!"Stop".equals(startBtn.getText())) {
                System.out.println("ssas");
                dispose();
            } else if (!"End".equals(startBtn.getText())) {
                timer.stop();
                System.out.println("cancel");
            }
        }

    }


    public static void main(double Liters) {

        EventQueue.invokeLater(() -> {
            ProgressBarExample progressBarExample = new ProgressBarExample();
            //double Liters=0;
            progressBarExample.initializeUI(Liters);
            progressBarExample.setVisible(true);

        });
    }
}