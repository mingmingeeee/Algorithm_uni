// 이진트리 표현
// -> Linked List
// 왼쪽 자식 주소 | data | 부모 노드 주소 | 오른쪽 자식 주소
// 이런 식으로 이루어짐

// Tree 알고리즘
// 1. 이진 트리의 순회

// 1-1. inorder 순회
// -> recursive함.
// 트리를 세 등분으로, 왼쪽 inorder 그리고 오른쪽 inorder...
// Left -> Root -> Right
// ... 수도 코드 >
// Inorder_tree_walk(x)
//  if x!= null
//    then Inorder_tree_walk(left[x]) // 왼쪽 자식
//         print key[x] // data
//         Inorder_tree_walk(right[x]) // 그 후 오른쪽 자식

// 1-2. preorder 순회
// Root -> Left -> Right
// Preorder_tree_walk(x)
//  if x!= null
//    then print key[x] // root
//         Pre_order_tree_walk(left[x])
//         Pre_order_tree_walk(right[x])

// 1-3. postorder 순회
// Left -> Right -> Root
//  if x!= null
//    then Post_order_tree_walk(left[x])
//         Post_order_tree_walk(right[x])
//         print key[x]

// 1-4. level-order 순회
// level순으로 방문
// -> queue 이용하여 구현
// Level_order_tree_traversal()
//  visit the root;
//  Q <- root; // Q is a queue
//  while Q is not empty do
//      v <- dequeue(Q);
//      visit children of v;
//      enqueue children of v into Q;
//  end
// end

// * Dynamic Set *
// 여러 개의 키를 저장
// 1. Insert
// 2. Search
// 3. Delete
// -> 이러한 연산들을 지원하는 자료구조

// 1. Binary Search Tree (이진검색트리)
// Red-Black, AVL: 이진 탐색 트리를 확장한 것
// Insert, Search, Delete 연산은 트리의 높이에 비례하는 시간 복잡도 가짐
// -> "BST" (이진검색트리)

// heap VS BST
// heap은 complete binary tree여야 함, max-heap이어야 한다
// -> 부모가 자식보다는 커야한다
// BST는 그 반대임
// complete binary tree가 아니어도 되고 max-heap이 아닌
// 본인 왼쪽은 다 본인 보다 작아야 하고
// 본인 오른쪽은 다 본인 보다 커야 한다

// 1-1. Search

// * Recursion
// Tree_Search(x, k) // x: root, k: 찾는 값
// if x = null or k = key[x]
//      then return x
// if k < key[x]
//      then return Tree_Search(left[x], k) // k가 key[x]보다 작다면 왼쪽 노드들
// else return Tree_Search(right[x], k) // k가 key[x]보다 크다면 오른쪽 노드들

// * 반복문
// Iterative_Tree_Search(x, k)
//  while x != null and k != key[x]
//      do if k < key[x]
//          then x <- left[x]
//          else x <- right[x]
//  return x

// 시간 복잡도: O(h) -> h는 트리의 높이
// 찾을 때마다 다음 레벨로 가니까

// * 최소값 찾기 알고리즘
// 이건 왼쪽 자식에 분명히 있으니까
// 거기서 더 내려갈 수 없으면 그게 최소값임
// Tree_Minimum(x)
//   while left[x] != null
//      do x <- left[x]
//   return x

// * 최대값 찾기 알고리즘
// Tree_maximum(x)
//   while right[x] != null
//     do x <- right[x]
//   return x

// * Successor
// 노드 x의 successor란?
// key[x]보다 크면서 가장 작은 키를 가진 노드
// successor는 항상 존재하는 게 아님
// 3가지 경우 존재
// 1. 노드 x의 오른쪽 부트리가 존재할 경우, 오른쪽 부트리의 최소 값
// 2. 오른쪽 부트리가 없는 경우
// 부모를 따라 루트까지 올라가면서 처음으로 누군가의 왼쪽 자식이 되는 노드
// 3. 그러한 노드 y가 존재하지 않을 경우 successor가 존재하지 않음
// (즉, x가 최대 값이라는 뜻)
// Tree_successor(x)
//  if right[x] != null // (1) 1번째 케이스
//      then return Tree_Minimum(right[x])
//  y <- p[x] // x의 부모 노드
//  while y!=null and x=right[y] // (2) 2, 3번째 케이스
//  // 부모가 존재하고 내가 부모y의 오른쪽 자식일 경우
//      do x <- y
//          y <- p[y] // 계속 부모-자식 관계 유지
//  return y
//  null이면 null return, 왼쪽으로 가면 왼쪽 부모 return
// 시간 복잡도: O(h)

// * Predecessor
// key[x]보다 작으면서 가장 큰 키를 가진 노드
// Successor와 반대
// 오른쪽/왼쪽만 바꿔주면 됨

