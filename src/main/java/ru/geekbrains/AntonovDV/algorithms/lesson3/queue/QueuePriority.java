package ru.geekbrains.AntonovDV.algorithms.lesson3.queue;

public class QueuePriority<E extends Comparable<? super E>> implements QueueInterface<E>
{
    private static final int INITIAL_HEAD = 0;
    private final E[] data;
    private int size;

    private int head;
    private int tail;

/*  Очередь в общем организована так же, как в QueueClass.
    Отличия касаются только метода insert().
    И добавлен метод swap().
*/
    @SuppressWarnings("unchecked")
    public QueuePriority (int maxSize)
    {
        this.data = (E[]) new Comparable [maxSize];
        this.head = INITIAL_HEAD;
        this.tail = head -1;
    }

    // то же, что в QueueClass.insert(), но добавленый в конец элемент сдвигаем на подходящую позицию,
    // чтобы соблюдалась сортировка всей очереди.
    @Override public boolean insert (E value)
    {
        if (isFull())   return false;

        if (++tail >= data.length)
            tail = 0;

        data[tail] = value;
        size++;

        int i=1,
            val = tail,    //< индекс ячейки, содержащей value
            test = val-1;  //< индекс ячейки сразу слева от val (её содержимое мы сравниваем с value)

        while (i++ < size)
        {
            if (val < 0)   val = data.length -1;
            if (test < 0)  test = data.length -1;

            if (data[test].compareTo(value) <= 0)
                break;
            swap(val--, test--);
        }
        return true;
    }// insert ()

    // вспомогательный метод (меняет местами два элемента массива)(без проверок)
    private void swap (int i, int j)
    {
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }// swap()

// Следующие методы идентичны в класах QueueClass и QueuePriority:

    @Override public E remove ()
    {
        E value = null;
        if (!isEmpty())
        {
            value = data[head];
            data[head] = null;
            if (++head >= data.length)
                head = 0;
            size--;
        }
        return value;
    }// remove ()

    @Override public E peek() {   return (isEmpty()) ? null : data[head];   }

    @Override public int size()    {   return size;   }
    @Override public boolean isEmpty()   {   return size == 0;   }
    @Override public boolean isFull()    {   return size == data.length;   }

    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder("[");
        int i = 0,
                pointer = head;
        while (i++ < size)
        {
            sb.append (data[pointer]);
            if (pointer != tail)
                sb.append(", ");
            if (++pointer >= data.length)
                pointer = 0;
        }
        sb.append(']');
        return sb.toString();
    }// toString ()

}// class QueuePriority
