package ru.geekbrains.AntonovDV.algorithms.lesson3.stack;

import java.util.Map;

public class Brackets
{

    private static final String EXAMPLE = """
                    public static void main(String[] args)
                    {
                        testStack();
                    ]
            """;

    public static void main (String[] args)
    {
        new Brackets().check (EXAMPLE);
    }// main ()

    public void check (String text)
    {
        StackInterface<Character> stack = new StackClass<>(text.length());
        int i = 0;
        for (; i < text.length(); i++)
        {
            char currentChar = text.charAt(i);
            if (!checkChar (stack, currentChar))
                System.err.println("Error: " + currentChar + " at " + i + "\n"+EXAMPLE.substring(0, i+1)+"<<<");
        }
        if (!stack.isEmpty())
            System.err.println("Error: missing right delimiter" + "\n"+EXAMPLE.substring(0, i)+"<<<");
    }// check ()

    private boolean checkChar (StackInterface<Character> stack, Character character)
    {
        boolean boolOk = true;
        switch (character)
        {
        case '{':    stack.push ('}');
            break;
        case '[':    stack.push (']');
            break;
        case '(':    stack.push (')');
            break;
        case '}':
        case ']':
        case ')':    boolOk = character.equals (stack.peek()); //< (в случае ошибки peek вернёт null)
            if (boolOk)
                stack.pop();
            break;
        default:
            break;
        }
        return boolOk;
    }// checkChar ()


}// class Brackets


//