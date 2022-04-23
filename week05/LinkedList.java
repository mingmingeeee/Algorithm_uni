public class LinkedList<T> {
    public Node<T> head;
    public int size = 0;

    public LinkedList(){ // 생성자
        head = null;
        size = 0;
    }

    public void addFirst(T item){
        Node<T> newNode = new Node<T>(item);

        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addAfter(Node<T> before, T item){
        Node<T> temp = new Node<T>(item);
        temp.next = before.next;
        before.next = temp;
        size++;
    }

    public T removeFirst(){
        if(head == null)
            return null;

        T temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    // 연결리스트에서는 이전 노드의 주소를 알아야 삭제할 수 있다
    public T removeAfter(Node<T> before){
        if(before.next == null)
            return null;

        T temp = before.next.data;
        before.next = before.next.next;
        size--;
        return temp;
    }

    public int search(T item){
        Node<T> p = head;
        int index = 0;

        while(p!=null){
            if(p.data.equals(item))
                return index;
            p = p.next;
            index++;
        }
        return -1;
    }

    public Node<T> getNode(int index){
        if(index < 0 || index >= size)
            return null;
        Node<T> p = head;
        for(int i=0; i<index; i++)
            p = p.next;
        return p;
    }

    public T get(int index){
        if(index < 0 || index >= size)
            return null;
        return getNode(index).data;
    }

    // index번째 위치에 새로운 데이터 삽입
    // addAfter가 더 쉽기 때문에 index-1 뒤에 넣는 식으로
    public void add(int index, T item){
        if(index < 0 || index >= size)
            return;
        if(index == 0)
            addFirst(item);
        else{
            Node<T> node = getNode(index-1);
            addAfter(node, item);
        }
    }

    // index번째 노드 삭제, 그 노드에 저장된 데이터 반환
    public T remove(int index){
        if(index < 0 || index >= size)
            return null;
        if(index == 0)
            return removeFirst();
        Node<T> prev = getNode(index-1);
        return removeAfter(prev);
    }

    // data값을 주고 이 data를 저장하고 있는 Node 삭제
    // 검색 후 삭제
    public T remove(T item){
        Node<T> p = head;
        Node<T> q = null;
        while(p!=null && !p.data.equals(item)){
            q = p;
            p = p.next;
        }
        if(p==null)
            return null;
        if(q==null)
            return removeFirst();
        else
            return removeAfter(q);
    }

    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<String>();

    }


}
