package ru.geekbrains.AntonovDV.java2.lesson1.sportsmen;

public class Cat implements Sportsmen
{
    static int counter = 1;
    public static final String TITLE = "Кот";

    private String name = "(cat#" + counter++ + ")";
    private final int maxRunningDistance,
                      maxJumpingHeight;


    public Cat (String name, int maxRunningDistance, int maxJumpingHeight)
    {
        if (name != null && !name.isBlank())
            this.name = name;
        this.maxRunningDistance = maxRunningDistance;
        this.maxJumpingHeight = maxJumpingHeight;
    }

    @Override public int run()
    {
        System.out.printf (runFormatString, TITLE, name, maxRunningDistance);
        return maxRunningDistance;
    }
    @Override public int jump()
    {
        System.out.printf (jumpFormatString, TITLE, name, maxJumpingHeight);
        return maxJumpingHeight;
    }

    @Override public String toString()
    {
        return name;
    }

    @Override public int getMaxRunningDistance()
    {
        return maxRunningDistance;
    }
    @Override public double getMaxJumpingHeight()
    {
        return maxJumpingHeight;
    }
    @Override public String getName ()
    {
        return name;
    }

}// class Cat
