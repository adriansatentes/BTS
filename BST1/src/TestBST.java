import java.util.Scanner;

public class TestBST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST bst = new BST();
        int[] values = null;

        while (true) {
        	System.out.println("Created by Adrian Marlowe Satentes");
            System.out.println("Menu:");
            System.out.println("1. Create BT and BST with values");
            System.out.println("2. Search for a value");
            System.out.println("3. Find the minimum value in BST");
            System.out.println("4. Find the maximum value in BST");
            System.out.println("5. Find the successor of a value in BST");
            System.out.println("6. Find the predecessor of a value in BST");
            System.out.println("7. Delete a value from BST");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: 	
                    System.out.print("Enter the number of values: ");
                    int numValues = scanner.nextInt();
                    values = new int[numValues];
                    System.out.println("Enter " + numValues + " values:");
                    for (int i = 0; i < numValues; i++) {
                        values[i] = scanner.nextInt();
                    }
                    bst.createBinaryTrees(values);
                    break;
                case 2:
                    if (values != null) {
                        System.out.print("Enter the value to search: ");
                        int searchValue = scanner.nextInt();
                        if (bst.search(searchValue)) {
                            System.out.println(searchValue + " is found in the BST.");
                        } else {
                            System.out.println(searchValue + " is NOT found in the BST.");
                        }
                    } else {
                        System.out.println("Please create the trees first.");
                    }
                    break;
                case 3:
                    if (values != null) {
                        int min = bst.min();
                        System.out.println("Minimum value in BST: " + min);
                    } else {
                        System.out.println("Please create the trees first.");
                    }
                    break;
                case 4:
                    if (values != null) {
                        int max = bst.max();
                        System.out.println("Maximum value in BST: " + max);
                    } else {
                        System.out.println("Please create the trees first.");
                    }
                    break;
                case 5:
                    if (values != null) {
                        System.out.print("Enter the value to find the successor for: ");
                        int successorValue = scanner.nextInt();
                        int successor = bst.successor(successorValue);
                        if (successor != -1) {
                            System.out.println("Successor of " + successorValue + " is: " + successor);
                        } else {
                            System.out.println("No successor found for " + successorValue);
                        }
                    } else {
                        System.out.println("Please create the trees first.");
                    }
                    break;
                case 6:
                    if (values != null) {
                        System.out.print("Enter the value to find the predecessor for: ");
                        int predecessorValue = scanner.nextInt();
                        int predecessor = bst.predecessor(predecessorValue);
                        if (predecessor != -1) {
                            System.out.println("Predecessor of " + predecessorValue + " is: " + predecessor);
                        } else {
                            System.out.println("No predecessor found for " + predecessorValue);
                        }
                    } else {
                        System.out.println("Please create the trees first.");
                    }
                    break;
                case 7:
                    if (values != null) {
                        System.out.print("Enter the value to delete from BST: ");
                        int deleteValue = scanner.nextInt();
                        bst.delete(deleteValue);
                        System.out.println(deleteValue + " deleted from the BST.");
                    } else {
                        System.out.println("Please create the trees first.");
                    }
                    break;
                case 8:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}