import java.util.ArrayList;
import java.util.List;

public class OMC {

    String name;

    List<OMC> isOrbitedBy = new ArrayList<>();
    OMC orbits;

    int orbitalDepth = -1;




    public OMC(String name) {

        this.name = name;

    }

    public void addOrbitingBody(OMC omc) {

        isOrbitedBy.add(omc);

    }

    public void setOrbits(OMC omc) {

        orbits = omc;

    }

    public int computeOrbitalDepth() {

        if (orbitalDepth == -1) {
            if (orbits != null) {
                orbitalDepth = orbits.computeOrbitalDepth() + 1;
            }
            else {
                orbitalDepth = 0;
            }
        }
        return orbitalDepth;

    }

    public void printAllOrbitingBodies() {
        System.out.print(this.name + ": ");
        for (OMC omc : isOrbitedBy) {
            System.out.print(omc.name + " ");
        }
        System.out.println();

    }


}
