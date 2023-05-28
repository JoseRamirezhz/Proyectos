package sv.edu.utec.appsupermercadosprecios;

public class modelo_productos {
    private String NombreProducto;
    private int ImgProducto;
    private String PrecioProducto;

    private String DescripcionProduct;

    private String medida;


    public String getNombreProducto() {
        return NombreProducto;
    }

    public int getImgProducto() {
        return ImgProducto;
    }



    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public void setImgProducto(int imgProducto) {
        ImgProducto = imgProducto;
    }


    public String getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        PrecioProducto = precioProducto;
    }

    public String getDescripcionProduct() {
        return DescripcionProduct;
    }

    public void setDescripcionProduct(String descripcionProduct) {
        DescripcionProduct = descripcionProduct;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
}
