package shop.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Supplier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String physicalAdress;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> products;
   
    public Supplier(){
        this.products = new ArrayList<>();
    }

    public Supplier(String name, String physicalAdress){
        this.name = name;
        this.physicalAdress = physicalAdress;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public Product getProduct(Integer id){
        if(products.size() < id || id < 0){return null;}
        else{return products.get(id);}
    }

    public void delProduct(Integer id){
        if(!(products.size() < id || id<0)){
            products.remove(id.intValue());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicalAdress() {
        return physicalAdress;
    }

    public void setPhysicalAdress(String physicalAdress) {
        this.physicalAdress = physicalAdress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    
}
