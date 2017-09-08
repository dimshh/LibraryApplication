package telran.input;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutputData {
    String getString(String prompt);
    void putObject(Object value);
    
    default <T> T getValue(
        String prompt, Function<String, T> mapper) {
        
        String promptText = prompt;
        T result = null;
        
        while (result == null) {
            try {
                result = mapper.apply(getString(promptText));
            } catch (RuntimeException e) {
                promptText = "Wrong input, try again";
            }
        }
        return result;
    }
    
    default <T> T getValue(String prompt,
        Function<String, T> mapper, Predicate<? super T> condition) {
        
        T result = getValue(prompt, mapper);
        
        while (!condition.test(result)) {
            result = getValue("Wrong input, try again", mapper);
        }
        return result;
    }
    
    default float getFloat(String prompt) {
        float result = getValue(prompt, Float::valueOf);
        
        return result;
    }
    
    default int getInteger(String prompt) {
        int result = getValue(prompt, Integer::valueOf);
        
        return result;
    }
    
    default int getInteger(String prompt, int min, int max) {
        int result = getValue(prompt, Integer::valueOf, n ->
            (n >= min) && (n <= max));
        
        return result;
    }
    
    default String getString(String prompt, Collection<String> options) {
        String result = getValue(prompt, s -> s, options::contains);
        
        return result;
    }
    
    default String getString(String prompt, int minLength, int maxLength) {
        String result = getValue(prompt, s -> s, s ->
            (s.length() >= minLength) && (s.length() <= maxLength));
        
        return result;
    }
}
