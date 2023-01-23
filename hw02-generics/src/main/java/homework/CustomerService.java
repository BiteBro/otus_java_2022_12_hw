package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    private final TreeMap<Customer, String> map;

    public CustomerService() {
        map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    }

    public TreeMap<Customer, String> getMap() {
        return map;
    }

    public Map.Entry<Customer, String> getSmallest() {
        return copy(map.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return copy(map.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }

    private Map.Entry copy(Map.Entry<Customer, String> entry) {
        Map.Entry<Customer, String> temp = null;
        if (entry != null){
            temp = new AbstractMap.SimpleEntry<>(new Customer(entry.getKey().getId(), entry.getKey().getName(),
                    entry.getKey().getScores()), entry.getValue());
        }
            return temp;
    }

}
