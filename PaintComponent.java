import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;


public class PaintComponent extends JComponent
{
    private Shapes s;
    private int answer;

     public PaintComponent()
     {
	      s = new Shapes();
        answer = 0;
     }


    public void paintComponent(Graphics g)
    {
        ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
        ArrayList<Ellipse2D.Double> ellips = new ArrayList<Ellipse2D.Double>();
        ArrayList<Line2D.Double> lines = new ArrayList<Line2D.Double>();
        int count = 0;
        Graphics2D g2 = (Graphics2D)g;

        if(!QuizQuiz.isVariationTwo())
        {
            answer = (int)(Math.random()*13 + 3);
            QuizQuiz.changeAnswer(answer + "");
        }

        if(!QuizQuiz.isVariationTwo())
        {
            int whatObject = (int)(Math.random()*3);
            for(int i = 0; i<answer; i++)
            {

                if(whatObject == 0)//Rectangle
                {
                    s.setObjectType(0);
                    int d1 = (int)(Math.random()*700); //x
                    int d2 = (int)(Math.random()*100); //y
                    int d3 = (int)(Math.random()*100); //w
                    int d4 = (int)(Math.random()*100); //h


                    g2.draw(new Rectangle(d1,d2,d3,d4));
                    s.addRectContents(new Rectangle(d1,d2,d3,d4) );

                }

                else if( whatObject == 1 )//Ellipse
                {
                    s.setObjectType(1);
                    int d1 = (int)(Math.random()*700); //x
                    int d2 = (int)(Math.random()*100); //y
                    int d3 = (int)(Math.random()*100); //w
                    int d4 = (int)(Math.random()*100); //h
                    g2.draw( new Ellipse2D.Double( d1, d2, d3, d4 ) );
                    s.addEllipseContents(new Ellipse2D.Double( d1, d2, d3, d4 ) );


                }


                else if( whatObject == 2 ) //line
                {
                    s.setObjectType(2);
                    int d1 = (int)(Math.random()*500);  //x
                    int d2 = (int)(Math.random()*100);//y
                    int d3 = d1 + (int)(Math.random()*100); //x end
                    int d4 = d2 + (int)(Math.random()*50); //y end
                    g2.draw( new Line2D.Double( d1, d2, d3, d4 ));
                    s.addLineContents(new Line2D.Double( d1, d2, d3, d4 ) );
                }



            }

        }


        else if (QuizQuiz.isVariationTwo() && !QuizQuiz.isVariationThree()) //is variation two
        {

            for(int i = 0; i < answer; i++)
            {
                int randomX = (int) (Math.random()*2);
                int randomY = (int) (Math.random()*2);
                if(s.getObjectType() == 0) //RECTANGLE
                {
                    rects = s.getRectContents();
                    int xRand = (int)( Math.random() * 8);
                    int yRand = (int) (Math.random() * 8);

                    if( rects.get(i).x + xRand > 700 )
                    {
                        rects.get(i).setLocation(  rects.get(i).x - xRand, rects.get(i).y);
                    }
                    else if(rects.get(i).x-xRand<100)
                    {
                        rects.get(i).setLocation( rects.get(i).x + xRand, rects.get(i).y );
                    }
                    else
                    {
                        if(randomX==0)
                            rects.get(i).setLocation( rects.get(i).x + xRand, rects.get(i).y );
                        else
                            rects.get(i).setLocation(  rects.get(i).x - xRand, rects.get(i).y);


                    }

                    if( rects.get(i).y + yRand > 100 )
                    {
                        rects.get(i).setLocation( rects.get(i).x, rects.get(i).y - yRand );
                    }
                    else if(rects.get(i).y-yRand<100)
                    {
                        rects.get(i).setLocation( rects.get(i).x, rects.get(i).y + yRand );
                    }
                    else
                    {
                        if(randomY==0)
                        rects.get(i).setLocation( rects.get(i).x, rects.get(i).y + yRand );
                        else
                        rects.get(i).setLocation( rects.get(i).x, rects.get(i).y - yRand );


                    }
                    g2.draw(rects.get(i));


                }
                else if(s.getObjectType() == 1) //ELLIPSE
                {
                    ellips = s.getEllipseContents();
                    int xRand = (int)( Math.random() * 8);
                    int yRand = (int) (Math.random() * 8);


                    if( ellips.get(i).x + xRand > 700 )
                    {
                        ellips.get(i).setFrame(ellips.get(i).x - xRand, ellips.get(i).y , ellips.get(i).width, ellips.get(i).height);
                    }
                    else if( ellips.get(i).x + xRand < 100 )
                    {
                        ellips.get(i).setFrame( ellips.get(i).x + xRand, ellips.get(i).y, ellips.get(i).width, ellips.get(i).height);


                    }
                    else
                    {
                        if(randomX==0)
                            ellips.get(i).setFrame( ellips.get(i).x + xRand, ellips.get(i).y, ellips.get(i).width, ellips.get(i).height);
                        else
                            ellips.get(i).setFrame( ellips.get(i).x - xRand, ellips.get(i).y, ellips.get(i).width, ellips.get(i).height);
                    }


                    if( ellips.get(i).y + yRand > 100 )
                    {
                        ellips.get(i).setFrame( ellips.get(i).x, ellips.get(i).y - yRand , ellips.get(i).width, ellips.get(i).height);
                    }
                    else if( ellips.get(i).y + yRand < 100 )
                    {
                        ellips.get(i).setFrame( ellips.get(i).x, ellips.get(i).y + yRand ,ellips.get(i).width, ellips.get(i).height);
                    }
                    else
                    {
                        if(randomY==0)
                            ellips.get(i).setFrame( ellips.get(i).x, ellips.get(i).y + yRand ,ellips.get(i).width, ellips.get(i).height);
                        else
                            ellips.get(i).setFrame( ellips.get(i).x, ellips.get(i).y - yRand ,ellips.get(i).width, ellips.get(i).height);
                    }


                    g2.draw(ellips.get(i));


                }

                else //LINE
                {
                    lines = s.getLineContents();
                    int xRand = (int) (Math.random() * 8);
                    int yRand = (int) (Math.random() * 8);

                    if( lines.get(i).x1 + xRand > 700 || lines.get(i).x2 + xRand > 700)
                    {
                        lines.get(i).setLine(  lines.get(i).x1 - xRand, lines.get(i).y1, lines.get(i).x2-yRand,lines.get(i).y2 );
                    }
                    else if( lines.get(i).x1 + xRand < 100 )
                    {
                        lines.get(i).setLine( lines.get(i).x1 + xRand, lines.get(i).y1, lines.get(i).x2+xRand, lines.get(i).y2 );
                    }
                    else
                    {
                        if(randomX==0)
                            lines.get(i).setLine( lines.get(i).x1 + xRand, lines.get(i).y1, lines.get(i).x2+xRand, lines.get(i).y2 );
                        else
                            lines.get(i).setLine( lines.get(i).x1 - xRand, lines.get(i).y1, lines.get(i).x2-xRand, lines.get(i).y2 );
                    }

                    if( lines.get(i).y1 + yRand > 100 || lines.get(i).y2 + yRand > 100 )
                    {
                        lines.get(i).setLine( lines.get(i).x1, lines.get(i).y1 - yRand,lines.get(i).x2, lines.get(i).y2-yRand  );
                    }
                    else if( lines.get(i).y1 + yRand < 100 )
                    {
                        lines.get(i).setLine(lines.get(i).x1, lines.get(i).y1+yRand, lines.get(i).x2, lines.get(i).y2+yRand);
                    }
                    else
                    {
                        if(randomY==0)
                            lines.get(i).setLine(lines.get(i).x1, lines.get(i).y1+yRand, lines.get(i).x2, lines.get(i).y2+yRand);  //x1 y1 x2 y2
                        else
                            lines.get(i).setLine(lines.get(i).x1, lines.get(i).y1-yRand, lines.get(i).x2, lines.get(i).y2-yRand);  //x1 y1 x2 y2
                    }

                    g2.draw(lines.get(i));


                }
            }
        }
        if(QuizQuiz.isVariationThree())//VARIATION THREE
        {
            int v3Answer = 0;
            for(int i = 0; i < answer; i++)
            {
                int whatColor = (int)(Math.random()*3);
                if(s.getObjectType() == 0) //RECTANGLE
                {
                    rects = s.getRectContents();

                    g2.draw(rects.get(i));
                    if(whatColor == 0)
                    {
                        g2.setColor(Color.red);
                        if(QuizQuiz.getColor() == 0)
                            v3Answer++;
                    }
                    else if(whatColor == 1)
                    {
                        g2.setColor(Color.green);
                        if(QuizQuiz.getColor()==1)
                            v3Answer++;
                    }
                    else
                    {
                        g2.setColor(Color.blue);
                        if(QuizQuiz.getColor()==2)
                            v3Answer++;
                    }


                }
                else if(s.getObjectType() == 1) //ELLIPSE
                {
                    ellips = s.getEllipseContents();



                    g2.draw(ellips.get(i));
                    if(whatColor == 0)
                    {
                        g2.setColor(Color.red);
                        if(QuizQuiz.getColor() == 0)
                            v3Answer++;
                    }
                    else if(whatColor == 1)
                    {
                        g2.setColor(Color.green);
                        if(QuizQuiz.getColor()==1)
                            v3Answer++;
                    }
                    else
                    {
                        g2.setColor(Color.blue);
                        if(QuizQuiz.getColor()==2)
                            v3Answer++;
                    }


                }

                else //LINE
                {
                    lines = s.getLineContents();

                    g2.draw(lines.get(i));
                    if(whatColor == 0)
                    {
                        g2.setColor(Color.red);
                        if(QuizQuiz.getColor() == 0)
                            v3Answer++;
                    }
                    else if(whatColor == 1)
                    {
                        g2.setColor(Color.green);
                        if(QuizQuiz.getColor()==1)
                            v3Answer++;
                    }
                    else
                    {
                        g2.setColor(Color.blue);
                        if(QuizQuiz.getColor()==2)
                            v3Answer++;
                    }


                }
            }
            QuizQuiz.changeAnswer(v3Answer + "");
        }

    }
}
