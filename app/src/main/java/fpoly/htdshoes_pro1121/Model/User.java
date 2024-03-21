package fpoly.htdshoes_pro1121.Model;

public class User {
    private int maAdmin;
    private String name;
    private String matkhau;
    private int role;

    public User(int maAdmin, String name, String matkhau, int role) {
        this.maAdmin = maAdmin;
        this.name = name;
        this.matkhau = matkhau;
        this.role = role;
    }

    public User(String name, String matkhau, int role) {
        this.name = name;
        this.matkhau = matkhau;
        this.role = role;
    }

    public User(String name, String matkhau) {
        this.name = name;
        this.matkhau = matkhau;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}