// 1-2. Insert
// leaf node에 추가하는 식으로 Insert
// 2개의 포인터 x, y 사용 -> 들어 가야 할 자리가 null일 수 있으니

// Tree_Insert(T, z) // T: Tree, z: insert할 node
//   y <- null // y는 항상 x보다 한 칸 뒤에서 따라가야 함
//   x <- root[T] // x는 루트에서 출발
//   while x!= null // x가 널이라면 y뒤에 새 노드를 insert 가능
//      do y <- x
//        if key[z] <- key[x]
//          then x <- left[x]
//          else x <- right[x]
//   p[z] <- y // y를 z의 부모 노드로 설정
//   if y = null // while문을 한 번도 실행하지 않았다는 뜻 (내려간 적이 없으니)
//               // == 원래 tree가 empty tree였다는 뜻
//      then root[T] <- z // z가 root
//   else if key[z] < key[y] // z가 부모보다 작으면
//      then left[y] <- z
//      else right[y] <- z
// 시간 복잡도: O(h)

// 1-3. Delete

// Case 1. 자식 노드가 없는 경우
// 그냥 삭제... leaf node 이므로

// Case 2. 자식 노드가 1개인 경우
// 자신의 자식 노드를 원래 자신의 위치로

// Case 3. 자식 노드가 2개인 경우
// node는 그대로 두고 data만 바꿀 것
// 어떤 data로?? -> 삭제할 data에 근접한 값으로!!
// successor나 predecessor 로
// 예제에서는 successor 찾는 것으로 함
// -> successor는 왼쪽 자식이 없음
// -> 그러므로 Case 1이나 Case 2와 같은 방법으로
// data바꾼 후 해당 node 삭제하면 됨

// * Delete 수도 코드
// Tree_Delete(T, z) // T: Tree, z: 삭제할 노드
// //삭제할 노드 찾은 뒤 실행
// *** 여기서 y는 실제로 삭제할 node ***
//   if left[z]=null or right[z]=null // 자식 노드가 0개이거나 1개
//      then y <- z // y를 실제로 삭제할 node z로 !!!
//   else y <- Tree_Succesor(z) // z의 자식이 2개라면 z의 successor를 y라고!!
//
//  -------------- node y 삭제 코드 --------------
//   if left[y] != null // y의 왼쪽 자식이 있다면?
//      then x <- left[y] // x를 왼쪽 자식으로
//   else x <- right[y] // 오른쪽 자식이 있다면 x를 오른쪽 자식으로
//
//   node y를 삭제하고 x가 올라가면 됨!!
//   --> x의 부모가 y의 부모가 되면 됨
//
//   if x != null // x가 null이 아니라면 (자식이 있다면)
//      then p[x] <- p[y] // x의 부모가 y의 부모가 됨
//
//   if p[y] = null // y의 부모가 null이라면 -> y가 root node라면
//      then root[T] <- x // 자식이 유일하므로 x가 tree의 root가 됨
//   else if y = left[[p[y]] // y가 root가 아니라 부모가 있다면
//          then left[p[y]] <- x // x가 y자리로 올라가는 것 (왼쪽 자식이었을 때)
//   else right[p[y]] <- x // (오른쪽 자식이었을 때)
//  -------------- node y 삭제 코드 --------------
//
//   if y!= z // case 3에 해당하는 -> y가 실제 삭제할 노드인데 이게 z랑 같지 않다면
//      then key[z] <- key[y] // 서로 data값 바꾸기
//       // copy y's satellite data into z
//   return y

// 각종 연산의 시간복잡도: O(h)
// 최악의 경우 트리의 높이: h=O(n)



// 2. Hash Table


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class

