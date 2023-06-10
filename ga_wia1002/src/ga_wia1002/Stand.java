package ga_wia1002;

public class Stand {
    private String name;
    private String standUser;
    private String destructivePower;
    private String speed;
    private String range;
    private String stamina;
    private String precision;
    private String developmentPotential;

    public Stand(String name, String standUser, String destructivePower, String speed, String range, String stamina,
                 String precision, String developmentPotential) {
        this.name = name;
        this.standUser = standUser;
        this.destructivePower = destructivePower;
        this.speed = speed;
        this.range = range;
        this.stamina = stamina;
        this.precision = precision;
        this.developmentPotential = developmentPotential;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandUser() {
        return standUser;
    }

    public void setStandUser(String standUser) {
        this.standUser = standUser;
    }

    public String getDestructivePower() {
        return destructivePower;
    }

    public void setDestructivePower(String destructivePower) {
        this.destructivePower = destructivePower;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getStamina() {
        return stamina;
    }

    public void setStamina(String stamina) {
        this.stamina = stamina;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getDevelopmentPotential() {
        return developmentPotential;
    }

    public void setDevelopmentPotential(String developmentPotential) {
        this.developmentPotential = developmentPotential;
    }
}
