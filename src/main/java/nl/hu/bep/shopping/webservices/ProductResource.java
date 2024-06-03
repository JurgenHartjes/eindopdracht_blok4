//package nl.hu.bep.shopping.webservices;
//
//import nl.hu.bep.shopping.model.Gebruiker;
//
//import javax.json.Json;
//import javax.json.JsonArrayBuilder;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//
//
//@Path("/product")
//public class ProductResource {
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getProducts() {
//        JsonArrayBuilder jab = Json.createArrayBuilder();
//        //tip: oneliners seem awesome, but what part exactly gives us the error if any occurs on line 23?
//        return jab.build().toString();
//
//    }
//}
