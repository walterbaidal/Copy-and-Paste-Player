import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 



import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
import javax.swing.JTextField;








import javax.swing.UIManager;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
 
//need JLayerPlayerPausable class
public class JLayerPausableTest{
        private SoundJLayer soundToPlay;
        private static JMenuItem menuCargar;
        private String cancionActual;
        public static void main(String[] args){
                JLayerPausableTest jLayerPausable = new JLayerPausableTest();

                JLayerPausableTest.interfaceGrafica(jLayerPausable);
        }
 
        //test only
        public static void interfaceGrafica(JLayerPausableTest jLayerPausable){

                JFrame frame = new JFrame("Copy & Paste Player");
                frame.setResizable(false);
                
                frame.setContentPane(new JLabel(new ImageIcon(JLayerPausableTest.class.getResource("bg.png"))));  
                
                menuCargar = new JMenuItem("Cargar");
                JButton buttonPlay = new JButton();
                JButton buttonPause = new JButton();
                JButton buttonStop = new JButton();
                JButton buttonNext = new JButton();
                JButton buttonPrev = new JButton();
            /*    JTextField txtRuta = new JTextField();*/
                JButton buttonElegir = new JButton("Archivo");
                JPanel panel = new JPanel(new GridBagLayout());

                frame.add(buttonPlay);
                frame.add(buttonStop);
                frame.add(buttonNext);
                frame.add(buttonPrev);
                frame.add(buttonElegir);

               /* panel.add(txtRuta);*/

                buttonPlay.setName("Play");
                buttonPlay.setIcon(new ImageIcon(JLayerPausableTest.class.getResource("play.png")));
                buttonPlay.setBorderPainted(false);
                buttonPlay.setFocusPainted(false);
                buttonPlay.setContentAreaFilled(false);
                buttonPlay.setBorder(BorderFactory.createEmptyBorder());
                buttonPlay.setLayout(null);
                buttonPlay.setBounds(193, 360, 100, 100);
                
                buttonStop.setName("Stop");
                buttonStop.setIcon(new ImageIcon(JLayerPausableTest.class.getResource("stop.png")));
                buttonStop.setBorderPainted(false);
                buttonStop.setFocusPainted(false);
                buttonStop.setContentAreaFilled(false);
                buttonStop.setBorder(BorderFactory.createEmptyBorder());
                buttonStop.setBounds(170, 316, 145, 50);

                buttonNext.setName("Next");
                buttonNext.setIcon(new ImageIcon(JLayerPausableTest.class.getResource("next.png")));
                buttonNext.setBorderPainted(false);
                buttonNext.setFocusPainted(false);
                buttonNext.setContentAreaFilled(false);
                buttonNext.setBorder(BorderFactory.createEmptyBorder());
                buttonNext.setLayout(null);
                buttonNext.setBounds(240, 360, 124, 100);

                buttonPrev.setName("Prev");
                buttonPrev.setIcon(new ImageIcon(JLayerPausableTest.class.getResource("prev.png")));
                buttonPrev.setBorderPainted(false);
                buttonPrev.setFocusPainted(false);
                buttonPrev.setContentAreaFilled(false);
                buttonPrev.setBorder(BorderFactory.createEmptyBorder());
                buttonPrev.setLayout(null);
                buttonPrev.setBounds(122, 360, 124, 100);

                buttonElegir.setName("File");
                buttonElegir.setForeground(Color.gray);
                buttonElegir.setBorderPainted(false);
                buttonElegir.setFocusPainted(false);
                buttonElegir.setContentAreaFilled(false);
                buttonElegir.setBorder(BorderFactory.createEmptyBorder());
                buttonElegir.setBounds(4, 4, 60, 30);
                
                /*     buttonElegir.addActionListener(new ButtonListener(jLayerPausable));*/
                buttonElegir.addActionListener(new ButtonListener(jLayerPausable));
                buttonPlay.addActionListener(new ButtonListener(jLayerPausable));
                buttonPause.addActionListener(new ButtonListener(jLayerPausable));
                buttonStop.addActionListener(new ButtonListener(jLayerPausable));               
               /* txtRuta.addActionListener(new ButtonListener(jLayerPausable));*/
                
               
                
                frame.add(panel);
              /*  txtRuta.setBounds(200, 20, 100, 10);*/
                frame.setPreferredSize(new Dimension(500, 500)); 
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
        }
        
        
 