Week05 {

    public static Tree root = null;
    public static int size = 0;

    public static void main(String[] args) throws IOException {
        File f = new File("shuffled_dict.txt");

        // 파일 존재하면 읽기
        if (f.exists()) {
            Scanner file_scan = new Scanner(f);

            // 파일 읽고 트리 만들기
            while (file_scan.hasNextLine()) {
                String words = file_scan.nextLine();

                int sep1 = words.indexOf("(");
                int sep2 = words.indexOf(")");

                if (sep1 < 0 || sep2 < 0)
                    return;

                String word = words.substring(0, sep1 - 1);
                String type = words.substring(sep1, sep2 + 1);
                String meaning = words.substring(sep2 + 1);

                BST_insert(new Tree(word, type, meaning));
            }

        }
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("$ ");
            String command = sc.next();

            switch (command) {
                case "size":
                    System.out.println(size);
                    break;
                case "add":
                    System.out.print("Word: ");
                    String word = sc.next();
                    System.out.print("Class: ");
                    String type = sc.next();
                    System.out.print("Meaning: ");
                    String meaning = sc.next();

                    BST_insert(new Tree(word, type, meaning));
                    break;
                case "delete":
                    String delete_word = sc.next();
                    Tree key = BST_search(root, delete_word);
                    BST_delete(key);
                    System.out.println("Deleted successfully.");
                    break;
                case "deleteall":
                    BST_delete_all();
                    System.out.println("1760 words were deleted successfully.");
                    break;
                case "print":
                    print(root);
                    break;
                case "exit":
                    return;
                default:
                    break;
            }
        }
    }

    public static void print(Tree p){
        if(p==null)
            return;
        System.out.printf("Word: %s\n Class: %s\n Meaning: %s\n", p.Word, p.Type, p.Meaning);
        print(p.left);
        print(p.right);
    }

    public static void BST_insert(Tree tree){
        Tree x = root; // x: root에서 출발
        Tree y = null; // y: x보다 한 칸 뒤에서 따라감

        while(x!=null){ // 트리가 끝나기 전 까지
            y = x;
            if(tree.Word.compareTo(x.Word) < 0){
                x = x.left;
            }else{
                x = x.right;
            }
        }

        tree.parent = y;

        if(y==null) // 삽입할 tree가 첫 트리라는 뜻
            root = tree;
        else if(tree.Word.compareTo(y.Word) < 0) // x가 마지막 노드였고 부모인 y보다 작으면 왼쪽 자식이 됨
            y.left = tree;
        else // y보다 크면 오른쪽 자식
            y.right = tree;

        size++;
    }

    public static Tree BST_search(Tree x, String key_data){
        if (x == null || key_data.compareTo(x.Word) == 0)
            return x;
        else if (key_data.compareTo(x.Word) < 0)
            return BST_search(x.left, key_data);
        else
            return BST_search(x.right, key_data);
    }

    public static Tree BST_minimum(Tree tree){
        if(tree.left==null)
            return tree;
        else
            return BST_minimum(tree.left);
    }

    // successor: 나보다 큰데 최소 값
    public static Tree BST_successor(Tree tree){
        if(tree.right!=null) // 오른쪽 서브트리가 있다면
            return BST_minimum(tree.right);

        // 오른쪽 서브트리가 없거나 현 tree가 최대 값인 경우를 위한
       Tree y = tree.parent;
        // 부모가 존재하고 내가 부모의 오른쪽 자식일 경우
        while(y!=null && y.right==tree){ // 위로 올라감
            tree = y; // tree가 y가 되고
            y = y.parent; // y는 올라가는 중이니 y의 parent이 됨
        }
        // 위로 올라가다가 부모가 null이거나 누군가의 왼쪽 자식이 되는 경우 y return
        return y;
    }

    public static Tree BST_delete(Tree target){
        Tree y = null; // 실제로 삭제할 노드 정함
        Tree x = null;
        size--;
        // 삭제할 노드의 자식 노드가 0개이거나 1개라면
        if(target.left == null || target.right == null)
            y = target;
        else // 삭제할 노드의 자식이 2개라면 삭제할 노드의 successor를 y로
            y = BST_successor(target);

        //////////////////////////////////////////
        // target 노드 삭제 -> 이 경우는 현재 자식이 0개이거나 1개인 경우
        // Case 1,2
        if(y.left != null) // y의 왼쪽 자식이 있다면?
            x = y.left;
        else
            x = y.right;

        // node y 삭제하고 x가 올라가면 됨
        if(x!=null)
            x.parent = y.parent;

        if(y.parent == null) // y가 root라면
            root = x;
        else if(y == y.parent.left) // y가 root가 아니라 부모가 있다면 -> 왼쪽 자식
            y.parent.left = x;
        else // 오른쪽 자식이었다면
            y.parent.right = x;
        //////////////////////////////////////////

        // Case 3
        if(y!=target) {// target이 y(처음에 정한)와 같지 않다면
            String tmpw = target.Word, tmpy = target.Type, tmpm = target.Meaning;
            target.Word = y.Word; target.Type = y.Type; target.Meaning = y.Meaning;
            y.Word = tmpw; y.Type = tmpy; y.Meaning = tmpm;

            if(y.parent.left != null) // 왼쪽 자식이라면
                y.parent.left = null;
            else // 오른쪽 자식이었다면
                y.parent.right = null;
        }
        return y;
    }

    public static void BST_delete_all() throws IOException{

        File f = new File("to_be_deleted_words.txt");
        if(f.exists()){
            Scanner file_scan = new Scanner(f);
            while(file_scan.hasNextLine()){
                String word = file_scan.next();
                Tree p = BST_search(root, word);
                if(p!=null)
                    BST_delete(p);
                else
                    System.out.println(word);
            }
        }

    }
}
