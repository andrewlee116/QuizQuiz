import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import static java.lang.Integer.parseInt;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class QuizQuiz
{
    private JFrame frame;
    private JPanel mainPanel, panel1, panel1a, panel1b, panel1c, panel2, panel3;
    private JLabel finalScore, time;
    private ImageComponent hearts3, hearts2, heart1;
    private ImageComponent correct, wrong, gameOver;
    private JButton startButton;
    private JTextField field;
    private static int color;
    private PaintComponent paint;
    private Timer t, dt;
    private int seconds, delayseconds;
    private String input;
    private static String answer;
    private int lives;
    private int score = 0;
    private String scoreString = "";
    private static boolean variationOneActive;
    private static boolean variationTwoActive;
    private static boolean variationThreeActive;
    private boolean makeVariationTwoActive;
    private boolean makeVariationThreeActive;

    public QuizQuiz()
    {
        setUpDelayTimer();
        delayseconds = 2;
        color = 0;
        finalScore = new JLabel();
        variationOneActive = true;
	    makeVariationTwoActive = false;
	    makeVariationThreeActive = true;

        answer = "";
        gameOver = new ImageComponent( "gameOver.png" );
        wrong = new ImageComponent( "wrong.png" );
        correct = new ImageComponent( "correct.png" );
        time = new JLabel();

        variationTwoActive = false;
        variationThreeActive = false;

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,3));
 	    panel1a = new JPanel();
	    panel1b = new JPanel();
	    panel1c = new JPanel();
        panel1a.setBackground(Color.white);
        panel1b.setBackground(Color.white);
        panel1c.setBackground(Color.white);
        panel1c.setBorder( BorderFactory.createLineBorder( Color.black) );

        setUpHearts();
        setUpScore();

        panel1.add( panel1a );
        panel1.add( panel1b );
        panel1.add( panel1c );

        paint = new PaintComponent();
	    panel3 = new JPanel();
        panel3.setBorder( BorderFactory.createLineBorder( Color.black) );
	    seconds = 50;
        time.setText( seconds + "" );
        frame = new JFrame();
        frame.setSize(1100,750);
        frame.setTitle( "QUIZQUIZ" );
        panel1.setForeground(Color.white);
        panel3.setBackground(Color.white);
        setUpTextField();
	    mainPanel = new JPanel();
        mainPanel.setLayout( new GridLayout(3,1) );
        mainPanel.add( panel1 );
        setUpStartButton();
        mainPanel.add( panel3 );
        mainPanel.setBackground(Color.white);
	    frame.add( mainPanel );
	    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    frame.setVisible( true );
    }

    public void setUpStartButton()
    {
        JButton asdf = new JButton("Start QuizQuiz!");
        Font lol = asdf.getFont();
        Font p1a = new Font( "Chalkduster", Font.BOLD, lol.getSize() + 50 );
        asdf.setFont( p1a );

        startButton = asdf;
        startButton.setBackground(new Color(50,190,0));
        startButton.setForeground(Color.white);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);

        class ButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                mainPanel.validate();
                startButton.setVisible( false );
                setUpTimer();
                setUpGame();
            }
        }

        mainPanel.add(startButton);
        ActionListener listener = new ButtonListener();
        startButton.addActionListener( listener );
    }

    public void setUpTextField()
    {
        field = new JTextField(2);
        panel3.add( field);
        panel3.validate();
        class KeyBoardListener implements KeyListener
        {
            public void keyPressed(KeyEvent event)
            {
   		           if( event.getKeyCode() == KeyEvent.VK_ENTER)
		           {
                       input = field.getText();
                       field.setText( "" );
                       logic();
                   }
            }
            @Override
            public void keyTyped(KeyEvent e){
            }
            @Override
            public void keyReleased(KeyEvent e){
            }
        }

        KeyListener listener = new KeyBoardListener() ;
        field.addKeyListener( listener );
        field.setFocusable( true );
    }

    public void setUpGameOver()
    {
        mainPanel.removeAll();
        gameOver = new ImageComponent( "gameOver.png" );
        mainPanel.add( gameOver );
        finalScore.setText( "Score: " + score );
        Font f = finalScore.getFont();
        Font bold = new Font( "Serif", Font.BOLD, f.getSize() + 50 );
        finalScore.setFont( bold );
        finalScore.setHorizontalAlignment( finalScore.CENTER );
        mainPanel.add(finalScore);
        mainPanel.invalidate();
        mainPanel.validate();
        mainPanel.repaint();
    }

    public void generateImage()
    {
        mainPanel.remove(1);
        mainPanel.add(paint, 1);
        mainPanel.repaint();
    }

    public void variationThree()
    {
        mainPanel.removeAll();
        mainPanel.add(panel1);
        changeAnswer((Math.random()*20)+"");
        mainPanel.add(panel3);
        mainPanel.invalidate();
        mainPanel.validate();
        mainPanel.repaint();
    }

    public void setUpTimer()
    {
        class TimerListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                seconds--;
                time.setText( seconds + "");

                if(makeVariationTwoActive )
                {
                    variationTwoActive = true;
                    generateImage();
                }
                else if(makeVariationThreeActive && (seconds%15 == 0))
                {
                    variationThreeActive = true;
                    variationTwoActive = true;
                    generateImage();
                }
                if(seconds==0)
                    logic();
            }
        }

        ActionListener listener = new TimerListener();
        t = new Timer( 100, listener );
        t.start();
        Font lol = time.getFont();
        Font p1a = new Font( "Serif", Font.BOLD, lol.getSize() + 50 );
        time.setFont( p1a );
        panel1a.add( time );
        panel1a.setBorder( BorderFactory.createLineBorder( Color.black) );
    }

    public void setUpDelayTimer()
    {
        class TimerListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                delayseconds--;
                if(delayseconds == 0)
                {
                    mainPanel.removeAll();
                    mainPanel.add(panel1);
                    mainPanel.add(paint);
                    mainPanel.add(panel3);
                    mainPanel.invalidate();
                    mainPanel.validate();
                    mainPanel.repaint();

                    if( lives > 0 )
                     {
                         setUpGame();
                     }
                    else
                     {
                         setUpGameOver();
                     }
                }
            }
        }

        ActionListener listener = new TimerListener();
        dt = new Timer( 1000, listener );
    }

    public void setUpHearts()
    {
        hearts3 = new ImageComponent( "hearts3.png" );
        panel1c.add( hearts3 );
        lives = 3;
    }

    public void updateHearts()
    {
        lives--;
        panel1c.removeAll();
        if( lives == 2 )
        {
            hearts2 = new ImageComponent( "hearts2.png" );
            panel1c.add( hearts2 );
            panel1c.invalidate();
            panel1c.validate();
            panel1c.repaint();
        }
        if( lives == 1 )
        {
            heart1 = new ImageComponent( "heart1.png" );
            panel1c.add( heart1 );
            panel1c.invalidate();
            panel1c.validate();
            panel1c.repaint();
        }
    }

    public void setUpScore()
    {
        JLabel a = new JLabel( "Score: " + score);
    	Font lol = time.getFont();
        Font p1a = new Font( "Serif", Font.BOLD, lol.getSize() + 50 );
        a.setFont( p1a );
        panel1b.add( a );
    	panel1b.validate();
        panel1b.setBorder( BorderFactory.createLineBorder( Color.black) );
    }

    public void updateScore()
    {
	       if(input == null || !input.equals(answer))
           {
                score += -10;
                JLabel a = new JLabel("Score: " + score);
                panel1b.removeAll();
                Font lol = a.getFont();
                Font p1a = new Font( "Serif", Font.BOLD, lol.getSize() + 50 );
                a.setFont( p1a );
                panel1b.add( a );
                panel1b.validate();
           }
           else if( input.equals( answer ) )
	       {
                scoreString = time.getText();
                score += parseInt( scoreString );
                JLabel a = new JLabel("Score: " + score);
                panel1b.removeAll();
                Font lol = a.getFont();
                Font p1a = new Font( "Serif", Font.BOLD, lol.getSize() + 50 );
                a.setFont( p1a );
                panel1b.add( a );
                panel1b.validate();
	       }
    }

    public void logic()
    {
        delayseconds = 2;
        variationTwoActive = false;
    	variationThreeActive = false;
    	makeVariationTwoActive = false;
    	makeVariationThreeActive = false;
        updateScore();
        if( input == null || input.equals( answer ) == false )
        {
            mainPanel.removeAll();
            mainPanel.add(panel1);
            mainPanel.add( new ImageComponent( "wrong.png" ) );
            mainPanel.add(panel3);
            mainPanel.invalidate();
            mainPanel.validate();
            mainPanel.repaint();
            updateHearts();
            t.stop();
            dt.start();
        }
        else
        {
            mainPanel.removeAll();
            mainPanel.add(panel1);
            mainPanel.add( new ImageComponent( "correct.png" ) );
            mainPanel.add(panel3);
            mainPanel.invalidate();
            mainPanel.validate();
            mainPanel.repaint();
            t.stop();
            dt.start();
        }
    }

    public void setUpGame()
    {
        seconds = 50;

        int x =(int)(Math.random()*3);
        if(x==0)
        {
            variationOneActive = true;
            makeVariationTwoActive = false;
            makeVariationThreeActive = false;
            generateImage();
            t.start();
        }
        else if(x==1)
        {
            variationOneActive = false;
            makeVariationTwoActive = true;
            makeVariationThreeActive = false;
            generateImage();
            t.start();
        }
        else
        {
            JLabel b = new JLabel();
            color =(int)(Math.random()*3);
            if(color == 0)
            {
                b.setText(" COUNT RED SHAPES! ");
                b.setForeground(Color.red);
                Font lol = b.getFont();
                Font p1a = new Font( "Serif", Font.BOLD, lol.getSize() + 10 );
                b.setFont( p1a );
                panel1b.add( b );
                panel1b.validate();
            }
            else if(color == 1)
            {
                b.setText(" COUNT GREEN SHAPES! ");
                b.setForeground(Color.green);
                Font lol = b.getFont();
                Font p1a = new Font( "Serif", Font.BOLD, lol.getSize() + 10 );
                b.setFont( p1a );
                panel1b.add( b );
                panel1b.validate();
            }
            else
            {
                b.setText(" COUNT BLUE SHAPES! ");
                b.setForeground(Color.blue);
                Font lol = b.getFont();
                Font p1a = new Font( "Serif", Font.BOLD, lol.getSize() + 10 );
                b.setFont( p1a );
                panel1b.add( b );
                panel1b.validate();
            }
            mainPanel.repaint();
            variationOneActive = false;
            makeVariationTwoActive = false;
            makeVariationThreeActive = true;
            variationThreeActive = true;
            generateImage();
            t.start();
        }
    }

     public static void changeAnswer(String ans)
     {
         answer = ans;
     }

     public static boolean isVariationOne()
     {
         return variationOneActive;
     }

     public static boolean isVariationTwo()
     {
         return variationTwoActive;
     }

     public static boolean isVariationThree()
     {
         return variationThreeActive;
     }

     public static String getSetupAnswer()
     {
         return answer;
     }

     public static int getColor()
     {
         return color;
     }
}
