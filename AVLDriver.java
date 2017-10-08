//Assignment satisfies extra credits;
import java.util.Scanner;

public class AVLDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree avl = new AVLTree();
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String[] stringlist = scanner.nextLine().split(" ");
			if (stringlist[0].equals("insert")){
				avl.insert(Integer.parseInt(stringlist[1]));
			}
			else if (stringlist[0].equals("delete")){
				avl.delete(Integer.parseInt(stringlist[1]));
			}
			else if (stringlist[0].equals("search")){
				avl.search(Integer.parseInt(stringlist[1]));
			}
			else if (stringlist[0].equals("ptraverse")){
				avl.traversePreOrder();
				System.out.println();
			}
			else if (stringlist[0].equals("itraverse")){
				avl.traverseInOrder();
				System.out.println();
			}
			else if (stringlist[0].equals("ptraverse")){
				avl.traversePostOrder();
				System.out.println();
			}
			else if (stringlist[0].equals("exit")){
				avl.exit();
			}
		}
		scanner.close();

	}

}
