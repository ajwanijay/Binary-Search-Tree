/**
 * Created by Jay on 3/6/17.
 */
public class Node {


        public int data;
        public Node Left;
        public Node Right;

        private Node() {}

        public Node(int data){this.data = data;}

    }


     class BST{

        private Node root;

        public BST(){
            root = null;
            Initialize();
        }

        private void Initialize(){
            root = new Node(8);
            root.Left = new Node(3);
            root.Left.Left = new Node(1);
            root.Left.Right = new Node(6);
            root.Left.Right.Left = new Node(4);
            root.Left.Right.Right = new Node(7);

            root.Right = new Node(10);
            root.Right.Right = new Node(14);
            root.Right.Right.Left = new Node(13);

        }


        public void Inorder(){
            Inorder(root);
        }

        public void Inorder(Node node){
            if(node != null){
                Inorder(node.Left);
                System.out.print(node.data + " ,");
                Inorder(node.Right);
            }
        }
//        --------------------------------------------------------

        public void AddAllGreaterValuetoANode(){
            int[] sum =new int[1];
            sum[0] = 0;
            AddAGVN(root,  sum);

        }

        private void AddAGVN(Node node, int[] sum) {
            if (node == null)
                return;

                AddAGVN(node.Right, sum);
                sum[0] = sum[0] + node.data;
                node.data = sum[0];
                AddAGVN(node.Left, sum);
        }
//-------------------------------------------------------------------

        public void CreateGreaterSumTree(){
            int[] sum = new int[1];
            sum[0]=0;
            CreateGST(root, sum);
        }

        private void CreateGST(Node node, int[] sum){
            if(node==null)
                return;

            CreateGST(node.Right, sum);
            sum[0] += node.data;
            node.data=sum[0] - node.data;
            CreateGST(node.Left, sum);
        }

//        -----------------------------------------------------------------
        public void FindKLargest(int k){
            if(k <=0)
                return;
            int[] count = new int[1];
            count[0]=0;
            FindKL(root, count, k);

        }
        private void FindKL(Node node, int[] count, int k){
            if(node!=null) {
                if (k <= 0)
                    return;

                FindKL(node.Right, count, k);
                count[0]++;
                if(count[0]==k){
                    System.out.println("Kth largest is : "  + node.data);
                    return;
                }

                FindKL(node.Left, count, k);
            }
        }

//        ------------------------------------------

        public int CountNodesInRange(int low, int high){
            return CountNIR(root, low, high);
        }

        private int CountNIR(Node node, int low, int high){
            if(node==null)
                return 0;

            // improving effciency
            if(node.data == low && node.data==high)
                return 1;

            //if node is in range , then include it in count
            if(node.data >= low && node.data <= high)
                return 1+ CountNIR(node.Left, low, high) + CountNIR(node.Right, low, high);

            // if node is small than range
            else if (node.data <=low)
                return CountNIR(node.Right, low , high);
            // if node is large than range
            else  return CountNIR(node.Left, low, high);

        }

//        ----------------------------------------

        public int GetCount(){
            return GetC(root);

        }
        private int GetC(Node node){
            if(node==null)
                return 0;
            return 1+ GetC(node.Left) + GetC(node.Right);
        }

        public void ArrToBST(Node node , int[] arr, int[] ptr){

            if (root==null)
                return;

            ArrToBST(node.Left, arr, ptr);

            node.data = ptr[0];
            ptr[0]++;

            ArrToBST(node.Right, arr, ptr);
        }

        public void StoreValueinArray(Node node , int[] arr, int[] ptr){

            if (node==null)
                return;

            StoreValueinArray(node.Left, arr,ptr);

            arr[ptr[0]] = node.data;
            ptr[0]++;

            StoreValueinArray(node.Right, arr, ptr);

        }

        public void ConvertBTtoBST(){
            if(root==null)
                return;

            int count = GetCount();
            int[] arr = new int[count];
            int[] ptr = new int[1];
            ptr[0]=0;

            StoreValueinArray(root, arr, ptr);

            java.util.Arrays.sort(arr);

            ptr[0]=0;

            ArrToBST(root,arr, ptr);


        }

        public void FindPairSumEqualX(int x){
            if(root == null || (root.Left ==null && root.Right==null))
                return;

            int count = GetCount();
            int[] arr = new int[count];
            int[] ptr = new int[1];
            ptr[0] = 0;

            StoreValueinArray(root, arr, ptr);

            int start = 0;
            int end = arr.length-1;

            while(start<end){
                if (arr[start]+arr[end] ==x){
                    System.out.println("Found pair " + arr[start] + " , " + arr[end]);
                    return;

                }

                else if(arr[start]+arr[end] <x){
                    start++;
                }
                else
                    end--;
            }

            System.out.println("no pair found");


        }

        public Node Ceiling(int key){
            return CeilingC(root, key);
        }

        private Node CeilingC(Node node, int key){
            if (node==null)
                return null;
            if (node.data==key)
                return node;

            else if (node.data < key){
                return CeilingC(node.Right, key);
            }
            else
                return CeilingC(node.Left, key);

        }

//        -----------------------------


         private int[] MergeArrays(int[] arr1, int[] arr2){

            int[] Merged = new int[arr1.length + arr2.length];
            int i=0, j=0,k=0;

            while (i <arr1.length && j< arr2.length){
                if(arr1[i] < arr2[j]){
                    Merged[k]= arr1[i];
                    k++;
                    i++;
                }
                else {
                    Merged[k]=arr2[j];
                    k++;
                    j++;
                }
            }

                while (i<arr1.length){
                    k++;
                    i++;

                }
                while (j<arr2.length){
                    k++;
                    j++;
                }
                return Merged;
            }

         




        public static void main(String args[]){
        BST tree = new BST();
//          tree.Inorder();
//            System.out.println();
//        tree.AddAllGreaterValuetoANode();
//            tree.CreateGreaterSumTree();
//            tree.Inorder();
//        tree.FindKLargest(3);
//            tree.ConvertBTtoBST();
//        tree.FindPairSumEqualX(10);
            tree.Ceiling(5);
        }

}
