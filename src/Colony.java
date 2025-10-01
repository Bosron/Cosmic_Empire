package src;

public class Colony {
    private String name = "";
    private int population = 0;
    private String government = "";
    private String purpose = "";
    private String type = "";
    private String celestialBody = "";

    public Colony(String name, int population, String government, String purpose, String type, String CelestialBody) {
        setName(name);
        setPopulation(population);
        setGovernment(government);
        setPurpose(purpose);
        setType(type);
        setCelestialBody(CelestialBody);
    }

    public Colony(){
        this("", 0, "", "", "", "");
    }

    public Colony(String values){
        this(values.split("\t")[0], Integer.parseInt(values.split("\t")[1]), values.split("\t")[2], values.split("\t")[3], values.split("\t")[4], values.split("\t")[5]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCelestialBody() {
        return celestialBody;
    }

    public void setCelestialBody(String celestialBody) {
        this.celestialBody = celestialBody;
    }

    @Override
    public String toString(){
        return getName() + " " + getPopulation() + " " + getGovernment() + " " + getPurpose() + " " + getType() + " " + getCelestialBody();
    }
}