        public static class ButtonListener implements ActionListener{
                JLayerPausableTest jLayerPausable;
                public ButtonListener(JLayerPausableTest jLayerPausable){
                        this.jLayerPausable = jLayerPausable;
                }
                @Override
                public void actionPerformed(ActionEvent evento) {
                        JButton botao = (JButton)evento.getSource();
                        if(botao.getName().equalsIgnoreCase("PLAY")){
                            botao.setName("Pause");
                            botao.setIcon(new ImageIcon(JLayerPausableTest.class.getResource("pause.png")));
                                this.jLayerPausable.soundToPlay.play();
                        }
                        else if(botao.getName().equalsIgnoreCase("PAUSE")){
                            botao.setName("Play");
                            botao.setIcon(new ImageIcon(JLayerPausableTest.class.getResource("play.png")));
                                this.jLayerPausable.soundToPlay.pauseToggle();
                        }
                        else if(botao.getName().equalsIgnoreCase("STOP")){
                                this.jLayerPausable.soundToPlay.stop();
                        }
                        else if(botao.getName().equalsIgnoreCase("FILE")){
                            this.file();
                        }
                        
                }
                public void file() {
                	JFileChooser fc = new JFileChooser();
                	int respuesta = fc.showOpenDialog(menuCargar);
                	if (respuesta == JFileChooser.APPROVE_OPTION)
        	        {
        	            //Crear un objeto File con el archivo elegido
        	            File archivoElegido = fc.getSelectedFile();
        	            //Mostrar el nombre del archvivo en un campo de texto
        						try {
        			                jLayerPausable.soundToPlay = new SoundJLayer(archivoElegido.getPath());
        						} catch (Exception e1) {
        							// TODO Auto-generated catch block
        							e1.printStackTrace();
        						};		
        	        }
                }
        }
 
}
 
class SoundJLayer implements Runnable{
        private String filePath;
        private JLayerPlayerPausable player;
        private Thread playerThread;
        private String namePlayerThread = "AudioPlayerThread";
        private PlaybackListener playbackListener = new PlaybackListener();

        public SoundJLayer(String filePath){
                this.filePath = filePath;
        }
       
        public SoundJLayer(String filePath, String namePlayerThread){
                this.filePath = filePath;
                this.namePlayerThread = namePlayerThread;
        }
 
        public void play(){
                if (this.player == null){
                        this.playerInitialize();
                }
                else if(!this.player.isPaused() || this.player.isComplete() || this.player.isStopped()){
                        this.stop();
                        this.playerInitialize();
                }
                this.playerThread = new Thread(this, namePlayerThread);
                this.playerThread.setDaemon(true);
 
                this.playerThread.start();

        }
 
        public void pause(){
                if (this.player != null){
                        this.player.pause();
 
                        if(this.playerThread != null){
                                //this.playerThread.stop(); //unsafe method
                                this.playerThread = null;
                        }
                }
        }
 
        public void pauseToggle(){
                if (this.player != null){
                        if (this.player.isPaused() && !this.player.isStopped()){
                                this.play();
                        }
                        else{
                                this.pause();
                        }
                }
        }
 
        public void stop(){
                if (this.player != null){
                        this.player.stop();
 
                        if(this.playerThread != null){
                                //this.playerThread.stop(); //unsafe method
                                this.playerThread = null;
                        }
                }
        }

        /*private void playerInitialize(){
                try{
                        String urlAsString =
                                        "file:///"
                                                        + new java.io.File(".").getCanonicalPath()
                                                        + "/"
                                                        + this.filePath;
 
                        this.player = new JLayerPlayerPausable(new java.net.URL(urlAsString));
                        this.player.setPlaybackListener(this.playbackListener);
                }
                catch (JavaLayerException e){
                        e.printStackTrace();
                }
        }*/
        private void playerInitialize(){
                try {
                        this.player = new JLayerPlayerPausable(this.filePath);
                        this.player.setPlaybackListener(this.playbackListener);
                }
                catch (JavaLayerException e) {
                        e.printStackTrace();
                }
        }
 
        // IRunnable members
        public void run(){
                try{
                        this.player.resume();
                }
                catch (javazoom.jl.decoder.JavaLayerException ex){
                        ex.printStackTrace();
                }
        }
       
        private static class PlaybackListener extends JLayerPlayerPausable.PlaybackAdapter {
                // PlaybackListener members
                @Override
                public void playbackStarted(JLayerPlayerPausable.PlaybackEvent playbackEvent){
                        System.err.println("PlaybackStarted()");
                }
               
                @Override
                public void playbackPaused(JLayerPlayerPausable.PlaybackEvent playbackEvent){
                        System.err.println("PlaybackPaused()");
                }
 
                @Override
                public void playbackFinished(JLayerPlayerPausable.PlaybackEvent playbackEvent){
                        System.err.println("PlaybackStopped()");
                }
        }
}