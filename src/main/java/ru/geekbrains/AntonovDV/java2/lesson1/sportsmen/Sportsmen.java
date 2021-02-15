package ru.geekbrains.AntonovDV.java2.lesson1.sportsmen;

public interface Sportsmen
{
    public static final String  runFormatString = "%s %s может пробежать %d м.",
                                jumpFormatString = "%s %s может подпрыгнуть на высоту %d см.";

    int run ();
    int jump ();

    int getMaxRunningDistance ();
    double getMaxJumpingHeight ();
    String getName ();
    //String getTitle ();

}// interface Sportsmen
