import java.util.*;

public class AutoDrinkCheck extends InventoryCheck {
    public List<String> availableList = new ArrayList<String>();

    public void AvailableDrinkType() {
        AlcoholList();
        MixerList();
        JuiceList();
        MixedDrink();

        boolean finish = true;

        Scanner scan = new Scanner(System.in);
        String fuck = "";
        System.out.println("What mixers/alcohol do you have? If you finished, enter END:");
        while (finish) {
            fuck = scan.nextLine();
            if (fuck.equals("END")) {
                finish = false;
            } else {
                availableList.add(fuck);
            }
        }
        System.out.println("The mixers/alcohol you have today is: ");
        for (int i = 0; i < availableList.size(); i++) {
            System.out.print(availableList.get(i) + " ");
        }
        System.out.println();

        // for(int x = 0; x < availableList.size(); x++)
        // {
        // drink.values().contains(availableList.get(x));
        // }
    }
}