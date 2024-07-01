//package nl.hu.bep.shopping.model;
//
//import com.azure.communication.email.EmailAsyncClient;
//import com.azure.communication.email.EmailClientBuilder;
//import com.azure.communication.email.implementation.models.EmailMessage;
//
//public class EmailClient
//{
//    public static void main( String[] args )
//    {
//
//// You can get your connection string from your resource in the Azure portal.
//        String connectionString = "endpoint=https://<resource-name>.communication.azure.com/;accesskey=<access-key>";
//
//        EmailAsyncClient emailClient = new EmailClientBuilder()
//                .connectionString(connectionString)
//                .buildAsyncClient();
//
//        EmailMessage message = new EmailMessage()
//                .setSenderAddress("<donotreply@xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx.azurecomm.net>")
//                .setToRecipients("jurgenhartjes6@gmail.com")
//                .setSubject("Welcome to Azure Communication Services Email")
//                .setBodyPlainText("This email message is sent from Azure Communication Services Email using the Java SDK.");
//    }
//}