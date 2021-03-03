package ru.geekbrains.AntonovDV.algorithms.lesson7;

import java.util.*;

public class GraphClass implements GraphInterface
{
    static final boolean STRICT_CHECK = true, SOFT_CHECK = !STRICT_CHECK,
                         VISITED = true, NOT_VISITED = !VISITED;

    private final Map<String, Vertex> vertices; // список будет упорядоченным (Linked…), чтобы иметь возможность использовать индексы.
    private final int sizemax;
    private static final int SIZEMIN = 1;
    //      Матрица как таковая отсутствует, но в каждом Vertex есть одномерный массив размером sizemax.
    //      Этот массив хранит ссылки на Vertex'ы, с которыми связан Vertex-объект.


    private class Vertex
    {
        String name = null;
        boolean visited = NOT_VISITED;
        int index = -1;
        Vertex[] friendlist;
        Vertex prev; //< только для ДЗ

        Vertex (String lable, int indx)
        {
            name = lable;
            friendlist = new Vertex [sizemax];
            Arrays.fill (friendlist, null);
            index = indx;
            prev = null;
        }

        @Override public String toString () {  return String.format ("(%s|%d|%c)", name, index, ((visited)?'v':'-'));  }
        @Override public int hashCode () {   return Objects.hash (name);   }
        @Override public boolean equals (Object o)
        {
            if (!(o instanceof Vertex)) return false;
            Vertex v = (Vertex) o;
            return this.name.equals (v.name);
        }
    }// class Vertex


    public GraphClass (int grsize)
    {
        if (grsize < SIZEMIN)
            throw new IllegalArgumentException (
                String.format ("ERROR @ GraphClass(%d) call : graph size must be >= %d", grsize, SIZEMIN));

        sizemax = grsize;
        vertices = new LinkedHashMap<> (sizemax);

    }// GraphClass ()


// (Вспомогательная.) Проверка корректности имени для будущей вершины.
    private boolean isLableValid (String lable, boolean throwexeption)
    {
        boolean boolOk = lable != null && !lable.isEmpty();

        if (!boolOk && throwexeption == STRICT_CHECK)
            throw new IllegalArgumentException ("ERROR @ isLableValid ("+lable+") call.");

        return boolOk;
    }// isLableValid ()


// (Возвращаем true, если вершину удалось добавить или она уже есть в vertices.)
    @Override public boolean addVertex (String label)
    {
        boolean boolOk = false;
        int size = getSize();

        if (size < sizemax && isLableValid (label, SOFT_CHECK))
        {
            if (!vertices.containsKey (label)) // проверка, чтобы не затереть вершину с её настройками
            {
                Vertex v = new Vertex (label, size);
                if (vertices.put (label, v) != null)
                    throw new RuntimeException ("ERROR @ addVertex ("+label+") call : Map.put() returned not null.");
            }
            boolOk = true;
        }
        return boolOk;
    }// addVertex ()


// (Возвращаем true, если ребро удалось добавить или оно уже «прописано» в matrix.)
    @Override public boolean addEdge (String from, String to)
    {
        boolean boolOk = false;

        if (isLableValid (from, SOFT_CHECK) && isLableValid (to, SOFT_CHECK))
        {
            Vertex vfrom = vertices.get (from),
                   vto = vertices.get (to);

            if (vfrom != null && vto != null)
            {
                vfrom.friendlist [vto.index] = vto;
                vto.friendlist [vfrom.index] = vfrom;
                //vto.prev = vfrom;   //< только для ДЗ
                boolOk = true;
            }
        }
        return boolOk;
    }// addEdge ()


// Обход графа вширину (Breadth-First Search).
// (Вариант bfs2() отличается от bfs() отлько вызовом searchBreadth2() вместо searchBreadth().)
    @Override public void bfs2 (String start, String end)   //< вариант для ДЗ
    {
        isLableValid (start, STRICT_CHECK);
        Vertex vstart = vertices.get (start),
               vend = vertices.get (end);

        if (vstart != null && vend != null)
        {
            unvisitAllVertices2 ();
            searchBreadth2 (vstart, vend);
            unvisitAllVertices2 ();
        }
    }// bfs2 ()

// (Вспомогательный метод обхода графа вширину, который вызывается из bfs().)
    private void searchBreadth2 (Vertex vstart, Vertex vend)   //< вариант для ДЗ
    {
        Queue<Vertex> qv = new LinkedList<>();

        if (treatVertexBfs2 (vstart, qv, vend))
            return;

        while (!qv.isEmpty())
        {
            Vertex v = qv.remove();
            for (Vertex friend : v.friendlist)
            {
                if (friend != null && friend.visited == NOT_VISITED)
                {
                    friend.prev = v;
                    if (treatVertexBfs2 (friend, qv, vend))
                        return;
                }
            }
        }
    }// searchBreadth2 ()

// (Вспомогательный метод. Проверяем, не достигнуто ли условие задания, и, если достигнуто, то
// выполняем соответствующую обработку результата.)
    private boolean treatVertexBfs2 (final Vertex vstart, Queue<Vertex> qv, final Vertex vend)   //< вариант для ДЗ
    {
        boolean boolFound = false;
        qv.add (vstart);
        vstart.visited = VISITED;

        Vertex v = null;
        if (vstart == vend)    v = vstart; //< если мы пытаемся ехать из Воронежа в Воронеж
        else
        {
    //Нам будет трудно вспомнить, по какой дороге мы попали в Воронеж, поэтому приходится
    //высматривать его издалека (из предшествующей ему вершины):
            for (Vertex friend : vstart.friendlist)
            {
                if (friend != null  &&  friend.name.equals (vend.name))
                {
                    v = friend;    break;
                }
            }
        }
    //Если Воронеж уже виден, то возвращаемся, составляя по дороге строку маршрута:
        if (v != null)
        {
            boolFound = true;
            goHome (vstart, vend); //< сбор информации о проделанном маршруте и вывод её на экран
        }
        return boolFound;
    }// treatVertexBfs ()

// (Вспомогательный метод. Выводим на экран маршрут, который удовлетворяет условию задания.)
    private void goHome (Vertex vstart, Vertex vend)   //< вариант для ДЗ
    {
            Stack<Vertex> stack = new Stack<>(); // используем стек, чтобы соблюсти порядок следования вершин
            Vertex v = vstart;

            stack.push (vend);
            if (vstart != vend) //< снова проверяем, что мы стартовали не из Воронежа
            {
                do
                {   stack.push (v);
                    v = v.prev;
                }
                while (v != null);
            }
            StringBuilder sb = new StringBuilder ("Кратчайшее расстояние от ")
                                    .append (stack.peek().name)
                                    .append (" до ")
                                    .append (vend.name)
                                    .append (" : \n");

            for (int i=0, n=stack.size();   i<n;   i++)
            {
                sb.append (stack.pop());
                if (i < n-1)
                    sb.append(" -> ");
            }
            System.out.println (sb);
    }// roundTrip ()

// (Вспомогательный метод; сбрасывает атрибут Vertex.visited у всех вершин графа.)
    private void unvisitAllVertices2 ()   //< вариант для ДЗ
    {
        for (Map.Entry<String, Vertex> entry : vertices.entrySet())
        {
            Vertex v = entry.getValue();
            v.visited = NOT_VISITED;
            v.prev = null;
        }
    }// unvisitAllVertices2 ()


//-------------------- Нижеследующие методы не имеют отношение к ДЗ ---------------------


