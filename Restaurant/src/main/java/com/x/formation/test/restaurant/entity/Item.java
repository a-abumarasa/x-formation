
package com.x.formation.test.restaurant.entity;   
import java.util.HashSet;


/**
 * Represent an Item of the menu, which can be requested by the client
 * <p>
 * It has three main attributes: Name, Category and the price
 * <p>
 * All items have id, which is attached automatically, here it has no needs, but it does in case of using database
 * <p>
 * some items have extra option e.g. sugar or lemon
 * @author aabum
 */
public class Item {
    
    private static int counter;
    private final int id;
    private String name;
    private float price; 
    private Category category;
    private String[] extraDemandsList;
    private HashSet<String> extraDemands;

    public Item() {
        id = counter++;
    }
    public Item(String name, float price) {
        this();
        this.name = name;
        this.price = price;
    }  
    public int getId() {
        return id;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }    
    public HashSet<String> getExtraDemands() {
        return extraDemands;
    }
    
    public boolean hasExtraDemands() {
        return extraDemandsList!= null;
    }    
    
    public String[] getExtraDemandsList() {
        return extraDemandsList;
    }

    public void setExtraDemandsList(String[] ExtraDemandsList) {
        extraDemands = new HashSet<>();
        this.extraDemandsList = ExtraDemandsList;
    }

    @Override
    public String toString() {
        StringBuilder extraListStr = new StringBuilder();
        if(extraDemands!=null){
            extraListStr.append("(");
            for (String str : extraDemands) {
                extraListStr.append(str);
                extraListStr.append(", ");
            }
            extraListStr.deleteCharAt(extraListStr.length()-2);
            extraListStr.append(")");
            return String.format("%s - %s\t\t%s", name,extraListStr, price);
        }else{
            return String.format("%s\t\t%s", name, price);            
        }
    }
    
}
