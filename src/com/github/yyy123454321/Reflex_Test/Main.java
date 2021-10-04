package com.github.yyy123454321.Reflex_Test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Main {
    static Toolkit toolkit;
    public static void main(String[] args) throws LineUnavailableException {
        //ReflexSoundWindow rsw = new ReflexSoundWindow();
        //ReflexGraphicsWindow rgw = new ReflexGraphicsWindow();
        ReflexWindow rw = new ReflexWindow();
        toolkit = Toolkit.getDefaultToolkit();
    }
}

class ReflexSoundWindow extends JFrame implements Runnable, KeyListener {
    int width = 500, height = 500;
    public static AudioInputStream ra, la;
    long startTime; int clickCount; boolean isRight = false, isPlaying = false;
    public ReflexSoundWindow() throws LineUnavailableException {
        try {
            ra = AudioSystem.getAudioInputStream(new File("C:/reflexTest/r.wav"));
            la = AudioSystem.getAudioInputStream(new File("C:/reflexTest/l.wav"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        addKeyListener(this);
        Thread th = new Thread(this);
        setTitle("Reflex Sound Test");
        setSize(width, height);
        setVisible(true);
        th.start();
    }
    public void paint(Graphics g) {}
    @Override
    public void run() {}
    public void testStart() {
        System.out.println("Start!");
        startTime = System.currentTimeMillis();
        clickCount = -1;
        isPlaying = true;
        next();
    }
    public void pressedLeft() {
        if(!isPlaying) { System.out.println("Press Up-arrow when you want start test"); return; }
        if(isRight) { System.out.println("You press wrong key! Stopping game..."); printEnd(); return; }
        next();
    }
    public void pressedRight() {
        if(!isPlaying) { System.out.println("Press Up-arrow when you want start test"); return; }
        if(!isRight) { System.out.println("You press wrong key! Stopping game..."); printEnd(); return; }
        next();
    }
    public void next() {
        clickCount++;
        isRight = (Math.random() > 0.5);
        try {
            Clip tC = AudioSystem.getClip();
            if(isRight) { tC.open(AudioSystem.getAudioInputStream(new File("C:/reflexTest/r.wav"))); }
            else { tC.open(AudioSystem.getAudioInputStream(new File("C:/reflexTest/l.wav"))); }
            System.out.println(isRight);
            tC.start();
            //Thread.sleep(200);
            //tC.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printEnd() {
        isPlaying = false;
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.println("Time: " + endTime + "ms");
        System.out.println("Count: " + clickCount);
        System.out.println("Time/Count: " + (1.0*endTime/clickCount));
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP: testStart(); break;
            case KeyEvent.VK_LEFT: pressedLeft(); break;
            case KeyEvent.VK_RIGHT: pressedRight(); break;
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {}
    @Override
    public void keyTyped(KeyEvent arg0) {}
}

class ReflexGraphicsWindow extends JFrame implements Runnable, KeyListener {
    int width = 1000, height = 500;
    long startTime; int clickCount; boolean isRight = false, isPlaying = false;
    public ReflexGraphicsWindow() throws LineUnavailableException {
        addKeyListener(this);
        Thread th = new Thread(this);
        setTitle("Reflex Graphics Test");
        setSize(width, height);
        setVisible(true);
        th.start();
    }
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        if(isPlaying) g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        if(!isPlaying) return;
        g.setColor(Color.RED);
        g.fillRect(width/2-5, height/2-5, 10, 10);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        g.setColor(Color.white);
        g.fillRect(width/8 + (isRight ? width/2 : 0), height/4, width/4, height/2);

    }
    @Override
    public void run() {}
    public void testStart() {
        System.out.println("Start!");
        startTime = System.currentTimeMillis();
        clickCount = -1;
        isPlaying = true;
        next();
    }
    public void pressedLeft() {
        if(!isPlaying) { System.out.println("Press Up-arrow when you want start test"); return; }
        if(isRight) { System.out.println("You press wrong key! Stopping game..."); printEnd(); return; }
        next();
    }
    public void pressedRight() {
        if(!isPlaying) { System.out.println("Press Up-arrow when you want start test"); return; }
        if(!isRight) { System.out.println("You press wrong key! Stopping game..."); printEnd(); return; }
        next();
    }
    public void next() {
        clickCount++;
        isRight = (Math.random() > 0.5);
        repaint();
    }
    public void printEnd() {
        isPlaying = false;
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.println("Time: " + endTime + "ms");
        System.out.println("Count: " + clickCount);
        System.out.println("Time/Count: " + (1.0*endTime/clickCount));
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP: testStart(); break;
            case KeyEvent.VK_LEFT: pressedLeft(); break;
            case KeyEvent.VK_RIGHT: pressedRight(); break;
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {}
    @Override
    public void keyTyped(KeyEvent arg0) {}
}


class ReflexWindow extends JFrame implements Runnable, KeyListener {
    int width = 1000, height = 500; AudioInputStream ra, la;
    long startTime; int clickCount; boolean isRight = false, isPlaying = false;
    public ReflexWindow() throws LineUnavailableException {
        try {
            ra = AudioSystem.getAudioInputStream(new File("C:/reflexTest/r.wav"));
            la = AudioSystem.getAudioInputStream(new File("C:/reflexTest/l.wav"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        addKeyListener(this);
        Thread th = new Thread(this);
        setTitle("Reflex Test");
        setSize(width, height);
        setVisible(true);
        th.start();
    }
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        if(isPlaying) g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        if(!isPlaying) return;
        g.setColor(Color.RED);
        g.fillRect(width/2-5, height/2-5, 10, 10);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        g.setColor(Color.white);
        g.fillRect(width/8 + (isRight ? width/2 : 0), height/4, width/4, height/2);

    }
    @Override
    public void run() {}
    public void testStart() {
        System.out.println("Start!");
        startTime = System.currentTimeMillis();
        clickCount = -1;
        isPlaying = true;
        next();
    }
    public void pressedLeft() {
        if(!isPlaying) { System.out.println("Press Up-arrow when you want start test"); return; }
        if(isRight) { System.out.println("You press wrong key! Stopping game..."); printEnd(); return; }
        next();
    }
    public void pressedRight() {
        if(!isPlaying) { System.out.println("Press Up-arrow when you want start test"); return; }
        if(!isRight) { System.out.println("You press wrong key! Stopping game..."); printEnd(); return; }
        next();
    }
    public void next() {
        clickCount++;
        isRight = (Math.random() > 0.5);
        try {
            Clip tC = AudioSystem.getClip();
            if(isRight) { tC.open(AudioSystem.getAudioInputStream(new File("C:/reflexTest/r.wav"))); }
            else { tC.open(AudioSystem.getAudioInputStream(new File("C:/reflexTest/l.wav"))); }
            System.out.println(isRight);
            tC.start();
            //Thread.sleep(200);
            //tC.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        repaint();
    }
    public void printEnd() {
        isPlaying = false;
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.println("Time: " + endTime + "ms");
        System.out.println("Count: " + clickCount);
        System.out.println("Time/Count: " + (1.0*endTime/clickCount));
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP: testStart(); break;
            case KeyEvent.VK_LEFT: pressedLeft(); break;
            case KeyEvent.VK_RIGHT: pressedRight(); break;
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {}
    @Override
    public void keyTyped(KeyEvent arg0) {}
}