    @Override public int addEdges (String from, String ... toothers)
    {
        int counter = 0;
        if (toothers != null && isLableValid (from, SOFT_CHECK))
        {
            for (String to : toothers)
                if (addEdge (from, to))
                    counter ++;
        }
        return counter;
    }// addEdges ()

// Обход графа вглубину (Depth-First Search).
    @Override public void dfs (String start)
    {
        isLableValid (start, STRICT_CHECK);
        Vertex v = vertices.get (start);
        if (v != null)
        {
            unvisitAllVertices ();
            searchDeep (v);
            unvisitAllVertices ();
        }
    }// dfs ()

// (Вспомогательный рекурсивный метод обхода графа вглубину, который вызывается из dfs().)
    private void searchDeep (Vertex v)
    {
        if (v.visited == VISITED)
            throw new RuntimeException ("ERROR @ doDFS("+v+") call : vertex is marked 'visited'.");

        System.out.print (v+" "); //< для демонстрации результата
        v.visited = VISITED;

        for (Vertex friend : v.friendlist)
            if (friend != null && friend.visited == NOT_VISITED)
                searchDeep (friend);
    }// searchDeep ()

// Обход графа вширину (Breadth-First Search).
    @Override public void bfs (String start)
    {
        isLableValid (start, STRICT_CHECK);
        Vertex v = vertices.get (start);
        if (v != null)
        {
            unvisitAllVertices ();
            searchBreadth (v);
            unvisitAllVertices ();
        }
    }// bfs ()

// (Вспомогательный метод обхода графа вширину, который вызывается из bfs().)
    private void searchBreadth (Vertex start)
    {
        Queue<Vertex> qv = new LinkedList<>();

        treatVertexBfs (start, qv);

        while (!qv.isEmpty())
        {
            Vertex v = qv.remove();
            System.out.print (v + " "); //< для демонстрации результата

            for (Vertex friend : v.friendlist)
            {
                if (friend != null && friend.visited == NOT_VISITED)
                    treatVertexBfs (friend, qv);
            }
        }
    }// searchBreadth ()

// (Вспомогательный метод.) Вынесли пару строк в отдельный метод не ради экономии, а по той причине, что
//  в bfs-обходе вершины обрабатывать желательно сразу, как только до них дотягиваемся, чтобы исключить
//  многократную обработку одной и той же вершины в «петлях».
    private void treatVertexBfs (Vertex v, Queue<Vertex> qv)
    {
        qv.add (v);
        v.visited = VISITED;
    }// treatVertexBfs ()


// (Вспомогательный метод; сбрасывает атрибут Vertex.visited у всех вершин графа.)
    private void unvisitAllVertices ()
    {
        for (Map.Entry<String, Vertex> entry : vertices.entrySet())
            entry.getValue().visited = NOT_VISITED;
    }// unvisitAllVerteces ()

// (Вспомогательный метод; обнуляет у всех вершин графа список связей friendlist.)
    public void unlinkAllVertices ()
    {
        for (Map.Entry<String, Vertex> entry : vertices.entrySet())
        {
            Vertex[] ff = entry.getValue().friendlist;
            for (int i=0, n=ff.length;    i<n;    i++)
                ff[i] = null;
        }
    }// unlinkAllVertices ()

    @Override public int getSize () {   return vertices.size();   }

    @Override public void display ()  {   System.out.println (this);   }

    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder ("\nРазмер графа ").append(getSize()).append(" вершин.\n");
        for (Map.Entry<String, Vertex> entry : vertices.entrySet())
        {
            Vertex vh = entry.getValue();
            sb.append (vh).append (":\n");

            for (Vertex friend : vh.friendlist)
                if (friend != null)
                    sb.append ("\t-> ")
                      .append (friend.name)
                      .append('\n');
        }
        return sb.toString ();
    }// toString ()

}// class GraphClass



