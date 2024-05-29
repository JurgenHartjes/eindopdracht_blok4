package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/login")
public class ListResource extends HttpServlet {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getShoppingLists() {
        List<ShoppingList> shoppingLists = Shop.getShop().getAllShoppingLists();
        if (shoppingLists.isEmpty()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("error", "no lists present");
            JsonArrayBuilder jab = Json.createArrayBuilder();
            jab.add(job);
            JsonArray endArray = jab.build();
            return endArray.toString();
        }
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (ShoppingList s : shoppingLists) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", s.getName());
            job.add("owner", s.getOwner().getName());
            jab.add(job);
        }
        JsonArray endArray = jab.build();
        return endArray.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createAccount")
    public String getShoppingListByName() {
        return "";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)   //http://localhost:8080/restservices/list?listname=boodschappenlijst&shoppername=Dum-Dum
    public String makeShoppingList(@QueryParam("listname") String listname, @QueryParam("shoppername") String shoppername) {
        JsonObjectBuilder job = Json.createObjectBuilder();



        try {
            List<Shopper> allShoppers = Shopper.getAllShoppers();

            Shopper shopper = null;
            for (Shopper s : allShoppers)   {
                if (s.getName().equals(shoppername))    {
                    shopper = s;
                } else {
                    job.add("message", "Could not find shopper");
                    return job.build().toString();
                }
            }
            for (Shopper s : allShoppers)   {
                if (s.getName().equals(shoppername))    {
                    ShoppingList newList = new ShoppingList(listname,shopper);
                    s.addList(newList);
                }
            }
            job.add("message", "Shopping list created successfully");
            job.add("listname", listname);
            job.add("shopper's name", shoppername);
        } catch (Exception e) {
            job.add("error", "Could not create shopping list: " + e.getMessage());
        }

        return job.build().toString();
    }

    @POST
    @Path("/addProduct")        //http://localhost:8080/restservices/list/addProduct?listname=boodschappenlijst&productname=Cola-Zero
    @Produces(MediaType.APPLICATION_JSON)
    public String addToShoppingList(@QueryParam("listname") String listName, @QueryParam("productname") String productName)  {
        JsonObjectBuilder job = Json.createObjectBuilder();
        for (Shopper s : Shopper.getAllShoppers()) {
            for (ShoppingList sl : s.getAllLists()) {
                if (sl.getName().equals(listName)) {
                    System.out.println( "|" + Product.getAllProducts() + "|");
                    for (Product p : Product.getAllProducts()) {
                        if (p.getName().equals(productName)) {
                            sl.addItem(p, 1);
                            job.add("message", "product addes succesfully");
//                        } else {
//                            job.add("message", "Could not find product");
//                            return job.build().toString();
                        }
                    }


//                } else {
//                    job.add("message", "Could not find list");
//                    return job.build().toString();
                }
            }
        }
        return job.build().toString();
    }
}
