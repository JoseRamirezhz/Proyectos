package sv.edu.utec.appsupermercadosprecios.modelos;

public class Productos {
    private String cod_producto;
    private String nom_producto;
    private String desc_producto;
    private String precio;
    private String foto;

    public Productos(String cod_producto, String nom_producto, String desc_producto, String precio, String foto) {
        this.cod_producto = cod_producto;
        this.nom_producto = nom_producto;
        this.desc_producto = desc_producto;
        this.precio = precio;
        this.foto = foto;
    }

    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }

    public String getDesc_producto() {
        return desc_producto;
    }

    public void setDesc_producto(String desc_producto) {
        this.desc_producto = desc_producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public  Productos(){

    }
}
