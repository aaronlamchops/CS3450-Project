package project;

public class Item {
	
	private int sku;
	private String name;
	private double quantity;
	private double price;
	private String distributor;
	private String weight;
	
	public Item(){
		sku = 0;
		name = null;
		quantity = 0.0;
		price = 0.0;
		distributor = null;
		weight = null;
	}
	
	public Item(int s, String n, double q, double p, String d, String w){
		sku = s;
		name = n;
		quantity = q;
		price = p;
		distributor = d;
		weight = w;
	}
	
	public Item(Item newItem){
		sku = newItem.getSku();
		name = newItem.getName();
		quantity = newItem.getQuantity();
		price = newItem.getPrice();
		distributor = newItem.getDist();
		weight = newItem.getWeight();
	}
	
	public void setItems(int s, String n, double q, double p, String d, String w){
		sku = s;
		name = n;
		quantity = q;
		price = p;
		distributor = d;
		weight = w;
	}
	
	public int getSku(){
		return sku;
	}
	
	public String getName(){
		return name;
	}
	
	public double getQuantity(){
		return quantity;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String getDist(){
		return distributor;
	}
	
	public String getWeight(){
		return weight;
	}
	
	public void setSku(int s){
		sku = s;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setQuantity(double q){
		quantity = q;
	}
	
	public void setPrice(double p){
		price = p;
	}
	
	public void setDist(String d){
		distributor = d;
	}
	
	public void setWeight(String w){
		weight = w;
	}
	
	public String skuToString(){
		return "SKU: " + this.getSku();
	}
	
	public String toString(){
		return "SKU = " + this.getSku() + "\r" +
				"NAME = " + this.getName() + "\r" +
				"QUANTITY = " + this.getQuantity() + "\r" +
				"PRICE = " + this.getPrice() + "\r" +
				"DISTRIBUTOR = " + this.getDist() + "\r" +
				"Weight = " + this.getWeight();
	}
}
