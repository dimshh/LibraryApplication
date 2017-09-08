package telran.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleData implements InputOutputData {
    private final BufferedReader console =
        new BufferedReader(new InputStreamReader(System.in));
    
    @Override
    public String getString(String prompt) {
        String result = null;
        
        System.out.println(prompt);
        
        try {
            result = console.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Console not ready");
        }
        return result;
    }

    @Override
    public void putObject(Object value) {
        System.out.println(value);
    }
}
