import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Shapes
{
    private ArrayList<Rectangle> rectContents;
    private ArrayList<Ellipse2D.Double> ellipseContents;
    private ArrayList<Line2D.Double> lineContents;
    private int objectType;

    public Shapes()
    {
        rectContents = new ArrayList<Rectangle>();
        ellipseContents = new ArrayList<Ellipse2D.Double>();
        lineContents = new ArrayList<Line2D.Double>();
        objectType = 0;
    }

    public void setObjectType(int x)
    {
        objectType = x;
    }

    public int getObjectType()
    {
        return objectType;
    }

    public ArrayList<Rectangle> getRectContents()
    {
        return rectContents;
    }

    public void addRectContents(Rectangle o)
    {
        rectContents.add(o);
    }

    public ArrayList<Ellipse2D.Double> getEllipseContents()
    {
        return ellipseContents;
    }

    public void addEllipseContents(Ellipse2D.Double p)
    {
        ellipseContents.add(p);
    }

    public ArrayList<Line2D.Double> getLineContents()
    {
        return lineContents;
    }

    public void addLineContents(Line2D.Double l)
    {
        lineContents.add(l);
    }
}
