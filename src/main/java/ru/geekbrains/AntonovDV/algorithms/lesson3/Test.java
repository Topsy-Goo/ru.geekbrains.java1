package ru.geekbrains.AntonovDV.algorithms.lesson3;


import ru.geekbrains.AntonovDV.algorithms.lesson3.queue.DequeClass;
import ru.geekbrains.AntonovDV.algorithms.lesson3.queue.DequeInterface;
import ru.geekbrains.AntonovDV.algorithms.lesson3.queue.QueueInterface;
import ru.geekbrains.AntonovDV.algorithms.lesson3.queue.QueuePriority;
import ru.geekbrains.AntonovDV.algorithms.lesson3.stack.StackClass;
import ru.geekbrains.AntonovDV.algorithms.lesson3.stack.StackInterface;

public class Test
{

    public static void main(String[] args)
    {
        //testStack (6);
        //testQueue();
        //System.out.println (reverseString ("string"));
        testDeque();
    }// main ()

    public static void testDeque ()
    {
        int size = 6;
        DequeInterface<Integer> deq = new DequeClass<>(size);

        for (int i=1, n=size;   i<=n;   i++)
        {
            System.out.printf ("\nAdding %d into Tail returned %b", i, deq.insertTail(i++));
            System.out.printf ("\nAdding %d into Head returned %b", i, deq.insertHead(i));
        }
        System.out.println("\n"+deq);

        //for (int i=1, n=size;   i<=n;   i++)
        {
            System.out.printf ("\nRemoving item %d from Tail", deq.removeTail());
            System.out.printf ("\nRemoving item %d from Tail", deq.removeTail());
            System.out.printf ("\nRemoving item %d from Tail", deq.removeTail());
            System.out.printf ("\nRemoving item %d from Head.", deq.removeHead());
            System.out.printf ("\nRemoving item %d from Head.", deq.removeHead());
            System.out.printf ("\nRemoving item %d from Head.", deq.removeHead());//*/
            //i++;
        }
        System.out.println("\n"+deq);
    }// testDeque ()

    public static String reverseString (String s)
    {
        int len;
        if (s != null && (len = s.length()) > 0)
        {
            StackClass<Character> stack = new StackClass<>(len);
            for (int i=0; i<len; i++)
                stack.push(s.charAt(i));

            StringBuilder sb = new StringBuilder(len);
            for (int i=0; i<len; i++)
                sb.append(stack.pop());

            return sb.toString();
        }
        return null;
    }// reverseString ()

    private static void testStack (int n)
    {
        System.out.println ("\nThe testStack starts:");
        {
            StackInterface<Integer> stack = new StackClass<>(n);
            for (int i=1; i<=n; i++)
                System.out.println("\tAdd value "+i+": " + stack.push(i));

            System.out.println("\tStack size: " + stack.size());
            System.out.print("\tStack top: " + stack.peek()+"\n\t");
            stack.display();

            while (!stack.isEmpty())
                System.out.println ("\t"+stack.pop());
        }
        System.out.println ("The testStack finished.\n");
    }// testStack ()

    private static void testQueue()
    {
        //QueueInterface<Integer> queue = new QueueClass<>(5);
        QueueInterface<Integer> queue = new QueuePriority<>(5);

        System.out.print(queue.insert(3)+", ");
        System.out.print(queue.insert(5)+", ");
        System.out.print(queue.insert(1)+", ");
        System.out.print(queue.insert(2)+", ");
        System.out.print(queue.insert(6)+", ");
        System.out.println(queue.insert(4));
        System.out.println(queue);

        System.out.println("Queue peek: " + queue.peek());
        System.out.println("Queue size: " + queue.size());
        System.out.println("Queue is full: " + queue.isFull());

        queue.remove();
        queue.insert(62);
        System.out.println(queue);
        queue.remove();
        queue.insert(14);
        System.out.println(queue);
    }


}// class Test
