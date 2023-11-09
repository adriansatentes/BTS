import java.util.Scanner;
import java.util.Random;
//Author: Satentes, Adrian Marlowe Q.
public class PerformanceAnalysis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the dataset: ");
        int size = scanner.nextInt();

        // Create large datasets with random values
        int[] values = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt(1000000); // Adjust the range as needed
        }
         //Author: Satentes, Adrian Marlowe Q.
        // Create Binary Tree and Binary Search Tree
        BST binarySearchTree = new BST();
        BinaryTree binaryTree = new BinaryTree();

        // Insertion performance analysis
        long startTime = System.nanoTime();
        for (int value : values) {
            binarySearchTree.add(value);
        }
        long endTime = System.nanoTime();
        long insertionTimeBST = endTime - startTime;
        
        startTime = System.nanoTime();
        for (int value : values) {
            binaryTree.add(value);
        }
        endTime = System.nanoTime();
        long insertionTimeBT = endTime - startTime;

        // Deletion performance analysis
        int deleteValue = values[random.nextInt(size)]; // Randomly select a value to delete

        startTime = System.nanoTime();
        binarySearchTree.delete(deleteValue);
        endTime = System.nanoTime();
        long deletionTimeBST = endTime - startTime;

        startTime = System.nanoTime();
        binaryTree.delete(deleteValue);
        endTime = System.nanoTime();
        long deletionTimeBT = endTime - startTime;

        // Searching performance analysis
        int searchValue = values[random.nextInt(size)]; // Randomly select a value to search for

        startTime = System.nanoTime();
        binarySearchTree.search(searchValue);
        endTime = System.nanoTime();
        long searchTimeBST = endTime - startTime;

        startTime = System.nanoTime();
        binaryTree.search(searchValue);
        endTime = System.nanoTime();
        long searchTimeBT = endTime - startTime;

        // Display the performance results
        System.out.println("Author: Adrian Marlowe Q. Satentes");
        System.out.println("Binary Search Tree (BST) Performance Analysis:");
        System.out.println("Insertion Time: " + insertionTimeBST + " nanoseconds");
        System.out.println("Deletion Time: " + deletionTimeBST + " nanoseconds");
        System.out.println("Search Time: " + searchTimeBST + " nanoseconds");

        System.out.println("\nBinary Tree (BT) Performance Analysis:");
        System.out.println("Insertion Time: " + insertionTimeBT + " nanoseconds");
        System.out.println("Deletion Time: " + deletionTimeBT + " nanoseconds");
        System.out.println("Search Time: " + searchTimeBT + " nanoseconds");
    }
}
