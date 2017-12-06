package com.tangshengbo.design.observer.number;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TangShengBo on 2017-10-15.
 */
public class FrameObserver extends Frame implements Observer, ActionListener {

    private GraphText graphText = new GraphText(60);
    private GraphCanvas graphCanvas = new GraphCanvas();
    private Button buttonClose = new Button("Close");

    public FrameObserver() {
        super("FrameObserver");
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        graphText.setEditable(Boolean.FALSE);
        graphCanvas.setSize(500, 500);
        add(graphText, BorderLayout.NORTH);
        add(graphCanvas, BorderLayout.SOUTH);
        add(buttonClose, BorderLayout.CENTER);
        pack();
        show();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        System.exit(0);
    }

    @Override
    public void update(NumberGenerator numberGenerator) {
        graphText.update(numberGenerator);
        graphCanvas.update(numberGenerator);
    }
}

class GraphCanvas extends Canvas implements Observer {

    private int number;

    @Override
    public void update(NumberGenerator numberGenerator) {
        number = numberGenerator.getNumber();
        repaint();
    }

    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.white);
        g.fillArc(0, 0, width, height, 0, 360);
        g.setColor(Color.green);
        g.fillArc(0, 0, width, height, 90, -number * 360 / 50);
    }
}

class GraphText extends TextField implements Observer {

    public GraphText(int columns) throws HeadlessException {
        super(columns);
    }

    @Override
    public void update(NumberGenerator numberGenerator) {
        int number = numberGenerator.getNumber();
        String text = number + ":";
        for (int i = 0; i < number; i++) {
            text += '*';
        }
        setText(text);
    }
}