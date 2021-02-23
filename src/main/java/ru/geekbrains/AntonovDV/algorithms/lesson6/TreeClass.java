package ru.geekbrains.AntonovDV.algorithms.lesson6;

import java.security.InvalidParameterException;
import java.util.Iterator;

public class TreeClass<E extends Comparable<? super E>> implements TreeInterface<E>
{
    private final int MAX_LEVEL; // применяем ограничения ДЗ на количество уровней
    private int size = 0;
    private Node root = null;

    private class Node
    {
        private E value = null;
        private Node leftHand = null,
                     rightHand = null,
                     parent = null;

        Node (E value) {   set (value);   }

        public void set (E v)
        {
            checkValue (v);
            this.value = v;
        }
/*        @Override public String toString () // для отладки
        {
            E r = (rightHand != null) ? rightHand.value : null,
              l = (leftHand != null) ? leftHand.value : null,
              p = (parent != null) ? parent.value : null;
            return String.format ("(P%s, v%s, L%s R%s)", p, value, l, r);
        }//*/
    }// class Node

    public TreeClass (int maxLevel) {   MAX_LEVEL = maxLevel;   } // применяем ограничения ДЗ на количество уровней

//---------------------------------- Интерфейсные методы

    @Override public boolean add (E v)
    {
        boolean boolOk = false;
        Node node = root;
        int cmp,
            level = 1;

        if (root == null)
        {
            root = new Node (v);
            size ++;
        }
        else
        while ((cmp = v.compareTo (node.value)) != 0)
        {
            if (cmp > 0)
            {
                if (node.rightHand == null)
                {
                    node.rightHand = new Node (v);
                    node.rightHand.parent = node;
                    size ++;
                    boolOk = true;
                    break;
                }
                if (level < MAX_LEVEL) // применяем ограничения ДЗ на количество уровней
                {
                    node = node.rightHand;
                    level ++;
                }
                else break;
            }
            else
            {
                if (node.leftHand == null)
                {
                    node.leftHand = new Node (v);
                    node.leftHand.parent = node;
                    size ++;
                    boolOk = true;
                    break;
                }
                if (level < MAX_LEVEL) // применяем ограничения ДЗ на количество уровней
                {
                    node = node.leftHand;
                    level ++;
                }
                else break;
            }
        }//while
        return boolOk;
    }// add ()

    @Override public boolean contains (E v)   {   return findnode (v) != null;   }

