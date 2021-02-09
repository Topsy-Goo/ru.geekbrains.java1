package ru.geekbrains.AntonovDV.algorithms.lesson3.stack;

import java.util.Map;

public class Brackets
{

    private static final String EXAMPLE = """
                    public static void main(String[] args)
                    {
                        testStack();
                    }
            """;

    private static final Map<Character, Character> BRACKETS_MAP = Map.of('{', '}', '[', ']', '(', ')');

    public static void main(String[] args)
    {
        new Brackets().check(EXAMPLE);
    }

    public void check(String text)
    {
        StackInterface<Character> stack = new StackClass<>(text.length());

        for (int i = 0; i < text.length(); i++)
        {
            char currentChar = text.charAt(i);
            checkChar(stack, currentChar, i);
        }

        if (!stack.isEmpty())
        {
            System.err.println("Error: missing right delimiter");
        }
    }

    private void checkChar (StackInterface<Character> stack, Character currentChar, int currentCharPosition)
    {
        switch (currentChar)
        {
        case '{':
        case '[':
        case '(':
            stack.push(currentChar);
            break;
        case '}':
        case ']':
        case ')':
            if (stack.isEmpty())
            {
                System.err.println("Error: " + currentChar + " at " + currentCharPosition);
                break;
            }

            Character lastOpenedBracket = stack.pop();
            if (!BRACKETS_MAP.get(lastOpenedBracket).equals(currentChar))
            {
                System.err.println("Error: " + currentChar + " at " + currentCharPosition);
            }
            break;
        default:
            break;
        }
    }// checkChar ()


}// class Brackets
