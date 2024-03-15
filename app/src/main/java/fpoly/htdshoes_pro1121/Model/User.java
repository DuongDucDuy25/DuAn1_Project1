package fpoly.htdshoes_pro1121.Model;

public class User {
    private int maAdmin;
    private String name;
    private String matkhau;
    private int round;

    public User(int maAdmin, String name, String matkhau, int round) {
        this.maAdmin = maAdmin;
        this.name = name;
        this.matkhau = matkhau;
        this.round = round;
    }

    public User(String name, String matkhau, int round) {
        this.name = name;
        this.matkhau = matkhau;
        this.round = round;
    }

    public User() {
    }

    public int getMaAdmin() {
        return maAdmin;
    }

    public void setMaAdmin(int maAdmin) {
        this.maAdmin = maAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
