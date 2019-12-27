import java.util.Map;

public class Reaction {

    String descriptorString;

    String[] reagents;
    int[] reagentQuantities;

    String product;
    int productQuantities;

    public Reaction(String descriptorString) {

        this.descriptorString = descriptorString;

        String[] splitDescriptor = descriptorString.split("=");

        //Products
        String[] splitProducts = splitDescriptor[1].split(" ");
        product = splitProducts[2].strip();
        productQuantities = Integer.parseInt(splitProducts[1]);
        //System.out.println("Products: " + product + " " + productQuantities);

        //Reagents
        String[] splitReagents = splitDescriptor[0].split(",");
        reagents = new String[splitReagents.length];
        reagentQuantities = new int[splitReagents.length];

        //System.out.println("Reagents: ");

        for (int i = 0; i < splitReagents.length; i++) {

            String[] quantityAndName = splitReagents[i].strip().split(" ");
            reagentQuantities[i] = Integer.parseInt(quantityAndName[0]);
            reagents[i] = quantityAndName[1].strip();
            //System.out.println(reagents[i] + " " + reagentQuantities[i]);

        }




    }

    public boolean requiredReagentsPresent(Map<String, Long> resources) {

        for (int i = 0; i < reagents.length; i++) {
            if (!(resources.get(reagents[i]) >= reagentQuantities[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isOREReaction() {
        for (String reagent: reagents) {
            if (reagent.equals("ORE")) {
                return true;
            }
        }
        return false;
    }

    public int getProductQuantities() {
        return productQuantities;
    }

    public int[] getReagentQuantities() {
        return reagentQuantities;
    }

    public String getProduct() {
        return product;
    }

    public String[] getReagents() {
        return reagents;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Reaction)) {
            return false;
        }

        Reaction r = (Reaction) o;

        return(r.descriptorString.equals(this.descriptorString));
    }

    @Override
    public int hashCode() {

        return this.descriptorString.hashCode()*this.reagents.length*productQuantities;

    }
}
