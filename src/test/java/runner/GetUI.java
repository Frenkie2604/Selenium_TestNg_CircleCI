package runner;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@FunctionalInterface
public interface GetUI<T> extends Supplier<T> {

    abstract class Cache {
        private volatile static Map<Integer, Object> instances = new HashMap<>();

        private static Object getInstance(int instanceId, Supplier<Object> create) {
            Object instance = instances.get(instanceId);
            if (instance == null) {
                synchronized (Cache.class) {
                    //instances.computeIfAbsent(instanceId, i -> create.get());
                    instance = instances.get(instanceId);
                    //to be refactored -> shortly someday
                    if (instance == null) {
                        instance = create.get();
                        instances.put(instanceId, instance);
                    }
                }
            }
            return instance;
        }
    }

    default T cache() {
        return (T) Cache.getInstance(this.hashCode(), () -> init());
    }

//    default T get(Optional<T> data) {
//
//        Object instance;
//        Optional dataToUse = Optional.of(data);
//        if(dataToUse.isPresent()){
//            Supplier<T> create = () -> get();
//            instance = create.get();
//        }else{
//            instance = init();
//        }
//        return (T)instance;
//    }

    @Override
    default T get() {
        return init();
    }

    T init();
}