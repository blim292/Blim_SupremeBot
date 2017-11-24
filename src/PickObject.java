import java.util.Scanner;

public class PickObject {
	String itemName, itemSize, itemColor;
	String category;
	String name, email, tele, address, apt, city, state, country;
	String zip = "";
	String ccNum, ccMonth, ccYear, ccv;
	
	//	Eventually use GUI for user selection.
	void pickItem() {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Please provide your billing/shipping info below.");
		System.out.print("Name: ");
		name = kb.nextLine();
		System.out.print("Email: ");
		email = kb.nextLine();
		System.out.print("Telephone Number: ");
		tele = kb.nextLine();
		System.out.print("Address: ");
		address = kb.nextLine();
		
		String tempConfirm;
		do {
			System.out.println("Do you have an apartment unit? (Y/N): ");
			tempConfirm = kb.nextLine();
		}
		while (!tempConfirm.equals("Y") && !tempConfirm.equals("N"));
		
		if (tempConfirm.equals("Y")) {
			System.out.print("Apt:");
			apt = kb.nextLine();
		}
		
		System.out.print("ZIP: ");
		zip = kb.nextLine();
		System.out.print("City: ");
		city = kb.nextLine();

		System.out.print("State: ");
		state = kb.nextLine();
		do {
			System.out.print("Country (USA or Canada): ");
			tempConfirm = kb.nextLine();
			tempConfirm = tempConfirm.toUpperCase();
		}
		while (!tempConfirm.equals("USA") && !tempConfirm.equals("CANADA"));
		country = tempConfirm;
		
		System.out.println("Please enter your cc info below.");
		System.out.print("Number: ");
		ccNum = kb.nextLine();
		System.out.print("CCV: ");
		ccv = kb.nextLine();
		System.out.print("CC Month: ");
		ccMonth = kb.nextLine();
		System.out.print("CC Year: ");
		ccYear = kb.nextLine();
		
		
		int userChoice;
		do {
			System.out.println("Pick the number that is associated with the product you are looking for.");
			System.out.println("1. Jackets");
			System.out.println("2. Shirts");
			System.out.println("3. Tops/Sweaters");
			System.out.println("4. Sweatshirts");
			System.out.println("5. Pants");
			System.out.println("6. T-shirts");
			System.out.println("7. Hats");
			System.out.println("8. Accessories");
			System.out.println("9. Skate");
			
			System.out.print("> ");
			userChoice = Integer.parseInt(kb.nextLine());
			System.out.println("You entered " + userChoice);
		}
		while (userChoice < 1 || userChoice > 8);
		

		switch (userChoice) {
		case 1:
			category = "/shop/all/jackets";
			break;
		case 2:
			category = "shop/all/shirts";
			break;
		case 3:
			category = "/shop/all/tops_sweaters";
			break;
		case 4:
			category = "shop/all/sweatshirts";
			break;
		case 5:
			category = "/shop/all/pants";
			break;
		case 6:
			category = "/shop/all/t-shirts";
			break;
		case 7:
			category = "/shop/all/hats";
			break;
		case 8:
			category = "/shop/all/accessories";
			break;
		case 9:
			category = "/shop/all/skate";
			break;
		default:
			System.out.println("Something went wrong with the input.");
			category = "";
			break;
		}	
		
		System.out.println("What is the name of the item you would like to purchase?");
		System.out.print("> ");
		itemName = kb.nextLine();

		String isSize;
		do {
			System.out.println("What size would you like to purchase?");
			System.out.print("> ");
			isSize = kb.nextLine();
		}
		while (!isSize.equals("XS") && !isSize.equals("S") && !isSize.equals("M") && !isSize.equals("L") && !isSize.equals("XL"));
		itemSize = isSize;
		
		System.out.println("What color would you like to purchase?");
		System.out.print("> ");
		itemColor = kb.nextLine();
		kb.close();
	}
	
}
