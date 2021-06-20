package rabbits;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cage {
    private String name;
    private int capacity;
    List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int count() {
        return data.size();
    }
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public  void add(Rabbit rabbit) {
        if (this.data.size() < capacity) {
            data.add(rabbit);
        }
    }

    public boolean removeRabbit(String name) {
        int index = findRabbitIndex(name);
        boolean isValid = false;
        if (index != -1) {
            data.remove(index);
            return true;
        }
        return isValid;
    }

    public List<Rabbit> removeSpecies(String species) {
        List<Rabbit> rabbitsToRemove = data.stream()
                .filter(rabbit -> rabbit.getSpecies()
                        .equals(species)).collect(Collectors.toList());
        data.removeAll(rabbitsToRemove);
        return data;
    }

    public Rabbit sellRabbit(String name) {
        int index = findRabbitIndex(name);
        data.get(index).setAvailable(false);

        return data.get(index);
    }
    public List<Rabbit> sellRabbitBySpecies(String species){
        List<Rabbit> rabbitsSold = data.stream()
                .filter(rabbit -> rabbit.getSpecies()
                        .equals(species)).collect(Collectors.toList());
        rabbitsSold.stream().forEach(rabbit -> rabbit.setAvailable(false));

        return rabbitsSold;
    }

    public int findRabbitIndex(String name) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

   public String report(){
       StringBuilder builder = new StringBuilder();
       builder.append("Rabbits available at ").append(getName()).append(":").append(System.lineSeparator());
       for (Rabbit rabbit : data) {
           if(rabbit.isAvailable()){
               builder.append(rabbit).append(System.lineSeparator());
           }
       }
       return builder.toString();
   }
}
