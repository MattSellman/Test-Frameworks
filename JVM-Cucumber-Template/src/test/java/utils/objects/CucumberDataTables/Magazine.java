package utils.objects.CucumberDataTables;

public class Magazine {
    private String name = null;
    private String publisher = null;
    private String quantity = null;

    public Magazine(String name, String publisher, String quantity) {
        this.name = name;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

