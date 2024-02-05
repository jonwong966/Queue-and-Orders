
import java.util.*;

public class InventoryCheck {
    enum drinkType {
        alcohol,
        mixers,
        juice,
        mixed
    }

    public List<String> alcList = new ArrayList<String>();
    public List<String> mixList = new ArrayList<String>();
    public List<String> juiceList = new ArrayList<String>();
    public Hashtable<String, List> drink = new Hashtable<String, List>();

    public void AlcoholList() {
        alcList.add("vodka");
        alcList.add("tequila");
        alcList.add("screwball");
        alcList.add("rumChata");
        alcList.add("dragonberryBacardi");
        alcList.add("malibu");
        alcList.add("gin");
        alcList.add("whiskey");
        alcList.add("soju");

    }

    public void MixerList() {
        mixList.add("grenadine");
        mixList.add("simpleSyrup");
        mixList.add("blueCuracao");
        mixList.add("AngostruaBitters");
        mixList.add("orangeBitters");
        mixList.add("peachSchanpps");
    }

    public void JuiceList() {
        juiceList.add("orange");
        juiceList.add("lemon");
        juiceList.add("lime");
        juiceList.add("cranberry");
        juiceList.add("pineapple");
        juiceList.add("calpico");
        juiceList.add("sprite");
        juiceList.add("gingerAle");
        juiceList.add("yakult");
        juiceList.add("lychee");
        juiceList.add("elderFlower");

    }

    public void MixedDrink() {
        List<String> JCS = new ArrayList<String>();
        JCS.add(alcList.get(8));
        JCS.add(juiceList.get(8));
        drink.put("Jackie's Creamy Surprise", JCS);
    }

    public void AddToInven(String name, drinkType type) {
        if (type == drinkType.alcohol) {
            alcList.add(name);
        } else if (type == drinkType.mixers) {
            mixList.add(name);
        } else if (type == drinkType.juice) {
            juiceList.add(name);
        } else if (type == drinkType.mixed) {

        }
    }

}
