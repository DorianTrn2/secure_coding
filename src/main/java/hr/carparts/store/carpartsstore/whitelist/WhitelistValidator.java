package hr.carparts.store.carpartsstore.whitelist;

import hr.carparts.store.carpartsstore.exception.IllegalClassException;
import hr.carparts.store.carpartsstore.model.CarPart;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WhitelistValidator {

    private static Set<Class> deserializationClassWhitelist;

    static {
        deserializationClassWhitelist = new HashSet<Class>();
        deserializationClassWhitelist.add(CarPart.class);
        deserializationClassWhitelist.add(List.class);
        deserializationClassWhitelist.add(ArrayList.class);
    }

    public static void validateSerializedFile(String binaryFile) throws IOException, IllegalClassException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(binaryFile))) {
            Object readObject;
            while ((readObject = ois.readObject()) != null){

                if (!deserializationClassWhitelist.contains(readObject.getClass())) {
                    throw new IllegalClassException("The class " + readObject.getClass() + " is not allowed");
                }
                if (readObject instanceof List<?> ticketList) {

                    for (Object object : ticketList) {
                        if (!deserializationClassWhitelist.contains(object.getClass())) {
                            throw new IllegalClassException("The class " + object.getClass() + " is not allowed");
                        }
                    }
                    ticketList.forEach(System.out::println);
                }

            }
        }
        catch (EOFException ex) {
            System.out.println("End of dat file");
        }
        catch (Exception ex) {
            throw new IllegalClassException("There was a problem with deserialization!", ex);
        }
    }

}