    @Override public boolean remove (E v)
    {
        Node delete = findnode (v);
        if (delete == null) return false;

    // После удаления delete у нас остануться три хвоста — left, right и parent. Можно вместо delete к parent
    // подключить right, и к концу ЛЕВОЙ цепочки right подключить left, т.к. любой элемент left меньше любого из
    // элементов right.
    // Можно сделать наоборот: вместо delete к parent подключить left, и к концу ПРАВОЙ цепочки left подключить
    // right.

        E _result = delete.value;
        int cmp;
        Node left = delete.leftHand,
             parent = delete.parent,
             right = delete.rightHand;

        if (right != null) // (всё равно с чего начинать. начнём с правой стороны)
        {
            resetParentLinkToNode (parent, delete, right);

            if (left != null)  // цепляем left к концу левой вертви right
            {
                while (right.leftHand != null)
                    right = right.leftHand;

                right.leftHand = left;
                left.parent = right;
            }
        }
        else resetParentLinkToNode (parent, delete, left); // здесь left может быть null
                                          // (К правой ветви left в данном случае цеплять нечего.)
        size --;
        delete.parent = delete.leftHand = delete.rightHand = null;
        delete.value = null;//*/

        return _result != null;
    }// remove ()

//---------------------------------- Вспомогательные

// (Вспомогательная. Заменяет однотипные куски кода в remove().)
// Заемняем у родительского нода одну из рук на set. Подходит для удаления любого звена включая root.
    private void resetParentLinkToNode (Node parent, Node delete, Node set)
    {
        if (parent != null)
        {
            if (parent.leftHand == delete)
                parent.leftHand = set;
            else
                parent.rightHand = set;
        }
        else root = set;

        if (set != null)
            set.parent = parent;

    }// resetParentLinkToNode ()

// (Вспомогательная.) Ищет Node по значению.
    private Node findnode (E v)
    {
        checkValue (v);
        Node node = root;
        int cmp;

        while (node != null && ((cmp = v.compareTo (node.value)) != 0))
        {
            node = (cmp > 0) ? node.rightHand : node.leftHand;
        }
        return node;
    }// findnode ()

// Проверяет объект v на пригодность к добавлению в Node в качестве value.
    private static void checkValue (Object v)
    {
        if (v == null)
            throw new InvalidParameterException ("null value is not allowed");
    }// checkValue ()

// (Вспомогательная.) Скатываемся по левому склону ветки до конца (до нода, у которого нет левой руки).
    private Node goDeep (Node node)
    {
        while (node != null && node.leftHand != null)
            node = node.leftHand;

        return node;
    }// goDeep ()

//-------------------------------- Итераторы

// Умолчальный итератор. Сортирует элементы по возрастанию.
    @Override public Iterator<E> iterator ()
    {
        return new Iterator<E> () {

            Node walker = goDeep (root);

            @Override public boolean hasNext ()
            {
                return walker != null;
            }

            @Override public E next ()
            {
                Node tmp = walker;
                E result = walker.value;

                while (walker != null) // walker == null означает, что этот вызов next() последний, result -- последний элемент
                {
                    if (walker.leftHand != null) // левая рука есть
                    {
                    // левая рука есть, значит мы поднялись сюда, а не скатились в результате вызова goDeep()
                    // (определяем, как и когда мы сюда поднялись:
                    //     (1) если поднялись по левой руке:
                    //         а) поднялись на этом вызове next() -- остаямся;
                    //         б) поднялись на предыдущем вызове next() -- спускаемся по правой руке);
                    //     (2) если поднялись по правой руке:
                    //         а) поднимаемся выше (мы здесь уже останавливались.)
                    //
                        if (tmp == walker.leftHand) // 1а -- остаёмся
                            break;
                        if (tmp == walker.rightHand) // 2а -- поднимаемся
                        {
                            tmp = walker;
                            walker = walker.parent;
                        }
                        else if (tmp == walker) // 1б -- пробуем спуститься по правой руке
                        {
                            if (walker.rightHand != null)
                            { // если есть правая рука, то переходим на неё и сразу скатываемся по её левому склону
                                walker = goDeep (walker.rightHand);
                                break;
                            }
                            else walker = walker.parent; // правой руки нет, -- поднимаемся выше
                        }
                        else throw new RuntimeException ("ERROR (1) @ Iterator.next().");
                    }
                    else //левой руки нет
                    if (walker.rightHand == null) // правой тоже нет, -- поднимаемся
                    {
                        walker = walker.parent;
                    }
                    // левой руки нет, правая рука есть. Это означает одно из двух:
                    //      (1) во время предыдущего вызова next() мы скатились сюда в результате вызова goDepp().
                    //          Теперь мы должны перейти на правую руку, скатиться по её левому склону и только
                    //          тогда остановиться;
                    //      (2) мы в процессе подъёма, -- мы ранее останавливались тут и теперь просто возвращаемся.
                    //
                    else if (tmp == walker) // (1)
                    {
                        walker = goDeep (walker.rightHand);
                        break;
                    }
                    else if (tmp == walker.rightHand) // (2)
                    {
                        tmp = walker;
                        walker = walker.parent;
                    }
                    else throw new RuntimeException ("ERROR (2) @ Iterator.next().");
                }//while

                return result;
            }// next ()
        };
    }// iterator ()

//------------------------------- Остальные

    @Override public boolean isEmpty () {   return size () == 0;   }
    @Override public int size ()        {   return size;   }
    @Override public void display ()    {   System.out.println ("  TreeClass:\n  " + this + " < in order");   }

    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder ("[");
        int i = 0;
        for (E v : this)
        {
            sb.append (v.toString ());
            if (i++ < size()-1)
                sb.append (", ");
        }
        sb.append ("]");
        return sb.toString ();
    }// toString ()

    public E getRootValue ()   {   return root.value;   }

    public boolean isBalanced ()    {   return isBalanced (root);   }

//------------------------------- Позаимствованные

    private boolean isBalanced (Node node)
    {
        return (node == null) ||
                isBalanced (node.leftHand) &&
                isBalanced (node.rightHand) &&
                Math.abs (height (node.leftHand) - height (node.rightHand)) <= 1;
    }
    private int height (Node node)
    {
        return node == null ? 0
                            : 1 + Math.max (height (node.leftHand), height (node.rightHand));
    }

}// class TreeClass
